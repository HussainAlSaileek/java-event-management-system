public class ConferenceHall extends Venue{
    private boolean hasSoundSystem;

    public ConferenceHall(String venueName, int maxCapacity,boolean hasSoundSystem) {
        super(venueName, maxCapacity);
        this.hasSoundSystem=hasSoundSystem;
    }

    public void sethasSoundSystem(boolean hasSmartBoard) {
        this.hasSoundSystem = hasSmartBoard;
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
        super.printDetails();
        System.out.println("Has sound system: " +gethasSoundSystem());

    }
}
