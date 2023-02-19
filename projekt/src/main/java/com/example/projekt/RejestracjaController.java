package com.example.projekt;

import com.example.projekt.entity.Lekarze;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class RejestracjaController {

    @FXML
    private AnchorPane anchor;

    @FXML
    private TextField email;

    @FXML
    private TextField haslo;

    @FXML
    private TextField imie;

    @FXML
    private TextField login;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField nazwisko;

    @FXML
    private TextField pesel;

    @FXML
    private AnchorPane second_form;

    @FXML
    private TextField telefon;

    @FXML
    private Button zaloguj;

    @FXML
    private Button zamknij;

    @FXML
    private Button zarejestruj_lekarz;

    @FXML
    private Button zarejestruj_pielegniarka;

    @FXML
    private Button zarejestuj;

    @FXML
    private Button zminimalizuj;

    @FXML
    private AnchorPane third_form;

    @FXML
    private Button cofnij;


    private int id = 0;

    public void rejestr() {
        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(Lekarze.class);

        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        SessionFactory factory = config.buildSessionFactory(builder.build());

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Lekarze x = new Lekarze();

        x.setImie(imie.getText());
        x.setNazwisko(nazwisko.getText());
        x.setPESEL(pesel.getText());
        x.setNr_telefonu(telefon.getText());
        x.setEmail(email.getText());
        x.setLogin(login.getText());
        x.setHaslo(haslo.getText());
        x.setIsLekarz(id);

        session.persist(x);
        transaction.commit();
        session.close();


    }

    public void zamknij() {
        System.exit(0);
    }

    public void zminimalizuj() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void handleBottomClick(javafx.event.ActionEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() == zaloguj) {
            main_form.getScene().getWindow().hide();
            LoadStages("ekranLogowania.fxml");
        } else if (mouseEvent.getSource() == zarejestruj_lekarz) {
            third_form.setVisible(false);
            second_form.setVisible(true);
            id = 1;
        } else if (mouseEvent.getSource() == zarejestruj_pielegniarka) {
            third_form.setVisible(false);
            second_form.setVisible(true);
            id = 0;
        }
        else if (mouseEvent.getSource() == cofnij) {
            third_form.setVisible(true);
            second_form.setVisible(false);
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

    @FXML
    void walidacja(MouseEvent event) {

    }


}
