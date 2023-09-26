/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khutro.aptech.group3.controller;

/**
 * @author CLD
 */


import java.net.URL;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;

import khutro.aptech.group3.App;
import khutro.aptech.group3.utils.AlertMessage;

public class LoginRegisterController implements Initializable {

    @FXML
    private Button login_btn;

    @FXML
    private Button login_create;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password;

    @FXML
    private CheckBox login_selectShowPassword;

    @FXML
    private TextField login_showPassword;

    @FXML
    private TextField login_username;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button signup_btn;

    @FXML
    private PasswordField signup_cPassword;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private Button signup_login;

    @FXML
    private TextField signup_name;

    @FXML
    private PasswordField signup_password;

    @FXML
    private TextField signup_phone;

    @FXML
    private TextField signup_username;

    private Connection connect; // Correct variable name

    private PreparedStatement prepare;
    private ResultSet result;

    // CONNECT DATABASE
    public Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/khutro", "root", "");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // LOGIN
    public void login() {
        AlertMessage alert = new AlertMessage();

        // CHECK EMPTY FIELDS
        if (login_username.getText().isEmpty() || login_password.getText().isEmpty()) {
            alert.errorMessage("Incorrect username or password");
        } else {
            String selectData = "SELECT * FROM user WHERE " + "username = ? and password = ?";

            connect = connectDB();

            if (login_selectShowPassword.isSelected()) {
                login_password.setText(login_showPassword.getText());
            } else {
                login_showPassword.setText(login_password.getText());
            }

            try {
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());

                result = prepare.executeQuery();

                //ALL DATA CORRECT -> GO TO DASHBOARD FORM
                if (result.next()) {
//                    alert.successMessage("You've signed up");

                    App.setRoot("dashboard");
                    //HIDE WINDOW OF LOGIN FORM
//                    login_btn.getScene().getWindow().hide();

                    //ERROR MESSAGE WILL APPEAR
                } else {
                    alert.errorMessage("Incorrect username or password");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    //SHOW PASSWORD
    public void showPassword() {

        if (login_selectShowPassword.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            login_password.setVisible(true);
        }
    }

    // REGISTER
    public void register() {
        AlertMessage alert = new AlertMessage();

        // CHECK EMPTY FIELDS
        if (signup_name.getText().isEmpty()
                || signup_phone.getText().isEmpty()
                || signup_username.getText().isEmpty()
                || signup_password.getText().isEmpty()
                || signup_cPassword.getText().isEmpty()) {
            alert.errorMessage("All fields are necessary to be filled");
        } else if (!signup_password.getText().equals(signup_cPassword.getText())) {
            alert.errorMessage("Password doesn't match");
        } else if (signup_password.getText().length() < 8) {
            alert.errorMessage("Invalid password, at least 8 characters needed");
        } else {
            String checkUsername = "SELECT * FROM user WHERE username = ?";
            Connection connect = connectDB();

            try {
                prepare = connect.prepareStatement(checkUsername);
                prepare.setString(1, signup_username.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(signup_username.getText() + " is already taken");
                } else {
                    String insertData = "INSERT INTO user "
                            + "(name, phone, username, password, created_time) "
                            + "VALUES(?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, signup_name.getText());
                    prepare.setString(2, signup_phone.getText());
                    prepare.setString(3, signup_username.getText());
                    prepare.setString(4, signup_password.getText());

                    Date date = new Date(0);
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(5, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Registered successfully");

                    registerClearFields();

                    signup_form.setVisible(false);
                    login_form.setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    //CLEAR ALL FIELDS OF REGISTER FORM
    public void registerClearFields() {
        signup_name.setText("");
        signup_phone.setText("");
        signup_username.setText("");
        signup_password.setText("");
        signup_cPassword.setText("");
    }

    public void switchForm(ActionEvent event) {
        //REGISTER FORM WILL BE VISIBLE
        if (event.getSource() == signup_login) {
            signup_form.setVisible(false);
            login_form.setVisible(true);
        } //LOGIN FORM WILL BE VISIBLE
        else if (event.getSource() == login_create) {
            signup_form.setVisible(true);
            login_form.setVisible(false);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }
}
