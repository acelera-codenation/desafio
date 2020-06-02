package br.com.samuelssantos;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@Path("/desafio-cesar")
public class CodenationDesafio {

    @Inject
    CriptografiaCesarService criptografiaCesarService;

    @ConfigProperty(name = "codenation-token")
    private String token;

    @Inject
    @RestClient
    CodenationApiService codenationApiService;

    @Inject
    ArquivoDesafioService arquivoService;

    @GET
    @Path("/enviar")
    @Produces(MediaType.APPLICATION_JSON)
    public Score desafio() {
        RespostaDesafio desafio = codenationApiService.getDesafio(token);
        desafio.decifrado = criptografiaCesarService.doDesencriptar(
                desafio.cifrado,
                desafio.numeroCasas);
        try {
            desafio.resumoCriptografico = getHash(desafio.decifrado);
            arquivoService.salvar(desafio);
            return sendFile();
        } catch (Exception e) {
            e.printStackTrace();
            return new Score();
        }
    }

    private String getHash(String texto) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(texto.getBytes(StandardCharsets.UTF_8));
        return String.format("%040x", new BigInteger(1, digest.digest()));
    }

    public Score sendFile() {
        try  {
            MultipartBody body = new MultipartBody();
            body.answer = arquivoService.ler();
            return codenationApiService.submitDesafio(token, body);
        } catch (Exception e) {
            e.printStackTrace();
            return new Score();
        }
    }
}