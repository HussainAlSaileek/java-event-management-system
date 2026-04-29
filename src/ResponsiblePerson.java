public class ResponsiblePerson {
    private String name;
    private String email;

    public ResponsiblePerson(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void printDetails() {
        System.out.println("Responsible Person: " + getName());
        System.out.println("Email: " + getEmail());
    }
}