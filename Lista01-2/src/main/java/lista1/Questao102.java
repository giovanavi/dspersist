package lista1;

import java.io.*;
import java.util.Scanner;

public class Questao102 {
    public static void main(String [] args) throws IOException, FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do primeiro arquivo:");
        String arq1 = sc.nextLine();
        System.out.println("Digite o nome do segundo arquivo:");
        String arq2 = sc.nextLine();
        System.out.println("Digite o nome do terceiro arquivo:");
        String arq3 = sc.nextLine();

        sc.close();

        InputStream is1 = new FileInputStream(arq1);
        Scanner entrada1 = new Scanner(is1);
        InputStream is2 = new FileInputStream(arq2);
        Scanner entrada2 = new Scanner(is2);

        PrintStream ps = new PrintStream(arq3);

        long comeco = System.currentTimeMillis();
        while(entrada1.hasNextLine()){
            ps.println(entrada1.nextLine());
        }
        while(entrada2.hasNextLine()) {
            ps.println(entrada2.nextLine());
        }
        long fim = System.currentTimeMillis();

        File file = new File(arq3);
        if(file.exists()) {
            double bytes = file.length();
            System.out.println("Tamanho do arquivo copiado: "+bytes+" bytes");
            System.out.println("Tempo total da cópia: "+(fim-comeco)+" milisegundos");
        }else {
            System.out.println("O arquivo não existe");
        }

        ps.close();
        entrada1.close();
        entrada2.close();
    }
}
