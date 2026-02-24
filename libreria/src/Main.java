import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal. Contiene el menu de linea de comandos para
 * interactuar con el sistema de gestion de la libreria.
 */
public class Main {

    private static Libreria libreria = new Libreria();
    private static List<Cliente> clientes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  Bienvenido al Sistema de la Libreria");
        System.out.println("===========================================");

        int opcion = -1;
        while (opcion != 5) {
            mostrarMenuPrincipal();
            opcion = leerEntero();
            switch (opcion) {
                case 1 -> menuGestionarLibros();
                case 2 -> menuGestionarClientes();
                case 3 -> menuVenderLibro();
                case 4 -> libreria.mostrarCatalogo();
                case 5 -> System.out.println("\nHasta pronto. Cerrando el sistema...");
                default -> System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    // ===================== MENUS =====================

    private static void mostrarMenuPrincipal() {
        System.out.println("\n----------- MENU PRINCIPAL -----------");
        System.out.println("1. Gestionar libros");
        System.out.println("2. Gestionar clientes");
        System.out.println("3. Vender un libro");
        System.out.println("4. Ver catalogo completo");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static void menuGestionarLibros() {
        System.out.println("\n------- GESTION DE LIBROS -------");
        System.out.println("1. Agregar libro al catalogo");
        System.out.println("2. Buscar libro");
        System.out.print("Seleccione una opcion: ");

        int opcion = leerEntero();
        switch (opcion) {
            case 1 -> agregarLibro();
            case 2 -> menuBuscarLibro();
            default -> System.out.println("Opcion no valida.");
        }
    }

    private static void menuGestionarClientes() {
        System.out.println("\n------- GESTION DE CLIENTES -------");
        System.out.println("1. Agregar cliente");
        System.out.println("2. Ver historial de compras de un cliente");
        System.out.print("Seleccione una opcion: ");

        int opcion = leerEntero();
        switch (opcion) {
            case 1 -> agregarCliente();
            case 2 -> verHistorialCliente();
            default -> System.out.println("Opcion no valida.");
        }
    }

    private static void menuBuscarLibro() {
        System.out.println("\n------- BUSCAR LIBRO -------");
        System.out.println("1. Buscar por titulo");
        System.out.println("2. Buscar por autor");
        System.out.print("Seleccione una opcion: ");

        int opcion = leerEntero();
        switch (opcion) {
            case 1 -> {
                System.out.print("Ingrese el titulo (o parte de el): ");
                String titulo = scanner.nextLine().trim();
                List<Libro> resultados = libreria.buscarPorTitulo(titulo);
                mostrarResultadosBusqueda(resultados);
            }
            case 2 -> {
                System.out.print("Ingrese el nombre del autor (Apellido Nombre): ");
                String autor = scanner.nextLine().trim();
                List<Libro> resultados = libreria.buscarPorAutor(autor);
                mostrarResultadosBusqueda(resultados);
            }
            default -> System.out.println("Opcion no valida.");
        }
    }

    // ===================== ACCIONES =====================

    private static void agregarLibro() {
        System.out.println("\n--- Agregar nuevo libro ---");
        System.out.println("Tipo de libro:");
        System.out.println("1. Novela");
        System.out.println("2. Libro de texto");
        System.out.print("Seleccione: ");
        int tipo = leerEntero();

        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo no valido. Operacion cancelada.");
            return;
        }

        System.out.print("Titulo: ");
        String titulo = scanner.nextLine().trim();

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();

        System.out.print("Precio: ");
        double precio = leerDouble();

        System.out.print("Anio de publicacion: ");
        int anio = leerEntero();

        Libro libro;
        if (tipo == 1) {
            System.out.print("Genero (ej: Ficcion, Misterio, Ciencia Ficcion): ");
            String genero = scanner.nextLine().trim();
            libro = new Novela(titulo, isbn, precio, anio, genero);
        } else {
            System.out.print("Nivel educativo (ej: Primaria, Secundaria, Universidad): ");
            String nivel = scanner.nextLine().trim();
            libro = new LibroDeTexto(titulo, isbn, precio, anio, nivel);
        }

        // Agregar autores
        System.out.println("Ingrese los autores en formato 'Apellido Nombre'.");
        System.out.println("Si son varios, separelos con coma (ej: Garcia Marquez,Borges Jorge Luis):");
        System.out.print("Autores: ");
        String inputAutores = scanner.nextLine().trim();
        String[] nombresAutores = inputAutores.split(",");

        for (String nombreAutor : nombresAutores) {
            String nombreLimpio = nombreAutor.trim();
            if (!nombreLimpio.isEmpty()) {
                System.out.print("Nacionalidad de " + nombreLimpio + ": ");
                String nacionalidad = scanner.nextLine().trim();
                Autor autor = new Autor(nombreLimpio, nacionalidad);
                libro.agregarAutor(autor);
                autor.agregarLibro(libro);
            }
        }

        libreria.agregarLibro(libro);

        // Mostrar descripcion usando la interfaz Describible
        if (libro instanceof Describible) {
            System.out.println(((Describible) libro).getDescripcion());
        }
    }

    private static void agregarCliente() {
        System.out.println("\n--- Agregar nuevo cliente ---");
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("ID del cliente: ");
        String id = scanner.nextLine().trim();

        // Verificar que el ID no este duplicado
        for (Cliente c : clientes) {
            if (c.getId().equalsIgnoreCase(id)) {
                System.out.println("Ya existe un cliente con ese ID. Operacion cancelada.");
                return;
            }
        }

        clientes.add(new Cliente(nombre, id));
        System.out.println("Cliente agregado correctamente.");
    }

    private static void verHistorialCliente() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        Cliente cliente = seleccionarCliente();
        if (cliente != null) {
            System.out.println("\n" + cliente.getHistorial());
        }
    }

    private static void menuVenderLibro() {
        System.out.println("\n--- Vender libro ---");

        if (libreria.getCatalogo().isEmpty()) {
            System.out.println("El catalogo esta vacio. No hay libros para vender.");
            return;
        }
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        // Seleccionar libro por ISBN
        System.out.print("Ingrese el ISBN del libro a vender: ");
        String isbn = scanner.nextLine().trim();
        Libro libro = libreria.buscarPorIsbn(isbn);

        if (libro == null) {
            System.out.println("No se encontro ningun libro con ese ISBN.");
            return;
        }

        if (libro.getStock() == 0) {
            System.out.println("Lo sentimos, ese libro no tiene stock disponible.");
            return;
        }

        System.out.println("Libro encontrado: " + libro.getTitulo());

        // Seleccionar cliente
        Cliente cliente = seleccionarCliente();
        if (cliente == null) return;

        boolean exito = libreria.venderLibro(libro, cliente);
        if (exito) {
            System.out.println("Venta realizada con exito.");
            System.out.println("  Libro: " + libro.getTitulo());
            System.out.println("  Cliente: " + cliente.getNombre());
            System.out.println("  Stock restante: " + libro.getStock());
        } else {
            System.out.println("No se pudo completar la venta.");
        }
    }

    // ===================== HELPERS =====================

    private static void mostrarResultadosBusqueda(List<Libro> resultados) {
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron libros con ese criterio.");
        } else {
            System.out.println("\nResultados encontrados: " + resultados.size());
            for (Libro libro : resultados) {
                System.out.println("  - " + libro.infoCatalogo());
                if (libro instanceof Describible) {
                    System.out.println("    " + ((Describible) libro).getDescripcion());
                }
            }
        }
    }

    private static Cliente seleccionarCliente() {
        System.out.println("Clientes registrados:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + clientes.get(i));
        }
        System.out.print("Seleccione el numero del cliente: ");
        int indice = leerEntero() - 1;

        if (indice < 0 || indice >= clientes.size()) {
            System.out.println("Seleccion no valida.");
            return null;
        }
        return clientes.get(indice);
    }

    /**
     * Lee un entero desde consola de forma segura.
     * Si el usuario ingresa texto, devuelve -1 y limpia el buffer.
     */
    private static int leerEntero() {
        try {
            String linea = scanner.nextLine().trim();
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Lee un double desde consola de forma segura.
     * Si el usuario ingresa texto invalido, devuelve 0.0.
     */
    private static double leerDouble() {
        try {
            String linea = scanner.nextLine().trim();
            return Double.parseDouble(linea);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
