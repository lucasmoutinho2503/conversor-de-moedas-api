import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.*;

public class Main {

    private static final String API_KEY = "823e364bd565c30675a9c580";

    private static final String[] MOEDAS = {
            "USD", // 1
            "EUR", // 2
            "BRL", // 3
            "GBP", // 4
            "JPY", // 5
            "AUD"  // 6
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Conversor de Moedas ===");
        mostrarOpcoes();

        System.out.print("Escolha a moeda de ORIGEM (1-6): ");
        int escolhaOrigem = scanner.nextInt();
        System.out.print("Escolha a moeda de DESTINO (1-6): ");
        int escolhaDestino = scanner.nextInt();

        if (escolhaOrigem < 1 || escolhaOrigem > 6 || escolhaDestino < 1 || escolhaDestino > 6) {
            System.out.println("Escolha inválida.");
            return;
        }

        String moedaOrigem = MOEDAS[escolhaOrigem - 1];
        String moedaDestino = MOEDAS[escolhaDestino - 1];

        System.out.print("Digite o valor a ser convertido: ");
        double valor = scanner.nextDouble();

        converterMoeda(moedaOrigem, moedaDestino, valor);
    }

    public static void mostrarOpcoes() {
        System.out.println("Moedas disponíveis:");
        for (int i = 0; i < MOEDAS.length; i++) {
            System.out.printf("%d - %s\n", i + 1, MOEDAS[i]);
        }
    }

    public static void converterMoeda(String from, String to, double amount) {
        try {
            String url = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", API_KEY, from, to);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

                if (jsonObject.get("result").getAsString().equals("success")) {
                    double taxaConversao = jsonObject.get("conversion_rate").getAsDouble();
                    double resultado = taxaConversao * amount;

                    System.out.printf("\n%.2f %s = %.2f %s\n", amount, from, resultado, to);
                } else {
                    System.out.println("Erro na conversão: " + jsonObject.get("error-type"));
                }
            } else {
                System.out.println("Erro na requisição: código " + response.statusCode());
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
