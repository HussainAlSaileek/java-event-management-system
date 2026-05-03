import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
public class EventMenu {

    private final EventManager eventManager;
    private final VenueManager venueManager;
    private final DepartmentManager departmentManager;
    private final Scanner scnr;

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

    public void addEvent() {



        while (true) {
            System.out.println("Select event type by number");
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

            System.out.println("Enter the event name");
            String eventName = scnr.nextLine();

            LocalDateTime[] dateTimes = getStartAndEndDateTime();
            LocalDateTime startDateTime = dateTimes[0];
            LocalDateTime endDateTime = dateTimes[1];


            int capacity = getCapacity();

            ArrayList<Venue> venues = venueManager.getAvailableVenues(startDateTime, endDateTime, capacity, eventType,  eventManager.getEvents());
            while (venues.isEmpty()) {
                int maxCapacityByTime = venueManager.maxCapacityByTime(startDateTime, endDateTime, eventType, eventManager.getEvents());
                if (maxCapacityByTime > 0) {
                    System.out.println("Maximum allowed Capacity at this time: " + maxCapacityByTime);
                    System.out.println("1. Change the capacity");
                    System.out.println("2. Change the time");
                    System.out.println("3. Cancel the event");

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
            Venue venue = selectVenue(venues);

            System.out.println("Enter the sponsoring department name");
            departmentManager.viewAllDepartments();
            String departmentName = scnr.nextLine();
            while (departmentManager.searchDepartment(departmentName) == null) {
                System.out.println("Department does not exist, please try again");
                departmentManager.viewAllDepartments();
                departmentName = scnr.nextLine();
            }

            Department department = departmentManager.searchDepartment(departmentName);




            if (choice == 1) {

                System.out.println("Enter the instructor's name");
                String instructorName = scnr.nextLine();
                AcademicEvent event = new AcademicEvent(eventName, startDateTime, endDateTime, venue, department, capacity, eventType, instructorName);
                eventManager.addEvent(event);
            }

            else if (choice == 2) {

                System.out.println("Enter the speaker's name");
                String speakerName = scnr.nextLine();
                ReligiousEvent event = new ReligiousEvent(eventName, startDateTime, endDateTime, venue, department, capacity, eventType, speakerName);
                eventManager.addEvent(event);
            }

            else if (choice == 3) {

                System.out.println("Enter the event's club");
                String club = scnr.nextLine();
                SocialEvent event = new SocialEvent(eventName, startDateTime, endDateTime, venue, department, capacity, eventType, club);
                eventManager.addEvent(event);
            }

            else {

                System.out.println("Enter the sport name");
                String sportName = scnr.nextLine();
                SportsEvent event = new SportsEvent(eventName, startDateTime, endDateTime, venue, department, capacity, eventType, sportName);
                eventManager.addEvent(event);
            }



        }





    }



    public void deleteEvent() {

        while (true) {

            System.out.println("Enter the event's name to be deleted:");
            String eventToDelete = scnr.nextLine();
            boolean condition = eventManager.deleteEvent(eventToDelete);

            if (!condition) {
                System.out.println("Event does not exist (choose number):");
                System.out.println("1. Try again");
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

    public void searchEvent() {

        while (true) {

            System.out.println("Enter the event's name to be searched:");
            String eventToSearch = scnr.nextLine();
            Event event = eventManager.findEvent(eventToSearch);

            if (event == null) {
                System.out.println("Event does not exist");
                System.out.println("1. Try again");
                System.out.println("2. Cancel");
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

    public void viewEventsByType() {

        while (true) {

            System.out.println("Event types: ");
            System.out.println("Academic/Religious/Social/Sport");
            System.out.println("Enter the type of event to be viewed by (name):");
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

    private LocalDateTime getDateTime(String label) {
        System.out.println("Enter the " + label + " date of the event (yyyy-MM-dd):");
        String date = scnr.nextLine();

        System.out.println("Enter the " + label + " time of the event (HH:mm):");
        String time = scnr.nextLine();

        return toDateTime(date + " " + time);
    }


    private int getCapacity() {
        int capacity;
        while (true) {
            System.out.println("Enter needed capacity for the event");
            System.out.println("Maximum allowed Capacity: " + venueManager.checkMaxCapacity());
            capacity = readInt();
            if (capacity < 1 || capacity > venueManager.checkMaxCapacity()) {
                System.out.println("Invalid capacity");
                continue;
            }
            break;
        }
        return capacity;
    }
    private LocalDateTime toDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        while (true) {
            try {
                return LocalDateTime.parse(dateTime, formatter);
            }
            catch (DateTimeParseException e) {
                System.out.println("Invalid date and time");
                System.out.println("Please try again, enter a date");
                String date =  scnr.nextLine();
                System.out.println("enter a time");
                String time = scnr.nextLine();
                dateTime = date + " " + time;
            }
        }


    }

    private Venue selectVenue(ArrayList<Venue> venues) {
        int choice;
        if (venues.isEmpty()) {
            System.out.println("There are no venues available");
            return null;
        }
        for (int i = 0; i < venues.size(); i++) {
            if (i == 0) {
                System.out.println("Select Venue");
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

    private LocalDateTime[] getStartAndEndDateTime(){
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        while (true) {
            startDateTime = getDateTime("start");


            endDateTime = getDateTime("end");

            if (!endDateTime.isAfter(startDateTime)) {
                System.out.println("Error: Start time must be before end time");
            } else {
                return new LocalDateTime[]{startDateTime, endDateTime};
            }
        }
    }

    private boolean askYesNo(String message) {
        while (true) {
            System.out.println(message);
            System.out.println("1. Yes");
            System.out.println("2. No");

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

    private int readInt() {
        int input = scnr.nextInt();
        scnr.nextLine();
        return input;
    }

}
