import java.util.concurrent.*;
import java.util.concurrent.ThreadLocalRandom;

public class AeropuertoControl {

    private static final ExecutorService executor = Executors.newFixedThreadPool(4);

    // Simula verificación con latencia y probabilidad de éxito
    public static CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(randomDelay());
                boolean disponible = Math.random() < 0.8; // 80% de probabilidad
                System.out.println("🛣️ Pista disponible: " + disponible);
                return disponible;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar pista.");
            }
        }, executor);
    }

    public static CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(randomDelay());
                boolean favorable = Math.random() < 0.85; // 85% de probabilidad
                System.out.println("🌦️ Clima favorable: " + favorable);
                return favorable;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar clima.");
            }
        }, executor);
    }

    public static CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(randomDelay());
                boolean despejado = Math.random() < 0.9; // 90% de probabilidad
                System.out.println("🚦 Tráfico aéreo despejado: " + despejado);
                return despejado;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar tráfico aéreo.");
            }
        }, executor);
    }

    public static CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(randomDelay());
                boolean disponible = Math.random() < 0.95; // 95% de probabilidad
                System.out.println("👷‍♂️ Personal disponible: " + disponible);
                return disponible;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar personal en tierra.");
            }
        }, executor);
    }

    private static int randomDelay() {
        return ThreadLocalRandom.current().nextInt(2000, 3001); // 2 a 3 segundos
    }

    public static void main(String[] args) {
        System.out.println("🛫 Verificando condiciones para aterrizaje...\n");

        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        CompletableFuture<Void> allChecks = CompletableFuture.allOf(pista, clima, trafico, personal);

        allChecks.thenApply(v -> {
                    try {
                        boolean condicionesOk = pista.get() && clima.get() && trafico.get() && personal.get();
                        return condicionesOk;
                    } catch (Exception e) {
                        throw new CompletionException("❌ Error al combinar verificaciones.", e);
                    }
                })
                .thenAccept(condicionesOk -> {
                    if (condicionesOk) {
                        System.out.println("\n🛬 Aterrizaje autorizado: todas las condiciones óptimas.");
                    } else {
                        System.out.println("\n🚫 Aterrizaje denegado: condiciones no óptimas.");
                    }
                })
                .exceptionally(ex -> {
                    System.out.println("\n❌ Error en el sistema: " + ex.getMessage());
                    return null;
                })
                .join();

        executor.shutdown();
    }
}
