package simubank.bankapi.controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class APICommunication {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();

        // Construire le corps de la première requête
        String requestBodyFirst = "{\n" +
                "  \"entete\": \"DemandePorteur\",\n" +
                "  \"horodatage\": \"2024-02-14T12:00:00\",\n" +
                "  \"emetteur\": \"2\",\n" +
                "  \"banque\": \"\",\n" +
                "  \"cardNumber\": \"972543412313466\",\n" +
                "  \"montant\": 50,\n" +
                "  \"reponse\":\"\"\n" +
                "}";

        // Construire la première requête à envoyer à la première API
        HttpRequest requestToFirstApi = HttpRequest.newBuilder()
                .uri(URI.create("https://1240-2001-861-e3d1-f460-35c4-9319-2df4-6ef7.ngrok-free.app/transfert"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBodyFirst))
                .build();

        // Envoyer la première requête à la première API
        CompletableFuture<HttpResponse<String>> responseFutureFirst = httpClient.sendAsync(requestToFirstApi, HttpResponse.BodyHandlers.ofString());

        // Traiter la réponse de la première API
        responseFutureFirst.thenAccept(responseFirst -> {
            System.out.println("Statut de la réponse de la première API : " + responseFirst.statusCode());
            System.out.println("Corps de la réponse de la première API : " + responseFirst.body());

            // Vérifier si la réponse de la première API est correcte avant d'exécuter la deuxième requête
            if (responseFirst.statusCode() == 200) {
                // Construire la deuxième requête à envoyer à la première API
                String requestBodySecond = "{\n" +
                        "  \"entete\": \"Reponse\",\n" +
                        "  \"horodatage\": \"2024-02-14T12:00:00\",\n" +
                        "  \"emetteur\": \"2\",\n" +
                        "  \"banque\": \"LCL\",\n" +
                        "  \"cardNumber\": \"972543412313466\",\n" +
                        "  \"montant\": 50,\n" +
                        "  \"reponse\":\"DEBIT_OK\"\n" +
                        "}";

                // Construire la deuxième requête à envoyer à la première API
                HttpRequest requestToSecondApi = HttpRequest.newBuilder()
                        .uri(URI.create("https://1240-2001-861-e3d1-f460-35c4-9319-2df4-6ef7.ngrok-free.app/transfert"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(requestBodySecond))
                        .build();

                // Envoyer la deuxième requête à la première API
                CompletableFuture<HttpResponse<String>> responseFutureSecond = httpClient.sendAsync(requestToSecondApi, HttpResponse.BodyHandlers.ofString());

                // Traiter la réponse de la deuxième API
                responseFutureSecond.thenAccept(responseSecond -> {
                    System.out.println("Statut de la réponse de la deuxième API : " + responseSecond.statusCode());
                    System.out.println("Corps de la réponse de la deuxième API : " + responseSecond.body());
                }).join(); // Attendre la fin de l'exécution de la deuxième requête
            }
        }).join(); // Attendre la fin de l'exécution de la première requête
    }
}
