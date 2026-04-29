public class Department {
    private String name;
    private ResponsiblePerson responsiblePerson;

    public Department(String name,ResponsiblePerson responsiblePerson){
        this.name=name;
        this.responsiblePerson=responsiblePerson;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return name;
    }

    public void printDetails(){
        System.out.println("Department name: "+getDepartmentName());
        System.out.println("responsible Person: "+responsiblePerson.getName());
    }


}
