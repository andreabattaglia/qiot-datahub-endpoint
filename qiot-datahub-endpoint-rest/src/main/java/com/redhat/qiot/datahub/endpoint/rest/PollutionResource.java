package com.redhat.qiot.datahub.endpoint.rest;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;


import org.jboss.resteasy.annotations.jaxrs.QueryParam;
import org.slf4j.Logger;


@Path("/pollution")
@ApplicationScoped
public class PollutionResource {

    @Inject
    Logger LOGGER;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void collect(@QueryParam String json) {
        LOGGER.info("Receiving pollution data. Storing: {}", json);
    }

}
