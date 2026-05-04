import java.util.Scanner;

public class MenuSystem {

    private final EventMenu eventMenu;
    private final VenueMenu venueMenu;
    private final DepartmentMenu departmentMenu;
    private final Scanner scnr;

    public MenuSystem(EventMenu eventMenu, VenueMenu venueMenu, DepartmentMenu departmentMenu, Scanner scnr) {
        this.eventMenu = eventMenu;
        this.venueMenu = venueMenu;
        this.departmentMenu = departmentMenu;
        this.scnr = scnr;
    }

    public void start() {
        while (true) {
            System.out.println("\n==============================");
            System.out.println("        MAIN MENU");
            System.out.println("==============================");
            System.out.println("1. Event Menu");
            System.out.println("2. Venue Menu");
            System.out.println("3. Department Menu");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = readInt();

            if (choice == 1) {
                eventMenu.start();
            }

            else if (choice == 2) {
                venueMenu.start();
            }

            else if (choice == 3) {
                departmentMenu.start();
            }

            else if (choice == 4) {
                System.out.println("Finish!");
                return;
            }

            else {
                System.out.println("Invalid choice");
            }
        }
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scnr.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}