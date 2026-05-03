/* Finish EventManager.
Add overlap checking.
Add event search/delete/view. */
import java.time.LocalDateTime;
import java.util.ArrayList;
public class EventManager {

    private final ArrayList<Event> events = new ArrayList<>();

    public ArrayList<Event> getEvents() {
        return events;
    }

    private boolean isVenueFree(Venue venue, LocalDateTime start, LocalDateTime end) {
        for (Event event : events) {
            boolean sameVenue = false;
            if (event.getVenue().getVenueName()
                    .equalsIgnoreCase(venue.getVenueName())) {
                sameVenue = true;
            }

            boolean overlaps = false;
            if (start.isBefore(event.getEndDateTime())
                    && end.isAfter(event.getStartDateTime())) {
                overlaps = true;
            }

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
                                               ArrayList<Venue> allVenues) {
        ArrayList<Venue> availableVenues = new ArrayList<>();

        for (Venue venue : allVenues) {
            if (!isVenueCompatible(eventType, venue)) {
                continue;
            }

            if (neededCapacity > venue.getMaxCapacity()) {
                continue;
            }

            if (isVenueFree(venue, start, end)) {
                availableVenues.add(venue);
            }
        }

        return availableVenues;
    }

    public ArrayList<Venue> getAvailableVenuesByCapacity(int neededCapacity, ArrayList<Venue> allVenues) {
        ArrayList<Venue> availableVenues = new ArrayList<>();

        for (Venue venue: allVenues) {


            if (venue.getMaxCapacity() >= neededCapacity) {
                availableVenues.add(venue);
            }
        }
        return availableVenues;
    }

    public int maxCapacityByTime(LocalDateTime start,
                                 LocalDateTime end,
                                 String eventType,
                                 ArrayList<Venue> allVenues) {
        int maxCapacity = 0;

        for (Venue venue : allVenues) {
            if (!isVenueCompatible(eventType, venue)) {
                continue;
            }

            if (isVenueFree(venue, start, end)
                    && venue.getMaxCapacity() > maxCapacity) {
                maxCapacity = venue.getMaxCapacity();
            }
        }

        return maxCapacity;
    }

    public boolean addEvent(Event newEvent) {
        if (!newEvent.getEndDateTime().isAfter(newEvent.getStartDateTime())) {
            System.out.println("Error: Start time must be before end time");
            return false;
        }

        if (!isVenueCompatible(newEvent.getEventType(), newEvent.getVenue())) {
            System.out.println("Error: This venue type is not suitable for this event type");
            return false;
        }

        if (newEvent.getNeededCapacity() > newEvent.getVenue().getMaxCapacity()) {
            System.out.println("Error: Needed capacity exceeds this venue's capacity");
            return false;
        }

        if (!isVenueFree(newEvent.getVenue(), newEvent.getStartDateTime(), newEvent.getEndDateTime())) {
            System.out.println("Error: Venue is already booked at this time");
            return false;
        }

        events.add(newEvent);
        System.out.println("Event added successfully!");
        return true;
    }


    public Event findEvent(String eventToFind) {
        for (Event event : events) {
            if (event.getEventName().equalsIgnoreCase(eventToFind)) {
                return event;
            }
        }
        return null;
    }

    public boolean deleteEvent(String eventName) {
        Event eventToDelete = findEvent(eventName);

        if (eventToDelete == null) {
            System.out.println("This event hasn't been added");
            return false;

        }
        events.remove(eventToDelete);
        System.out.println("Event deleted successfully");
        return true;
    }

    public void viewAllEvents() {
        if (events.isEmpty()) {
            System.out.println("No events found.");
            return;
        }

        for (Event event : events) {
            event.printDetails();
            System.out.println("--------------------");
        }
    }

    public void viewEventDetails(String eventName) {
        Event event = findEvent(eventName);

        if (event == null) {
            System.out.println("Event not found.");
        } else {
            event.printDetails();
        }
    }

    public void viewEventsByType(String eventType) {
        boolean found = false;

        for (Event event : events) {
            if (event.getEventType().equalsIgnoreCase(eventType)) {
                event.printDetails();
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No events found for type: " + eventType);
        }
    }

    private boolean isVenueCompatible(String eventType, Venue venue) {
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


}
