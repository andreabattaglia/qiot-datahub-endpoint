package com.redhat.qiot.datahub.endpoint.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;

/**
 * Message coming from MQTT have a byte[] payload
 */
@ApplicationScoped
public class MQTTPollutionEndpointService {

    @Inject
    Logger LOGGER;

    @Incoming("pollution-external")
    @Outgoing("pollution-internal")
    public String collect(byte[] json) {
	String data = new String(json);
	LOGGER.info("MQTT: Received POLLUTION message {}", data);
	return data;
    }

}