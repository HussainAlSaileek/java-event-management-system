import java.util.Scanner;
public class VenueMenu {


    private final Scanner scnr;
    private final VenueManager venueManager;

    public VenueMenu(VenueManager venueManager, Scanner scnr) {
        this.venueManager = venueManager;
        this.scnr = scnr;
    }


    public void setVenueList() {
        Venue v1=new SportsArea("feild 1",90,"Football");
        Venue v2=new LectureHall("room:1001",120,true);
        Venue v3=new ConferenceHall("Hall A",70,true);
        Venue v4=new PublicSpace("the stadium",750);
        Venue v5=new LectureHall("room:500",45,false);
        venueManager.addVenue(v1);
        venueManager.addVenue(v2);
        venueManager.addVenue(v3);
        venueManager.addVenue(v4);
        venueManager.addVenue(v5);

    }

    private int readInt() {
        int input = scnr.nextInt();
        scnr.nextLine();
        return input;
    }


    public void start(){
        while (true){
            System.out.println("Venue Menu (Select From 1-5)");

            System.out.println("1. View all types of venues");
            System.out.println("2. View all venues ");
            System.out.println("3. Search for venue");
            System.out.println("4. See venue details");
            System.out.println("5. back");

            int choice = readInt();

            if (choice == 1){
                venueManager.viewAllTypesOfVenues();
            }

            else if (choice == 2){
                venueManager.viewAllVenues();
            }

            else if (choice == 3){
                System.out.println("Enter venue name:");
                String venueName = scnr.nextLine();

                Venue venue = venueManager.searchVenue(venueName);

                if (venue == null) {
                    System.out.println("Venue not found");
                } else {
                    System.out.println("Venue "+ venue.getVenueName()+" was found");
                }
            }
            else if (choice == 4){
                System.out.println("Enter venue name:");
                String name = scnr.nextLine();
                venueManager.showVenueDetails(name);
            }

            else if (choice == 5){
                return;
            }

            else {
                System.out.println("Invalid choice");
            }



        }

    }





}
