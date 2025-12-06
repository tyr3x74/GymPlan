import java.util.HashMap;
import java.util.Map;

public class Profile {
    String name;
    double weight;
    double height;
    double fat;
    int age;
    int freq;
    String gender;

    Profile(String name, double weight, double height, double fat, int age, int freq, String gender) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.fat = fat;
        this.age = age;
        this.freq = freq;
        this.gender = gender;
    }

    public double bmi(){
        return weight / Math.pow(height / 100.0, 2);
    }

    public String bmiStatus() {
        double b = bmi();
        if (b < 18.5){
            return "Underweight";
        }
        else if (b < 25){
            return "Normal";
        }
        else if (b < 30){
            return "Overweight";
        }
        else{
            return "Obese";
        }
    }
    public String fatStatus() {
        if (fat < 10){
            return "Very Lean";
        }
        else if (fat < 18){
            return "Fit";
        }
        else if (fat < 25){
            return "Average Healthy";
        }
        else {
            return "High Body Fat";
        }
    }

    public String Goal() {
        double b = bmi();

        if (b < 18.5){
            return "Bulking";
        }
        else if (fat > 25){
            return "Cutting";
        }
        else if (fat < 18 && b >= 23){
            return "Cutting";
        }
        else if (fat < 18 && b < 25){
            return "Recomposition";
        }
        else if (b >= 25 && fat >= 20){
            return "Cutting";
        }
        else {
            return "Recomposition";
        }
    }

    public double BMR() {
        if (gender.toUpperCase().startsWith("M")) {
            return (10 * weight) + (6.25 * height) - (5 * age) + 5;
        } else {
            return (10 * weight) + (6.25 * height) - (5 * age) - 161;
        }
    }
    public double TotalEnergy() {
        double bmr = BMR();
        double activity;

        if (freq <= 2) {
            activity = 1.375;
        } else if (freq <= 4) {
            activity = 1.55;
        } else {
            activity = 1.725;
        }

        return bmr * activity;
    }
    public double TargetCalories() {
        double tdee = TotalEnergy();
        String goal = Goal();

        if (goal.equals("Cutting")) {
            return tdee - 400;
        } else if (goal.equals("Bulking")) {
            return tdee + 400;
        } else {
            return tdee;
        }
    }
    public double protein(){
        double b = bmi();
        String goal = Goal();
        double baseProtein = weight * 1.8;

        if(goal == "Bulking"){
            baseProtein = weight * 2.2;
        } else if (goal == "Cutting") {
            baseProtein = weight * 2.0;
        } else if (goal == "Recomposition") {
            baseProtein = weight * 2.0;
        }

        if (b < 18.5) {
            baseProtein *= 1.1;
        } else if (b >= 30) {
            baseProtein *= 0.9;
        }

        return baseProtein;
    }
    public Map<String, Double> calculate(){
        double calories = TargetCalories();
        double protein = protein();
        double fatPercentage;
        String goal = Goal();

        Map<String, Double> makro = new HashMap<>();
        if(goal == "Cutting"){
            fatPercentage = 0.25;
        } else if (goal == "Bulking") {
            fatPercentage = 0.30;
        } else {
            fatPercentage = 0.27;
        }

        double fat = (calories * fatPercentage) / 9;
        double karbo = (calories - (protein * 4) - (calories * fatPercentage)) / 4;
        makro.put("calories", calories);
        makro.put("protein", protein);
        makro.put("fat", fat);
        makro.put("carbs", karbo);
        makro.put("bmr", BMR());
        makro.put("tdee", TotalEnergy());

        return makro;
    }

}
