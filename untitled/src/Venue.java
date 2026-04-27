public abstract class Venue {
    private String venueName;
    private int maxCapacity;

    public Venue(String venueName, int maxCapacity) {
        this.venueName = venueName;
        this.maxCapacity = maxCapacity;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    // Abstract methods
    public abstract void printDetails();
    public abstract String getVenueType();
}
