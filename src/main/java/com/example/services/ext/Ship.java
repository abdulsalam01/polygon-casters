/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.services.ext;

import com.example.constants.Defaults;
import com.example.constants.Endpoints;
import com.example.constants.Environments;
import com.example.helpers.Seeder;
import com.example.models.AuthManager;
import com.example.models.SessionManager;
import com.example.models.ShipManager;
import com.example.models.DataKeeperManager;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graalvm.compiler.hotspot.replacements.Log;
import org.springframework.stereotype.Service;

/**
 *
 * @author abdulsalam
 */
@Service
public class Ship extends Base {

    private static AuthManager authManager;

    public Ship() {
        super();        
        Ship.authManager = SessionManager.getCurrentSession();
    }

    private static String[] prepareStatements() {
        Ship.authManager = Auth.getInstance().Current();
        return new String[]{Defaults.AUTH, Ship.authManager.getToken()};
    }

    public static CompletableFuture<List<ShipManager>> Runner() {
        int n = Environments.SEEDER_NUMBER;
        return Environments.IS_TESTMODE_ACTIVE ? Ship.DoManual(n) : Ship.Do();
    }
    
    public List<ShipManager> Current() {
        if (DataKeeperManager.getShips().isEmpty()) {
            try {
                return Ship.Runner().get();
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(Ship.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Log.println("Get from memory");
        return DataKeeperManager.getShips();
    }
    
    private static CompletableFuture<List<ShipManager>> Do() {
        try {
            String url = Endpoints.SHIP;
            String[] params = Ship.prepareStatements();

            // Prepare the HTTP GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header(params[0], params[1])
                    .GET()
                    .build();

            // Send the request asynchronously and handle the response stream.
            return Ship.httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
                    .thenApply(HttpResponse::body)
                    .thenApply(inputStream -> {
                        return Ship.streamProcessShipData(inputStream);
                    })
                    .exceptionally(ex -> {
                        Logger.getLogger(Ship.class.getName()).log(Level.SEVERE, "Request failed", ex);
                        return null;
                    });
        } catch (Exception ex) {
            Logger.getLogger(Ship.class.getName()).log(Level.SEVERE, "Failed to execute async request", ex);
        }

        return CompletableFuture.completedFuture(null);
    }

    private static CompletableFuture<List<ShipManager>> DoManual(int num) {
        return CompletableFuture.completedFuture(Seeder.embedShipData(num));
    }    
    
    private static List<ShipManager> streamProcessShipData(InputStream inputStream) {
        List<ShipManager> shipList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = objectMapper.getFactory();

        // Wrap InputStream with BufferedInputStream for better performance.
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             JsonParser parser = jsonFactory.createParser(bufferedInputStream)) {

            // Ensure we are starting with an array of ship objects.
            if (parser.nextToken() != JsonToken.START_ARRAY) {
                throw new IOException("Expected data to start with an array");
            }

            // Process the JSON array sequentially.
            while (parser.nextToken() != JsonToken.END_ARRAY) {
                try {
                    // Deserialize each ship object.
                    ShipManager ship = objectMapper.readValue(parser, ShipManager.class);
                    shipList.add(ship);
                } catch (IOException e) {
                    Logger.getLogger(Ship.class.getName()).log(Level.SEVERE, "Failed to process a ship", e);
                }
            }

            // Save the ships in temporary storage.
            DataKeeperManager.saveShips(shipList);
        } catch (IOException e) {
            Logger.getLogger(Ship.class.getName()).log(Level.SEVERE, "Failed to process ship data stream", e);
        }

        return shipList;
    }
}
