import java.util.*;
import java.util.stream.*;

public class PizzeriaApp {
    public static void main(String[] args) {
        List<Pedido> pedidos = List.of(
                new Pedido("José", "domicilio", "324-9089"),
                new Pedido("Marco", "local", "311-4560"),
                new Pedido("Janina", "domicilio", null),
                new Pedido("Serina", "domicilio", "323-6734"),
                new Pedido("Martin", "local", null),
                new Pedido("Julio", "domicilio", "324-8765")
        );

        pedidos.stream()
                .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio")) // solo a domicilio
                .map(Pedido::getTelefono)
                .flatMap(Optional::stream)
                .map(num -> "📞 Confirmación enviada al número: " + num)
                .forEach(System.out::println);
    }
}
