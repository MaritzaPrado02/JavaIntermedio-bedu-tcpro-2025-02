public abstract class MaterialCurso {
    // Variables
    protected String titulo;
    protected String autor;

    // Constructor
    public MaterialCurso(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    // Métodos
    public abstract void mostrarDetalle();

    public String getAutor() {
        return autor;
    }
}
