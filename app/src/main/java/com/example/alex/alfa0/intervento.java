package com.example.alex.alfa0;

/**
 * Created by Alex on 10/02/2018.
 */

public class intervento {
    private String id = "";
    private String nome = " ";
    private String cognome = " ";
    private String dataNascita = "";
    private String via = " ";
    private String numero = " ";
    private String citta = " ";
    private String comune = " ";
    private String cap = " ";
    private String data = " ";
    private String chiamata = " ";
    private String codice = " ";
    private String operatore = " ";
    private String attivo;

    public intervento() {

    }

    public intervento(String id, String nome, String cognome, String dataNascita, String via, String numero, String citta, String comune, String cap, String data, String chiamata, String codice, String operatore, String attivo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.via = via;
        this.numero = numero;
        this.citta = citta;
        this.comune = comune;
        this.cap = cap;
        this.data = data;
        this.chiamata = chiamata;
        this.codice = codice;
        this.operatore = operatore;
        this.attivo = attivo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getChiamata() {
        return chiamata;
    }

    public void setChiamata(String chiamata) {
        this.chiamata = chiamata;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getOperatore() {
        return operatore;
    }

    public void setOperatore(String operatore) {
        this.operatore = operatore;
    }

    public String getAttivo() {
        return attivo;
    }

    public void setAttivo(String attivo) {
        this.attivo = attivo;
    }

    @Override
    public String toString() {
        return "intervento{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita='" + dataNascita + '\'' +
                ", via='" + via + '\'' +
                ", numero='" + numero + '\'' +
                ", citta='" + citta + '\'' +
                ", comune='" + comune + '\'' +
                ", cap='" + cap + '\'' +
                ", data='" + data + '\'' +
                ", chiamata='" + chiamata + '\'' +
                ", codice='" + codice + '\'' +
                ", operatore='" + operatore + '\'' +
                ", attivo='" + attivo + '\'' +
                '}';
    }
}

