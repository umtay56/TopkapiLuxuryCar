package model;

public class Employee extends Person {

    private final boolean salesPermit;
    private final String password;

    public Employee(int id, String name, String surname, boolean salesPermit, String password) {
        super(id, name, surname);
        this.salesPermit = salesPermit;
        this.password = password;
    }

    @Override
    public String getRole() {
        return "Employee";
    }

    public boolean isSalesPermit() {
        return salesPermit;
    }
    public String getPassword() {
        return password;
    }
}