package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.GalleryInfo;
import model.MainBranchInfo;
import model.SubBranchInfo;

public class GalleryInfoController {

    @FXML
    private TextArea galleryInfoTextArea;


    @FXML
    public void initialize() {
        StringBuilder infoBuilder = new StringBuilder();


        GalleryInfo mainBranch = new MainBranchInfo(
                "Topkapi Luxury Gallery - Central Kızılay",
                "Atatürk Boulevard No: 15, Ankara",
                "+90 312 123 45 67",
                "Weekdays: 09:00 - 18:00, Saturday: 10:00 - 16:00",
                15
        );

        infoBuilder.append("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n");
        infoBuilder.append("   🏢  CENTRAL BRANCH   \n");
        infoBuilder.append("▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄\n\n");
        infoBuilder.append(mainBranch.getFormattedInfo()).append("\n");



        GalleryInfo subBranch = new SubBranchInfo(
                "Topkapi Luxury Gallery - İstanbul",
                "Istiklal Steet No: 42, İstanbul",
                "+90 212 987 65 43",
                "Weekdays: 09:30 - 17:30, Saturday: Close",
                "İstanbul"
        );

        infoBuilder.append("▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄\n\n");
        infoBuilder.append("   🏢  ISTANBUL BRANCH   \n");
        infoBuilder.append("▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄\n\n");

        infoBuilder.append(subBranch.getFormattedInfo()).append("\n");

        galleryInfoTextArea.setText(infoBuilder.toString());
    }
}
