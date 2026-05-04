import java.time.LocalDateTime;

public abstract class Event {

    // General fields every child from Event class will need
    protected String eventName;
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected Venue venue;
    protected Department sponsorDepartment;
    protected int neededCapacity;
    protected String eventType;


    // General constructor to be called by super by children
    public Event(String eventName, LocalDateTime startDateTime, LocalDateTime endDateTime, Venue venue, Department sponsorDepartment, int neededCapacity, String eventType) {
        this.endDateTime = endDateTime;
        this.eventName = eventName;
        this.venue = venue;
        this.sponsorDepartment = sponsorDepartment;
        this.startDateTime = startDateTime;
        this.neededCapacity = neededCapacity;
        this.eventType = eventType;
    }

    // Getters for every field, unused methods are kept for possible future use
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

    // General print method to be overridden by children
    public void printDetails() {
        System.out.println("Name: " + eventName);
        System.out.println("Type: " + eventType);
        System.out.println("Sponsoring department: " + sponsorDepartment.getDepartmentName());
        System.out.println("Venue: " + venue.getVenueName());
        System.out.println("Start time: " + startDateTime);
        System.out.println("End time: " + endDateTime);
        System.out.println("Needed capacity: " + neededCapacity);
    }
}
