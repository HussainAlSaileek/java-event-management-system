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

    public String getName() {
        return name;
    }


}
