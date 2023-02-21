package com.example.projekt;

import com.example.projekt.entity.Lekarze;
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
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

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
    private Button logowanie_pielegniarka;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button rejestracja;

    @FXML
    private AnchorPane second_form;


    @FXML
    private Button zamknij;

    @FXML
    private Button zminimalizuj;

    @FXML
    private Button cofnij;


    public static int idlekarza;

    private int id = 0;

    public void zamknij() {
        System.exit(0);
    }

    public void zminimalizuj() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    private void handleBottomClick(javafx.event.ActionEvent mouseEvent) throws IOException {

        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(Lekarze.class);

        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        SessionFactory factory = config.buildSessionFactory(builder.build());

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Lekarze where login=:login and haslo=:haslo");
        query.setParameter("login", login.getText());
        query.setParameter("haslo", haslo.getText());


        List list = query.list();

        if (mouseEvent.getSource() == rejestracja) {
            main_form.getScene().getWindow().hide();
            LoadStages("rejestracja.fxml");
        } else if (list.size() == 1) {
            if (id == 1) {
                Query isLekarz = session.createQuery("select id_lekarza from Lekarze where login=:login and haslo=:haslo and isLekarz=1");
                isLekarz.setParameter("login", login.getText());
                isLekarz.setParameter("haslo", haslo.getText());
                List listA = isLekarz.list();
                idlekarza = (int) listA.get(0);
                System.out.println("id lekarza " + listA.get(0));

                if (listA.size() == 1) {
                    main_form.getScene().getWindow().hide();
                    LoadStages("kartaPacjentow.fxml");
                } else {
                    loginalert.setText("logujesz sie na zle konto");
                    hasloalert.setText("logujesz sie na zle konto");
                    loginalert.setVisible(true);
                    hasloalert.setVisible(true);
                }
            } else {
                Query isLekarz = session.createQuery("from Lekarze where login=:login and haslo=:haslo and isLekarz=0");
                isLekarz.setParameter("login", login.getText());
                isLekarz.setParameter("haslo", haslo.getText());
                List listA = isLekarz.list();

                if (listA.size() == 1) {
                    main_form.getScene().getWindow().hide();
                    LoadStages("ekranPielegniarki.fxml");
                } else {
                    loginalert.setText("logujesz sie na zle konto");
                    hasloalert.setText("logujesz sie na zle konto");
                    loginalert.setVisible(true);
                    hasloalert.setVisible(true);
                }
            }

        } else if (mouseEvent.getSource() == logowanie_lekarz) {
            second_form.setVisible(false);
            main_form.setVisible(true);
            id = 1;
        } else if (mouseEvent.getSource() == logowanie_pielegniarka) {
            second_form.setVisible(false);
            main_form.setVisible(true);
            id = 0;
        } else if (mouseEvent.getSource() == cofnij) {
            second_form.setVisible(true);
            main_form.setVisible(false);
        } else {
            loginalert.setVisible(true);
            hasloalert.setVisible(true);
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



