/**
 * Representa un libro de texto educativo.
 * Extiende Libro e implementa Describible para mostrar su nivel educativo.
 */
public class LibroDeTexto extends Libro implements Describible {

    private String nivelEducativo;

    public LibroDeTexto(String titulo, String isbn, double precio, int anioPublicacion, String nivelEducativo) {
        super(titulo, isbn, precio, anioPublicacion);
        this.nivelEducativo = nivelEducativo;
    }

    // --- Getters y Setters ---

    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(String nivelEducativo) {
        if (nivelEducativo != null && !nivelEducativo.trim().isEmpty()) {
            this.nivelEducativo = nivelEducativo.trim();
        }
    }

    // --- Implementacion de Describible ---

    @Override
    public String getDescripcion() {
        return String.format(
            "[Libro de Texto] Titulo: %s | Nivel educativo: %s | Autor(es): %s",
            getTitulo(), nivelEducativo, getNombresAutores()
        );
    }

    // --- Implementacion de getTipo ---

    @Override
    public String getTipo() {
        return "Libro de Texto (" + nivelEducativo + ")";
    }
}
