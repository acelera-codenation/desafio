package br.com.samuelssantos;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey="codenation-api")
public interface CodenationApiService {

    @GET
    @Path("/v1/challenge/dev-ps/generate-data")
    @Produces(MediaType.APPLICATION_JSON)
    RespostaDesafio getDesafio(@QueryParam("token") String token);

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/v1/challenge/dev-ps/submit-solution")
    Score submitDesafio(@QueryParam("token") String token, @MultipartForm MultipartBody data);
}
