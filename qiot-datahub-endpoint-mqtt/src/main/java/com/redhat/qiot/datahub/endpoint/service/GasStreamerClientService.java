package com.redhat.qiot.datahub.endpoint.service;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


/**
 * @author abattagl
 *
 */
@Path("/v1")
@RegisterRestClient(configKey = "gas-streamer-api")
public interface GasStreamerClientService {

    @GET
    @Path("/gas")
    @Consumes(MediaType.TEXT_PLAIN)
    void sendToStreamer() throws Exception;
}
