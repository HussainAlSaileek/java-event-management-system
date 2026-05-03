import java.time.LocalDateTime;

public class SocialEvent extends Event {

    private String club;

    public SocialEvent(String eventName, LocalDateTime startDateTime, LocalDateTime endDateTime, Venue venue, Department sponsorDepartment, int neededCapacity, String eventType,  String club) {
        super(eventName, startDateTime, endDateTime, venue, sponsorDepartment, neededCapacity, eventType);
        this.club = club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Club: " + club);
    }
}

