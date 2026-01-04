package service;

import model.Tyre;

public class TestDriveService {

    public boolean performTestDrive(Tyre vehicle) {

        if (vehicle == null) {
            return false;
        }

        return true;
    }
}