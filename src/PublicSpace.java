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
        super.printDetails();
    }
}
