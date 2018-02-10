package com.example.alex.alfa0;

/**
 * Created by Alex on 10/02/2018.
 */

public class intervento {
    private String nome = " ";
    private String cognome = " ";
    private String via = " ";
    private String numero = " ";
    private String citta = " ";
    private String comune = " ";
    private String cap = " ";
    private String data = " ";
    private String chiamata = " ";
    private String codice = " ";
    private String operatore = " ";

    public intervento(String nome, String cognome, String via, String numero, String citta, String chiamata, String codice){
        this.nome = nome;
        this.cognome = cognome;
        this.via = via;
        this.numero = numero;
        this.citta = citta;
        this.chiamata = chiamata;
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getVia() {
        return via;
    }

    public String getNumero() {
        return numero;
    }

    public String getCitta() {
        return citta;
    }

    public String getComune() {
        return comune;
    }

    public String getCap() {
        return cap;
    }

    public String getData() {
        return data;
    }

    public String getChiamata() {
        return chiamata;
    }

    public String getCodice() {
        return codice;
    }

    public String getOperatore() {
        return operatore;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setChiamata(String chiamata) {
        this.chiamata = chiamata;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public void setOperatore(String operatore) {
        this.operatore = operatore;
    }
}
