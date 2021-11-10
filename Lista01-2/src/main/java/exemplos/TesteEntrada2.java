package exemplos;

import java.io.*;
import java.util.Scanner;

//lê tudo, faz oq o (testeEntrada) faz utilizando o Scanner.

public class TesteEntrada2 {
    public static void main(String [] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        String nome = sc.nextLine();
//		String str = sc.nextLine();
        sc.close();
        InputStream is = new FileInputStream(nome);
        Scanner entrada = new Scanner(is);

        while(entrada.hasNextLine()){
            System.out.println(entrada.nextLine());
        }
        entrada.close();
        is.close();

    }
}
