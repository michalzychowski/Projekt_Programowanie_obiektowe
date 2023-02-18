package com.example.projekt.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "wizyta")
public class Wizyta {

    @Id
    @Column(name = "id_wizyta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_wizyta;

    @Column(name = "id_lekarza")
    private int id_lekarza;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "adres")
    private String adres;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "rozpoznanie")
    private String rozpoznanie;

    @Column(name = "objawy")
    private String objawy;

    @Column(name = "PESEL")
    private String PESEL;

    @Column(name = "leki")
    private String leki;

    public Wizyta(int id_wizyta, int id_lekarza, String imie, String nazwisko, String adres, LocalDate data, String rozpoznanie, String objawy, String PESEL, String leki) {
        this.id_wizyta = id_wizyta;
        this.id_lekarza = id_lekarza;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
        this.data = data;
        this.rozpoznanie = rozpoznanie;
        this.objawy = objawy;
        this.PESEL = PESEL;
        this.leki = leki;
    }

    public Wizyta() {
    }

    public int getId_wizyta() {
        return id_wizyta;
    }

    public void setId_wizyta(int id_wizyta) {
        this.id_wizyta = id_wizyta;
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

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getRozpoznanie() {
        return rozpoznanie;
    }

    public void setRozpoznanie(String rozpoznanie) {
        this.rozpoznanie = rozpoznanie;
    }

    public String getObjawy() {
        return objawy;
    }

    public void setObjawy(String objawy) {
        this.objawy = objawy;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getLeki() {
        return leki;
    }

    public void setLeki(String leki) {
        this.leki = leki;
    }

    @Override
    public String toString() {
        return "Wizyta{" +
                "id_wizyta=" + id_wizyta +
                ", id_lekarza=" + id_lekarza +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", adres='" + adres + '\'' +
                ", data=" + data +
                ", rozpoznanie='" + rozpoznanie + '\'' +
                ", objawy='" + objawy + '\'' +
                ", PESEL='" + PESEL + '\'' +
                ", leki='" + leki + '\'' +
                '}';
    }
}
