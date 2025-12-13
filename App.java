import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║                                                ║");
        System.out.println("║            🏋️  G Y M P L A N  💪              ║");
        System.out.println("║                                                ║");
        System.out.println("║        Your Personal Fitness Companion         ║");
        System.out.println("║                                                ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("   \"Transform Your Body, Transform Your Life\"");
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();
        System.out.println("📊 Aplikasi ini akan membantu kamu:");
        System.out.println("   ✓ Menghitung BMI, BMR, dan TDEE");
        System.out.println("   ✓ Menentukan goal fitness kamu");
        System.out.println("   ✓ Merencanakan nutrisi harian");
        System.out.println("   ✓ Membuat jadwal latihan mingguan");
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🤗Mari kita mulai dengan mengisi biodata kamu!🤗");
        System.out.print("Nama kamu              : ");
        String name = sc.nextLine();

        System.out.print("Gender (M/F)           : ");
        String gender = sc.nextLine().trim();

        System.out.print("Berat badan (kg)       : ");
        double weight = Double.parseDouble(sc.nextLine());

        System.out.print("Tinggi badan (cm)      : ");
        double height = Double.parseDouble(sc.nextLine());

        System.out.print("Umur (tahun)           : ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("Perkiraan body fat (%) : ");
        double bodyFat = Double.parseDouble(sc.nextLine());

        System.out.print("Frekuensi latihan per minggu (1-6) : ");
        int freq = Integer.parseInt(sc.nextLine());
        if (freq < 1) freq = 1;
        if (freq > 6) freq = 6;


        Profile profile = new Profile(name, gender, weight, height, age, bodyFat);
        Map<String, Double> result = profile.calculate();
        ScheduleGenerator generator = new ScheduleGenerator(profile.getGoal());

        while (true) {
            System.out.println("\n========================================");
            System.out.println("           MENU UTAMA");
            System.out.println("========================================");
            System.out.println("1. Lihat Profil");
            System.out.println("2. Lihat Nutrisi Harian");
            System.out.println("3. Lihat Rekomendasi Jadwal Latihan Mingguan");
            System.out.println("4. Workout Finder");
            System.out.println("5. Keluar");
            System.out.println("========================================");
            System.out.print("Pilih menu (1-5): ");

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    showProfile(name, gender, weight, height, age, bodyFat, result);
                    break;
                case 2:
                    showNutrition(result, profile.getGoal());
                    break;
                case 3:
                    showSchedule(generator, freq, profile.getGoal());
                    break;
                case 4:
                    showWorkoutFinder(generator, sc);
                    break;
                case 5:
                    System.out.println("\nTerima kasih telah menggunakan GYMPLAN!");
                    System.out.println("Semangat latihannya! 💪");
                    return;
                default:
                    System.out.println("\n❌ Pilihan tidak valid. Silakan pilih 1-5.");
            }
            System.out.print("\nTekan ENTER untuk kembali ke menu...");
            sc.nextLine();

        }


    }

    private static void showProfile(String name, String gender, double weight,
                                    double height, int age, double bodyFat,
                                    Map<String, Double> result) {
        System.out.println("\n========================================");
        System.out.println("           PROFIL USER");
        System.out.println("========================================");
        System.out.println("Nama          : " + name);
        System.out.println("Gender        : " + (gender.equalsIgnoreCase("M") ? "Pria" : "Wanita"));
        System.out.println("Berat Badan   : " + weight + " kg");
        System.out.println("Tinggi Badan  : " + height + " cm");
        System.out.println("Umur          : " + age + " tahun");
        System.out.println("Body Fat      : " + bodyFat + " %");
        System.out.println("----------------------------------------");
        System.out.printf("BMI           : %.2f ", result.get("BMI"));

        double bmi = result.get("BMI");
        if (bmi < 18.5) {
            System.out.println("(Underweight)");
        } else if (bmi < 25) {
            System.out.println("(Normal)");
        } else if (bmi < 30) {
            System.out.println("(Overweight)");
        } else {
            System.out.println("(Obese)");
        }

        System.out.printf("BMR           : %.0f kcal/hari\n", result.get("BMR"));
        System.out.printf("TDEE          : %.0f kcal/hari\n", result.get("TDEE"));
        System.out.println("========================================");


    }

    private static void showNutrition(Map<String, Double> result, String goal) {
        System.out.println("\n========================================");
        System.out.println("        NUTRISI HARIAN");
        System.out.println("========================================");
        System.out.println("Goal          : " + goal);
        System.out.println("----------------------------------------");
        System.out.printf("Target Kalori : %.0f kcal/hari\n", result.get("TargetCalories"));
        System.out.println();
        System.out.printf("Protein       : %.0f gram/hari\n", result.get("Protein"));
        System.out.printf("Karbohidrat   : %.0f gram/hari\n", result.get("Carbs"));
        System.out.printf("Lemak         : %.0f gram/hari\n", result.get("Fat"));
        System.out.println("========================================");

        if (goal.equals("Cutting")) {
            System.out.println("\n💡 Tips Cutting:");
            System.out.println("   - Prioritaskan protein tinggi");
            System.out.println("   - Kurangi karbo di malam hari");
            System.out.println("   - Perbanyak sayuran untuk kenyang");
        } else if (goal.equals("Bulking")) {
            System.out.println("\n💡 Tips Bulking:");
            System.out.println("   - Makan 4-5 kali sehari");
            System.out.println("   - Tambah karbo di sekitar workout");
            System.out.println("   - Jangan skip makan");
        } else {
            System.out.println("\n💡 Tips Recomposition:");
            System.out.println("   - Konsisten dengan target kalori");
            System.out.println("   - Fokus pada protein & latihan");
            System.out.println("   - Sabar dengan prosesnya");
        }
    }

    private static void showSchedule(ScheduleGenerator generator, int freq, String goal) {
        System.out.println("\n========================================");
        System.out.println("    JADWAL LATIHAN MINGGUAN");
        System.out.println("========================================");
        System.out.println("Goal          : " + goal);
        System.out.println("Frekuensi     : " + freq + " hari/minggu");
        System.out.println("========================================");

        generator.generateSchedule(freq);

        System.out.println("\n========================================");
        System.out.println("[-] Keterangan Priority:");
        System.out.println("   Main     - Latihan utama (wajib)");
        System.out.println("   Support  - Latihan pendukung");
        System.out.println("   Optional - Latihan opsional");
        System.out.println("========================================");
    }
    private static void showWorkoutFinder(ScheduleGenerator generator, Scanner sc) {
        System.out.println("\n========================================");
        System.out.println("        WORKOUT FINDER ");
        System.out.println("========================================");
        System.out.println("Group tersedia: Push, Pull, Legs, Full Body, Cardio, Core, All");
        System.out.print("Pilih group: ");
        String group = sc.nextLine().trim();
        if (group.isEmpty()) group = "All";

        System.out.print("Min difficulty (1-10): ");
        int minDiff = Integer.parseInt(sc.nextLine());

        System.out.print("Max difficulty (1-10): ");
        int maxDiff = Integer.parseInt(sc.nextLine());

        if (minDiff > maxDiff) {
            int tmp = minDiff;
            minDiff = maxDiff;
            maxDiff = tmp;
        }

        System.out.print("Limit hasil (contoh: 5): ");
        int limit = Integer.parseInt(sc.nextLine());

        generator.workoutFinder(group, minDiff, maxDiff, limit);
    }
}