package lista1;

//1.Crie uma aplicação para receber via entrada de teclado um nome de arquivo texto e
//uma string S. A aplicação deve exibir todas as linhas que tenham S como substring.

import java.util.Scanner;
import java.io.*;

public class Questao101 {
    public static void main(String [] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo:");
        String nome = sc.nextLine();
        System.out.println("Digite a substring:");
        String str = sc.nextLine();

        sc.close();

        InputStream is = new FileInputStream(nome);
        Scanner entrada = new Scanner(is);

        while(entrada.hasNextLine()){
            String s = entrada.nextLine();
            if(s.contains(str)){
                System.out.println(s);
            }
        }
        is.close();
        entrada.close();
    }
}

