package service;

import data.Database;
import model.Employee;
import model.Person;

public class AuthService {
    public Person login(String username, String password) {

        for (Person person : Database.people) {

            if (person.getName().equalsIgnoreCase(username)) {

                if (person instanceof Employee) {
                    Employee emp = (Employee) person;

                    if (emp.getPassword().equals(password)) {
                        return person;
                    }
                }
            }
        }
        return null;
    }
}