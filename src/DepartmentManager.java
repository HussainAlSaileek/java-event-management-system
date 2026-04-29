import java.util.ArrayList;

public class DepartmentManager {
    private ArrayList<Department> departments = new ArrayList<>();

    public boolean departmentExists(String name) {
        for (Department department : departments) {
            if (department.getDepartmentName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void addDepartment(Department department) {
        if (departmentExists(department.getDepartmentName())==false) {
            departments.add(department);
            System.out.println("Department added successfully.");
        } else {
            System.out.println("Department already exists.");
        }
    }

    public void viewAllDepartments() {
        if (departments.size()==0) {
            System.out.println("No departments available.");
        } else {
            for (Department department : departments) {
                System.out.println(department.getDepartmentName());
            }
        }
    }

    public Department searchDepartment(String name) {
        for (Department department : departments) {
            if (department.getDepartmentName().equals(name)) {
                return department;
            }
        }
        return null;
    }

    public void showDepartmentDetails(String name) {
        Department department = searchDepartment(name);

        if (department != null) {
            department.printDetails();
        } else {
            System.out.println("Department not found.");
        }
    }
}