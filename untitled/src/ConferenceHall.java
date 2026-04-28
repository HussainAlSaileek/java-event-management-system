public class ConferenceHall extends Venue{
    private boolean hasSoundSystem;

    public ConferenceHall(String venueName, int maxCapacity,boolean hasSoundSystem) {
        super(venueName, maxCapacity);
        this.hasSoundSystem=hasSoundSystem;
    }

    public void sethasSoundSystem(boolean hasSmartBoard) {
        this.hasSoundSystem = hasSoundSystem;
    }

    public boolean gethasSoundSystem(){
        return this.hasSoundSystem;
    }

    @Override
    public String getVenueType(){
        return "ConferenceHall" ;
    }

    @Override
    public void printDetails(){
        System.out.println("Venue name: " + getVenueName());
        System.out.println("Venue type: " + getVenueType());
        System.out.println("Max capacity: " + getMaxCapacity());
        System.out.println("hasSmartBoard: " +gethasSoundSystem());

    }
}
