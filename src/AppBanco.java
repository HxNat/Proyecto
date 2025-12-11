import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class AppBanco {
    private final Scanner scanner;
    private final Map<String, String> usuariosRegistrados;
    private final List<Banco> bancosVinculados;
    private final Map<String, Recibo> recibosDisponibles;
    private String usuarioActual; 

    public AppBanco() {
        this.scanner = new Scanner(System.in);
        this.usuariosRegistrados = new HashMap<>();
        this.bancosVinculados = new ArrayList<>();
        this.recibosDisponibles = new HashMap<>();
        this.usuarioActual = null;
        
        inicializarDatosDePrueba();
    }

    private void inicializarDatosDePrueba() {
        // Usuarios de prueba
        usuariosRegistrados.put("leonard", "12345");

        // Bancos vinculados con saldos aleatorios
        Random random = new Random();
        String[] nombresBancos = {"Bancolombia", "Banco Agrario", "Banco Bogotá", "BBVA", "Crediservir", "Daviplata", "Nequi"};
        for (String nombre : nombresBancos) {
            long saldoInicial = random.nextInt(10000000) + 100000;
            bancosVinculados.add(new Banco(nombre, saldoInicial));
        }

        // Recibos (monto fijo y fecha)
        recibosDisponibles.put("1", new Recibo("LUZ", 50000, "15/10/2023"));
        recibosDisponibles.put("2", new Recibo("GAS", 30000, "20/10/2023"));
        recibosDisponibles.put("3", new Recibo("AGUA", 20000, "25/10/2023"));
    }

    // --- Métodos de Interfaz (Menús) ---

    public void iniciar() {
        boolean salirApp = false;
        while (!salirApp) {
            limpiarPantalla();
            System.out.println(" *** CENS - Aplicación Bancaria *** ");
            System.out.println(" ");
            System.out.println("Escoja la opción: ");
            System.out.println("I: Ingresar R: Registrar E: Exit");
            String option = scanner.nextLine().toUpperCase();

            if (option.equals("I")) {
                if (manejarLogin()) {
                    menuPrincipal();
                }
            } else if (option.equals("R")) {
                manejarRegistro();
            } else if (option.equals("E")) {
                salirApp = true;
            } else {
                System.out.println("Opción inválida. Presione ENTER para continuar.");
                scanner.nextLine();
            }
        }
        limpiarPantalla();
        System.out.println("Gracias por usar CENS. ¡Hasta luego!");
    }

    private boolean manejarLogin() {
        int intentos = 3;
        while (intentos > 0) {
            limpiarPantalla();
            System.out.println(" *** INGRESAR *** ");
            System.out.print("Usuario: ");
            String usurname = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();
            System.out.println("Presione [ ENTER ] para ingresar...");
            scanner.nextLine();
            intentos--;

            if (usuariosRegistrados.containsKey(usurname) && usuariosRegistrados.get(usurname).equals(password)) {
                this.usuarioActual = usurname;
                return true; // Login exitoso
            } else {
                System.out.println("Error en el login. Le quedan " + intentos + " intentos.");
                if (intentos > 0) {
                    System.out.println("Presione ENTER para continuar.");
                    scanner.nextLine();
                }
            }
        }
        limpiarPantalla();
        System.out.println("Ha intentado ingresar tres veces sin éxito. Contacte con nosotros o cree otra cuenta.");
        System.out.println("Presione ENTER para volver al menú principal.");
        scanner.nextLine();
        return false; // Login fallido
    }

    private void manejarRegistro() {
        limpiarPantalla();
        System.out.println(" *** REGISTRO *** ");
        System.out.print("Ingrese un nombre de usuario: ");
        String nuevoUsuario = scanner.nextLine().trim(); // Validación: limpiar espacios

        if (nuevoUsuario.isEmpty()) {
            System.out.println("El nombre de usuario no puede estar vacío. Presione ENTER para continuar.");
            scanner.nextLine();
            return;
        }
        
        if (usuariosRegistrados.containsKey(nuevoUsuario)) {
            System.out.println("El usuario ya existe. Presione ENTER para continuar.");
            scanner.nextLine();
        } else {
            System.out.print("Ingrese una contraseña: ");
            String nuevaPassword = scanner.nextLine().trim(); // Validación: limpiar espacios

            if (nuevaPassword.isEmpty()) {
                System.out.println("La contraseña no puede estar vacía. Presione ENTER para continuar.");
                scanner.nextLine();
                return;
            }
            
            usuariosRegistrados.put(nuevoUsuario, nuevaPassword);
            System.out.println("Cuenta registrada exitosamente. Presione ENTER para continuar.");
            scanner.nextLine();
        }
    }

    private void menuPrincipal() {
        String option;
        do {
            limpiarPantalla();
            System.out.println(" *** MENÚ PRINCIPAL CENS (Usuario: " + usuarioActual + ") *** ");
            System.out.println(" ");
            System.out.println("Escoja la opción: ");
            System.out.println("R: Recibos B: Bancos E: Cerrar Sesión");
            option = scanner.nextLine().toUpperCase();

            if (option.equals("R")) {
                menuRecibos();
            } else if (option.equals("B")) {
                menuBancos();
            }
        } while (!option.equals("E"));
        this.usuarioActual = null; // Cerrar sesión
    }

    // --- Lógica de Recibos ---

    private void menuRecibos() {
        String option;
        do {
            limpiarPantalla();
            System.out.println(" *** RECIBOS *** ");
            System.out.println("Elija el tipo de recibo a consultar:");
            recibosDisponibles.forEach((key, recibo) -> 
                System.out.println(key + ": " + recibo.getTipo() + " - " + (recibo.isPagado() ? "Pagado" : "Pendiente"))
            );
            System.out.println("V: Volver al Menú Principal");
            option = scanner.nextLine().toUpperCase();

            if (recibosDisponibles.containsKey(option)) {
                Recibo reciboSeleccionado = recibosDisponibles.get(option);
                mostrarDetalleRecibo(reciboSeleccionado);
            } else if (!option.equals("V")) {
                System.out.println("Opción inválida. Presione ENTER para continuar.");
                scanner.nextLine();
            }
        } while (!option.equals("V"));
    }
    
    private void mostrarDetalleRecibo(Recibo recibo) {
        limpiarPantalla();
        System.out.println(" *** DETALLE DEL RECIBO DE " + recibo.getTipo() + " *** ");
        System.out.println(recibo.toString());

        if (recibo.isPagado()) {
            System.out.println("\nEste recibo ya ha sido pagado. Presione ENTER para continuar.");
            scanner.nextLine();
            return;
        }

        System.out.println("\n¿Desea pagar este recibo? (SI/NO)");
        String pagarOpcion = scanner.nextLine().toLowerCase();

        if (pagarOpcion.equals("si")) {
            seleccionarBancoParaPago(recibo);
        } else {
            System.out.println("Pago cancelado. Presione ENTER para continuar.");
            scanner.nextLine();
        }
    }

    private void seleccionarBancoParaPago(Recibo recibo) {
        limpiarPantalla();
        System.out.println(" *** PAGO DE RECIBO DE " + recibo.getTipo() + " (Monto: $" + recibo.getMonto() + ") *** ");
        System.out.println("Seleccione el banco para pagar:");

        for (int i = 0; i < bancosVinculados.size(); i++) {
            System.out.println((i + 1) + ". " + bancosVinculados.get(i).getNombre() + " (Saldo: $" + bancosVinculados.get(i).getSaldo() + ")");
        }
        
        System.out.print("Número de banco: ");
        String numeroBancoStr = scanner.nextLine();

        // Manejo de excepciones para robustez
        try {
            int bancoIndex = Integer.parseInt(numeroBancoStr) - 1;
            if (bancoIndex >= 0 && bancoIndex < bancosVinculados.size()) {
                realizarPago(recibo, bancosVinculados.get(bancoIndex));
            } else {
                System.out.println("Opción de banco inválida. Presione ENTER para continuar.");
                scanner.nextLine();
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Por favor, ingrese un NÚMERO válido. Presione ENTER para continuar.");
            scanner.nextLine();
        }
    }
    
    private void realizarPago(Recibo recibo, Banco banco) {
        limpiarPantalla();
        System.out.println(" *** CONFIRMACIÓN DE PAGO *** ");
        System.out.println("Pagar recibo de " + recibo.getTipo() + " (Monto: $" + recibo.getMonto() + ") con:");
        System.out.println("Banco: " + banco.getNombre() + " (Saldo actual: $" + banco.getSaldo() + ")");
        System.out.println("\n¿Confirmar pago? (SI/NO)");
        String confirmar = scanner.nextLine().toLowerCase();

        if (confirmar.equals("si")) {
            if (banco.debitarMonto(recibo.getMonto())) {
                recibo.marcarComoPagado();
                System.out.println("\n TU RECIBO DEL " + recibo.getTipo() + " ESTE MES YA ESTA PAGO.");
                System.out.println("Nuevo saldo en " + banco.getNombre() + ": $" + banco.getSaldo());
            } else {
                System.out.println("\n SALDO INSUFICIENTE. Pague con otro banco.");
            }
        } else {
            System.out.println("\nPago cancelado. No se realizó el cargo.");
        }
        System.out.println("\nPresione ENTER para continuar.");
        scanner.nextLine();
    }

    // --- Lógica de Bancos ---

    private void menuBancos() {
        String option;
        do {
            limpiarPantalla();
            System.out.println(" *** BANCOS VINCULADOS *** ");
            System.out.println("Seleccione el número del banco que quisiera revisar:");

            for (int i = 0; i < bancosVinculados.size(); i++) {
                // Muestra el nombre del banco y el saldo
                System.out.println((i + 1) + ". " + bancosVinculados.get(i).getNombre() + " (Saldo: $" + bancosVinculados.get(i).getSaldo() + ")");
            }
            System.out.println("V: Volver al Menú Principal");
            
            System.out.print("Opción: ");
            option = scanner.nextLine().toUpperCase();

            if (!option.equals("V")) {
                // Manejo de excepciones para robustez
                try {
                    int bancoIndex = Integer.parseInt(option) - 1;
                    if (bancoIndex >= 0 && bancoIndex < bancosVinculados.size()) {
                        mostrarDetalleBanco(bancosVinculados.get(bancoIndex));
                    } else {
                        System.out.println("Opción inválida. Presione ENTER para continuar.");
                        scanner.nextLine();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ERROR: Por favor, ingrese un NÚMERO válido. Presione ENTER para continuar.");
                    scanner.nextLine();
                }
            }
        } while (!option.equals("V"));
    }

    private void mostrarDetalleBanco(Banco banco) {
        limpiarPantalla();
        System.out.println(" *** DETALLE DE CUENTA BANCARIA *** ");
        System.out.println(banco.getNombre());
        System.out.println(" ");
        System.out.println("Usted tiene en " + banco.getNombre() + " un total de: $" + banco.getSaldo() + " pesos.");
        System.out.println("Esta opción es solo para revisar. Use el menú 'R: Recibos' para realizar pagos.");
        System.out.println("\nPresione ENTER para continuar.");
        scanner.nextLine();
    }

    // --- Utilidades ---
    
    private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}