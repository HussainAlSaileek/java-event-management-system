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


    public ArrayList<Venue> getAvailableVenues(LocalDateTime start, LocalDateTime end, int neededCapacity, ArrayList<Venue> allVenues) {
        ArrayList<Venue> availableVenues = new ArrayList<>();


        for (Venue venue: allVenues) {
            boolean isAvailable = true;

            if (neededCapacity > venue.getMaxCapacity()) {
                continue;
            }
            for (Event event : events) {
                boolean sameVenue = false;

                if (event.getVenue().getVenueName().equalsIgnoreCase(venue.getVenueName())) {
                    sameVenue = true;
                }

                if (sameVenue && start.isBefore(event.getEndDateTime()) && end.isAfter(event.getStartDateTime())) {
                    isAvailable = false;
                    break;
                }


            }



            if (isAvailable) {
                availableVenues.add(venue);
            }

        }
        return availableVenues;
    }

    public boolean addEvent(Event newEvent) {

        if (!newEvent.getEndDateTime().isAfter(newEvent.getStartDateTime())) {
            System.out.println("Error: Start time must be before end time");
            return false;
        }

        if (newEvent.getNeededCapacity() > newEvent.getVenue().getMaxCapacity()) {
            System.out.println("Error: Needed capacity exceeds this venue's capacity");
            return false;
        }

        for (Event event : events) {

            if (event.checkOverlap(newEvent) && event.getVenue().getVenueName().equalsIgnoreCase(newEvent.getVenue().getVenueName())) {
                System.out.println("Error: Venue is already booked at this time");
                return false;
            }
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


}
