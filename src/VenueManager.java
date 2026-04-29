import java.util.ArrayList;

public class VenueManager {
    private ArrayList<Venue> venues = new ArrayList<>();

    public void addVenue(Venue venue) {
        venues.add(venue);
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




}
