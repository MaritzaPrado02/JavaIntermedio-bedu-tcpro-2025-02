import java.util.concurrent.*;
import java.util.concurrent.ThreadLocalRandom;

public class MovilidadApp {

    // Simula el cálculo de la ruta con latencia de 2 a 3 segundos
    public static CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("🚦 Calculando ruta...");
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3001));
                return "Ruta: Centro -> Norte";
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al calcular la ruta.");
            }
        });
    }

    // Simula la estimación de tarifa con latencia de 1 a 2 segundos
    public static CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("💰 Estimando tarifa...");
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2001));

                //Simula posible error
                if (Math.random() < 0.3) throw new RuntimeException("Demanda demasiado alta");

                return 82.00;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al estimar la tarifa.");
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> rutaFuture = calcularRuta();
        CompletableFuture<Double> tarifaFuture = estimarTarifa();

        // Combinar ambas operaciones una vez que terminen
        rutaFuture.thenCombine(tarifaFuture, (ruta, tarifa) ->
                        "✅ " + ruta + " | Tarifa estimada: $" + tarifa
                )
                .exceptionally(error ->
                        "❌ Ocurrió un problema al procesar tu solicitud: " + error.getMessage()
                )
                .thenAccept(System.out::println);

        Thread.sleep(5000);
    }
}
