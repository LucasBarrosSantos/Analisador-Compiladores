package lexer;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public final class TokensAll {

    private int posicao;
    private List<String> tokens = new ArrayList();
    private String token = new String();

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public void add(String token, int idTabela) throws IOException {
        String n = "";
        int posicaoAtual = posicaoToken(token);

        if (getTokens().isEmpty()) {
            tokens.add(posicaoAtual + " " + token + " " + idTabela + "\n");

            // Token que vai para a tabela.txt
            tokenSimbolo(token + " " + posicaoAtual + "\n");
            token(idTabela  + " " + posicaoAtual + "\n");
        }
        if (getTokens().size() > 1 && containsTheToken(token)) {           
           for (String s : getTokens()) {
                if (s.contains(token)) {
                    n = s.substring(0, Math.min(s.length(), 1));
                    break;
                }
            }            
            token(idTabela + " " + n + "\n");
        }

        if (canAdd(token + " " + idTabela + "\n")) {

            tokens.add(posicaoAtual + " " + token + " " + idTabela + "\n");

            // Token que vai para a tabela.txt
            tokenSimbolo(token + " " + posicaoAtual + "\n");
            token(idTabela + " " + posicaoAtual + "\n");
        }

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean canAdd(String token) {
        if (!getTokens().isEmpty()) {
            for (String s : getTokens()) {
                if (s.contains(token)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int posicaoToken(String token) {
        for (String s : getTokens()) {
            if (s.contains(token)) {
                return getPosicao();
            }
        }
        return posicao += 1;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public boolean containsTheToken(String token) {
        return getTokens().stream().anyMatch((t) -> (t.contains(token)));
    }

    public static void main(String[] args) throws IOException {

        TokensAll all = new TokensAll();

        all.add("main", 300);
        all.add("soma", 123);
        all.add("x", 125);
        all.add("y", 125);
        all.add("z", 40);
        all.add("Soma", 41);
        all.add("cont", 91);
        all.add("max", 93);
        all.add("Soma", 41);
        all.add("y", 125);
        all.add("x", 125);

        all.getTokens().stream().forEach((t) -> {
            System.out.println(t + " ");
        });
    }

    public static void tokenSimbolo(String yytex) throws IOException {
        File file = new File("").getAbsoluteFile();
        //"/src/lexer/Arquivo.txt";  

        File arq = new File(file + "/src/lexer/Simbolos.txt");

        //neste ponto criamos o arquivo fisicamente
        arq.createNewFile();

        //Devemos passar no construtor do FileWriter qual arquivo
        // vamos manipular.
        // Esse construtor aceita dois tipos de parâmetros,
        // File ou String.
        //O parâmetro true indica que reescrevemos no arquivo
        // sem apagar o que já existe.
        // O false apagaria o conteúdo do arquivo e escreveria
        // o novo conteúdo.
        // Se não usar o 2° parâmetro, ele por padrão será false.
        //O mais importante, essa linha abre o fluxo do arquivo 
        FileWriter fileWriter = new FileWriter(arq, true);
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(yytex);
            //o método flush libera a escrita no arquivo
            printWriter.flush();

            //No final precisamos fechar o arquivo
        }

    }

    public static void token(String yytex) throws IOException {
        File file = new File("").getAbsoluteFile();
        //"/src/lexer/Arquivo.txt";  

        File arq = new File(file + "/src/lexer/Tokens.txt");

        //neste ponto criamos o arquivo fisicamente
        arq.createNewFile();

        //Devemos passar no construtor do FileWriter qual arquivo
        // vamos manipular.
        // Esse construtor aceita dois tipos de parâmetros,
        // File ou String.
        //O parâmetro true indica que reescrevemos no arquivo
        // sem apagar o que já existe.
        // O false apagaria o conteúdo do arquivo e escreveria
        // o novo conteúdo.
        // Se não usar o 2° parâmetro, ele por padrão será false.
        //O mais importante, essa linha abre o fluxo do arquivo 
        FileWriter fileWriter = new FileWriter(arq, true);
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(yytex);
            //o método flush libera a escrita no arquivo
            printWriter.flush();

            //No final precisamos fechar o arquivo
        }

    }
}
