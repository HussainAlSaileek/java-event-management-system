import java.time.LocalDateTime;

public class SportsEvent extends Event {

private String sportName;

    public SportsEvent(String eventName, LocalDateTime startDateTime, LocalDateTime endDateTime, Venue venue, Department sponsorDepartment, int neededCapacity, String eventType, String sportName) {
        super(eventName, startDateTime, endDateTime, venue, sponsorDepartment, neededCapacity, eventType);
        this.sportName = sportName;
    }

    // Getter for possible future use
    public String getSportName() {
        return sportName;
    }

    // Overriding parent method to include new field
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Sports Event Name: " + sportName);
    }
}

