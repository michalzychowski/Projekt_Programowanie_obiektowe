package com.example.projekt;

import com.example.projekt.entity.Lekarze;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class RejestracjaLekarz {

    @FXML
    private TextField email;

    @FXML
    private TextField haslo;

    @FXML
    private TextField imie;

    @FXML
    private TextField login;

    @FXML
    private TextField nazwisko;

    @FXML
    private TextField pesel;

    @FXML
    private TextField telefon;

    @FXML
    private Button zarejestruj;

    private int idLek = 0;

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



        session.persist(x);
        transaction.commit();
        session.close();


    }
}
