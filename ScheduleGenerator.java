import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class WorkoutNode {
    String name;
    int priority;
    String type;
    boolean cardio;

    WorkoutNode(String name, int priority, String type, boolean cardio) {
        this.name = name;
        this.priority = priority;
        this.type = type;
        this.cardio = cardio;
    }
}

class Graph {
    List<WorkoutNode> Nodes = new ArrayList<>();

    public void addNode(String name, int priority, String type, boolean cardio) {
        WorkoutNode node = new WorkoutNode(name, priority, type, cardio);
        Nodes.add(node);
    }

    public List<WorkoutNode> mergeSort(List<WorkoutNode> list) {
        if (list.size() <= 1){
            return list;
        }
        int mid = list.size() / 2;
        List<WorkoutNode> left = mergeSort(new ArrayList<>(list.subList(0, mid)));
        List<WorkoutNode> right = mergeSort(new ArrayList<>(list.subList(mid, list.size())));

        return merge(left, right);
    }

    private List<WorkoutNode> merge(List<WorkoutNode> left, List<WorkoutNode> right) {
        List<WorkoutNode> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).priority <= right.get(j).priority) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));

        return result;
    }

    public List<WorkoutNode> Priority() {
        return mergeSort(Nodes);
    }
}

class BST {
    static class Node {
        WorkoutNode workout;
        Node left, right;

        Node(WorkoutNode workout) {
            this.workout = workout;
        }
    }

    Node root;

    public void insert(WorkoutNode workout) {
        root = insertRec(root, workout);
    }

    private Node insertRec(Node root, WorkoutNode workout) {
        if (root == null) return new Node(workout);

        if (workout.priority < root.workout.priority)
            root.left = insertRec(root.left, workout);
        else
            root.right = insertRec(root.right, workout);

        return root;
    }

    public void inorder(Node root, List<WorkoutNode> output) {
        if (root != null) {
            inorder(root.left, output);
            output.add(root.workout);
            inorder(root.right, output);
        }
    }

    public List<WorkoutNode> getSortedWorkouts() {
        List<WorkoutNode> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    public WorkoutNode search(int priority) {
        return searchRec(root, priority);
    }

    private WorkoutNode searchRec(Node root, int priority) {
        if (root == null) return null;

        if (root.workout.priority == priority) return root.workout;

        if (priority < root.workout.priority)
            return searchRec(root.left, priority);

        return searchRec(root.right, priority);
    }
}

public class ScheduleGenerator {
    static final String[] WEEK = {
            "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"
    };

    String goal;
    BST bst;

    ScheduleGenerator(String goal, BST bst) {
        this.goal = goal;
        this.bst = bst;
    }

    private String buildWorkout(WorkoutNode workout, int index, int totalFreq) {
        String name = workout.name;
        String tipe = workout.type;
        
        if(tipe == "strength"){
            name += " Strength";
        } else if (tipe == "hypertrophy") {
            name += " Hypertrophy";
        }
        if (workout.cardio) {
            if (goal == "Cutting") {
                if (index % 2 == 0) {
                    name += " + HIIT 15 min";
                } else {
                    name += " + LISS 20 min";
                }
            } else if (goal == "Recomposition") {
                    name += " + HIIT 10 min";
            } else {
                name += " + LISS 12 min";
            }
        }

        return name;
    }

    public List<String> buildSchedule(int freq) {
        List<String> schedule = new ArrayList<>(Collections.nCopies(7, "Rest"));
        List<WorkoutNode> sortedWorkouts = bst.getSortedWorkouts();
        int dayIndex = 0;
        int workoutCount = 0;
        for (int i = 0; i < sortedWorkouts.size() && workoutCount < freq; i++) {
            WorkoutNode workout = sortedWorkouts.get(i);
            while (dayIndex < 7 && !schedule.get(dayIndex).equals("Rest")) {
                dayIndex++;
            }
            String workoutName = buildWorkout(workout, workoutCount, freq);
            schedule.set(dayIndex, workoutName);
            if (dayIndex >= 7) {
                break;
            }
            workoutCount++;
            dayIndex++;
            if (freq == 6 && workoutCount == 3){
                dayIndex++;

            }
            if (freq == 2 && workoutCount < 2){
                dayIndex++;
            }
            if (freq <= 5 && workoutCount % 2 == 0 && dayIndex < 6) {
                dayIndex++;
            }
        }
        return schedule;
    }
}