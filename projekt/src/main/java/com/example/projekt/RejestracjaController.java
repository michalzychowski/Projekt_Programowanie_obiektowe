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
    private PasswordField haslo;

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

    @FXML
    private Label alert_email;

    @FXML
    private Label alert_haslo;

    @FXML
    private Label alert_imie;

    @FXML
    private Label alert_login;

    @FXML
    private Label alert_nazwisko;

    @FXML
    private Label alert_pesel;

    @FXML
    private Label alert_tel;


    private int id = 0;

    public void rejestr() {
        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(Lekarze.class);

        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        SessionFactory factory = config.buildSessionFactory(builder.build());

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Lekarze l = new Lekarze();

        l.setImie(imie.getText());
        l.setNazwisko(nazwisko.getText());
        l.setPESEL(pesel.getText());
        l.setNr_telefonu(telefon.getText());
        l.setEmail(email.getText());
        l.setLogin(login.getText());
        l.setHaslo(haslo.getText());
        l.setIsLekarz(id);

        if (isEmailOk(email.getText()) && isNazwaOk(login.getText()) && isHasloOk(haslo.getText())
                && isNazwaOk(imie.getText()) && isNazwaOk(nazwisko.getText()) && isTelOk(telefon.getText())
                && isPESELOk(pesel.getText())) {
            session.persist(l);
            transaction.commit();
            session.close();
        }


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
        } else if (mouseEvent.getSource() == cofnij) {
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

    public void wyczysc() {
        imie.setText("");
        nazwisko.setText("");
        pesel.setText("");
        telefon.setText("");
        email.setText("");
        login.setText("");
        haslo.setText("");
    }


    private boolean isEmailOk(String s) {
        return s.matches("\\w{2,24}[a-z]\\d*@{1}\\w{0,24}[a-z].*");
    }

    private boolean isNazwaOk(String s) {
        return s.matches("\\w{2,24}[A-Za-z].*");
    }

    private boolean isHasloOk(String s) {
        return s.matches("(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    private boolean isTelOk(String s) {
        return s.matches("^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{3}$");
    }

    private boolean isPESELOk(String s) {
        return s.matches("^\\d{11}$");
    }

    @FXML
    void walidacja(MouseEvent event) {
        if (!isEmailOk(email.getText()) || email.getText().equals("")) {
            alert_email.setVisible(true);
        } else if (isEmailOk(email.getText())) {
            alert_email.setVisible(false);
        }

        if (!isNazwaOk(login.getText()) || login.getText().equals("")) {
            alert_login.setVisible(true);
        } else if (isNazwaOk(login.getText())) {
            alert_login.setVisible(false);
        }

        if (!isNazwaOk(imie.getText()) || imie.getText().equals("")) {
            alert_imie.setVisible(true);
        } else if (isNazwaOk(imie.getText())) {
            alert_imie.setVisible(false);
        }

        if (!isNazwaOk(nazwisko.getText()) || nazwisko.getText().equals("")) {
            alert_nazwisko.setVisible(true);
        } else if (isNazwaOk(nazwisko.getText())) {
            alert_nazwisko.setVisible(false);
        }

        if (!isHasloOk(haslo.getText()) || haslo.getText().equals("")) {
            alert_haslo.setVisible(true);
        } else if (isHasloOk(haslo.getText())) {
            alert_haslo.setVisible(false);
        }

        if (!isTelOk(telefon.getText()) || telefon.getText().equals("")) {
            alert_tel.setVisible(true);
        } else if (isTelOk(telefon.getText())) {
            alert_tel.setVisible(false);
        }

        if (!isPESELOk(pesel.getText()) || pesel.getText().equals("")) {
            alert_pesel.setVisible(true);
        } else if (isPESELOk(pesel.getText())) {
            alert_pesel.setVisible(false);
        }
    }


}
