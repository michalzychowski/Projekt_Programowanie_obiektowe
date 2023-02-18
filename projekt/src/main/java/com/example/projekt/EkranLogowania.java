package com.example.projekt;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class EkranLogowania {

    @FXML
    private PasswordField haslo;

    @FXML
    private Label hasloalert;

    @FXML
    private TextField login;

    @FXML
    private Label loginalert;

    @FXML
    private Button logowanie_lekarz;

    @FXML
    private Button logowanie_pilegniarka;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button rejestracja;

    @FXML
    private AnchorPane second_form;

    @FXML
    private Button zaloguj;

    @FXML
    private Button zamknij;

    @FXML
    private Button zminimalizuj;

    private int id = 0;

    public void zamknij(){
        System.exit(0);
    }

    public void zminimalizuj(){
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    private void handleBottomClick(javafx.event.ActionEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() == rejestracja) {
            main_form.getScene().getWindow().hide();
            LoadStages("rejestracja.fxml");
        }
        else if (mouseEvent.getSource() == zaloguj){
            main_form.getScene().getWindow().hide();
            if (id==1){
                LoadStages("kartaPacjentow.fxml");
            }
            else {
                LoadStages("ekranPielegniarki.fxml");
            }
        }
        else if (mouseEvent.getSource() == logowanie_lekarz){
            second_form.setVisible(false);
            main_form.setVisible(true);
            id = 1;
        }
        else if (mouseEvent.getSource() == logowanie_pilegniarka){
            second_form.setVisible(false);
            main_form.setVisible(true);
            id = 0;
        }
    }
    private void LoadStages(String fxml) {
        try {
            FXMLLoader x = new FXMLLoader(getClass().getResource(fxml));
            Stage stage = new Stage();
            Parent root = x.load();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

