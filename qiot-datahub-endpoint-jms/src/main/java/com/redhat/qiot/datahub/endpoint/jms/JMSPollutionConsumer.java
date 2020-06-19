package com.redhat.qiot.datahub.endpoint.jms;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;


import org.slf4j.Logger;


import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;


/**
 * A bean consuming prices from the JMS queue.
 */
@ApplicationScoped
public class JMSPollutionConsumer implements Runnable {

    @Inject
    Logger LOGGER;

    @Inject
    ConnectionFactory connectionFactory;

    private final ExecutorService scheduler = Executors
            .newSingleThreadExecutor();

    void onStart(@Observes StartupEvent ev) {
        scheduler.submit(this);
    }

    void onStop(@Observes ShutdownEvent ev) {
        scheduler.shutdown();
    }

    @Override
    public void run() {
        try (JMSContext context = connectionFactory
                .createContext(Session.AUTO_ACKNOWLEDGE)) {
            JMSConsumer consumer = context
                    .createConsumer(context.createQueue("pollution"));
            while (true) {
                Message message = consumer.receive();
                if (message == null) {
                    // receive returns `null` if the JMSConsumer is closed
                    return;
                }
                TextMessage textMessage = (TextMessage) message;
                LOGGER.info(
                        "JMS: Received pollution message {}",
                        textMessage.getText());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
