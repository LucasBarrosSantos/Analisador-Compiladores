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
    
    // Definição de que as palavras reservadas iniciaram de 300
    // Para que não tenha conflitos com a numeração da Tabela ASC
    int id = 310;

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }
    
    // Adiciona uma palavra Todos os tokens exceto um ID(lexema)
    public void add(String token) throws IOException {
        
        if (getTokens().isEmpty()) {
            tokens.add(token + "\n");

            // Token que vai para a tabela.txt
            token(getId() + "\n");
            id += 10;
        }
        if (canAdd(token + "\n")) {

            tokens.add(token + "\n");

            // Token que vai para a tabela.txt
            token(getId() + "\n");
            id += 10;
        }

    }
    
    // Adiciona uma ID com um identificador
    public void add(String token, int idTabela) throws IOException {
        String n = "";
        int posicaoAtual = posicaoToken(token);

        if (getTokens().isEmpty()) {
            tokens.add(token);

            // Token que vai para a tabela.txt
            // Se for palavra reservada add apenas o identificador 
            // Ex.: token = public, então add no arquivo simbolo 300
            //tokenSimbolo(token + " " + posicaoAtual + "\n");
            token(idTabela + " " + posicaoAtual + "\n");
        }
        if (getTokens().size() > 1 && containsTheToken(token)) {
            for (String s : getTokens()) {
                if (s.equals(token)) {
                   token(idTabela + " " + getPosicao() + "\n");
                }
            }
        }

        if (canAdd(token + " " + idTabela + "\n")) {

            tokens.add(token);

            // Token que vai para a tabela.txt
            //tokenSimbolo(token + " " + posicaoAtual + "\n");
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
        for(String s : getTokens()){
            if(!getTokens().isEmpty()){
                if(!s.contains(token)){
                    return true;
                }
            }
        }
        //return !(!getTokens().isEmpty() && !getTokens().stream().noneMatch((s) -> (s.contains(token))));
        return false;
    }

    public int posicaoToken(String token) {
        for (String s : getTokens()) {
            if (s.equals(token)) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void tokenSimbolo(String yytex) throws IOException {
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

    public void token(String yytex) throws IOException {
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
