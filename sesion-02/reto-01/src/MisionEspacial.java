import java.util.concurrent.*;

public class MisionEspacial {
    public static void main(String[] args) {
        System.out.println("🚀 Simulación de misión espacial iniciada...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Enviar tareas al pool
        Future<String> nav = executor.submit(new SistemaNavegacion());
        Future<String> soporte = executor.submit(new SistemaSoporteVital());
        Future<String> termico = executor.submit(new SistemaControlTermico());
        Future<String> com = executor.submit(new SistemaComunicaciones());

        try {
            // Obtener resultados
            System.out.println(com.get());
            System.out.println(soporte.get());
            System.out.println(termico.get());
            System.out.println(nav.get());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("❌ Error durante la simulación: " + e.getMessage());
        } finally {
            executor.shutdown();
        }

        System.out.println("✅ Todos los sistemas reportan estado operativo.");
    }
}
