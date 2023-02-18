package com.example.projekt.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pacjenci")
public class Pacjenci {

    @Id
    @Column(name = "id_pacjenta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pacjenta;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "nr_telefonu")
    private String nr_telefonu;

    @Column(name = "adres")
    private String adres;
    @Column(name = "PESEL")
    private String PESEL;

    @Column(name = "email")
    private String email;

    @Column(name = "data_ur")
    private LocalDate data_ur;

    public Pacjenci(int id_pacjenta, String imie, String nazwisko, String nr_telefonu, String adres, String PESEL, String email, LocalDate data_ur) {
        this.id_pacjenta = id_pacjenta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nr_telefonu = nr_telefonu;
        this.adres = adres;
        this.PESEL = PESEL;
        this.email = email;
        this.data_ur = data_ur;
    }

    public Pacjenci() {
    }

    public int getId_pacjenta() {
        return id_pacjenta;
    }

    public void setId_pacjenta(int id_pacjenta) {
        this.id_pacjenta = id_pacjenta;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNr_telefonu() {
        return nr_telefonu;
    }

    public void setNr_telefonu(String nr_telefonu) {
        this.nr_telefonu = nr_telefonu;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getData_ur() {
        return data_ur;
    }

    public void setData_ur(LocalDate data_ur) {
        this.data_ur = data_ur;
    }

    @Override
    public String toString() {
        return "Pacjenci{" +
                "id_pacjenta=" + id_pacjenta +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", nr_telefonu='" + nr_telefonu + '\'' +
                ", adres='" + adres + '\'' +
                ", PESEL='" + PESEL + '\'' +
                ", email='" + email + '\'' +
                ", data_ur=" + data_ur +
                '}';
    }
}
