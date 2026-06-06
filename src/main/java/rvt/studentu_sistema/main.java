package rvt.studentu_sistema;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== STUDENTU REĢISTRĀCIJAS SISTĒMA ===");
            System.out.println("Izvēlieties darbību: register | show | remove | edit | exit");
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "register":
                    registerStudent();
                    break;
                case "show":
                    showStudents();
                    break;
                case "remove":
                    removeStudent();
                    break;
                case "edit":
                    editStudent();
                    break;
                case "exit":
                    System.out.println("Programma apturēta. Atā!");
                    return;
                default:
                    System.out.println("Nezināma komanda! Mēģini vēlreiz.");
            }
        }
    }

    private static void registerStudent() {
        List<Student> currentStudents = FileHandler.loadStudents();

        System.out.print("Ievadiet vārdu (min 3 burti): ");
        String vards = scanner.nextLine().trim();
        if (!Validator.validateVardsUzvards(vards)) {
            System.out.println("Kļūda: Nepareizs vārda formāts!");
            return;
        }

        System.out.print("Ievadiet uzvārdu (min 3 burti): ");
        String uzvards = scanner.nextLine().trim();
        if (!Validator.validateVardsUzvards(uzvards)) {
            System.out.println("Kļūda: Nepareizs uzvārda formāts!");
            return;
        }

        System.out.print("Ievadiet e-pastu: ");
        String epasts = scanner.nextLine().trim();
        if (!Validator.validateEpasts(epasts)) {
            System.out.println("Kļūda: Nepareizs e-pasta formāts!");
            return;
        }

        System.out.print("Ievadiet personas kodu (piem. 123456-12345): ");
        String pk = scanner.nextLine().trim();
        if (!Validator.validatePersonasKods(pk)) {
            System.out.println("Kļūda: Nepareizs personas koda formāts!");
            return;
        }

        try {
            // Pārbaudām kļūdu izņēmumus (vai nav aizņemts)
            Validator.checkUniqueness(pk, epasts, currentStudents);
            
            Student newStudent = new Student(vards, uzvards, epasts, pk);
            FileHandler.saveStudent(newStudent);
            System.out.println("Students veiksmīgi reģistrēts!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showStudents() {
        List<Student> students = FileHandler.loadStudents();
        if (students.isEmpty()) {
            System.out.println("Sistēmā nav reģistrētu studentu.");
            return;
        }

        // Tāfeles bildes piemērs: Formatētas tabulas izvade ar precīzu platumu
        printLine("-", 95);
        System.out.printf("| %-12s | %-15s | %-25s | %-13s | %-15s |\n", "Vārds", "Uzvārds", "E-pasts", "Pers. kods", "Reg. Datums");
        printLine("-", 95);
        
        for (Student s : students) {
            System.out.printf("| %-12s | %-15s | %-25s | %-13s | %-15s |\n", 
                s.getVards(), s.getUzvards(), s.getEpasts(), s.getPersonasKods(), s.getVards() /*Šeit var ielikt datumu saīsināti*/);
        }
        printLine("-", 95);
    }

    private static void removeStudent() {
        System.out.print("Ievadiet dzēšamā studenta personas kodu: ");
        String pk = scanner.nextLine().trim();
        List<Student> students = FileHandler.loadStudents();
        
        boolean removed = students.removeIf(s -> s.getPersonasKods().equals(pk));
        
        if (removed) {
            FileHandler.rewriteFile(students);
            System.out.println("Students veiksmīgi dzēsts!");
        } else {
            System.out.println("Students ar šādu personas kodu netika atrasts.");
        }
    }

    private static void editStudent() {
        System.out.print("Ievadiet rediģējamā studenta personas kodu: ");
        String pk = scanner.nextLine().trim();
        List<Student> students = FileHandler.loadStudents();
        
        for (Student s : students) {
            if (s.getPersonasKods().equals(pk)) {
                System.out.print("Jaunais vārds (atstāj tukšu, lai nemainītu): ");
                String vards = scanner.nextLine().trim();
                if (!vards.isEmpty() && Validator.validateVardsUzvards(vards)) s.setVards(vards);

                System.out.print("Jaunais uzvārds (atstāj tukšu, lai nemainītu): ");
                String uzvards = scanner.nextLine().trim();
                if (!uzvards.isEmpty() && Validator.validateVardsUzvards(uzvards)) s.setUzvards(uzvards);

                System.out.print("Jaunais e-pasts (atstāj tukšu, lai nemainītu): ");
                String epasts = scanner.nextLine().trim();
                if (!epasts.isEmpty() && Validator.validateEpasts(epasts)) s.setEpasts(epasts);

                FileHandler.rewriteFile(students);
                System.out.println("Dati veiksmīgi atjaunoti!");
                return;
            }
        }
        System.out.println("Students netika atrasts.");
    }

    // Palīgmetode līniju vilkšanai (izmantota bildē uz tāfeles)
    public static void printLine(String character, int times) {
        System.out.println(character.repeat(times));
    }
}
