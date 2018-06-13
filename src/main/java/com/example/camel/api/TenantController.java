package com.example.camel.api;

import com.example.camel.model.Tenant;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("tenant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TenantController {

    @Autowired
    CamelContext camelContext;

    @Autowired
    ProducerTemplate producerTemplate;

    @GET
    public Response ping() {
        return Response.ok("Alive").build();
    }

    @POST
    public Response receiveTenantRequest(Tenant tenant) {

        Exchange exchange = ExchangeBuilder.anExchange(camelContext).withBody(tenant).build();
        Exchange response = producerTemplate.send("direct:REST", exchange);
        UUID uniqueRequestId = (UUID) response.getIn().getHeader("ID");
        return Response.ok("Request Received and Processing:" + uniqueRequestId).build();
    }

}
