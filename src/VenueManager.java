import java.util.ArrayList;

public class VenueManager {
    private ArrayList<Venue> venues = new ArrayList<>();

    public boolean venueExists(String name) {
        for (Venue venue : venues) {
            if (venue.getVenueName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void addVenue(Venue venue) {
        if (venueExists(venue.getVenueName())==false) {
            venues.add(venue);
        }
        else {
            System.out.println("venue already exist");
        }
    }

    public void viewAllVenues() {
        if (venues.size() == 0) {
            System.out.println("No venues available.");
        } else {

            for (Venue venue : venues) {
                System.out.println(venue.getVenueName()+ " - " + venue.getVenueType());
            }
        }
    }

    public Venue searchVenue(String name){

        for (Venue venue : venues) {
            if (venue.getVenueName().equals(name) ){
                return venue;
            }
        }

            return null;
        }

        public void showVenuDetails(String name){
        Venue venue=searchVenue(name);
        if (venue!=null){
            venue.printDetails();
        }
        else {
            System.out.println("venue not found");
        }

        }

        public boolean checkAttendanceValidation(int attendance,Venue venue){
        if (attendance<=venue.getMaxCapacity()) {
            if (searchVenue(venue.getVenueName()) != null) {
                System.out.println("valid attendance");
                return true;
            } else {
                System.out.println("Invalid attendance");
                return false;
            }
        }
        else {
                System.out.println("venue not found" );
                return false;
            }
        }


    }






