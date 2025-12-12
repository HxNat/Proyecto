import java.util.Scanner;

/**
 * Clase que maneja los tipos de recibos (Luz, Gas, Agua), sus montos, fechas y lógica de pago.
 */
public class ReciboManager {
    // Montos fijos para cada tipo de recibo
    private long montoLuz = 50000;
    private long montoGas = 30000;
    private long montoAgua = 20000;
    // Fechas máximas de pago
    private String fechaMaxLuz = "15/10/2023";
    private String fechaMaxGas = "20/10/2023";
    private String fechaMaxAgua = "25/10/2023";
    
    /**
     * Maneja la selección y pago de un recibo.
     * @param scanner Scanner para entrada
     * @param ui Instancia de UI para manejo de pantalla
     * @param bancoManager Instancia de BancoManager para pagos
     */
    public void manejarRecibos(Scanner scanner, UI ui, BancoManager bancoManager) {
        ui.limpiarPantalla();
        ui.imprimirHeader();
        System.out.println("Recibos");
        System.out.println("Elija el tipo de recibo:");
        System.out.println("1: Luz");
        System.out.println("2: Gas");
        System.out.println("3: Agua");
        String tipoRecibo = scanner.nextLine();
        
        // Determinar monto, tipo y fecha según selección
        long monto = 0;
        String tipo = "";
        String fecha = "";
        if (tipoRecibo.equals("1")) {
            monto = montoLuz;
            tipo = "LUZ";
            fecha = fechaMaxLuz;
        } else if (tipoRecibo.equals("2")) {
            monto = montoGas;
            tipo = "GAS";
            fecha = fechaMaxGas;
        } else if (tipoRecibo.equals("3")) {
            monto = montoAgua;
            tipo = "AGUA";
            fecha = fechaMaxAgua;
        } else {
            // Opción inválida
            System.out.println("Opción inválida. Presione cualquier tecla para continuar.");
            scanner.nextLine();
            return;
        }
        
        // Mostrar detalles del recibo
        System.out.println("Su recibo de " + tipo + " de este mes es de: " + monto + " pesos");
        System.out.println("Fecha máxima para pagar: " + fecha);
        System.out.println(" ");
        System.out.println("¿Desea pagar este recibo? (si/no)");
        String comprobante = scanner.nextLine().toLowerCase();
        
        if (comprobante.equals("si")) {
            // Proceder al pago seleccionando banco
            bancoManager.seleccionarBancoParaPago(scanner, ui, monto, tipo);
        }
        
        // Opción para regresar o salir
        System.out.println("Cualquier tecla para Regresar, E:Exit");
        String option = scanner.nextLine().toUpperCase();
    }
}