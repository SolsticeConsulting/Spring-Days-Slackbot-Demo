package com.solstice;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by thomasklein on 5/25/17.
 */
@Service
public class GiphyClient {

    // This includes the public beta API key
    // Details found here: https://github.com/Giphy/GiphyAPI
    private static final String giphyURL = "http://api.giphy.com/v1/gifs/search?q=epic+fail&api_key=dc6zaTOxFJmzC&rating=pg-13";

    @Async
    public static Future<String> getFailureGif() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(giphyURL, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode data = root.path("data");
            Iterator<JsonNode> gifData = data.getElements();
            List<JsonNode> gifs = new ArrayList<>();
            gifData.forEachRemaining(gifs::add);

            return new AsyncResult<>(getRandomGif(gifs));
        } catch(IOException e) {
            return new AsyncResult<>(e.toString());
        }
    }

    private static String getRandomGif(List<JsonNode> gifs) {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(gifs.size());
        Iterator<JsonNode> images = gifs.get(index).get("images").getElements();
        List<JsonNode> imagesSizes = new ArrayList<>();
        images.forEachRemaining(imagesSizes::add);
        for (JsonNode node: imagesSizes) {
            if (node.get("height").asInt() <= 100 && node.get("width").asInt() <= 300) {
                return node.get("url").asText();
            }
        }
        return "";
    }
}
