import java.util.HashMap;
import java.util.Map;

public class Profile {

    private String name;
    private String gender;
    private double weight;
    private double height;
    private int age;
    private double bodyFat;

    private String goal;

    public Profile(String name, String gender, double weight, double height, int age, double bodyFat) {
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.bodyFat = bodyFat;
    }

    public Map<String, Double> calculate() {
        Map<String, Double> map = new HashMap<>();

        double heightMeter = height / 100.0;
        double bmi = weight / (heightMeter * heightMeter);

        double bmr;
        if (gender.equalsIgnoreCase("M")) {
            bmr = 10 * weight + 6.25 * height - 5 * age + 5;
        } else {

            bmr = 10 * weight + 6.25 * height - 5 * age - 161;
        }
        double tdee = bmr * 1.5;
        this.goal = decideGoal(bmi, bodyFat);


        double targetCalories;
        if (goal.equals("Cutting")) {
            targetCalories = tdee - 300;
        } else if (goal.equals("Bulking")) {
            targetCalories = tdee + 250;
        } else {
            targetCalories = tdee;
        }


        double protein = weight * 2.0;
        double fat = weight * 0.8;
        double remainingCalories = targetCalories - (protein * 4 + fat * 9);
        double carbs = remainingCalories / 4;

        map.put("BMI", bmi);
        map.put("BMR", bmr);
        map.put("TDEE", tdee);
        map.put("TargetCalories", targetCalories);
        map.put("Protein", protein);
        map.put("Carbs", carbs);
        map.put("Fat", fat);

        return map;
    }

    private String decideGoal(double bmi, double bf) {
        if (bf > 25 || bmi >= 27) {
            return "Cutting";
        } else if (bf < 15 && bmi <= 23) {
            return "Bulking";
        } else {
            return "Recomposition";
        }
    }

    public String getGoal() {
        return goal;
    }
}
