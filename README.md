# CENS - Aplicación Bancaria en Consola

## Objetivo Principal de la Aplicación

El objetivo principal de esta aplicación es simular un **sistema de gestión de finanzas personales** en un entorno de consola, permitiendo al usuario:

1.  **Centralizar la gestión de pagos:** Ofrecer una interfaz única para visualizar y liquidar recibos de servicios públicos (Luz, Gas, Agua).
2.  **Integrar cuentas bancarias:** Demostrar cómo una aplicación puede interactuar con múltiples cuentas (`Banco`) para realizar débitos.
3.  **Asegurar la robustez de las transacciones:** Servir como ejemplo funcional de **Programación Orientada a Objetos (POO)**, aplicando encapsulamiento y un sólido manejo de excepciones (`try-catch`) para garantizar que las transacciones (pagos) sean seguras y a prueba de errores de entrada del usuario.

---

## Características Adicionales

* **Modelado POO:** Uso de clases separadas para la lógica (`AppBanco`), cuentas (`Banco`) y facturas (`Recibo`).
* **Gestión de Cuentas:** Visualización de saldos de bancos vinculados.
* **Robustez:** Implementación de `try-catch` para evitar fallos cuando el usuario introduce texto en lugar de números en los menús.

## Estructura del Proyecto

El proyecto se organiza en las siguientes clases:

| Archivo | Rol | Descripción |
| :--- | :--- | :--- |
| `MainApp.java` | **Lanzador** | Contiene el método `main` y es el punto de entrada de la aplicación. |
| `AppBanco.java` | **Lógica/Controlador** | El núcleo de la aplicación. Maneja menús, login, registro, inicializa datos de prueba y coordina las transacciones. |
| `Banco.java` | **Modelo** | Modela una cuenta bancaria, con métodos para consultar el saldo y `debitarMonto`. |
| `Recibo.java` | **Modelo** | Modela una factura de servicio, con información del monto, tipo y estado de pago. |

## Cómo Ejecutar el Proyecto

### 1. Requisitos

Asegúrate de tener instalado:
* **Java Development Kit (JDK)** (versión 8 o superior).
* **VS Code** con las extensiones de Java.

### 2. Ejecución

1.  Asegúrate de que los cuatro archivos (`MainApp.java`, `AppBanco.java`, `Banco.java`, `Recibo.java`) estén en la misma carpeta.
2.  Abre el archivo **`MainApp.java`** en VS Code.
3.  Haz clic en el botón **"Run"** (Ejecutar) que aparece en la parte superior del archivo o sobre el método `main`.
4.  La aplicación se ejecutará en la terminal integrada de VS Code.

### Cuentas de Prueba

La aplicación carga automáticamente los siguientes datos al iniciar:

| Tipo | Detalle |
| :--- | :--- |
| **Usuario** | `leonard` |
| **Contraseña** | `12345` |
| **Bancos** | 7 cuentas con saldos aleatorios. |
| **Recibos** | Luz ($50k), Gas ($30k), Agua ($20k) en estado pendiente. |

## Flujo de Uso

1.  Selecciona **I (Ingresar)** e inicia sesión con el usuario de prueba.
2.  En el Menú Principal, selecciona **R (Recibos)**.
3.  Elige un recibo pendiente (ej. `1` para LUZ).
4.  Selecciona el número de un banco para realizar el pago.
5.  El sistema validará el saldo y actualizará el estado del recibo.