public class Operador {
    private String tipo;
    private int valor;

    private String token;

    public Operador(String tipo, int valor,String token) {
        this.tipo = tipo;
        this.valor = valor;
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public int getValor() {
        return valor;
    }

    public String getToken(){
        return token;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
