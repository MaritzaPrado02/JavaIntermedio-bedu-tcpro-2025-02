import reactor.core.publisher.*;
import reactor.util.function.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MeridianPrimeReactive {

    private static final Random random = new Random();
    private static final AtomicInteger rojoConsecutivo = new AtomicInteger(0);
    private static final Flux<String> sensoresTrafico = Flux.interval(Duration.ofMillis(500))
            .map(i -> random.nextInt(101)) // 0-100%
            .doOnNext(c -> System.out.println("🚗 Trafico: " + c + "%"))
            .filter(c -> c > 70)
            .map(c -> "🚗 Alerta: Congestión del " + c + "% en Avenida Solar")
            .onBackpressureBuffer(); // simula backpressure

    private static final Flux<String> contaminacionAire = Flux.interval(Duration.ofMillis(600))
            .map(i -> random.nextInt(80)) // PM2.5: 0-79
            .doOnNext(p -> System.out.println("🌫️ PM2.5: " + p + " ug/m3"))
            .filter(p -> p > 50)
            .map(p -> "🌫️ Alerta: Contaminación alta (PM2.5: " + p + " ug/m3)");

    private static final Flux<String> accidentesViales = Flux.interval(Duration.ofMillis(800))
            .map(i -> {
                String[] prioridades = {"Baja", "Media", "Alta"};
                return prioridades[random.nextInt(3)];
            })
            .doOnNext(p -> System.out.println("🚑 Accidente prioridad: " + p))
            .filter(p -> p.equals("Alta"))
            .map(p -> "🚑 Emergencia vial: Accidente con prioridad Alta");

    private static final Flux<String> trenesMaglev = Flux.interval(Duration.ofMillis(700))
            .map(i -> random.nextInt(11)) // 0-10 minutos
            .doOnNext(r -> System.out.println("🚝 Retraso tren: " + r + " min"))
            .filter(r -> r > 5)
            .map(r -> "🚝 Tren maglev con retraso crítico: " + r + " minutos")
            .onBackpressureBuffer();

    private static final Flux<String> semaforos = Flux.interval(Duration.ofMillis(400))
            .map(i -> {
                String[] estados = {"Verde", "Amarillo", "Rojo"};
                return estados[random.nextInt(3)];
            })
            .doOnNext(e -> System.out.println("🚦 Estado semáforo: " + e))
            .filter(e -> {
                if (e.equals("Rojo")) {
                    int count = rojoConsecutivo.incrementAndGet();
                    return count >= 3;
                } else {
                    rojoConsecutivo.set(0);
                    return false;
                }
            })
            .map(e -> "🚦 Semáforo en Rojo detectado 3 veces seguidas en cruce Norte");

    // Alerta global si se detectan 3 o más eventos críticos "al mismo tiempo"
    private static final Flux<String> sistemaGlobal = Flux.merge(
                    sensoresTrafico, contaminacionAire, accidentesViales, trenesMaglev, semaforos
            )
            .window(Duration.ofSeconds(1)) // ventana de 1 segundo para agrupar eventos simultáneos
            .flatMap(window -> window.collectList()
                    .filter(lista -> lista.size() >= 3)
                    .map(lista -> {
                        lista.forEach(System.out::println);
                        return "🚨 Alerta global: Múltiples eventos críticos detectados en Meridian Prime";
                    })
            );

    public static void main(String[] args) throws InterruptedException {
        System.out.println("🌐 Iniciando monitoreo reactivo en Meridian Prime...\n");

        // Suscripciones a cada flujo individual (opcional)
        sensoresTrafico.subscribe();
        contaminacionAire.subscribe();
        accidentesViales.subscribe();
        trenesMaglev.subscribe();
        semaforos.subscribe();

        // Alerta global
        sistemaGlobal.subscribe(alerta -> {
            System.out.println("\n" + alerta + "\n");
        });

        // Mantener app corriendo (simulación continua)
        Thread.sleep(30000); // 30 segundos de simulación
    }
}
