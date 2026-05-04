import java.time.LocalDateTime;

public class SocialEvent extends Event {

    private String club;

    public SocialEvent(String eventName, LocalDateTime startDateTime, LocalDateTime endDateTime, Venue venue, Department sponsorDepartment, int neededCapacity, String eventType,  String club) {
        super(eventName, startDateTime, endDateTime, venue, sponsorDepartment, neededCapacity, eventType);
        this.club = club;
    }

    // Getter for possible future use
    public String getClub() {
        return club;
    }

    // Overriding parent method to include new field
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Club: " + club);
    }
}

