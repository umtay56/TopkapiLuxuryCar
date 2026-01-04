package model;

public class SubBranchInfo extends GalleryInfo {

    private final String city;

    public SubBranchInfo(String galleryName, String address, String phoneNumber, String openingHours, String city) {
        super(galleryName, address, phoneNumber, openingHours);
        this.city = city;
    }

    @Override
    public String getEmployeeActivitySummary() {
        return city + " Branch: 3 sales consultants, 1 technical staff at the entrance.";
    }

    @Override
    public String getFormattedInfo() {
        return super.getFormattedInfo() +
                " Branch Type: Side Branch (" + city + ")\n" +
                " Employee Status: " + getEmployeeActivitySummary();
    }
}
