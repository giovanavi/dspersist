package exemplos;

import java.io.*;

//FileInputStream : lê o arquivo em fluxo, cada fluxo traz um caracter, traz byte a byte.
//InputStreamReader: interpreta o arquivo através de um esquema de decodificação, pode receber o enconding
//a ser utilizado como parâmetro, se desejado, tal como UTF-8 ou ISO-8869-1. (encapsula o InputStream).
//BufferedReader : lê a linha inteira. Vai armazenado os dados do arquivo numa estrutura para depois juntar
//e executar alguma operação (encapsula o InputStreamReader). s.readLine: para quando encontrar um "\n";

public class TesteEntrada {
    public static void main(String [] args) throws IOException{
//InputStream
        InputStream is = new FileInputStream("arquivo.txt");
//		char c = (char)is.read();//(char) : transformando em char
//		System.out.print(c);
//InputStreamReader
        InputStreamReader isr = new InputStreamReader(is); //lendo com assentos e etc.
//		char d = (char)isr.read();
//		System.out.print(d);
//BufferedReader
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        System.out.print(s);


    }
}