package com.dell.lead;

/**
 * @author giovana
 * @version 1
 * Classe Hospede
 */

public class Hospede {
    private long cpf;
    private long rg;
    private String nome;
    private int idade;
    private String endereço;

    public Hospede(){

    }

    public Hospede(long cpf, long rg, String nome, int idade, String endereço) {
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.idade = idade;
        this.endereço = endereço;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getRg() {
        return rg;
    }

    public void setRg(long rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }
}
