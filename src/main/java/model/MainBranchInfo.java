package model;

public class MainBranchInfo extends GalleryInfo {

    private final int numberOfEmployees;

    public MainBranchInfo(String galleryName, String address, String phoneNumber, String openingHours, int numberOfEmployees) {
        super(galleryName, address, phoneNumber, openingHours);
        this.numberOfEmployees = numberOfEmployees;
    }

    @Override
    public String getEmployeeActivitySummary() {
        return "Central Branch: All " + numberOfEmployees + " employees.";
    }

    @Override
    public String getFormattedInfo() {
        return super.getFormattedInfo() +
                " Branch Type: Central\n" +
                " Employee status: " + getEmployeeActivitySummary();
    }
}
