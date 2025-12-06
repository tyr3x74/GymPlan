import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("  /$$$$$$  /$$     /$$ /$$      /$$       /$$$$$$$  /$$                    \n" +
                " /$$__  $$|  $$   /$$/| $$$    /$$$      | $$__  $$| $$                    \n" +
                "| $$  \\__/ \\  $$ /$$/ | $$$$  /$$$$      | $$  \\ $$| $$  /$$$$$$  /$$$$$$$ \n" +
                "| $$ /$$$$  \\  $$$$/  | $$ $$/$$ $$      | $$$$$$$/| $$ |____  $$| $$__  $$\n" +
                "| $$|_  $$   \\  $$/   | $$  $$$| $$      | $$____/ | $$  /$$$$$$$| $$  \\ $$\n" +
                "| $$  \\ $$    | $$    | $$\\  $ | $$      | $$      | $$ /$$__  $$| $$  | $$\n" +
                "|  $$$$$$/    | $$    | $$ \\/  | $$      | $$      | $$|  $$$$$$$| $$  | $$\n" +
                " \\______/     |__/    |__/     |__/      |__/      |__/ \\_______/|__/  |__/\n"
        );
        System.out.print("Nama : ");
        String nama = sc.nextLine();

        System.out.print("Gender (M/F): ");
        String gender = sc.nextLine();

        System.out.print("Berat (kg): ");
        double weight = sc.nextDouble();

        System.out.print("Tinggi (cm): ");
        double height = sc.nextDouble();


        System.out.print("Lemak tubuh (%): ");
        int bodyfat = sc.nextInt();

        System.out.print("Umur: ");
        int age = sc.nextInt();

        System.out.print("Frekuensi latihan per minggu (1-6): ");
        int freq = sc.nextInt();

        if(freq < 1){
            System.out.println("[!] Frekuensi latihan tidak bisa kurang dari 1");
        }
        if(freq > 6){
            System.out.println("[!] Frekuensi latihan tidak bisa lebih dari 6");
        }

        Profile user = new Profile(nama, weight, height, bodyfat, age, freq, gender);
        Map<String, Double> makro = user.calculate();
        String goal = user.Goal();

        System.out.println("==============================      HASIL ANALISIS     ================================");
        System.out.printf("Nama                 : %s", nama);
        System.out.printf("\nBMI                  : %.0f (%s)", user.bmi(), user.bmiStatus());
        System.out.printf("\nBodyfat              : %s (%d%%)", user.fatStatus(), bodyfat);
        System.out.printf("\nRekomendasi Goal     : %s", goal);
        System.out.println("\n==========================      KEBUTUHAN NUTRISI HARIAN     ===========================");
        System.out.printf("Target Kalori        : %.0f kkal/hari", makro.get("calories"));
        System.out.printf("\nProtein              : %.0f gram/hari", makro.get("protein"));
        System.out.printf("\nKarbo                : %.0f gram/hari", makro.get("carbs"));
        System.out.printf("\nLemak                : %.0f gram/hari", makro.get("fat"));
        System.out.println("\n==========================          JADWAL LATIHAN          ===========================");
        ScheduleGenerator generator = new ScheduleGenerator(goal);
        List<String> schedule = generator.buildSchedule(freq);
        for (int i = 0; i < 7; i++) {
            String day = ScheduleGenerator.WEEK[i];
            String workout = schedule.get(i);

            if (workout.equals("Rest")) {
                System.out.printf("%s : %s (Recovery Day)\n", day, workout);
            } else {
                System.out.printf("%s : %s\n", day, workout);
            }
        }
        System.out.println("==========================      REKOMENDASI LATIHAN          ===========================");
        System.out.println("COMMING SOOOOOONNNNNNN!!!!!!!!!");
    }

}
