package model;

public abstract class Person {
    private final long id;
    private final String name;
    private final String surname;

    //constructor class
    public Person(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    //getter setters
    public abstract String getRole();
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getFullName() { return name + " " + surname; }

    @Override
    public String toString() {
        return getRole() + ": " + getFullName();
    }
}