package rvt.studentu_sistema;

import java.util.List;

public class Validator {

    public static boolean validateVardsUzvards(String teksts) {
        // Tikai burti (ieskaitot latviešu mīkstinājumus), garums vismaz 3
        return teksts.matches("^[a-zA-ZāčēģīķļņšūžĀČĒĢĪĶĻŅŠŪŽ]{3,30}$");
    }

    public static boolean validateEpasts(String epasts) {
        // Standarta e-pasta formāta pārbaude
        return epasts.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    public static boolean validatePersonasKods(String pk) {
        
        return pk.matches("^\\d{6}-\\d{5}$|^\\d{11}$");
    }

    // Pārbauda unikālās vērtības 
    public static void checkUniqueness(String pk, String epasts, List<Student> students) throws Exception {
        for (Student s : students) {
            if (s.getPersonasKods().equals(pk)) {
                throw new Exception("KĻŪDA: Students ar šādu personas kodu jau eksistē!");
            }
            if (s.getEpasts().equalsIgnoreCase(epasts)) {
                throw new Exception("KĻŪDA: Šāds e-pasts jau ir reģistrēts sistēmā!");
            }
        }
    }
}