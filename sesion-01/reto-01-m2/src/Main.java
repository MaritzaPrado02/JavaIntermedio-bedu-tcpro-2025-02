import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Crear listas para cada tipo de orde
        List<OrdenMasa> ordenesMasa = Arrays.asList(
                new OrdenMasa("A001", 960),
                new OrdenMasa("A002", 320)
        );

        List<OrdenPersonalizada> ordenesPersonalizadas = Arrays.asList(
                new OrdenPersonalizada("P456", 140, "Maritza Prado"),
                new OrdenPersonalizada("P567", 200, "Erika López")
        );

        List<OrdenPrototipo> ordenesPrototipo = Arrays.asList(
                new OrdenPrototipo("T333", 15, "Diseño"),
                new OrdenPrototipo("T468", 12, "Pruebas")
        );

        // Mostrar órdenes
        System.out.println("📋 Órdenes registradas:");
        mostrarOrdenes(ordenesMasa);

        System.out.println("\n📋 Órdenes registradas:");
        mostrarOrdenes(ordenesPersonalizadas);

        System.out.println("\n📋 Órdenes registradas:");
        mostrarOrdenes(ordenesPrototipo);

        // Procesar personalizadas
        System.out.println("\n💰 Procesando órdenes personalizadas...");
        procesarPersonalizadas(new ArrayList<OrdenProduccion>(ordenesPersonalizadas), 250);

        // Contar totales
        System.out.println("\n📊 Resumen total de órdenes:");
        System.out.println("🔧 Producción en masa: " + ordenesMasa.size());
        System.out.println("🛠️ Personalizadas: " + ordenesPersonalizadas.size());
        System.out.println("🧪 Prototipos: " + ordenesPrototipo.size());
    }

    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
        }
    }

    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada personalizada) {
                personalizada.aplicarCostoAdicional(costoAdicional);
            }
        }
    }
}
