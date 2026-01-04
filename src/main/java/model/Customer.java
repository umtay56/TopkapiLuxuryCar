package model;

public class Customer extends Person {

    private final String licenseType;
    private final String phoneNumber;
    private final String email;
    private final String tcNo;
    private final String city;
    private final String district;
    private final String fullAddress;

    public Customer(int id, String name, String surname, String phoneNumber, String email, String licenseType, String phoneNumber1, String email1, String tcNo, String city, String district, String fullAddress) {
        super(id, name, surname);
        this.licenseType = licenseType;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.tcNo = tcNo;
        this.city = city;
        this.district = district;
        this.fullAddress = fullAddress;
    }

    //getter methods
    public String getLicenseType() {
        return licenseType;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public String getTcNo() {
        return tcNo;
    }
    public String getCity() {
        return city;
    }
    public String getDistrict() {
        return district;
    }
    public String getFullAddress() {
        return fullAddress;
    }

    @Override
    public String getRole() {
        return "Customer";
    }
}