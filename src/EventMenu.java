import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
public class EventMenu {

    // Needed managers to use data
    private final EventManager eventManager;
    private final VenueManager venueManager;
    private final DepartmentManager departmentManager;
    private final Scanner scnr;

    // Initialization
    public EventMenu(EventManager eventManager, VenueManager venueManager, DepartmentManager departmentManager, Scanner scnr) {
        this.eventManager = eventManager;
        this.venueManager = venueManager;
        this.departmentManager = departmentManager;
        this.scnr = scnr;
    }

    // First method to start using the event menu
    public void start() {

        System.out.println("Event Menu (Select From 1-6)");

        while (true) {

            System.out.println("\n==============================");
            System.out.println("        EVENT MENU");
            System.out.println("==============================");
            System.out.println("1. Add Event");
            System.out.println("2. Delete Event");
            System.out.println("3. Search Event");
            System.out.println("4. View All Events");
            System.out.println("5. View Events by Type");
            System.out.println("6. Back");
            System.out.print("Enter choice: ");

            int choice = readInt();

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
                eventManager.viewAllEvents();
            }

            else if (choice == 5) {
                viewEventsByType();
            }

            else if (choice == 6) {
                return;
            }

            else {
                System.out.println("Invalid choice");
            }
        }

    }

    // Adds events, longest method
    public void addEvent() {

        // Complete loop, user starts here and returns here after a
        // successful operation or by command
        while (true) {
            System.out.println("Select event type by number:");
            System.out.println("1. Academic Event");
            System.out.println("2. Religious Event");
            System.out.println("3. Social Event");
            System.out.println("4. Sports Event");
            System.out.println("5. Back");

            int choice = readInt();



            if (choice == 5) {
                return;
            }

            else if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice");
                continue;
            }

            String eventType;

            if (choice == 1) {
                eventType = "Academic";
            }
            else if (choice == 2) {
                eventType = "Religious";
            }
            else if (choice == 3) {
                eventType = "Social";
            }
            else {
                eventType = "Sport";
            }

            System.out.println("Enter the event name: ");
            String eventName = scnr.nextLine();

            // Time input by getStartAndEndDateTime() which returns a list of
            // [startDateTime, endDateTime]
            LocalDateTime[] dateTimes = getStartAndEndDateTime();
            LocalDateTime startDateTime = dateTimes[0];
            LocalDateTime endDateTime = dateTimes[1];

            // Capacity input
            int capacity = getCapacity();

            // Venue input, filters the venue options by time and capacity entered, loops
            // until the user successfully chooses a venue or cancels the event
            ArrayList<Venue> venues = venueManager.getAvailableVenues(startDateTime, endDateTime, capacity, eventType,  eventManager.getEvents());
            while (venues.isEmpty()) {

                int maxCapacityByTime = venueManager.maxCapacityByTime(startDateTime, endDateTime, eventType, eventManager.getEvents());

                if (maxCapacityByTime > 0) {
                    System.out.println("Maximum allowed Capacity at this time: " + maxCapacityByTime);
                    System.out.println("1. Change The Capacity");
                    System.out.println("2. Change The Time");
                    System.out.println("3. Cancel The Event");

                    int changeChoice = readInt();
                    if (changeChoice == 1) {
                        capacity = getCapacity();
                    }
                    else if (changeChoice == 2) {
                        eventManager.viewAllEvents();
                        dateTimes = getStartAndEndDateTime();
                        startDateTime = dateTimes[0];
                        endDateTime = dateTimes[1];
                    }
                    else if (changeChoice == 3) {
                        return;
                    }
                    else {
                        System.out.println("Invalid choice");
                    }

                }
                else {
                    System.out.println("All venues are booked at this time, try changing the time: ");
                    eventManager.viewAllEvents();
                    dateTimes = getStartAndEndDateTime();
                    startDateTime = dateTimes[0];
                    endDateTime = dateTimes[1];

                }
                venues = venueManager.getAvailableVenues(startDateTime, endDateTime, capacity, eventType, eventManager.getEvents());
            }
            // Final venue input, options of possible venues to choose from here
            Venue venue = selectVenue(venues);

            // Department input
            System.out.println("Enter the sponsoring department name: ");
            departmentManager.viewAllDepartments();
            String departmentName = scnr.nextLine();
            while (departmentManager.searchDepartment(departmentName) == null) {
                System.out.println("Department does not exist, please try again");
                departmentManager.viewAllDepartments();
                departmentName = scnr.nextLine();
            }
            Department department = departmentManager.searchDepartment(departmentName);

            // Finally adding event, asks additional input depending on type
            if (choice == 1) {

                System.out.println("Enter the instructor's name: ");
                String instructorName = scnr.nextLine();
                AcademicEvent event = new AcademicEvent(eventName, startDateTime, endDateTime, venue, department, capacity, eventType, instructorName);
                eventManager.addEvent(event);
            }

            else if (choice == 2) {

                System.out.println("Enter the speaker's name: ");
                String speakerName = scnr.nextLine();
                ReligiousEvent event = new ReligiousEvent(eventName, startDateTime, endDateTime, venue, department, capacity, eventType, speakerName);
                eventManager.addEvent(event);
            }

            else if (choice == 3) {

                System.out.println("Enter the event's club: ");
                String club = scnr.nextLine();
                SocialEvent event = new SocialEvent(eventName, startDateTime, endDateTime, venue, department, capacity, eventType, club);
                eventManager.addEvent(event);
            }

            else {

                System.out.println("Enter the sport name: ");
                String sportName = scnr.nextLine();
                SportsEvent event = new SportsEvent(eventName, startDateTime, endDateTime, venue, department, capacity, eventType, sportName);
                eventManager.addEvent(event);
            }



        }





    }

    // deletes events by name, loops until the user cancels
    public void deleteEvent() {

        while (true) {

            System.out.println("Enter the event's name to be deleted:");
            String eventToDelete = scnr.nextLine();
            boolean condition = eventManager.deleteEvent(eventToDelete);

            if (!condition) {
                System.out.println("Event does not exist (choose number): ");
                System.out.println("1. Try Again");
                System.out.println("2. Cancel");
                int choice = readInt();
                if (choice == 1) {
                    eventManager.viewAllEvents();
                }
                else if (choice == 2) {
                    return;
                }
                else {
                    System.out.println("Invalid choice");
                }
            }

            else {
                if (askYesNo("Do you want to delete another event?")) {
                    continue;
                }
                else {
                    return;
                }
            }
        }


    }

    // Searches events by name and prints their details until user cancels
    public void searchEvent() {

        while (true) {

            System.out.println("Enter the event's name to be searched: ");
            String eventToSearch = scnr.nextLine();
            Event event = eventManager.findEvent(eventToSearch);

            if (event == null) {
                System.out.println("Event does not exist");
                System.out.println("1. Try Again");
                System.out.println("2. Cancel");
                System.out.print("Enter choice: ");
                int choice = readInt();
                if (choice == 1) {
                    continue;
                }
                else if (choice == 2) {
                    return;
                }
                else {
                    System.out.println("Invalid choice");
                    continue;
                }
            }
            event.printDetails();

            if (askYesNo("Do you want to search for another event?")) {
                continue;
            }
            else {
                return;
            }
        }
    }

    // Prints the details of all events of type X looping until user cancels
    public void viewEventsByType() {

        while (true) {

            System.out.println("Event types: ");
            System.out.println("Academic/Religious/Social/Sport");
            System.out.println("Enter the type of event to be viewed by (name): ");
            String eventType = scnr.nextLine();

            eventManager.viewEventsByType(eventType);

            if (askYesNo("Do you want to view another type's events?")) {
                continue;
            }
            else {
                return;
            }
        }
    }

    // HELPER METHODS
    // Gets the date and time, uses 'label' to differentiate between start and end
    private LocalDateTime getDateTime(String label) {
        System.out.println("Enter the " + label + " date of the event (yyyy-MM-dd): ");
        String date = scnr.nextLine();

        System.out.println("Enter the " + label + " time of the event (HH:mm): ");
        String time = scnr.nextLine();

        return toDateTime(date + " " + time);
    }

    // Helper method to validate input of user and converts the
    // input from string to localdatetime type
    private LocalDateTime toDateTime(String dateTime) {
        // decides format of the string to convert from
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime newDateTime;

        // loops until the user inputs time in the correct format and
        // checks if it's not in the past
        while (true) {
            try {
                newDateTime = LocalDateTime.parse(dateTime, formatter);
                if (newDateTime.isBefore(LocalDateTime.now())) {
                    System.out.println("Invalid date and time, the event can not be in the past");
                    System.out.println("Please try again, enter the date (yyyy-MM-dd): ");
                    String date =  scnr.nextLine();
                    System.out.println("enter a time(HH:mm): ");
                    String time = scnr.nextLine();
                    dateTime = date + " " + time;
                    continue;
                }
            }
            // DateTimeParseException is thrown when the date is not a real date
            // and when the format in user input is incorrect
            catch (DateTimeParseException e) {
                System.out.println("Invalid date and time");
                System.out.println("Please try again, enter a date (yyyy-MM-dd)");
                String date =  scnr.nextLine();
                System.out.println("enter a time (HH:mm)");
                String time = scnr.nextLine();
                dateTime = date + " " + time;
                continue;
            }
            return newDateTime;
        }
    }

    // Helper method to take initial capacity, only validates if capacity input is lower than
    // max venue capacity and more than 0
    private int getCapacity() {
        int capacity;
        while (true) {
            System.out.println("Enter needed capacity for the event: ");
            System.out.println("Global maximum allowed capacity: " + venueManager.checkMaxCapacity());
            capacity = readInt();
            if (capacity < 1 || capacity > venueManager.checkMaxCapacity()) {
                System.out.println("Invalid capacity");
                continue;
            }
            break;
        }
        return capacity;
    }

    // Helper method to select venue by taking the list of available venues then
    // prompting user to choose from them
    private Venue selectVenue(ArrayList<Venue> venues) {
        int choice;
        if (venues.isEmpty()) {
            System.out.println("There are no venues available");
            return null;
        }
        for (int i = 0; i < venues.size(); i++) {
            if (i == 0) {
                System.out.println("Select Venue: ");
            }
            System.out.print(i+1);
            System.out.println(". " + venues.get(i).getVenueName());
        }
        while (true) {
            choice = readInt();
            if (choice < 1 || choice > venues.size()) {
                System.out.println("Invalid choice");
            }
            else {
                return venues.get(choice-1);
            }
        }
    }

    // Helper method to return a list of [startDateTime, endDateTime] after validation
    // if the start is before end, uses getDateTime to prompt the user for input
    private LocalDateTime[] getStartAndEndDateTime(){
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        while (true) {

            startDateTime = getDateTime("start");
            endDateTime = getDateTime("end");

            if (!endDateTime.isAfter(startDateTime)) {
                System.out.println("Error: Start time must be before end time");
                System.out.println("Please try again");
            } else {
                return new LocalDateTime[]{startDateTime, endDateTime};
            }
        }
    }

    // Helper method for repeated yes or no prompt for user
    private boolean askYesNo(String message) {
        while (true) {
            System.out.println(message);
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Enter choice: ");

            int choice = readInt();

            if (choice == 1) {
                return true;
            }
            else if (choice == 2) {
                return false;
            }
            else {
                System.out.println("Invalid choice");
            }
        }
    }

    // cleans int input from scanner to deal with leftover input from user, validates input
    private int readInt() {
        while (true) {
            try {
                int input = Integer.parseInt(scnr.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

}
