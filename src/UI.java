import java.util.Scanner;

/**
 * Clase utilitaria para manejar la interfaz de usuario: limpiar pantalla, imprimir header y manejar opciones inválidas.
 */
public class UI {
    /**
     * Limpia la pantalla de la consola (simulado con secuencia ANSI).
     */
    public void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    /**
     * Imprime el header de la aplicación.
     */
    public void imprimirHeader() {
        System.out.println(" *** CENS *** ");
        System.out.println(" ");
    }
    
    /**
     * Maneja opciones inválidas, mostrando mensaje y esperando ENTER.
     * @param scanner Scanner para entrada
     */
    public void opcionInvalida(Scanner scanner) {
        System.out.println("Opción inválida. Presione ENTER para continuar.");
        scanner.nextLine();
    }
}