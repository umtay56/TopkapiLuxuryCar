package model;

public class Manager extends Employee {

    private final int adminDiscount;

    public Manager(int id, String name, String surname, boolean salesPermit, String password, int adminDiscount) {
        super(id, name, surname, salesPermit, password);
        this.adminDiscount = adminDiscount;
    }

    @Override
    public String getRole() {
        return "Manager";
    }
    public int getAdminDiscount() {
        return adminDiscount;
    }
}