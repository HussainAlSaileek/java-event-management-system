public class DepartmentMenu {

    DepartmentManager depatrmentList= new DepartmentManager();


    private ResponsiblePerson person1= new ResponsiblePerson("Dr.Ahmed","ahmed@university.edu");
    private ResponsiblePerson person2= new ResponsiblePerson("Dr.Salem","salem@university.edu");
    private ResponsiblePerson person3= new ResponsiblePerson("Dr.Ali","ali@university.edu");
    private ResponsiblePerson person4= new ResponsiblePerson("Dr.Salman","salman@university.edu");


    public void setDepartmentList(VenueManager venueList) {
        Department d1=new Department("Math",person1);
        Department d2=new Department("Physics",person2);
        Department d3=new Department("Coe",person3);
        Department d4=new Department("SWE",person4);

        depatrmentList.addDepartment(d1);
        depatrmentList.addDepartment(d2);
        depatrmentList.addDepartment(d3);
        depatrmentList.addDepartment(d4);

    }
}
