public class VolumeException extends Exception {
    private int valor;

    public VolumeException(int valor) {
        this.valor = valor;
    }

    public String getMessage() {
        return  "O valor" + valor + "não está dentro do limite aceitável para volume.";
    }
}