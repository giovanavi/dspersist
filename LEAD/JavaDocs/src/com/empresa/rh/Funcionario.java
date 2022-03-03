package com.empresa.rh;
/**
 * Representa os dados de um usuário na empresa,
 * @author Giovana - Empresa.com
 */
public class Funcionario {
    private String nome;
    private int[] faltasMes;

    /**
     *
     * @param nome nome do funcionario
     */
    public Funcionario(String nome){
        this.nome = nome;
        faltasMes = new int[12];
    }

    /**
     * Fornece a quantidade de faltas do funcionário em um determinado mês.
     * @param indiceMes índice do mês no array de faltas
     * @return a quantidade de faltas do funcionário no mês indicado.
     */
    public int qtdFaltasNoMes(int indiceMes){
        return faltasMes[indiceMes];
    }
}
