package model;

public abstract class GalleryInfo {

    private String galleryName;
    private String address;
    private String phoneNumber;
    private String openingHours;

    public GalleryInfo(String galleryName, String address, String phoneNumber, String openingHours) {
        this.galleryName = galleryName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.openingHours = openingHours;
    }

    public abstract String getEmployeeActivitySummary();

    public String getFormattedInfo() {
        return " Gallery Name: " + galleryName + "\n" +
                "📍 Address: " + address + "\n" +
                "📞 Phone: " + phoneNumber + "\n" +
                "🕒 Working Hours: " + openingHours + "\n";
    }

    //getter setter methods
    public String getGalleryName() {
        return galleryName;
    }
    public void setGalleryName(String galleryName) {
        this.galleryName = galleryName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getOpeningHours() {
        return openingHours;
    }
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }
}