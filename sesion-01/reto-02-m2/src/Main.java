import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<MaterialCurso> materiales = new ArrayList<>();
        materiales.add(new Video("Introducción a la IA", "Emanuel", 18));
        materiales.add(new Video("Redes neuronales", "Ariana", 30));
        materiales.add(new Articulo( "Historia de la IA", "Emanuel", 1450));
        materiales.add(new Articulo("Etica en el uso de la IA", "Teresa", 850));
        materiales.add(new Ejercicio("Entrenar un modelo simple", "Ariana"));
        materiales.add(new Ejercicio("Clasificación de imagenes con IA", "Ariana"));

        System.out.println("📚 Todos los materiales:");
        mostrarMateriales(materiales);

        System.out.println("\n🕒 Duración total de videos:");
        contarDuracionVideos(filtrarVideos(materiales));

        System.out.println("\n📌 Marcando ejercicios como revisados...");
        marcarEjerciciosRevisados(materiales);

        System.out.println("\n🔍 Materiales del autor 'Emanuel':");
        filtrarPorAutor(materiales, m -> m.getAutor().equalsIgnoreCase("Emanuel"))
                .forEach(MaterialCurso::mostrarDetalle);
    }

    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        for (MaterialCurso m : lista) {
            m.mostrarDetalle();
        }
    }

    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = lista.stream().mapToInt(Video::getDuracion).sum();
        System.out.println("🕒 Total: " + total + " minutos");
    }

    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        for (Object obj : lista) {
            if (obj instanceof Ejercicio e) {
                e.marcarRevisado();
            }
        }
    }

    public static List<Video> filtrarVideos(List<MaterialCurso> materiales) {
        List<Video> videos = new ArrayList<>();
        for (MaterialCurso m : materiales) {
            if (m instanceof Video v) {
                videos.add(v);
            }
        }
        return videos;
    }

    public static List<MaterialCurso> filtrarPorAutor(List<MaterialCurso> lista, Predicate<MaterialCurso> filtro) {
        List<MaterialCurso> resultado = new ArrayList<>();
        for (MaterialCurso m : lista) {
            if (filtro.test(m)) {
                resultado.add(m);
            }
        }
        return resultado;
    }
}
