package br.com.samuelssantos;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@ApplicationScoped
public class ArquivoDesafioService {

    @ConfigProperty(name = "arquivo_resposta", defaultValue = "answer.json")
    private String fileName;

    public void salvar(RespostaDesafio desafio) {
        if (desafio == null) throw new NullPointerException();
        Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true));
        try (FileWriter writer = new FileWriter(fileName)) {
            jsonb.toJson(desafio, writer);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public byte[] ler() throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        byte [] content = new byte[fis.available()];
        fis.read(content);
        return content;
    }

}
