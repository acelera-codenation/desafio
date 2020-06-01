package br.com.samuelssantos;

import javax.json.bind.annotation.JsonbProperty;

public class RespostaDesafio {

    @JsonbProperty("numero_casas")
    public int numeroCasas;
    public String token;
    public String cifrado;
    public String decifrado;
    @JsonbProperty("resumo_criptografico")
    public String resumoCriptografico;

    @Override
    public String toString() {
        return "RespostaDesafio{" +
                "numeroCasas=" + numeroCasas +
                ", token='" + token + '\'' +
                ", cifrado='" + cifrado + '\'' +
                ", decifrado='" + decifrado + '\'' +
                ", resumoCriptografico='" + resumoCriptografico + '\'' +
                '}';
    }
}
