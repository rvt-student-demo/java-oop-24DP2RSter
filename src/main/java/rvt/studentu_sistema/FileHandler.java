package rvt.studentu_sistema;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "studenti.csv";

    // Saglabā vienu jaunu studentu failā
    public static void saveStudent(Student student) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(student.toCsvRow());
        } catch (IOException e) {
            System.out.println("Kļūda saglabājot failā: " + e.getMessage());
        }
    }

    // Nolasa visus studentus no faila sarakstā
    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return students;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    students.add(new Student(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        } catch (IOException e) {
            System.out.println("Kļūda lasot failu: " + e.getMessage());
        }
        return students;
    }

    // Pārraksta visu failu ar jauno sarakstu (izmanto pie remove un edit)
    public static void rewriteFile(List<Student> students) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                out.println(student.toCsvRow());
            }
        } catch (IOException e) {
            System.out.println("Kļūda atjauninot failu: " + e.getMessage());
        }
    }
}