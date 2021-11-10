package exemplos;

import java.io.*;

//Saída mais rápida
//Com o scanner vc vai lendo e salvando no arquivo.
//Fechando arquivos no finally
//PrintStream com append : concatena o arquivo

public class TesteSaida2 {
    public static void  main(String [] args) throws FileNotFoundException{
//		PrintStream ps = new PrintStream("saida.txt");
//		ps.println("java");
//		ps.close();
//		Scanner sc = new Scanner(System.in);
//		while(sc.hasNextLine()){
//			ps.println(sc.nextLine());
//		}
// com append
        PrintStream ps = new PrintStream(new FileOutputStream("saida.txt"), true);
        ps.append("concatenação");
        ps.close();
    }
}
