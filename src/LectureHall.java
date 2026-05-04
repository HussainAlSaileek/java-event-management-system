public class LectureHall extends Venue{
    private boolean hasSmartBoard;

    public LectureHall(String venueName, int maxCapacity,boolean hasSmartBoard ) {
        super(venueName, maxCapacity);
        this.hasSmartBoard=hasSmartBoard;
    }



    public void sethasSmartBoard(boolean hasSmartBoard) {
        this.hasSmartBoard = hasSmartBoard;
    }

    public boolean gethasSmartBoarde(){
        return this.hasSmartBoard;
    }

    @Override
    public String getVenueType(){
        return "LectureHall" ;
    }

    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println("hasSmartBoard: "+gethasSmartBoarde());
    }
}
