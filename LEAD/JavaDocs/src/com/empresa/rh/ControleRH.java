package com.empresa.rh;
/**
 *Esta classe é responsável por gerenciar as atividades
 *do setor de RH (Recursos Humanos) como verificação de
 *disponibilidade de funcionário, cálculo de faltas,
 *cálculo de salário para o mês base etc.
 *
 * @author Giovana – Empresa.com
 *
 */
public class ControleRH {
    private double salarioBase;
    private int quantidadeFuncionarios;


    /**
     *Fornece a quantidade de funcionários contratados atualmente.
     *@return quantidade de funcionários da empresa.
     */
    public int getQuantidadeFuncionarios() {
        return quantidadeFuncionarios;
    }

    /**
     * Calcula a quantidade de faltas acumuladas do funcionário durante o ano.
     * Para obter a quantidade de faltas de cada mês, o método
     * {@link com.empresa.rh.Funcionario#qtdFaltasNoMes(int)} é utilizado.
     * @param f funcionario
     * @return a quantidade de faltas acumuladas do funcionário em 12 meses.
     */
    public int faltasAcumuladasAno(Funcionario f) {
        int faltasAcumuladas = 0;
        for (int mes=0; mes<12; mes++) {
            // Para cada mês do ano, a quantidade de faltas é consultada
            // na classe Funcionario e somada ao total acumulado
            faltasAcumuladas += f.qtdFaltasNoMes(mes);
        }
        return faltasAcumuladas;
    }

    /**
     @deprecated Esta versão não é mais utilizada. O setor do RH emite apenas o
      * número de faltas para um período de 12 meses. Utilize a versão para um ano
      * completo: {@link ControleRH#faltasAcumuladasAno(Funcionario)}.
      * @param f funcionario
      * @param mesFinal mes  final
      * @param mesInicial mes inicial
      * @return faltas acumuladas do periodo especificado
     */
    public int faltasAcumuladasPeriodo(Funcionario f, int mesInicial, int mesFinal){
        int faltasAcumuladas = 0;
        for (int i=mesInicial; i<=mesFinal; i++){
            int aux = f.qtdFaltasNoMes(i);
            faltasAcumuladas+=aux;
        }
        return faltasAcumuladas;
    }
}
