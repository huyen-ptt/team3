/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khutro.aptech.group3.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import khutro.aptech.group3.App;

/**
 * FXML Controller class
 *
 * @author CLD
 */
public class HeaderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void handleswitchDetail() throws IOException {
        App.setRoot("detail");
    }

    public void handleswitchHome() throws IOException {
        App.setRoot("dashboard");
    }

    public void handleswitchContact() throws IOException {
        App.setRoot("contact");
    }

    public void handleswitchLogin() throws IOException {
        App.setRoot("loginRegister");
    }
        public void handleswitchAddRoom() throws IOException {
        App.setRoot("add_room");
    }

}
