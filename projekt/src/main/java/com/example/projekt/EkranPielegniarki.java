package com.example.projekt;

import com.example.projekt.entity.Pacjenci;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class EkranPielegniarki implements Initializable {

    @FXML
    private TextField adres;

    @FXML
    private TableColumn<Pacjenci, String> adres_column;

    @FXML
    private DatePicker data_ur;

    @FXML
    private TableColumn<Pacjenci, Date> data_ur_column;

    @FXML
    private Button dodaj;

    @FXML
    private Button edytuj;

    @FXML
    private TextField email;

    @FXML
    private TableColumn<Pacjenci, String> email_column;

    @FXML
    private TableColumn<Pacjenci, Integer> id_pacjenta_column;

    @FXML
    private TextField imie;

    @FXML
    private TableColumn<Pacjenci, String> imie_column;

    @FXML
    private TextField nazwisko;

    @FXML
    private TableColumn<Pacjenci, String> nazwisko_column;

    @FXML
    private TextField nr_tel;

    @FXML
    private TableColumn<Pacjenci, String> nr_tel_column;

    @FXML
    private TableView<Pacjenci> pacjenci_tabela;

    @FXML
    private TextField pesel;

    @FXML
    private TableColumn<Pacjenci, String> pesel_column;

    @FXML
    private Button umow;

    @FXML
    private Button usun;

    @FXML
    private Button wyloguj;

    @FXML
    private Button wyczysc;

    @FXML
    private TextField dd;

    @FXML
    private Label alert_PESEL;

    @FXML
    private Label alert_adres;

    @FXML
    private Label alert_data_ur;

    @FXML
    private Label alert_email;

    @FXML
    private Label alert_imie;

    @FXML
    private Label alert_nazwisko;

    @FXML
    private Label alert_tel;


    @FXML
    private AnchorPane main_form;

    private List<Pacjenci> pacjenci;

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }

    public void fetchData() {
        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(Pacjenci.class);

        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        SessionFactory factory = config.buildSessionFactory(builder.build());

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        pacjenci = loadAllData(Pacjenci.class, session);

        transaction.commit();
        session.close();
    }


    public void setData() {
        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(Pacjenci.class);

        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        SessionFactory factory = config.buildSessionFactory(builder.build());

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Pacjenci p = new Pacjenci();

        p.setImie(imie.getText());
        p.setNazwisko(nazwisko.getText());
        p.setPESEL(pesel.getText());
        p.setData_ur(data_ur.getValue());
        p.setAdres(adres.getText());
        p.setNr_telefonu(nr_tel.getText());
        p.setEmail(email.getText());

        if (isEmailOk(email.getText()) && isNazwaOk(imie.getText()) && isNazwaOk(nazwisko.getText()) && isTelOk(nr_tel.getText())
                && isPESELOk(pesel.getText()) && data_ur.getValue() != null && isNazwaOk(adres.getText())) {
            session.persist(p);
            transaction.commit();
            session.close();
        }

        zaktualizuj();
    }

    public void deleteData() {
        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(Pacjenci.class);

        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        SessionFactory factory = config.buildSessionFactory(builder.build());

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Pacjenci p = new Pacjenci();
        p.setId_pacjenta(Integer.parseInt(dd.getText()));
        p.setImie(imie.getText());
        p.setNazwisko(nazwisko.getText());
        p.setPESEL(pesel.getText());
        p.setData_ur(data_ur.getValue());
        p.setAdres(adres.getText());
        p.setNr_telefonu(nr_tel.getText());
        p.setEmail(email.getText());

        if (isEmailOk(email.getText()) && isNazwaOk(imie.getText()) && isNazwaOk(nazwisko.getText()) && isTelOk(nr_tel.getText())
                && isPESELOk(pesel.getText()) && data_ur.getValue() != null && isNazwaOk(adres.getText())) {
            session.delete(p);
            transaction.commit();
            session.close();
        }

        zaktualizuj();
    }

    public void editData() {
        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(Pacjenci.class);

        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        SessionFactory factory = config.buildSessionFactory(builder.build());

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Pacjenci p = new Pacjenci();
        p.setId_pacjenta(Integer.parseInt(dd.getText()));
        p.setImie(imie.getText());
        p.setNazwisko(nazwisko.getText());
        p.setPESEL(pesel.getText());
        p.setData_ur(data_ur.getValue());
        p.setAdres(adres.getText());
        p.setNr_telefonu(nr_tel.getText());
        p.setEmail(email.getText());

        if (isEmailOk(email.getText()) && isNazwaOk(imie.getText()) && isNazwaOk(nazwisko.getText()) && isTelOk(nr_tel.getText())
                && isPESELOk(pesel.getText()) && data_ur.getValue() != null && isNazwaOk(adres.getText())) {
            session.update(p);
            transaction.commit();
            session.close();
        }

        zaktualizuj();
    }

    public void zaktualizuj() {
        fetchData();
        observableList.removeAll(observableList);
        for (Pacjenci temp : pacjenci) {
            observableList.add(temp);
        }
    }

    public void wyczysc() {
        imie.setText("");
        nazwisko.setText("");
        pesel.setText("");
        data_ur.setValue(null);
        adres.setText("");
        nr_tel.setText("");
        email.setText("");
    }

    public void wybrane() {

        Pacjenci pacjentDane = pacjenci_tabela.getSelectionModel().getSelectedItem();
        dd.setText(String.valueOf(pacjentDane.getId_pacjenta()));
        imie.setText(String.valueOf(pacjentDane.getImie()));
        nazwisko.setText(String.valueOf(pacjentDane.getNazwisko()));
        pesel.setText(String.valueOf(pacjentDane.getPESEL()));
        data_ur.setValue(pacjentDane.getData_ur());
        adres.setText(String.valueOf(pacjentDane.getAdres()));
        nr_tel.setText(String.valueOf(pacjentDane.getNr_telefonu()));
        email.setText(String.valueOf(pacjentDane.getEmail()));

    }

    ObservableList<Pacjenci> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_pacjenta_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, Integer>("id_pacjenta"));
        imie_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, String>("imie"));
        nazwisko_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, String>("nazwisko"));
        pesel_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, String>("PESEL"));
        data_ur_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, Date>("data_ur"));
        adres_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, String>("adres"));
        nr_tel_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, String>("nr_telefonu"));
        email_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, String>("email"));

        fetchData();

        for (Pacjenci temp : pacjenci) {
            observableList.add(temp);
        }
        pacjenci_tabela.setItems(observableList);
    }

    @FXML
    private void handleBottomClick(javafx.event.ActionEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() == wyloguj) {
            main_form.getScene().getWindow().hide();
            LoadStages("ekranLogowania.fxml");
        } else if (mouseEvent.getSource() == umow) {
            main_form.getScene().getWindow().hide();
            LoadStages("rejestracjaPacjenta.fxml");
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

    private boolean isEmailOk(String s) {
        return s.matches("\\w{2,24}[a-z]\\d*@{1}\\w{0,24}[a-z].*");
    }

    private boolean isNazwaOk(String s) {
        return s.matches("\\w{2,24}[A-Za-z].*");
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

        if (!isNazwaOk(adres.getText()) || adres.getText().equals("")) {
            alert_adres.setVisible(true);
        } else if (isNazwaOk(adres.getText())) {
            alert_adres.setVisible(false);
        }


        if (!isTelOk(nr_tel.getText()) || nr_tel.getText().equals("")) {
            alert_tel.setVisible(true);
        } else if (isTelOk(nr_tel.getText())) {
            alert_tel.setVisible(false);
        }

        if (!isPESELOk(pesel.getText()) || pesel.getText().equals("")) {
            alert_PESEL.setVisible(true);
        } else if (isPESELOk(pesel.getText())) {
            alert_PESEL.setVisible(false);
        }

        if (data_ur.getValue() == null) {
            alert_data_ur.setVisible(true);
        } else if (data_ur.getValue() != null) {
            alert_data_ur.setVisible(false);
        }
    }
}

