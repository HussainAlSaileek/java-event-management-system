import java.util.Scanner;
public class EventMenu {

    private EventManager eventManager;
    private VenueManager venueManager;
    private DepartmentManager departmentManager;
    Scanner scnr = new Scanner(System.in);

    public EventMenu(EventManager eventManager, VenueManager venueManager, DepartmentManager departmentManager) {
        this.eventManager = eventManager;
        this.venueManager = venueManager;
        this.departmentManager = departmentManager;
    }

    public void start() {

        System.out.println("Event Menu (Select From 1-6)");
        System.out.println("1. Add Event");
        System.out.println("2. Delete Event");
        System.out.println("3. Search Event");
        System.out.println("4. View All Events");
        System.out.println("5. View Events by Type");
        System.out.println("6. Back");
    }

    public <T extends Event> void addEvent(T eventToAdd) {

        System.out.println("Select Event Type");
        System.out.println("1. Academic Event");
        System.out.println("2. Religious Event");
        System.out.println("3. Social Event");
        System.out.println("4. Sports Event");

        int choice = scnr.nextInt();


    }
}
