package com.dell.lead;

import javax.swing.*;
import java.util.*;

/**
 * @author giovana
 * @version 1
 */

public class Principal {
    public static void main(String[] args) {
        Hospede hospede = new Hospede();

        cadastrarHospede(hospede);

        JOptionPane.showMessageDialog(null, "Hóspede cadastrado com sucesso");

        Quarto quarto = new Quarto();
        String numero = JOptionPane.showInputDialog("Digite o número do quarto:");
        quarto.setNumero(Integer.parseInt(numero));
        quarto.setHospede(hospede);

        JOptionPane.showMessageDialog(null, "Reserva cadastrado com sucesso");

        Map<Quarto, Hospede> reservas = new HashMap<>();
        reservas.put(quarto, hospede);
        //Acabei usando o HashMap porque ele associa uma chave a um valor, sendo assim ele associa bem um quarto a um
        //hóspede.
        for (Quarto q : reservas.keySet()) {
            Hospede h = q.getHospede();
            System.out.println("Quarto nº"+q.getNumero()+" reservado por "+h.getNome());
        }

    }

    //método usado para cadastrar um hóspede
    public static Hospede cadastrarHospede(Hospede h){
        String nome, idade, cpf;

        String rg = JOptionPane.showInputDialog("Digite seu rg");
        h.setRg(Integer.parseInt(rg));

        while(true){
            try {
                cpf = JOptionPane.showInputDialog("Digite seu CPF");
                h.setCpf(Integer.parseInt(cpf));
                break;
            } catch (NumberFormatException ex) {
                System.err.println("Entrada inválida " + ex.getMessage());
            }
        }

        while(true){
            nome = JOptionPane.showInputDialog("Digite seu nome");
            if (validacaoNome(nome)) {
                h.setNome(nome);
                break;
            }
            System.err.println("O nome inserido é inválido.");
        }

        while(true) {
            try {
                idade = JOptionPane.showInputDialog("Digite sua idade");
                h.setIdade(Integer.parseInt(idade));
                break;
            } catch (NumberFormatException e) {
                System.err.println("O campo idade deve ser um número inteiro. " + e.getMessage());
            }
        }

        String endereco = JOptionPane.showInputDialog("Digite seu endereço");
        h.setEndereço(endereco);

        return h;
    }

    //método usado para validar a String apenas com letras
    public static boolean validacaoNome(String nome){
        String teste = nome.toUpperCase();
        if(!teste.matches("[ A-Z]*")){
            return false;
        }
        return true;
    }
}
