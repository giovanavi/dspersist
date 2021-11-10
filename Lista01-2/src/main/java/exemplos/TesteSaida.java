package exemplos;

import java.io.*;

//FileOutputStream pode receber um booleano como segundo parâmetro, para indicar se vc
//quer reescrever o arquivo manter o que já estava escrito(append)

//OutputStreamWriter :
//BufferdeWriter : para escrever no arquivo.


public class TesteSaida {
    public static void main(String [] args) throws IOException{
        OutputStream os = new FileOutputStream("saida.txt");
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write("Java");
        bw.newLine();
        bw.close();
    }
}
