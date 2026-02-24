import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que gestiona el catalogo de libros y las operaciones de la libreria.
 * Se encarga de agregar libros, buscar y procesar ventas.
 */
public class Libreria {

    private List<Libro> catalogo;

  www  public Libreria() {
        this.catalogo = new ArrayList<>();
    }

    // --- Getters ---

    public List<Libro> getCatalogo() {
        return new ArrayList<>(catalogo); // copia defensiva
    }

    // --- Metodos ---

    /**
     * Agrega un libro al catalogo.
     * Si ya existe un libro con el mismo ISBN, incrementa su stock en 1.
     * Si no existe, lo agrega con stock inicial de 1.
     */
    public void agregarLibro(Libro libro) {
        if (libro == null) return;

        Libro existente = buscarPorIsbn(libro.getIsbn());
        if (existente != null) {
            existente.incrementarStock();
            System.out.println("El libro ya existia en el catalogo. Stock actualizado a: " + existente.getStock());
        } else {
            catalogo.add(libro);
            System.out.println("Libro agregado al catalogo correctamente.");
        }
    }

    /**
     * Busca libros cuyo titulo contenga el texto indicado (sin importar mayusculas).
     * @return Lista de coincidencias, vacia si no encuentra nada.
     */
    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : catalogo) {
            if (libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    /**
     * Busca libros cuyo autor coincida exactamente con el nombre indicado (sin importar mayusculas).
     * @return Lista de coincidencias, vacia si no encuentra nada.
     */
    public List<Libro> buscarPorAutor(String nombreAutor) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : catalogo) {
            if (libro.tieneAutor(nombreAutor)) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    /**
     * Procesa la venta de un libro a un cliente.
     * Reduce el stock del libro y registra la compra en el historial del cliente.
     * @return true si la venta fue exitosa, false si no hay stock disponible.
     */
    public boolean venderLibro(Libro libro, Cliente cliente) {
        if (libro == null || cliente == null) return false;

        boolean vendido = libro.reducirStock();
        if (vendido) {
            cliente.agregarLibroComprado(libro);
        }
        return vendido;
    }

    /**
     * Busca un libro en el catalogo por su ISBN exacto.
     * @return El libro encontrado o null si no existe.
     */
    public Libro buscarPorIsbn(String isbn) {
        for (Libro libro : catalogo) {
            if (libro.getIsbn().equalsIgnoreCase(isbn)) {
                return libro;
            }
        }
        return null;
    }

    /**
     * Muestra el catalogo completo con la informacion de cada libro.
     */
    public void mostrarCatalogo() {
        if (catalogo.isEmpty()) {
            System.out.println("El catalogo esta vacio.");
            return;
        }
        System.out.println("\n===== CATALOGO DE LA LIBRERIA =====");
        for (int i = 0; i < catalogo.size(); i++) {
            System.out.println((i + 1) + ". " + catalogo.get(i).infoCatalogo());
        }
        System.out.println("===================================\n");
    }
}
