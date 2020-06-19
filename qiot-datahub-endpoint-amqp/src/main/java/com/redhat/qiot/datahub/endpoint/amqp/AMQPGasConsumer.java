package com.redhat.qiot.datahub.endpoint.amqp;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;


/**
 * A bean consuming data from the "prices" AMQP queue and applying some
 * conversion.
 * The result is pushed to the "my-data-stream" stream which is an in-memory
 * stream.
 */
@ApplicationScoped
public class AMQPGasConsumer {

    @Inject
    Logger LOGGER;

    @Incoming("gas")
    public void process(String json) {
        LOGGER.info("AMQP: Received gas message {}", json);
    }

}