package com.redhat.qiot.datahub.endpoint.mqtt;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;


/**
 * Message coming from MQTT have a byte[] payload
 */
@ApplicationScoped
public class MQTTGasConsumer {

    @Inject
    Logger LOGGER;

    @Incoming("gas")
    public void collect(byte[] json) {
        LOGGER.info("MQTT: Received gas message {}", new String(json));
    }

}