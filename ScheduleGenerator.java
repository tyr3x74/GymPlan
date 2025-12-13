import java.util.*;

public class ScheduleGenerator {

    static class Workout {
        String name;
        String muscleGroup;
        int difficulty;
        int cuttingPriority;
        int bulkingPriority;
        int recompPriority;

        public Workout(String name, String muscleGroup, int difficulty,
                       int cuttingPriority, int bulkingPriority, int recompPriority) {
            this.name = name;
            this.muscleGroup = muscleGroup;
            this.difficulty = difficulty;
            this.cuttingPriority = cuttingPriority;
            this.bulkingPriority = bulkingPriority;
            this.recompPriority = recompPriority;
        }

        public int getPriorityForGoal(String goal) {
            return switch (goal) {
                case "Cutting" -> cuttingPriority;
                case "Bulking" -> bulkingPriority;
                case "Recomposition" -> recompPriority;
                default -> 1;
            };
        }

        public String priorityString(int priority) {
            return switch (priority) {
                case 3 -> "Main";
                case 2 -> "Support";
                default -> "Optional";
            };
        }

        public String toString(String goal) {
            int priority = getPriorityForGoal(goal);
            return String.format("%s | Group: %s | Diff: %d | Priority: %s",
                    name, muscleGroup, difficulty, priorityString(priority));
        }

    }

    static class BSTNode {
        Workout data;
        BSTNode left, right;

        BSTNode(Workout data) {
            this.data = data;
        }
    }

    static class WorkoutBST {
        BSTNode root;

        public void insert(Workout w) {
            root = insertRec(root, w);
        }

        private BSTNode insertRec(BSTNode node, Workout w) {
            if (node == null) return new BSTNode(w);
            if (w.difficulty < node.data.difficulty) {
                node.left = insertRec(node.left, w);
            } else {
                node.right = insertRec(node.right, w);
            }
            return node;
        }

        public void printInOrder(String goal) {
            inorder(root, goal);
        }

        private void inorder(BSTNode node, String goal) {
            if (node == null) return;
            inorder(node.left, goal);
            System.out.println(node.data.toString(goal));
            inorder(node.right, goal);
        }
        public List<Workout> findInRange(int minDiff, int maxDiff) {
            List<Workout> out = new ArrayList<>();
            findInRangeRec(root, minDiff, maxDiff, out);
            return out;
        }

        private void findInRangeRec(BSTNode node, int minDiff, int maxDiff, List<Workout> out) {
            if (node == null) return;

            int d = node.data.difficulty;

            if (d < minDiff) {
                findInRangeRec(node.right, minDiff, maxDiff, out);
                return;
            }

            if (d > maxDiff) {
                findInRangeRec(node.left, minDiff, maxDiff, out);
                return;
            }

            findInRangeRec(node.left, minDiff, maxDiff, out);
            out.add(node.data);
            findInRangeRec(node.right, minDiff, maxDiff, out);
        }
    }

    static class MuscleGraph {
        String[] groups = {"Push", "Pull", "Legs", "Full Body", "Cardio", "Core"};
        Map<String, Integer> indexMap = new HashMap<>();
        List<Integer>[] adj;

        @SuppressWarnings("unchecked")
        MuscleGraph() {
            int n = groups.length;
            adj = (ArrayList<Integer>[]) new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
                indexMap.put(groups[i], i);
            }

            addEdge("Push", "Pull");
            addEdge("Push", "Legs");
            addEdge("Pull", "Legs");
            addEdge("Full Body", "Cardio");
            addEdge("Full Body", "Core");
            addEdge("Legs", "Cardio");
            addEdge("Core", "Cardio");
        }

        private void addEdge(String a, String b) {
            int u = indexMap.get(a);
            int v = indexMap.get(b);
            adj[u].add(v);
            adj[v].add(u);
        }

        public void printGraph() {
            for (int i = 0; i < groups.length; i++) {
                System.out.print(groups[i] + " -> ");
                for (int v : adj[i]) {
                    System.out.print(groups[v] + " ");
                }
                System.out.println();
            }
        }

