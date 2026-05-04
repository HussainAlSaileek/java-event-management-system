import java.util.Scanner;
public class VenueMenu {


    private final Scanner scnr;
    private final VenueManager venueManager;

    public VenueMenu(VenueManager venueManager, Scanner scnr) {
        this.venueManager = venueManager;
        this.scnr = scnr;
    }


    public void setVenueList() {
        Venue v1=new SportsArea("Field 1",90,"Football");
        Venue v2=new LectureHall("Room:1001",120,true);
        Venue v3=new ConferenceHall("Hall A",70,true);
        Venue v4=new PublicSpace("Stadium",750);
        Venue v5=new LectureHall("Room:500",45,false);
        venueManager.addVenue(v1);
        venueManager.addVenue(v2);
        venueManager.addVenue(v3);
        venueManager.addVenue(v4);
        venueManager.addVenue(v5);

    }

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


    public void start() {
        while (true) {
            System.out.println("\n==============================");
            System.out.println("          VENUE MENU");
            System.out.println("==============================");
            System.out.println("1. Add Venue");
            System.out.println("2. View All Venue Types");
            System.out.println("3. View All Venues");
            System.out.println("4. Search Venue");
            System.out.println("5. Show Venue Details");
            System.out.println("6. Back");
            System.out.print("Enter choice: ");

            int choice = readInt();

            if (choice == 1) {
                addVenue();
            }
            else if (choice == 2) {
                venueManager.viewAllTypesOfVenues();
            }
            else if (choice == 3) {
                venueManager.viewAllVenues();
            }
            else if (choice == 4) {
                System.out.println("Enter venue name:");
                String venueName = scnr.nextLine();

                Venue venue = venueManager.searchVenue(venueName);

                if (venue == null) {
                    System.out.println("Venue not found.");
                } else {
                    System.out.println("Venue " + venue.getVenueName() + " was found.");
                }
            }
            else if (choice == 5) {
                System.out.println("Enter venue name:");
                String name = scnr.nextLine();
                venueManager.showVenueDetails(name);
            }
            else if (choice == 6) {
                return;
            }
            else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void addVenue() {
        System.out.println("\nSelect venue type:");
        System.out.println("1. Sports Area");
        System.out.println("2. Lecture Hall");
        System.out.println("3. Conference Hall");
        System.out.println("4. Public Space");
        System.out.println("5. Cancel");
        System.out.print("Enter choice: ");

        int choice = readInt();

        if (choice == 5) {
            return;
        }

        if (choice < 1 || choice > 5) {
            System.out.println("Invalid venue type.");
            return;
        }

        System.out.println("Enter venue name:");
        String venueName = scnr.nextLine();

        if (venueManager.venueExists(venueName)) {
            System.out.println("Venue already exists.");
            return;
        }

        int maxCapacity = readPositiveInt("Enter maximum capacity: ");

        Venue venue;

        if (choice == 1) {
            System.out.println("Enter sport type:");
            String sportType = scnr.nextLine();

            venue = new SportsArea(venueName, maxCapacity, sportType);
        }
        else if (choice == 2) {
            boolean hasSmartBoard = askYesNo("Does it have a smart board?");
            venue = new LectureHall(venueName, maxCapacity, hasSmartBoard);
        }
        else if (choice == 3) {
            boolean hasSoundSystem = askYesNo("Does it have a sound system?");
            venue = new ConferenceHall(venueName, maxCapacity, hasSoundSystem);
        }
        else {
            venue = new PublicSpace(venueName, maxCapacity);
        }

        venueManager.addVenue(venue);
        System.out.println("Venue added successfully.");
    }

    private int readPositiveInt(String message) {
        while (true) {
            System.out.print(message);
            int value = readInt();

            if (value > 0) {
                return value;
            }

            System.out.println("Invalid input. Number must be greater than 0.");
        }
    }

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
                System.out.println("Invalid choice.");
            }
        }
    }





}
