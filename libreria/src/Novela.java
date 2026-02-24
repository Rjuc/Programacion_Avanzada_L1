/**
 * Representa una novela.
 * Extiende Libro e implementa Describible para mostrar su genero literario.
 */
public class Novela extends Libro implements Describible {

    private String genero;

    public Novela(String titulo, String isbn, double precio, int anioPublicacion, String genero) {
        super(titulo, isbn, precio, anioPublicacion);
        this.genero = genero;
    }

    // --- Getters y Setters ---

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if (genero != null && !genero.trim().isEmpty()) {
            this.genero = genero.trim();
        }
    }

    // --- Implementacion de Describible ---

    @Override
    public String getDescripcion() {
        return String.format(
            "[Novela] Titulo: %s | Genero: %s | Autor(es): %s",
            getTitulo(), genero, getNombresAutores()
        );
    }

    // --- Implementacion de getTipo ---

    @Override
    public String getTipo() {
        return "Novela (" + genero + ")";
    }
}
