public class Banco {
    private final String nombre;
    private long saldo;

    public Banco(String nombre, long saldo) {
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public long getSaldo() {
        return saldo;
    }

    public boolean debitarMonto(long monto) {
        if (this.saldo >= monto) {
            this.saldo -= monto;
            return true;
        }
        return false; // Saldo insuficiente
    }

    @Override
    public String toString() {
        return nombre + " - Saldo actual: $" + saldo;
    }
}