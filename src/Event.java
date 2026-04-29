import java.time.LocalDateTime;

public abstract class Event {
    protected String eventName;
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected Venue venue;
    protected Department sponsorDepartment;
    protected int neededCapacity;
    protected final String eventType;



    public Event(String eventName, LocalDateTime startDateTime, LocalDateTime endDateTime, Venue venue, Department sponsorDepartment, int neededCapacity, String eventType) {
        this.endDateTime = endDateTime;
        this.eventName = eventName;
        this.venue = venue;
        this.sponsorDepartment = sponsorDepartment;
        this.startDateTime = startDateTime;
        this.neededCapacity = neededCapacity;
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
    public Venue getVenue() {
        return venue;
    }
    public Department getSponsorDepartment() {
        return sponsorDepartment;
    }
    public int getNeededCapacity() {
        return neededCapacity;
    }
    public String getEventType() {
        return eventType;
    }

    public boolean checkOverlap(Event other) {
        if (this.startDateTime.isBefore(other.endDateTime)
                && this.endDateTime.isAfter(other.startDateTime)) {
        return true;}
        else {return false;}
    }

    /* FIXME: getSponsorDepartmentName() & getVenueName() are not implemented */
    //Fixed//
    public void printDetails() {
        System.out.println("Name: " + eventName);
        System.out.println("Sponsoring department: " + sponsorDepartment.getDepartmentName());
        System.out.println("Venue: " + venue.getVenueName());
        System.out.println("Time: " + startDateTime);
    }
}
