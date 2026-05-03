import java.time.LocalDateTime;
import java.util.ArrayList;

public class VenueManager {
    private ArrayList<Venue> venues = new ArrayList<>();

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public boolean venueExists(String name) {
        for (Venue venue : venues) {
            if (venue.getVenueName().equalsIgnoreCase(name)) {
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

    //print all provided venues//
    public void viewAllTypesOfVenues(){
        System.out.println("SportsArea");
        System.out.println("LectureHall");
        System.out.println("ConferenceHall");
        System.out.println("PublicSpace");
    }

    // search for specific venue in the venueList //
    public Venue searchVenue(String name){

        for (Venue venue : venues) {
            if (venue.getVenueName().equalsIgnoreCase(name) ){
                return venue;
            }
        }

            return null;
        }

        public void showVenueDetails(String name){
        Venue venue=searchVenue(name);
        if (venue!=null){
            venue.printDetails();
        }
        else {
            System.out.println("venue not found");
        }

        }
        // set the maximum venue in the venueList//
    public int checkMaxCapacity(){
        int max=0;
        for (Venue venue : venues) {
            if (max< venue.getMaxCapacity()){
                max= venue.getMaxCapacity();
            }
        }
        return max;
    }

    public boolean isVenueFree(Venue venue, LocalDateTime start, LocalDateTime end, ArrayList<Event> events) {
        for (Event event : events) {
            boolean sameVenue = event.getVenue().getVenueName().equalsIgnoreCase(venue.getVenueName());
            boolean overlaps = start.isBefore(event.getEndDateTime()) && end.isAfter(event.getStartDateTime());

            if (sameVenue && overlaps) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Venue> getAvailableVenues(LocalDateTime start,
                                               LocalDateTime end,
                                               int neededCapacity,
                                               String eventType,
                                               ArrayList<Event> events) {
        ArrayList<Venue> availableVenues = new ArrayList<>();

        for (Venue venue : venues) {
            if (!isVenueCompatible(eventType, venue)) {
                continue;
            }

            if (neededCapacity > venue.getMaxCapacity()) {
                continue;
            }

            if (isVenueFree(venue, start, end, events)) {
                availableVenues.add(venue);
            }
        }

        return availableVenues;
    }
        // to check if the venue matches with the event//
    public boolean isVenueCompatible(String eventType, Venue venue) {
        String type = eventType.toLowerCase();
        String venueType = venue.getVenueType().toLowerCase();

        if (type.equals("sport")) {
            return venueType.equals("sportsarea");
        }

        if (type.equals("academic")) {
            return venueType.equals("lecturehall") || venueType.equals("conferencehall");
        }

        if (type.equals("religious")) {
            return venueType.equals("lecturehall")
                    || venueType.equals("conferencehall")
                    || venueType.equals("publicspace");
        }

        if (type.equals("social")) {
            return venueType.equals("publicspace") || venueType.equals("conferencehall");
        }

        return false;
    }

    public ArrayList<Venue> getAvailableVenuesByCapacity(int neededCapacity) {
        ArrayList<Venue> availableVenues = new ArrayList<>();

        for (Venue venue: venues) {
            if (venue.getMaxCapacity() >= neededCapacity) {
                availableVenues.add(venue);
            }
        }
        return availableVenues;
    }

    public int maxCapacityByTime(LocalDateTime start,
                                 LocalDateTime end,
                                 String eventType,
                                 ArrayList<Event> events) {

        int maxCapacity = 0;

        for (Venue venue : venues) {

            if (!isVenueCompatible(eventType, venue)) {
                continue;
            }

            if (isVenueFree(venue, start, end, events) && venue.getMaxCapacity() > maxCapacity) {
                maxCapacity = venue.getMaxCapacity();
            }
        }

        return maxCapacity;
    }



}
