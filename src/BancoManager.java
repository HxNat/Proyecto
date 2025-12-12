import java.util.Random;
import java.util.Scanner;

/**
 * Clase que maneja la lista de bancos, saldos aleatorios y lógica de pagos.
 */
public class BancoManager {
    // Lista de bancos disponibles
    private String[] bancos = {"Bancolombia", "Banco Agrario", "Banco Bogotá", "BBVA", "Crediservir", "Daviplata", "Nequi"};
    // Saldos aleatorios para cada banco
    private long[] saldos = new long[7];
    
    /**
     * Constructor: Inicializa saldos aleatorios para cada banco.
     */
    public BancoManager() {
        Random random = new Random();
        for (int i = 0; i < saldos.length; i++) {
            saldos[i] = random.nextInt(10000000) + 100000;  // Saldo entre 100000 y 10000000
        }
    }
    
    /**
     * Muestra la lista de bancos y permite revisar saldos (sin pago).
     * @param scanner Scanner para entrada
     * @param ui Instancia de UI para manejo de pantalla
     */
    public void mostrarBancos(Scanner scanner, UI ui) {
        ui.limpiarPantalla();
        ui.imprimirHeader();
        System.out.println("bancos vinculados con la app");
        System.out.println(" ");
        // Listar bancos
        for (int i = 0; i < bancos.length; i++) {
            System.out.println((i + 1) + ". " + bancos[i]);
        }
        System.out.println("seleccione el numero del banco que quisiera revisar");
        String numero_banco = scanner.nextLine();
        int bancoIndex = Integer.parseInt(numero_banco) - 1;
        
        if (bancoIndex >= 0 && bancoIndex < bancos.length) {
            // Mostrar detalles del banco seleccionado
            ui.limpiarPantalla();
            ui.imprimirHeader();
            System.out.println(bancos[bancoIndex]);
            System.out.println(" ");
            System.out.println("Ustd tiene en " + bancos[bancoIndex] + " un total de: " + saldos[bancoIndex] + " de pesos");
            System.out.println("Esta opción es solo para revisar. Use 'R' para pagar recibos.");
        }
        
        // Opción para regresar
        System.out.println("Cualquier tecla para Regresar, E:Exit");
        scanner.nextLine().toUpperCase();
    }
    
    /**
     * Permite seleccionar un banco para pagar un recibo, verificando saldo.
     * @param scanner Scanner para entrada
     * @param ui Instancia de UI para manejo de pantalla
     * @param monto Monto del recibo a pagar
     * @param tipo Tipo de recibo (LUZ, GAS, AGUA)
     */
    public void seleccionarBancoParaPago(Scanner scanner, UI ui, long monto, String tipo) {
        System.out.println("Seleccione el banco para pagar:");
        // Listar bancos
        for (int i = 0; i < bancos.length; i++) {
            System.out.println((i + 1) + ". " + bancos[i]);
        }
        String numero_banco = scanner.nextLine();
        int bancoIndex = Integer.parseInt(numero_banco) - 1;
        
        if (bancoIndex >= 0 && bancoIndex < bancos.length) {
            // Mostrar saldo y confirmar pago
            System.out.println("Usted tiene en " + bancos[bancoIndex] + " un total de: " + saldos[bancoIndex] + " pesos");
            System.out.println("¿Desea pagar el recibo con este banco? (si/no)");
            String comprobante = scanner.nextLine().toLowerCase();
            
            if (comprobante.equals("si")) {
                // Verificar y procesar pago
                if (saldos[bancoIndex] >= monto) {
                    saldos[bancoIndex] -= monto;  // Deducir monto
                    System.out.println("TU RECIBO DEL " + tipo + " ESTE MES YA ESTA PAGO");
                } else {
                    System.out.println("SALDOS INSUFICIENTES, PAGUE CON OTRO BANCO");
                }
            } else {
                System.out.println("Pago cancelado.");
            }
        } else {
            System.out.println("Banco inválido.");
        }
    }
}