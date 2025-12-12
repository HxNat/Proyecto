import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase que maneja la gestión de usuarios: registro, login y validación.
 * Usa un mapa para almacenar usuarios (usuario -> contraseña).
 */
public class UserManager {
    // Mapa para almacenar usuarios registrados
    private Map<String, String> usuariosRegistrados = new HashMap<>();
    // Número máximo de intentos de login
    private int intentosMax = 3;
    // Mensaje de alerta para login fallido
    private String alerta = "ha intentado ingresar tres veces pero no ha podido, si es de su agrado, contactese con nosotros, o creé otra cuenta";
    
    /**
     * Registra un nuevo usuario en el mapa.
     * @param usuario Nombre de usuario
     * @param password Contraseña
     */
    public void registrarUsuario(String usuario, String password) {
        usuariosRegistrados.put(usuario, password);
    }
    
    /**
     * Intenta realizar el login con 3 intentos.
     * @param scanner Scanner para entrada
     * @param ui Instancia de UI para manejo de pantalla
     * @return true si login exitoso, false si falló
     */
    public boolean intentarLogin(Scanner scanner, UI ui) {
        int ent = intentosMax;  // Contador de intentos
        boolean loginExitoso = false;
        
        // Bucle de intentos de login
        while (ent > 0 && !loginExitoso) {
            ui.limpiarPantalla();
            ui.imprimirHeader();
            System.out.print("usuario: ");
            String usurname = scanner.nextLine();
            System.out.print("contraseña: ");
            String password = scanner.nextLine();
            System.out.println("Presione [ ENTER ] para ingresar a la app");
            scanner.nextLine();  // Consumir ENTER
            ent--;  // Decrementar intentos
            
            // Verificar credenciales
            if (usuariosRegistrados.containsKey(usurname) && usuariosRegistrados.get(usurname).equals(password)) {
                loginExitoso = true;
            } else {
                // Mostrar error y continuar si quedan intentos
                ui.limpiarPantalla();
                System.out.println("Hubo un error en el login, revise y vuelva a intentarlo, tiene " + ent + " intentos válidos");
                if (ent > 0) {
                    System.out.println("Presione ENTER para continuar.");
                    scanner.nextLine();
                }
            }
        }
        
        // Si falló, mostrar alerta
        if (!loginExitoso) {
            ui.limpiarPantalla();
            System.out.println(alerta);
        }
        return loginExitoso;
    }
    
    /**
     * Registra un nuevo usuario pidiendo datos al usuario.
     * @param scanner Scanner para entrada
     * @param ui Instancia de UI para manejo de pantalla
     */
    public void registrarNuevoUsuario(Scanner scanner, UI ui) {
        ui.limpiarPantalla();
        ui.imprimirHeader();
        System.out.println("Registro de nueva cuenta");
        System.out.print("Ingrese un nombre de usuario: ");
        String nuevoUsuario = scanner.nextLine();
        
        // Verificar si el usuario ya existe
        if (usuariosRegistrados.containsKey(nuevoUsuario)) {
            System.out.println("El usuario ya existe. Presione ENTER para continuar.");
            scanner.nextLine();
        } else {
            System.out.print("Ingrese una contraseña: ");
            String nuevaPassword = scanner.nextLine();
            registrarUsuario(nuevoUsuario, nuevaPassword);  // Agregar al mapa
            System.out.println("Cuenta registrada exitosamente. Presione ENTER para continuar.");
            scanner.nextLine();
        }
    }
}