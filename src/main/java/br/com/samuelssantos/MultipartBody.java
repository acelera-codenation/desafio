package br.com.samuelssantos;

import org.jboss.resteasy.annotations.jaxrs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartFilename;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.core.MediaType;

public class MultipartBody {

    @FormParam("answer")
    @PartFilename("answer.json")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] answer;

}