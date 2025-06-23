import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulacionHospital {
    public static void main(String[] args) {
        System.out.println("🏥 Iniciando acceso a la Sala de cirugía...\n");

        RecursoMedico uci = new RecursoMedico("UCI");

        // Control acceso profesionales
        Runnable draSanchez = () -> uci.usar("Dra. López");
        Runnable drGomez = () -> uci.usar("Dr. Elias");
        Runnable enfermeraLuna = () -> uci.usar("Enfermera Vidal");
        Runnable drRivera = () -> uci.usar("Dr. Prado");
        Runnable draFernandez = () -> uci.usar("Dra. Cortés");

        // ExecutorService con 4 hilos
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Ejecutar los accesos
        executor.execute(draSanchez);
        executor.execute(drGomez);
        executor.execute(enfermeraLuna);
        executor.execute(drRivera);
        executor.execute(draFernandez);

        executor.shutdown();
    }
}
