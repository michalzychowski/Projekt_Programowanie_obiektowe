package com.example.projekt;

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
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class KartaPacjentow implements Initializable {

    @FXML
    private TextField adres;

    @FXML
    private TableColumn<Wizyta, String> adres_column;

    @FXML
    private TextField data;

    @FXML
    private TableColumn<Wizyta, String> data_column;

    @FXML
    private Button edytuj;

    @FXML
    private TextField imie;

    @FXML
    private TableColumn<Wizyta, String> imie_column;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField nazwisko;

    @FXML
    private TableColumn<Wizyta, String> nazwisko_column;

    @FXML
    private TextField objawy;

    @FXML
    private TableColumn<Wizyta, String> objawy_column;

    @FXML
    private TableView<Wizyta> pacjenci_tabela;

    @FXML
    private TextField pesel;

    @FXML
    private TableColumn<Wizyta, String> pesel_column;

    @FXML
    private TextField rozpoznanie;

    @FXML
    private TableColumn<Wizyta, String> rozpoznanie_column;

    @FXML
    private Button wyloguj;

    @FXML
    private Button wystaw_recepte;

    @FXML
    private Button wyczysc;
    @FXML
    private TextField id;

    @FXML
    private TextField id1;

    @FXML
    private TextField leki;

    @FXML
    private TableColumn<Wizyta, String> leki_column;


    private List<Wizyta> pacjenci;

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }

    public void fetchData() {
        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(Wizyta.class);

        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        SessionFactory factory = config.buildSessionFactory(builder.build());

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        EkranLogowania ekranLogowania = new EkranLogowania();
        Query isLekarz = session.createQuery("from Wizyta where id_lekarza=:id_lekarza");
        isLekarz.setParameter("id_lekarza",ekranLogowania.idlekarza );

        pacjenci = isLekarz.list();

        transaction.commit();
        session.close();
    }

    public void editData() {
        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(Wizyta.class);

        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        SessionFactory factory = config.buildSessionFactory(builder.build());

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Wizyta p = new Wizyta();

        p.setId_wizyta(Integer.parseInt(id.getText()));
        p.setId_lekarza(Integer.parseInt(id1.getText()));
        p.setImie(imie.getText());
        p.setNazwisko(nazwisko.getText());
        p.setPESEL(pesel.getText());
        p.setData(LocalDate.parse(data.getText()));
        p.setAdres(adres.getText());
        p.setObjawy(objawy.getText());
        p.setRozpoznanie(rozpoznanie.getText());
        p.setLeki(leki.getText());


        session.update(p);
        transaction.commit();
        session.close();

        zaktualizuj();
    }

    public void wybrane() {
        Wizyta wizytaDane = pacjenci_tabela.getSelectionModel().getSelectedItem();

        id.setText(String.valueOf(wizytaDane.getId_wizyta()));
        id1.setText(String.valueOf(wizytaDane.getId_lekarza()));
        imie.setText(String.valueOf(wizytaDane.getImie()));
        nazwisko.setText(String.valueOf(wizytaDane.getNazwisko()));
        pesel.setText(String.valueOf(wizytaDane.getPESEL()));
        data.setText(String.valueOf(wizytaDane.getData()));
        adres.setText(String.valueOf(wizytaDane.getAdres()));
        objawy.setText(String.valueOf(wizytaDane.getObjawy()));
        rozpoznanie.setText(String.valueOf(wizytaDane.getRozpoznanie()));
        leki.setText(String.valueOf(wizytaDane.getLeki()));
    }

    public void zaktualizuj() {
        fetchData();
        observableList.removeAll(observableList);
        for (Wizyta temp : pacjenci) {
            observableList.add(temp);
        }
    }

    public void wyczysc() {
        imie.setText("");
        nazwisko.setText("");
        pesel.setText("");
        data.setText("");
        adres.setText("");
        objawy.setText("");
        rozpoznanie.setText("");
        leki.setText("");
    }

    ObservableList<Wizyta> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imie_column.setCellValueFactory(new PropertyValueFactory<Wizyta, String>("imie"));
        nazwisko_column.setCellValueFactory(new PropertyValueFactory<Wizyta, String>("nazwisko"));
        pesel_column.setCellValueFactory(new PropertyValueFactory<Wizyta, String>("PESEL"));
        adres_column.setCellValueFactory(new PropertyValueFactory<Wizyta, String>("adres"));
        data_column.setCellValueFactory(new PropertyValueFactory<Wizyta, String>("data"));
        rozpoznanie_column.setCellValueFactory(new PropertyValueFactory<Wizyta, String>("rozpoznanie"));
        objawy_column.setCellValueFactory(new PropertyValueFactory<Wizyta, String>("objawy"));
        leki_column.setCellValueFactory(new PropertyValueFactory<Wizyta, String>("leki"));


        fetchData();

        for (Wizyta temp : pacjenci) {
            observableList.add(temp);
        }
        pacjenci_tabela.setItems(observableList);
    }

    @FXML
    private void handleBottomClick(javafx.event.ActionEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() == wyloguj) {
            main_form.getScene().getWindow().hide();
            LoadStages("ekranLogowania.fxml");
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