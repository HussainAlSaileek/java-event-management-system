public class PublicSpace extends Venue{

    public PublicSpace(String venueName, int maxCapacity) {
        super(venueName, maxCapacity);
    }

    @Override
    public String getVenueType(){
        return "PublicSpace" ;
    }

    @Override
    public void printDetails(){
        System.out.println("Venue name: " + getVenueName());
        System.out.println("Venue type: " + getVenueType());
        System.out.println("Max capacity: " + getMaxCapacity());
    }
}
