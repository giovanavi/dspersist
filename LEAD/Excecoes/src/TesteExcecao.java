public class TesteExcecao {
    public static void main(String[] args) {
        String vetor[] = new String[2];
        vetor[0] = "Projeto";
        vetor[1] = "LEAD";


        try{
            System.out.println(vetor[5]);
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }finally {
            System.out.println("FIM DA EXECUÇÃO");
        }

    }
    
}
