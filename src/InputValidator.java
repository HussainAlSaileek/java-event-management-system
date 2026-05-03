public class InputValidator {


    public static boolean isValidAttendance(int attendance, Venue venue) {
        if (venue == null) {
            return false;
        }
        else{
            if (attendance > 0 && attendance <= venue.getMaxCapacity()){
                return true;
            }
        else return false;
        }
    }

    public static boolean isValidCapacity(int capacity) {
        if (capacity>0){
            return true;
        }
        else{
            return false;
        }
    }


}
