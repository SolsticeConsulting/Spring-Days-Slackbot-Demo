package com.solstice;

import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * Created by Justin Baumgartner on 5/14/17.
 */
@Component
public class CustomerSupportBot extends Bot {

    private final CustomerService customerService;

    @Autowired
    CustomerSupportBot(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Value("${slackBotToken}")
    private String slackToken;

    @Override
    public String getSlackToken() {
        return slackToken;
    }

    @Override
    public Bot getSlackBot() {
        return this;
    }

    @Controller(events = {EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE})
    public void onReceiveDirectMessage(WebSocketSession session, Event event) {
        String response = customerService.handleCustomerMessage(event.getUserId(), event.getText());
        reply(session, event, new Message(response));
    }

}
