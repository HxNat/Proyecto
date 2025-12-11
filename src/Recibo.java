public class Recibo {
    private final String tipo;
    private final long monto;
    private final String fechaMaximaPago;
    private boolean pagado;

    public Recibo(String tipo, long monto, String fechaMaximaPago) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaMaximaPago = fechaMaximaPago;
        this.pagado = false;
    }

    public String getTipo() {
        return tipo;
    }

    public long getMonto() {
        return monto;
    }

    public String getFechaMaximaPago() {
        return fechaMaximaPago;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void marcarComoPagado() {
        this.pagado = true;
    }

    @Override
    public String toString() {
        return "Recibo de " + tipo + "\n" +
               "Monto: $" + monto + "\n" +
               "Fecha máxima de pago: " + fechaMaximaPago + "\n" +
               "Estado: " + (pagado ? "PAGADO" : "PENDIENTE");
    }
}