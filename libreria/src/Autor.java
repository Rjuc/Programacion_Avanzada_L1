import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un autor de libros.
 * Almacena su nombre en formato "Apellido Nombre" y su nacionalidad.
 */
public class Autor {

    private String nombre;
    private String nacionalidad;
    private List<Libro> librosEscritos;

    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.librosEscritos = new ArrayList<>();
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        if (nacionalidad != null && !nacionalidad.trim().isEmpty()) {
            this.nacionalidad = nacionalidad.trim();
        }
    }

    public List<Libro> getLibrosEscritos() {
        return new ArrayList<>(librosEscritos); // copia defensiva
    }

    // --- Metodos ---

    /**
     * Agrega un libro a la lista del autor, evitando duplicados por ISBN.
     */
    public void agregarLibro(Libro libro) {
        if (libro != null && !contieneLibro(libro.getIsbn())) {
            librosEscritos.add(libro);
        }
    }

    private boolean contieneLibro(String isbn) {
        for (Libro l : librosEscritos) {
            if (l.getIsbn().equalsIgnoreCase(isbn)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return nombre + " (" + nacionalidad + ")";
    }
}
