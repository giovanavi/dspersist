package com.dell.lead;

/**
 * @author giovana
 * @version 1
 * Classe Quarto
 */

public class Quarto {
    private int numero;
    private Hospede hospede;

    public Quarto(int numero, Hospede hospede){
        this.numero = numero;
        this.hospede = hospede;
    }
    public Quarto(){}


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }
}
