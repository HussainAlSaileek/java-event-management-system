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


    private VenueManager venueManager;

    public EventManager(VenueManager venueManager) {
        this.venueManager = venueManager;
    }




    public boolean addEvent(Event newEvent) {
        if (!newEvent.getEndDateTime().isAfter(newEvent.getStartDateTime())) {
            System.out.println("Error: Start time must be before end time");
            return false;
        }

        if (!venueManager.isVenueCompatible(newEvent.getEventType(), newEvent.getVenue())) {
            System.out.println("Error: This venue type is not suitable for this event type");
            return false;
        }

        if (newEvent.getNeededCapacity() > newEvent.getVenue().getMaxCapacity()) {
            System.out.println("Error: Needed capacity exceeds this venue's capacity");
            return false;
        }

        if (!venueManager.isVenueFree(newEvent.getVenue(), newEvent.getStartDateTime(), newEvent.getEndDateTime(),events)) {
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



}
