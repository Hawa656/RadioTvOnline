package com.example.radiotvolnline.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class ApiService {
    private final String apiKey = "votre-api-key";
    private final String apiSecret = "votre-api-secret";

    public String getPullUrls(String channelId) throws IOException {
        // Encodez votre clé d'API et votre secret en base64 pour l'authentification
        String auth = apiKey + ":" + apiSecret;
        byte[] authBytes = auth.getBytes();
        String encodedAuth = Base64.getEncoder().encodeToString(authBytes);

        // Construire l'URL de l'API en utilisant le canal ID en tant que paramètre
        String apiUrl = "https://api.livestream.com/v3/accounts/{account_id}/channels/" + channelId + "/pull_urls";

        // Créer une instance HttpURLConnection et ajouter les en-têtes d'authentification
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "Basic " + encodedAuth);

        // Effectuer une requête HTTP GET et récupérer la réponse de l'API
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Retourner la réponse de l'API sous forme de chaîne de caractères
        return response.toString();
    }
}

