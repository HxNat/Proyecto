import java.util.Scanner;

/**
 * Clase principal de la aplicación CENS.
 * Coordina el flujo general: menú inicial, login, registro y navegación por menús.
 */
public class CENSApp {
    public static void main(String[] args) {
        // Inicializar Scanner para entrada de usuario
        Scanner scanner = new Scanner(System.in);
        
        // Crear instancias de las clases manejadoras
        UserManager userManager = new UserManager();  // Maneja usuarios y login
        ReciboManager reciboManager = new ReciboManager();  // Maneja recibos
        BancoManager bancoManager = new BancoManager();  // Maneja bancos y pagos
        UI ui = new UI();  // Maneja interfaz de usuario
        
        // Definir usuario predeterminado para pruebas (como "variables" iniciales)
        userManager.registrarUsuario("leonard", "12345");
        
        // Bucle principal de la aplicación
        boolean salirApp = false;
        while (!salirApp) {
            // Limpiar pantalla y mostrar header
            ui.limpiarPantalla();
            ui.imprimirHeader();
            
            // Mostrar menú inicial
            System.out.println("Escoja la opción: ");
            System.out.println("I: Ingresar R: Registrar E: Exit");
            String option = scanner.nextLine().toUpperCase();
            
            if (option.equals("I")) {
                // Intentar login; si exitoso, entrar al menú principal
                if (userManager.intentarLogin(scanner, ui)) {
                    boolean salirMenu = false;
                    while (!salirMenu) {
                        // Limpiar y mostrar menú principal
                        ui.limpiarPantalla();
                        ui.imprimirHeader();
                        System.out.println("Escoja la opción: ");
                        System.out.println("R:Recibos B:Bancos E:Exit");
                        option = scanner.nextLine().toUpperCase();
                        
                        if (option.equals("R")) {
                            // Manejar opción de recibos
                            reciboManager.manejarRecibos(scanner, ui, bancoManager);
                        } else if (option.equals("B")) {
                            // Mostrar bancos
                            bancoManager.mostrarBancos(scanner, ui);
                        } else if (option.equals("E")) {
                            // Salir del menú principal
                            salirMenu = true;
                        } else {
                            // Opción inválida
                            ui.opcionInvalida(scanner);
                        }
                    }
                } else {
                    // Si login falló, salir de la app
                    salirApp = true;
                }
            } else if (option.equals("R")) {
                // Registrar nuevo usuario
                userManager.registrarNuevoUsuario(scanner, ui);
            } else if (option.equals("E")) {
                // Salir de la app
                salirApp = true;
            } else {
                // Opción inválida en menú inicial
                ui.opcionInvalida(scanner);
            }
        }
        
        // Mensaje final al salir
        ui.limpiarPantalla();
        System.out.println("Gracias por usar CENS. ¡Hasta luego!");
        scanner.close();
    }
}