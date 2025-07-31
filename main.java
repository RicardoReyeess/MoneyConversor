import models.*;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SearchConversion search = new SearchConversion();

        try {
            System.out.println("Introduce la moneda base (ej. USD): ");
            String base = scanner.nextLine().toUpperCase();

            System.out.println("Introduce la cantidad: ");
            double cantidad = Double.parseDouble(scanner.nextLine());

            System.out.println("Introduce la moneda destino (ej. MXN): ");
            String destino = scanner.nextLine().toUpperCase();

            ExchangeResponse response = search.buscarConversion(base);
            Map<String, Double> tasas = response.conversion_rates();

            if (!tasas.containsKey(destino)) {
                System.out.println("Moneda destino no válida o no disponible.");
                return;
            }

            double tasa = tasas.get(destino);
            double resultado = cantidad * tasa;

            System.out.printf("Resultado: %.2f %s = %.2f %s\n", cantidad, base, resultado, destino);

            JsonGenerator generator = new JsonGenerator();
            generator.saveJson(response, base + "_conversion");

        } catch (NumberFormatException e) {
            System.out.println("Cantidad no válida.");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}