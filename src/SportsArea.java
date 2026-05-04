public class SportsArea extends Venue{
    private String sportType;

    public SportsArea(String venueName, int maxCapacity,String sportType) {
        super(venueName, maxCapacity);
        this.sportType=sportType;
    }


    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public String getSportType(){
        return this.sportType;
    }


    @Override
    public String getVenueType(){
        return "SportsArea" ;
    }
    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("Sport type: " + getSportType());
    }



}
