import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        // Managers (data holders)
        VenueManager venueManager = new VenueManager();
        EventManager eventManager = new EventManager(venueManager);
        DepartmentManager departmentManager = new DepartmentManager();

        // Menus (to interact with the user)
        VenueMenu venueMenu = new VenueMenu(venueManager, scnr);
        EventMenu eventMenu = new EventMenu(eventManager, venueManager, departmentManager, scnr);
        DepartmentMenu departmentMenu = new DepartmentMenu(departmentManager, scnr);

        // create default data
        venueMenu.setVenueList();
        departmentMenu.setDepartmentList();

        // Main menu
        MenuSystem menuSystem = new MenuSystem(eventMenu, venueMenu, departmentMenu, scnr);
        menuSystem.start();
    }
}