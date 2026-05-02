import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class EventMenu {

    private EventManager eventManager;
    private VenueManager venueManager;
    private DepartmentManager departmentManager;
    Scanner scnr;

    public EventMenu(EventManager eventManager, VenueManager venueManager, DepartmentManager departmentManager, Scanner scnr) {
        this.eventManager = eventManager;
        this.venueManager = venueManager;
        this.departmentManager = departmentManager;
        this.scnr = scnr;
    }

    public void start() {

        System.out.println("Event Menu (Select From 1-6)");

        while (true) {

            System.out.println("1. Add Event");
            System.out.println("2. Delete Event");
            System.out.println("3. Search Event");
            System.out.println("4. View All Events");
            System.out.println("5. View Events by Type");
            System.out.println("6. Back");

            int choice = scnr.nextInt();

            if (choice == 1) {
                addEvent();
            }

            else if (choice == 2) {
                deleteEvent();
            }

            else if (choice == 3) {
                searchEvent();
            }

            else if (choice == 4) {
                viewAllEvents();
            }

            else if (choice == 5) {
                viewEventsByType();
            }

            else if (choice == 6) {
                break;
            }

            else {
                System.out.println("Invalid choice");
            }
        }

    }

    public void addEvent() {

        System.out.println("Select Event Type");

        while (true) {
            System.out.println("1. Academic Event");
            System.out.println("2. Religious Event");
            System.out.println("3. Social Event");
            System.out.println("4. Sports Event");
            System.out.println("5. Back");

            int choice = scnr.nextInt();







            if (choice == 5) {
                break;
            }

            else if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice");
                continue;
            }

                        /* protected String eventName;
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected Venue venue;
    protected Department sponsorDepartment;
    protected int neededCapacity;
    protected String eventType; */

            System.out.println("Enter Event Name");
            String eventName = scnr.next();

            System.out.println("Enter Start Date of Event (in year-month-day format):");
            String startDate = scnr.nextLine();


            System.out.println("Enter Start Time of Event (in hour:minute format):");
            System.out.println("hour should be between 0 and 23");
            String startTime = scnr.nextLine();

            String dateTime = startDate + " " + startTime;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDateTime startDateTime = LocalDateTime.parse(dateTime, formatter);

            System.out.println("Enter End Date of Event:");
            String endDate = scnr.nextLine();
            System.out.println("Enter End Time of Event:");
            String endTime = scnr.nextLine();





            if (choice == 2) {

            }

            else if (choice == 3) {

            }

            else if (choice == 4) {

            }

            else if (choice == 1) {

            }

            else {
                System.out.println("Invalid choice");
            }

        }





    }

    public void deleteEvent() {

    }

    public void searchEvent() {}

    public void viewAllEvents() {}

    public void viewEventsByType() {}

    public LocalDateTime readDateTime(String Date, String Time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(Date, formatter);
    }

}
