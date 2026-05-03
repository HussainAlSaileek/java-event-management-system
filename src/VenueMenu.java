public class VenueMenu {

    VenueManager venueList = new VenueManager();

    public void setVenueList(VenueManager venueList) {
        Venue v1=new SportsArea("feild 1",85,"Football");
        Venue v2=new LectureHall("room:1001",120,true);
        Venue v3=new ConferenceHall("Hall A",90,true);
        Venue v4=new PublicSpace("feild 1",450);
        venueList.addVenue(v1);
        venueList.addVenue(v2);
        venueList.addVenue(v3);
        venueList.addVenue(v4);
    }
}
