package Lexemas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lexer.TokensAll;

public class Token {

    public int count = 310;
    /**
     * Nome do Token
     */
    public String nome;

    /**
     * Codigo ASC do Token
     */
    public int codASC;

    /**
     * Posicao do Token na Tabela
     */
    public int posicao;

    int TokenEncontrado = 0;

    /**
     * Lista de Tokens
     */
    List<Token> tokens;

    TokensAll tokensAll = new TokensAll();

    public Token() {
        tokens = new ArrayList<>();
    }

    public Token(String token) {
        this.nome = token;
    }

    public Token(String token, int codASC) {
        this.nome = token;
        this.codASC = codASC;
    }

    public Token(String token, int codASC, int posicao) {
        this.nome = token;
        this.codASC = codASC;
        this.posicao = posicao;
    }

    public Token(Token token) {
        tokens.add(token);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodASC() {
        return codASC;
    }

    public void setCodASC(int codASC) {
        this.codASC = codASC;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void addSingle(Token token) throws IOException {
        // Adicionar os TokensSingle na Lista, mas sem mandar para as tabelas
        boolean achou = false;
        token.codASC = count;
        tokens.add(token);
        
        for (Token t : tokens) {
            if (t.getNome().equals(token.getNome())) {
                token.codASC = t.getCodASC();
                if (t.getCodASC() >= count) {
                    count = t.getCodASC();
                    achou = true;
                }
                if(t.getCodASC() == token.getCodASC()){
                    count = t.getCodASC();
                    achou = true;
                }
            }
        }
        tokensAll.token("" + count + "\n");
        for(Token t : tokens){
            if(t.getCodASC() > count){
                count = t.getCodASC();
            }
        }

        if (achou) {
            count += 10;
        }

        
    }

    public void add(Token token) throws IOException {

        if (tokens.isEmpty()) {
            if (token.getPosicao() == 0) {
                token.posicao = getPosicao() + 1;
                tokenSimbolo(token.getNome() + " " + token.getPosicao() + "\n");
                token("" + token.getCodASC() + " " + token.getPosicao() + "\n");
            }
            tokens.add(token);

        } else {
            for (Token t : tokens) {
                if (!contenToken(token)) {
                    if (token.getPosicao() == 0) {
                        token.posicao = TokenEncontrado;
                    }
                    tokenSimbolo(token.getNome() + " " + token.getPosicao() + "\n");
                    token("" + token.getCodASC() + " " + token.getPosicao() + "\n");
                    tokens.add(token);
                }
                break;
            }
        }

    }

    public boolean contenToken(Token token) throws IOException {
        for (Token t : tokens) {
            if (t.getNome().equals(token.getNome()) && t.getCodASC() == token.getCodASC()) {
                token.posicao = t.getPosicao();
                tokens.add(t);
                token("" + token.getCodASC() + " " + token.getPosicao() + "\n");
                return true;
            }

            if ((t.getNome().equals(token.getNome())) && t.getCodASC() == token.getCodASC() && t.getPosicao() == token.getPosicao()) {
                tokens.add(t);
                token("" + token.getCodASC() + " " + token.getPosicao() + "\n");
                return true;
            }
            // Recebe sempre a posicao do ultimo elemento
            if (t.getPosicao() + 1 >= TokenEncontrado) {
                TokenEncontrado = t.getPosicao() + 1;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.count;
        hash = 17 * hash + Objects.hashCode(this.nome);
        hash = 17 * hash + this.codASC;
        hash = 17 * hash + this.posicao;
        hash = 17 * hash + this.TokenEncontrado;
        hash = 17 * hash + Objects.hashCode(this.tokens);
        hash = 17 * hash + Objects.hashCode(this.tokensAll);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Token other = (Token) obj;
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.codASC != other.codASC) {
            return false;
        }
        if (this.posicao != other.posicao) {
            return false;
        }
        if (this.TokenEncontrado != other.TokenEncontrado) {
            return false;
        }
        if (!Objects.equals(this.tokens, other.tokens)) {
            return false;
        }
        return Objects.equals(this.tokensAll, other.tokensAll);
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

    public  void token(String yytex) throws IOException {
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
