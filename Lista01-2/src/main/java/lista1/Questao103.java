package lista1;

import java.io.*;
import java.util.Scanner;

//3.Escreva uma aplicação para ler um arquivo binário origem e gravá-lo em outro arquivo
//(arquivo destino).
//-Os nomes dos arquivos (origem e destino) devem ser definidos na chamada da aplicação via
//argumento de linha de comando.
//-A leitura e gravação devem ser realizadas byte a byte.
//-Ao final, deve-se exibir o tempo total da cópia em milisegundos, caso a cópia tenha sido
//bem sucedida.

public class Questao103 {
    public static void main(String [] args) throws IOException, FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do primeiro arquivo:");
        String arq1 = sc.nextLine(); //ler
        System.out.println("Digite o nome do segundo arquivo:");
        String arq2 = sc.nextLine(); //gravar
        int b;

        InputStream is = new FileInputStream(arq1);
        InputStreamReader isr = new InputStreamReader(is);
        OutputStream os = new FileOutputStream(arq2);
        OutputStreamWriter osw = new OutputStreamWriter(os);

        long comeco = System.currentTimeMillis();
        while((b = isr.read()) != -1) {
            osw.write(b);
        }
        long fim = System.currentTimeMillis();

        isr.close();
        osw.close();

        File file = new File(arq2);
        if(file.exists()) {
            System.out.println("Tempo total da cópia: "+(fim-comeco)+" milisegundos");
        }
    }
}
