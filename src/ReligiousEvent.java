import java.time.LocalDateTime;

public class ReligiousEvent extends Event {

    private String speakerName;

    public ReligiousEvent(String eventName, LocalDateTime startDateTime, LocalDateTime endDateTime, Venue venue, Department sponsorDepartment, int neededCapacity, String eventType, String speakerName) {
        super(eventName, startDateTime, endDateTime, venue, sponsorDepartment, neededCapacity, eventType);
        this.speakerName = speakerName;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Speaker Name: " + speakerName);
    }
}
