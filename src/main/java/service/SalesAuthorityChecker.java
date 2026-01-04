package service;

import model.Employee;
import model.Manager;
import model.Person;

public class SalesAuthorityChecker {

    public boolean hasPermission(Person person) {

        if (person instanceof Manager) {
            return true;
        }

        if (person instanceof Employee) {
            return ((Employee) person).isSalesPermit();
        }

        return false;
    }
}