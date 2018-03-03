package com.tutorialspoint;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        App app = (App) context.getBean("app");
        Event event = context.getBean(Event.class);
        app.logEvent(event, "Some event for 1");

        event = context.getBean(Event.class);
        app.logEvent(event, "Some event for 2");

        context.close();
    }

    private void logEvent(Event event, String msg) {
      String message = msg.replaceAll(String.valueOf(client.getId()), client.getName());
      event.setMessage(message);

      eventLogger.logEvent(event);
    }
}
