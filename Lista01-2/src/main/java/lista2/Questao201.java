package lista2;

import java.io.*;

public class Questao201 {
    private static final int BUFFER_SIZE = 4096;
    public static void main(String[] args) throws IOException, FileNotFoundException {
        if (args.length < 2) {
            System.out.println("Insira o nome de um arquivo de origem e um de destino");
            System.exit(0);
        }
        String input = args[0];
        String output = args[1];

        InputStream is = new BufferedInputStream(new FileInputStream(input), BUFFER_SIZE);
        OutputStream os = new BufferedOutputStream(new FileOutputStream(output),BUFFER_SIZE);

        byte[] b = new byte[BUFFER_SIZE];

        long comeco= System.currentTimeMillis();
        while(is.read(b)!=-1) {
            os.write(b);
        }
        long fim = System.currentTimeMillis();

        File file= new File(output);
        if(file.exists()) {
            System.out.println("O tempo total da cópia: "+(fim-comeco)+" milisegundos");
        }

        is.close();
        os.close();
    }
}
