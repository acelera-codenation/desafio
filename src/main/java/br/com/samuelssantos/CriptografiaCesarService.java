package br.com.samuelssantos;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class CriptografiaCesarService {

    private static final int TAMANHO_ALFABETO = 26;
    private static final char LETRA_A = 'a';

    public String doEncriptar(String texto, int numeroCasas) {
        StringBuilder textoCifrado = new StringBuilder();
        List<Character> pontuacao = Arrays.asList(
                ' ', '.', '!', '?', ',',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        char[] msg = texto.toCharArray();
        for (char character : msg) {
            if (pontuacao.contains(character)) textoCifrado.append(character);
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