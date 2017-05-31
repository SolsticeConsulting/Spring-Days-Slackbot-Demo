package com.solstice;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Justin Baumgartner on 5/14/17.
 */
@Component
public class ApiAiClient {

    @Value("${apiAiToken}")
    private String apiAiToken;

    private static final Logger logger = LoggerFactory.getLogger(ApiAiClient.class);

    public Result processText(String text) {
        AIConfiguration configuration = new AIConfiguration(apiAiToken);
        AIDataService dataService = new AIDataService(configuration);
        AIRequest request = new AIRequest(text);

        try {
            AIResponse response = dataService.request(request);
            if (response.getStatus().getCode() == 200) {
                return response.getResult();
            } else {
                logger.debug("API AI response invalid. Response: {}", response);
            }

        } catch (Exception e) {
            logger.error("API AI request failed. Exception: {}", e.getMessage());
        }
        return null;
    }

}
