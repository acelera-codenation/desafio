package br.com.samuelssantos;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CriptografiaCesarService {

    private static final int TAMANHO_ALFABETO = 26;
    private static final char LETRA_A = 'a';

    @ConfigProperty(name = "caracteres_ignorados")
    public String caracteresIgnorados;

    private List<Character> getCaracteresIgnorados() {
        return caracteresIgnorados
                .chars()
                .mapToObj(e -> (char)e)
                .collect(Collectors.toList());
    }

    public String doEncriptar(String texto, int numeroCasas) {
        StringBuilder textoCifrado = new StringBuilder();
        List<Character> ignorados = getCaracteresIgnorados();
        char[] msg = texto.toCharArray();
        for (char character : msg) {
            if (ignorados.contains(character)) textoCifrado.append(character);
            else {
                int posicaoInicial = character - LETRA_A;
                int novaPosicao = (posicaoInicial + numeroCasas) % TAMANHO_ALFABETO;
                char novaLetra = (char) (LETRA_A + novaPosicao);
                textoCifrado.append(novaLetra);
            }
        }

        return textoCifrado.toString();
    }

    public String doDesencriptar(String texto, int numeroCasas) {
        return doEncriptar(texto, TAMANHO_ALFABETO - (numeroCasas % TAMANHO_ALFABETO));
    }
}