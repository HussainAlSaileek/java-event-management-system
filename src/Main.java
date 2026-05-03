import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        // Managers (data holders)
        VenueManager venueManager = new VenueManager();
        EventManager eventManager = new EventManager(venueManager);
        DepartmentManager departmentManager = new DepartmentManager();

        // Menus (user interaction)
        VenueMenu venueMenu = new VenueMenu(venueManager, scnr);
        EventMenu eventMenu = new EventMenu(eventManager, venueManager, departmentManager, scnr);
        DepartmentMenu departmentMenu = new DepartmentMenu(departmentManager, scnr);

        // Initialize default data
        venueMenu.setVenueList(venueManager);
        departmentMenu.setDepartmentList();

        // Main menu
        MenuSystem menuSystem = new MenuSystem(eventMenu, venueMenu, departmentMenu, scnr);
        menuSystem.start();
    }
}