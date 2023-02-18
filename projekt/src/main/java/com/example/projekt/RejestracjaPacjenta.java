package com.example.projekt;

import com.example.projekt.entity.Lekarze;
import com.example.projekt.entity.Pacjenci;
import com.example.projekt.entity.Wizyta;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class RejestracjaPacjenta implements Initializable {

    @FXML
    private TextField adres;


    @FXML
    private TableColumn<Pacjenci, String> adres_column;


    @FXML
    private Button anuluj;

    @FXML
    private Button anuluj1;

    @FXML
    private Button cofnij;

    @FXML
    private Button dalej;

    @FXML
    private TableColumn<Pacjenci, Date> data_column;


    @FXML
    private DatePicker data_ur;


    @FXML
    private TextField email;

    @FXML
    private TextField email1;

    @FXML
    private TableColumn<Pacjenci, String> email_column;

    @FXML
    private TableColumn<Lekarze, String> email_column1;

    @FXML
    private TextField id;

    @FXML
    private TextField id1;

    @FXML
    private TableColumn<Pacjenci, Integer> id_column;

    @FXML
    private TableColumn<Lekarze, Integer> id_column1;

    @FXML
    private TextField imie;

    @FXML
    private TextField imie1;

    @FXML
    private TableColumn<Pacjenci, String> imie_column;

    @FXML
    private TableColumn<Lekarze, String> imie_column1;

    @FXML
    private AnchorPane lekarz;

    @FXML
    private TextField nazwisko;

    @FXML
    private TextField nazwisko1;

    @FXML
    private TableColumn<Pacjenci, String> nazwisko_column;

    @FXML
    private TableColumn<Lekarze, String> nazwisko_column1;

    @FXML
    private TextField nr_tel;

    @FXML
    private TextField nr_tel1;

    @FXML
    private AnchorPane pacjent;

    @FXML
    private TextField pesel;

    @FXML
    private TextField pesel1;

    @FXML
    private TableColumn<Pacjenci, String> pesel_column;

    @FXML
    private TableColumn<Lekarze, String> pesel_column1;

    @FXML
    private TableView<Pacjenci> table;

    @FXML
    private TableView<Lekarze> table1;

    @FXML
    private TableColumn<Pacjenci, String> telefon_column;

    @FXML
    private TableColumn<Lekarze, String> telefon_column1;

    @FXML
    private Button zapisz;

    @FXML
    private AnchorPane main_form;

    @FXML
    private DatePicker data_wizyty;



    private List<Pacjenci> pacjenci;
    private List<Lekarze> lekarze;

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
        config.addAnnotatedClass(Lekarze.class);

        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        SessionFactory factory = config.buildSessionFactory(builder.build());

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        pacjenci = loadAllData(Pacjenci.class, session);
        lekarze = loadAllData(Lekarze.class, session);

        transaction.commit();
        session.close();
    }

    public void setData() {
        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(Wizyta.class);

        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        SessionFactory factory = config.buildSessionFactory(builder.build());

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Wizyta w = new Wizyta();

        w.setId_lekarza(Integer.parseInt(id1.getText()));
        w.setImie(imie.getText());
        w.setPESEL(pesel.getText());
        w.setNazwisko(nazwisko.getText());
        w.setAdres(adres.getText());
        w.setData(data_wizyty.getValue());

        session.persist(w);
        transaction.commit();
        session.close();

        zaktualizuj();
    }

    public void wybrane() {

        Pacjenci pacjentDane = table.getSelectionModel().getSelectedItem();

        id.setText(String.valueOf(pacjentDane.getId_pacjenta()));
        imie.setText(String.valueOf(pacjentDane.getImie()));
        nazwisko.setText(String.valueOf(pacjentDane.getNazwisko()));
        pesel.setText(String.valueOf(pacjentDane.getPESEL()));
        data_ur.setValue(pacjentDane.getData_ur());
        adres.setText(String.valueOf(pacjentDane.getAdres()));
        nr_tel.setText(String.valueOf(pacjentDane.getNr_telefonu()));
        email.setText(String.valueOf(pacjentDane.getEmail()));

    }

    public void wybrane1(){

        Lekarze lekarzeDane = table1.getSelectionModel().getSelectedItem();

        id1.setText(String.valueOf(lekarzeDane.getId_lekarza()));
        imie1.setText(String.valueOf(lekarzeDane.getImie()));
        nazwisko1.setText(String.valueOf(lekarzeDane.getNazwisko()));
        pesel1.setText(String.valueOf(lekarzeDane.getPESEL()));
        nr_tel1.setText(String.valueOf(lekarzeDane.getNr_telefonu()));
        email1.setText(String.valueOf(lekarzeDane.getEmail()));
    }

    ObservableList<Pacjenci> observableList = FXCollections.observableArrayList();
    ObservableList<Lekarze> observableList1 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, Integer>("id_pacjenta"));
        imie_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, String>("imie"));
        nazwisko_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, String>("nazwisko"));
        pesel_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, String>("PESEL"));
        data_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, Date>("data_ur"));
        adres_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, String>("adres"));
        telefon_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, String>("nr_telefonu"));
        email_column.setCellValueFactory(new PropertyValueFactory<Pacjenci, String>("email"));

        id_column1.setCellValueFactory(new PropertyValueFactory<Lekarze, Integer>("id_lekarza"));
        imie_column1.setCellValueFactory(new PropertyValueFactory<Lekarze, String>("imie"));
        nazwisko_column1.setCellValueFactory(new PropertyValueFactory<Lekarze, String>("nazwisko"));
        pesel_column1.setCellValueFactory(new PropertyValueFactory<Lekarze, String>("PESEL"));
        telefon_column1.setCellValueFactory(new PropertyValueFactory<Lekarze, String>("nr_telefonu"));
        email_column1.setCellValueFactory(new PropertyValueFactory<Lekarze, String>("email"));

        fetchData();

        for (Pacjenci temp : pacjenci) {
            observableList.add(temp);
        }
        table.setItems(observableList);

       for (Lekarze temp1 : lekarze){
           observableList1.add(temp1);
       }
        table1.setItems(observableList1);
    }

    public void zaktualizuj() {
        fetchData();
        observableList.removeAll(observableList);
        for (Pacjenci temp : pacjenci) {
            observableList.add(temp);
        }
    }
    @FXML
    private void handleBottomClick(javafx.event.ActionEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() == dalej) {
            pacjent.setVisible(false);
            lekarz.setVisible(true);
        }
        else if(mouseEvent.getSource() == cofnij){
            pacjent.setVisible(true);
            lekarz.setVisible(false);
        }
        else if(mouseEvent.getSource() == anuluj || mouseEvent.getSource() == anuluj1){
            main_form.getScene().getWindow().hide();
            LoadStages("ekranPielegniarki.fxml");
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
