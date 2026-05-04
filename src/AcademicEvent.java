import java.time.LocalDateTime;

public class AcademicEvent extends Event {
    private final String instructorName;

    public AcademicEvent(String eventName, LocalDateTime startDateTime, LocalDateTime endDateTime, Venue venue, Department sponsorDepartment, int neededCapacity, String eventType, String instructorName) {
        super(eventName, startDateTime, endDateTime, venue, sponsorDepartment, neededCapacity, eventType);
        this.instructorName = instructorName;
    }

    // Getter for possible future use
    public String getInstructorName() {
        return instructorName;
    }

    // Overriding parent method to include new field
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Instructor: " + instructorName);
    }
}

