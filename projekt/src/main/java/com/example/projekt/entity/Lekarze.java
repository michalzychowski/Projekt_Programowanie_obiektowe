package com.example.projekt.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lekarze")
public class Lekarze {

    @Id
    @Column(name = "id_lekarza")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_lekarza;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "nr_telefonu")
    private String nr_telefonu;

    @Column(name = "email")
    private String email;
    @Column(name = "PESEL")
    private String PESEL;

    @Column(name = "login")
    private String login;

    @Column(name = "haslo")
    private String haslo;

    @Column(name = "isLekarz")
    private String isLekarz;

    public Lekarze(int id_lekarza, String imie, String nazwisko, String nr_telefonu, String email, String PESEL, String login, String haslo, String isLekarz) {
        this.id_lekarza = id_lekarza;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nr_telefonu = nr_telefonu;
        this.email = email;
        this.PESEL = PESEL;
        this.login = login;
        this.haslo = haslo;
        this.isLekarz = isLekarz;
    }

    public Lekarze() {
    }

    public int getId_lekarza() {
        return id_lekarza;
    }

    public void setId_lekarza(int id_lekarza) {
        this.id_lekarza = id_lekarza;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getIsLekarz() {
        return isLekarz;
    }

    public void setIsLekarz(String isLekarz) {
        this.isLekarz = isLekarz;
    }

    @Override
    public String toString() {
        return "Lekarze{" +
                "id_lekarza=" + id_lekarza +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", nr_telefonu='" + nr_telefonu + '\'' +
                ", email='" + email + '\'' +
                ", PESEL='" + PESEL + '\'' +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", isLekarz='" + isLekarz + '\'' +
                '}';
    }
}
