import java.time.LocalDateTime;

public class AcademicEvent extends Event {
    private String instructorName;

    public AcademicEvent(String eventName, LocalDateTime startDateTime, LocalDateTime endDateTime, Venue venue, Department sponsorDepartment, int neededCapacity, String eventType) {
        super(eventName, startDateTime, endDateTime, venue, sponsorDepartment, neededCapacity, eventType);
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Instructor: " + instructorName);
    }
}

