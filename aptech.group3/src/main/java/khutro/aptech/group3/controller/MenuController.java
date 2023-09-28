/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khutro.aptech.group3.controller;

import java.io.IOException;
import khutro.aptech.group3.App;

/**
 *
 * @author CLD
 */
public class MenuController {

    public void handleSwitchDashboard() throws IOException {
        App.setRoot("home");
    }

    public void handleSwitchRoom() throws IOException {
        App.setRoot("room");
    }

    public void handleSwitchContact()  throws IOException {
        App.setRoot("contact");
    }

    public void handleLogout()  throws IOException{
        App.setRoot("loginRegister");

    }
}
