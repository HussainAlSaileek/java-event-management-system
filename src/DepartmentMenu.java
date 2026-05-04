import java.util.Scanner;

public class DepartmentMenu {

    private final DepartmentManager departmentManager;
    private final Scanner scnr;

    public DepartmentMenu(DepartmentManager departmentManager, Scanner scnr) {
        this.departmentManager = departmentManager;
        this.scnr = scnr;
    }
    //make default departments and the responsible people for them //
    public void setDepartmentList() {
        ResponsiblePerson person1 = new ResponsiblePerson("Dr.Shinwari", "shinwari@university.edu");
        ResponsiblePerson person2 = new ResponsiblePerson("Dr.Ahmed", "ahmed@university.edu");
        ResponsiblePerson person3 = new ResponsiblePerson("Dr.Ali", "ali@university.edu");
        ResponsiblePerson person4 = new ResponsiblePerson("Dr.Salman", "salman@university.edu");
        ResponsiblePerson person5 = new ResponsiblePerson("Dr.Mohammed", "Mohammed@university.edu");
        ResponsiblePerson person6 = new ResponsiblePerson("Dr.Ali Alibeid", "aalibeid@university.edu");

        departmentManager.addDepartment(new Department("Coe", person1));
        departmentManager.addDepartment(new Department("ICS", person2));
        departmentManager.addDepartment(new Department("Math", person3));
        departmentManager.addDepartment(new Department("Physics", person4));
        departmentManager.addDepartment(new Department("SWE", person5));
        departmentManager.addDepartment(new Department("Physical Education", person6));
    }

    public void start() {
        while (true) {
            System.out.println("Department Menu (Select From 1-4)");
            System.out.println("1. View all departments");
            System.out.println("2. Search department");
            System.out.println("3. Show department details");
            System.out.println("4. Back");

            int choice = readInt();

            if (choice == 1) {
                departmentManager.viewAllDepartments();
            }

            else if (choice == 2) {
                System.out.println("Enter department name: ");
                String name = scnr.nextLine();

                Department department = departmentManager.searchDepartment(name);

                if (department == null) {
                    System.out.println("Department not found");
                } else {
                    System.out.println("Department " + department.getDepartmentName()+" was found");
                }
            }

            else if (choice == 3) {
                System.out.println("Enter department name:");
                String name = scnr.nextLine();
                departmentManager.showDepartmentDetails(name);
            }

            else if (choice == 4) {
                return;
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