import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que representa la base de cualquier libro en el catalogo.
 * No se pueden crear instancias directas; siempre se trabaja con Novela o LibroDeTexto.
 */
public abstract class Libro {

    private String titulo;
    private String isbn;
    private double precio;
    private int anioPublicacion;
    private int stock;
    private List<Autor> autores;

    public Libro(String titulo, String isbn, double precio, int anioPublicacion) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.precio = precio;
        this.anioPublicacion = anioPublicacion;
        this.stock = 1;
        this.autores = new ArrayList<>();
    }

    // --- Getters y Setters ---

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            this.titulo = titulo.trim();
        }
    }

    public String getIsbn() {
        return isbn;
    }

    // El ISBN es un identificador unico, no debe cambiarse despues de la creacion.

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
        }
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        if (anioPublicacion > 0) {
            this.anioPublicacion = anioPublicacion;
        }
    }

    public int getStock() {
        return stock;
    }

    /**
     * Incrementa el stock en 1 cuando se agrega un ejemplar existente al catalogo.
     */
    public void incrementarStock() {
        this.stock++;
    }

    /**
     * Reduce el stock en 1. Solo debe llamarse desde Libreria al vender.
     * @return true si la operacion fue exitosa, false si no habia stock.
     */
    public boolean reducirStock() {
        if (this.stock > 0) {
            this.stock--;
            return true;
        }
        return false;
    }

    public List<Autor> getAutores() {
        return new ArrayList<>(autores); // copia defensiva
    }

    public void agregarAutor(Autor autor) {
        if (autor != null) {
            autores.add(autor);
        }
    }

    // --- Metodos ---

    /**
     * Devuelve los nombres de todos los autores separados por coma.
     */
    public String getNombresAutores() {
        if (autores.isEmpty()) {
            return "Sin autor registrado";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < autores.size(); i++) {
            sb.append(autores.get(i).getNombre());
            if (i < autores.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Verifica si alguno de los autores del libro coincide con el nombre buscado.
     */
    public boolean tieneAutor(String nombreAutor) {
        for (Autor a : autores) {
            if (a.getNombre().equalsIgnoreCase(nombreAutor)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Muestra la informacion del catalogo: titulo, ISBN, precio, anio y autores.
     */
    public String infoCatalogo() {
        return String.format(
            "Titulo: %s | ISBN: %s | Precio: $%.2f | Anio: %d | Autor(es): %s | Stock: %d",
            titulo, isbn, precio, anioPublicacion, getNombresAutores(), stock
        );
    }

    /**
     * Metodo abstracto que las subclases deben implementar para mostrar su tipo especifico.
     */
    public abstract String getTipo();
}
