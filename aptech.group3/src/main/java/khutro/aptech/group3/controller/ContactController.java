package khutro.aptech.group3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ContactController {
    @FXML
    private Hyperlink aptechLink;

    @FXML
    private Hyperlink facebookLink;

    @FXML
    void openAptech(ActionEvent event) {
        openWebpage("https://aptechvietnam.com.vn/");
    }

    @FXML
    void openFacebook(ActionEvent event) {
        openWebpage("https://www.facebook.com/aptechvietnam.com.vn");
    }

    private void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }
    }
}
