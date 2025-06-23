import java.util.concurrent.locks.ReentrantLock;

public class RecursoMedico {
    private final String nombre;
    private final ReentrantLock lock = new ReentrantLock();

    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    public void usar(String profesional) {
        System.out.println("🔄 " + profesional + " intentando acceder a " + nombre + "...");
        lock.lock(); // bloqueo del recurso
        try {
            System.out.println("👩‍⚕️ " + profesional + " ha ingresado a " + nombre);
            Thread.sleep(1000); // Simulación de acceso al recurso
            System.out.println("✅ " + profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            System.err.println("❌ Error en " + profesional + ": " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
