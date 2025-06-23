public class Articulo extends MaterialCurso {
    // Variable
    private int palabras; // como conteo

    // Constructor
    public Articulo(String titulo, String autor, int palabras) {
        super(titulo, autor);
        this.palabras = palabras;
    }

    // Métodos
    @Override
    public void mostrarDetalle() {
        System.out.println("📄 Artículo: " + titulo + " - Autor: " + autor + " - Palabras: " + palabras);
    }
}
