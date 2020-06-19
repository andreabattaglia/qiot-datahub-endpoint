package com.redhat.qiot.datahub.endpoint.mqtt;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;


/**
 * Message coming from MQTT have a byte[] payload
 */
@ApplicationScoped
public class MQTTPollutionConsumer {

    @Inject
    Logger LOGGER;

    @Incoming("pollution")
    public void collect(byte[] json) {
        LOGGER.info("MQTT: Received pollution message {}", new String(json));
    }

}