import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un cliente de la libreria.
 * Lleva registro de todos los libros que ha comprado.
 */
public class Cliente {

    private String nombre;
    private String id;
    private List<Libro> librosComprados;

    public Cliente(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.librosComprados = new ArrayList<>();
    }

    // --- Getters y Setters ---

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
        }
    }

    public String getId() {
        return id;
    }

    // El ID es un identificador unico; no se modifica tras la creacion.

    public List<Libro> getLibrosComprados() {
        return new ArrayList<>(librosComprados); // copia defensiva
    }

    // --- Metodos ---

    /**
     * Registra un libro como comprado por este cliente.
     */
    public void agregarLibroComprado(Libro libro) {
        if (libro != null) {
            librosComprados.add(libro);
        }
    }

    /**
     * Muestra el historial de compras del cliente.
     */
    public String getHistorial() {
        if (librosComprados.isEmpty()) {
            return nombre + " no ha comprado ningun libro aun.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Historial de compras de ").append(nombre).append(":\n");
        for (int i = 0; i < librosComprados.size(); i++) {
            sb.append("  ").append(i + 1).append(". ").append(librosComprados.get(i).getTitulo())
              .append(" (").append(librosComprados.get(i).getTipo()).append(")");
            if (i < librosComprados.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Cliente: " + nombre + " | ID: " + id;
    }
}
