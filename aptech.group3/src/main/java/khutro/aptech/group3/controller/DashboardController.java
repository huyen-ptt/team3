/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khutro.aptech.group3.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import khutro.aptech.group3.App;

/**
 *
 * @author CLD
 */
public class DashboardController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void handelswitchDetail() throws IOException {
        App.setRoot("detail");
    }
    
}
