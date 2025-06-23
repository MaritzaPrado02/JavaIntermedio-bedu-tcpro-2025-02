import java.util.*;
import java.util.stream.*;

public class ClinicaApp {
    public static void main(String[] args) {
        // Datos de ejemplo
        Sucursal centro = new Sucursal("Centro", List.of(
                new Encuesta("Maritza", "El tiempo de espera fue excesivo.", 1),
                new Encuesta("Marco", null, 5),
                new Encuesta("Mariel", "No me dieron muchos detalles sobre mi diagnostico.", 2)
        ));

        Sucursal norte = new Sucursal("Norte", List.of(
                new Encuesta("Fernando", "La atención fue buena para mí.", 5),
                new Encuesta("Marta", null, 2),
                new Encuesta("Luis", "Esperé más de 3 horas", 1)
        ));

        List<Sucursal> sucursales = List.of(centro, norte);

        // Procesamiento con Stream API y flatMap
        sucursales.stream()
                .flatMap(sucursal ->
                        sucursal.getEncuestas().stream()
                                .filter(e -> e.getCalificacion() <= 3)
                                .flatMap(e -> e.getComentario()
                                        .map(comentario ->
                                                Stream.of("Sucursal " + sucursal.getNombre()
                                                        + ": Seguimiento a paciente con comentario: \"" + comentario + "\""))
                                        .orElseGet(Stream::empty))
                )
                .forEach(System.out::println);
    }
}
