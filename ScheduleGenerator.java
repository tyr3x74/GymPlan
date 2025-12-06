import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScheduleGenerator {
    static final String[] WEEK = {
            "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"
    };

    String goal;

    ScheduleGenerator(String goal) {
        this.goal = goal;
    }


    private List<String> buildFullBodySchedule(int freq) {
        List<String> schedule = new ArrayList<>(Collections.nCopies(7, "Rest"));

        if (freq == 1) {
            schedule.set(0, "Full Body Strength");
        } else {
            schedule.set(0, "Full Body Strength");
            schedule.set(3, "Full Body Hypertrophy");
        }

        return schedule;
    }

    private List<String> buildCuttingSchedule(int freq) {
        List<String> schedule = new ArrayList<>(Collections.nCopies(7, "Rest"));

        if (freq == 3) {
            schedule.set(0, "Full Body Strength + HIIT 15 min");
            schedule.set(2, "Full Body Hypertrophy + LISS 20 min");
            schedule.set(4, "Full Body Power + HIIT 15 min");
        } else if (freq == 4) {
            schedule.set(0, "Upper Body + LISS 20 min");
            schedule.set(1, "Lower Body + Core");
            schedule.set(3, "Upper Body + HIIT 15 min");
            schedule.set(4, "Lower Body + LISS 25 min");
        } else if (freq == 5) {
            schedule.set(0, "Push + HIIT 15 min");
            schedule.set(1, "Pull + Core");
            schedule.set(2, "Legs + LISS 20 min");
            schedule.set(3, "Chest + HIIT 20 min");
            schedule.set(5, "Back + LISS 20 min");
        } else if (freq == 6) {
            schedule.set(0, "Push + HIIT 15 min");
            schedule.set(1, "Pull + Core");
            schedule.set(2, "Legs + LISS 25 min");
            schedule.set(3, "Shoulders + HIIT 15 min");
            schedule.set(4, "Back + LISS 20 min");
            schedule.set(5, "Arms + HIIT 15 min");
        }

        return schedule;
    }

    private List<String> buildBulkingSchedule(int freq) {
        List<String> schedule = new ArrayList<>(Collections.nCopies(7, "Rest"));

        if (freq == 3) {
            schedule.set(0, "Full Body Strength");
            schedule.set(2, "Full Body Hypertrophy");
            schedule.set(4, "Full Body Power");
        } else if (freq == 4) {
            schedule.set(0, "Upper Body Strength");
            schedule.set(1, "Lower Body Strength");
            schedule.set(3, "Upper Body Hypertrophy");
            schedule.set(5, "Lower Body Hypertrophy");
        } else if (freq == 5) {
            schedule.set(0, "Push");
            schedule.set(1, "Pull");
            schedule.set(2, "Legs");
            schedule.set(4, "Upper Body");
            schedule.set(5, "Lower Body");
        } else if (freq == 6) {
            schedule.set(0, "Push");
            schedule.set(1, "Pull");
            schedule.set(2, "Legs");
            schedule.set(3, "Chest");
            schedule.set(4, "Back");
            schedule.set(5, "Shoulders");
        }

        return schedule;
    }

    private List<String> buildRecompSchedule(int freq) {
        List<String> schedule = new ArrayList<>(Collections.nCopies(7, "Rest"));

        if (freq == 3) {
            schedule.set(0, "Full Body Strength");
            schedule.set(2, "Full Body Hypertrophy + Core");
            schedule.set(5, "Full Body + HIIT 10 min");
        } else if (freq == 4) {
            schedule.set(0, "Upper Body Strength");
            schedule.set(1, "Lower Body + Core");
            schedule.set(3, "Upper Body Hypertrophy");
            schedule.set(5, "Lower Body + HIIT 15 min");
        } else if (freq == 5) {
            schedule.set(0, "Push");
            schedule.set(1, "Pull");
            schedule.set(2, "Legs");
            schedule.set(4, "Upper Body");
            schedule.set(5, "Lower Body + Core");
        } else if (freq == 6) {
            schedule.set(0, "Push");
            schedule.set(1, "Pull");
            schedule.set(2, "Legs + HIIT 10 min");
            schedule.set(3, "Chest");
            schedule.set(4, "Back");
            schedule.set(5, "Arms + Core");
        }

        return schedule;
    }
    public List<String> buildSchedule(int freq) {
        List<String> schedule = new ArrayList<>();

        if (freq <= 2) {
            schedule = buildFullBodySchedule(freq);
        } else if (goal == "Cutting") {
            schedule = buildCuttingSchedule(freq);
        } else if (goal == "Bulking") {
            schedule = buildBulkingSchedule(freq);
        } else {
            schedule = buildRecompSchedule(freq);
        }

        return schedule;
    }
}
