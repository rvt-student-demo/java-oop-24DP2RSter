package rvt.studentu_sistema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Student {
    private String vards;
    private String uzvards;
    private String epasts;
    private String personasKods;
    private String registracijasLaiks;

    // Konstruktors jauna studenta izveidei (automātiski pieliek pašreizējo laiku)
    public Student(String vards, String uzvards, String epasts, String personasKods) {
        this.vards = vards;
        this.uzvards = uzvards;
        this.epasts = epasts;
        this.personasKods = personasKods;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.registracijasLaiks = LocalDateTime.now().format(formatter);
    }

    // Konstruktors, lai nolasītu datus no CSV faila
    public Student(String vards, String uzvards, String epasts, String personasKods, String registracijasLaiks) {
        this.vards = vards;
        this.uzvards = uzvards;
        this.epasts = epasts;
        this.personasKods = personasKods;
        this.registracijasLaiks = registracijasLaiks;
    }

    public String getVards() { return vards; }
    public void setVards(String vards) { this.vards = vards; }
    public String getUzvards() { return uzvards; }
    public void setUzvards(String uzvards) { this.uzvards = uzvards; }
    public String getEpasts() { return epasts; }
    public void setEpasts(String epasts) { this.epasts = epasts; }
    public String getPersonasKods() { return personasKods; }

    // Pārvērš studenta datus vienā CSV līnijā
    public String toCsvRow() {
        return vards + "," + uzvards + "," + epasts + "," + personasKods + "," + registracijasLaiks;
    }
}
