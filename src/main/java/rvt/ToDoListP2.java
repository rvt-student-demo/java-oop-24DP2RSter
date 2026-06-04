package rvt;

import java.util.ArrayList;
import java.io.*; 

public class ToDoListP2 {

    private ArrayList<String> tasks;
    private final String filePath = "todo.csv";

    public ToDoListP2() {
        this.tasks = new ArrayList<>();
        loadFromFile(); 
    }

    private void loadFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String rinda;
            
            br.readLine(); 
            
            while ((rinda = br.readLine()) != null) {
                if (!rinda.isEmpty()) {
                    this.tasks.add(rinda);
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }

    private int getLastId() {
        if (this.tasks.isEmpty()) {
            return 0;
        }
        String pēdējais = this.tasks.get(this.tasks.size() - 1);
        String[] cipari = pēdējais.split(",");
        return Integer.parseInt(cipari[0]);
    }

    public void add(String task) {
        if (checkEventString(task)) {
            int jaunaisId = getLastId() + 1;
            this.tasks.add(jaunaisId + "," + task);
            updateFile();
        }
    }

    private void updateFile() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(filePath));
            pw.println("id,task");
            
            for (String t : this.tasks) {
                pw.println(t);
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Kļūda ar failu!");
        }
    }

    public void remove(int id) {
        int indexKoDzēst = -1;
        
        for (int i = 0; i < this.tasks.size(); i++) {
            String[] gabali = this.tasks.get(i).split(",");
            int sarakstaId = Integer.parseInt(gabali[0]);
            
            if (sarakstaId == id) {
                indexKoDzēst = i;
                break;
            }
        }
        
        if (indexKoDzēst != -1) {
            this.tasks.remove(indexKoDzēst);
            updateFile();
        }
    }

    
    public boolean checkEventString(String value) {
        
        if (value.length() < 3) {
            return false;
        }
        return value.matches("^[a-zA-Z0-9āčēģīķļņōŗšūžĀČĒĢĪĶĻŅŌŖŠŪŽ ]+$");
    }
}