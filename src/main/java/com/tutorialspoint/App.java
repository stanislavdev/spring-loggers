package com.tutorialspoint;

import com.tutorialspoint.loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger eventLogger;
    private Map<EventType, EventLogger> loggerMap;
    private EventLogger defaultLogger;

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggerMap) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggerMap = loggerMap;
        this.defaultLogger = eventLogger;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        App app = (App) context.getBean("app");

        Event event = context.getBean(Event.class);
        app.logEvent(event, "Some event for 1", EventType.INFO);

        event = context.getBean(Event.class);
        app.logEvent(event, "Some event for 2",EventType.ERROR);

        event = context.getBean(Event.class);
        app.logEvent(event, "Some event for 3", null);

        context.close();
    }

    private void logEvent(Event event, String msg, EventType eventType) {
      String message = msg.replaceAll(String.valueOf(client.getId()), client.getName());
      event.setMessage(message);

      EventLogger eventLogger = loggerMap.get(eventType);
      if (eventLogger == null){
          eventLogger = defaultLogger;
      }

      eventLogger.logEvent(event);
    }
}