        public List<String> buildSplit(String startGroup, int days) {
            List<String> result = new ArrayList<>();
            boolean[] visited = new boolean[groups.length];
            Queue<Integer> q = new ArrayDeque<>();

            int start = indexMap.get(startGroup);
            q.add(start);
            visited[start] = true;

            while (!q.isEmpty() && result.size() < days) {
                int u = q.poll();
                result.add(groups[u]);

                for (int v : adj[u]) {
                    if (!visited[v]) {
                        visited[v] = true;
                        q.add(v);
                    }
                }
            }

            int idx = 0;
            while (result.size() < days && !result.isEmpty()) {
                result.add(result.get(idx % result.size()));
                idx++;
            }

            return result;
        }
    }

    private String goal;
    private List<Workout> allWorkouts = new ArrayList<>();
    private WorkoutBST bst = new WorkoutBST();
    private MuscleGraph graph = new MuscleGraph();

    public ScheduleGenerator(String goal) {
        this.goal = goal;
        seedWorkouts();
    }

    private void seedWorkouts() {
        // Format: (name, group, difficulty, cuttingPriority, bulkingPriority, recompPriority)

        // === PUSH ===
        addWorkout("Bench Press", "Push", 7, 2, 3, 3);
        addWorkout("Incline Dumbbell Press", "Push", 6, 2, 3, 3);
        addWorkout("Overhead Press", "Push", 8, 2, 3, 3);
        addWorkout("Dumbbell Shoulder Press", "Push", 6, 2, 3, 2);
        addWorkout("Triceps Pushdown", "Push", 5, 2, 2, 2);
        addWorkout("Dips", "Push", 7, 2, 3, 3);
        addWorkout("Lateral Raise", "Push", 4, 2, 2, 2);
        addWorkout("Cable Fly", "Push", 5, 1, 2, 2);

        // === PULL ===
        addWorkout("Pull Up", "Pull", 8, 2, 3, 3);
        addWorkout("Barbell Row", "Pull", 7, 2, 3, 3);
        addWorkout("Lat Pulldown", "Pull", 6, 2, 3, 3);
        addWorkout("Deadlift", "Pull", 9, 2, 3, 3);
        addWorkout("Face Pull", "Pull", 4, 2, 2, 2);
        addWorkout("Bicep Curl", "Pull", 4, 1, 2, 2);
        addWorkout("Hammer Curl", "Pull", 4, 1, 2, 2);
        addWorkout("Cable Row", "Pull", 6, 2, 3, 2);

        // === LEGS ===
        addWorkout("Squat", "Legs", 9, 2, 3, 3);
        addWorkout("Romanian Deadlift", "Legs", 8, 2, 3, 3);
        addWorkout("Bulgarian Split Squat", "Legs", 7, 2, 3, 3);
        addWorkout("Leg Press", "Legs", 6, 2, 3, 2);
        addWorkout("Leg Extension", "Legs", 4, 1, 2, 2);
        addWorkout("Leg Curl", "Legs", 4, 1, 2, 2);
        addWorkout("Calf Raise", "Legs", 3, 1, 2, 1);
        addWorkout("Lunges", "Legs", 6, 2, 3, 3);

        // === FULL BODY ===
        addWorkout("Full Body Circuit", "Full Body", 6, 3, 2, 3);
        addWorkout("Kettlebell Swing", "Full Body", 7, 3, 2, 3);
        addWorkout("Burpees", "Full Body", 8, 3, 1, 2);
        addWorkout("Thruster", "Full Body", 7, 3, 2, 3);
        addWorkout("Clean and Press", "Full Body", 8, 2, 3, 3);
        addWorkout("Turkish Get Up", "Full Body", 6, 2, 2, 2);

        // === CARDIO ===
        addWorkout("Jogging", "Cardio", 3, 3, 1, 2);
        addWorkout("HIIT Sprint", "Cardio", 7, 3, 1, 3);
        addWorkout("Jump Rope", "Cardio", 5, 3, 1, 2);
        addWorkout("Cycling", "Cardio", 4, 3, 1, 2);
        addWorkout("Rowing Machine", "Cardio", 6, 3, 2, 3);
        addWorkout("Stair Climber", "Cardio", 6, 3, 1, 2);
        addWorkout("Battle Ropes", "Cardio", 7, 3, 2, 3);

        // === CORE ===
        addWorkout("Plank", "Core", 4, 3, 2, 3);
        addWorkout("Side Plank", "Core", 5, 3, 2, 3);
        addWorkout("Crunch", "Core", 3, 2, 2, 2);
        addWorkout("Russian Twist", "Core", 4, 3, 2, 3);
        addWorkout("Leg Raise", "Core", 6, 3, 2, 3);
        addWorkout("Mountain Climber", "Core", 6, 3, 2, 3);
        addWorkout("Ab Wheel Rollout", "Core", 7, 3, 2, 3);
        addWorkout("Bicycle Crunch", "Core", 4, 2, 2, 2);
    }

    private void addWorkout(String name, String group, int diff,
                            int cuttingPriority, int bulkingPriority, int recompPriority) {
        Workout w = new Workout(name, group, diff, cuttingPriority, bulkingPriority, recompPriority);
        allWorkouts.add(w);
        bst.insert(w);
    }

    public void printWorkoutLibrary() {
        bst.printInOrder(goal);
    }

    public void printMuscleGraph() {
        graph.printGraph();
    }

    public void generateSchedule(int freq) {
        String startGroup;
        if (goal.equalsIgnoreCase("Cutting")) {
            startGroup = "Full Body";
        } else if (goal.equalsIgnoreCase("Bulking")) {
            startGroup = "Push";
        } else {
            startGroup = "Legs";
        }

        List<String> split = graph.buildSplit(startGroup, freq);

        for (int day = 0; day < split.size(); day++) {
            String group = split.get(day);
            System.out.println("\nHari " + (day + 1) + " - " + group);

            List<Workout> dayWorkouts = new ArrayList<>();
            for (Workout w : allWorkouts) {
                if (w.muscleGroup.equalsIgnoreCase(group)) {
                    dayWorkouts.add(w);
                }
            }

            bubbleSortByPriority(dayWorkouts);

            if (dayWorkouts.isEmpty()) {
                System.out.println("Belum ada latihan untuk group ini.");
            } else {
                for (Workout w : dayWorkouts) {
                    System.out.println("- " + w.toString(goal));
                }
            }
        }
    }

    private void bubbleSortByPriority(List<Workout> list) {
        int n = list.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                int priority1 = list.get(j).getPriorityForGoal(goal);
                int priority2 = list.get(j + 1).getPriorityForGoal(goal);

                if (priority1 < priority2) {
                    Workout tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
    public void workoutFinder(String group, int minDiff, int maxDiff, int limit) {
        List<Workout> candidates = bst.findInRange(minDiff, maxDiff);

        List<Workout> filtered = new ArrayList<>();
        for (Workout w : candidates) {
            if (group == null || group.equalsIgnoreCase("All") || w.muscleGroup.equalsIgnoreCase(group)) {
                filtered.add(w);
            }
        }

        bubbleSortByPriority(filtered);

        if (filtered.isEmpty()) {
            System.out.println("Tidak ada workout yang cocok untuk filter ini.");
            return;
        }

        int take = Math.min(limit, filtered.size());
        for (int i = 0; i < take; i++) {
            System.out.println("- " + filtered.get(i).toString(goal));
        }
    }
}