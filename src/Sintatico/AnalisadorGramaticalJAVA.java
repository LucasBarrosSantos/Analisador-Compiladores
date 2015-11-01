package Sintatico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnalisadorGramaticalJAVA {

    /**
     * Valor inteiro que vai compara os Tokens
     */
    private Integer lookahead;
    /**
     * Usado para manter o controle dos Tokens armazenados na listITokens.<p>
     * Pesquisa um Objeto em uma determinada posição na Lista.
     */
    private int index;
    private int indice;
    private List<Integer> listITokens; // Guarda todos os Tokens 

    public AnalisadorGramaticalJAVA(Integer lookahead) {
        this.lookahead = lookahead;
    }

    public AnalisadorGramaticalJAVA() {
        listITokens = new ArrayList<>();
    }

    public void Reconhecer(int token) throws IOException {
        if (getLookahead() == token) {
            index++;
            lookahead = proximo_token();
            
            if(token == ValueEnum.ID.getValue())
                lookahead = proximo_token();
            
            if(token == ValueEnum.NUM.getValue())
                lookahead = proximo_token();
            
            if(token == ValueEnum.OP_RELACIONAL.getValue())
            lookahead = proximo_token();

        } else {
            error(token);
        }
    }

    /**
     *
     * @throws IOException
     */
    public void classDeclaration() throws IOException, Exception {
        arvore(indice, "classDeclaration");
        index++;
        lookahead = proximo_token();
        if (lookahead == ValueEnum.PUBLIC.getValue()) {
            Reconhecer(ValueEnum.PUBLIC.getValue());
            arvore(indice + 1, "public");

            Reconhecer(ValueEnum.CLASS.getValue());
            arvore(indice + 1, "class");

            Reconhecer(ValueEnum.ID.getValue());
            arvore(indice + 1, "id");

            indice++;
            classBody();
        } else {
            error(ValueEnum.PUBLIC.getValue());
        }
    }

    void error(int token) {

    }

    public int proximo_token() throws IOException {

        File arq = new File("/home/lucas/NetBeansProjects/Analisador /src/lexer/Tokens.txt");
        BufferedReader leitor;
        try (FileReader reader = new FileReader(arq)) {
            leitor = new BufferedReader(reader);
            FileWriter writer = new FileWriter(arq, true);
            PrintWriter escritor = new PrintWriter(writer);
            String teste;
            while ((teste = leitor.readLine()) != null) {
                if (!"".equals(teste) && !"\n".equals(teste) && !" ".equals(teste)) {
                    escritor.println(teste);
                    teste.replace(" ", "");
                    if (teste.length() < 4) {
                        int v = Integer.parseInt(teste);
                        listITokens.add(v);
                    }
                    teste.replace(" ", "");
                    if (!"".equals(teste) && !"\n".equals(teste) && teste.length() > 3) {
                        int n = Integer.parseInt(teste.substring(0, 3));
                        listITokens.add(n);
                    }
                }

            }//end while
        }
        leitor.close();
        return getListITokens().get(index - 1);
        /*
         * index -1, pois no incremento o index == 1, mas a primeira posição é 0!
         */
    }

    public Integer getLookahead() {
        return lookahead;
    }

    public void setLookahead(Integer lookahead) {
        this.lookahead = lookahead;
    }

    public List<Integer> getListITokens() {
        return listITokens;
    }

    public void setListITokens(List<Integer> listITokens) {
        this.listITokens = listITokens;
    }

    private void arvore(int indice, String s) {
        for (int i = 0; i <= indice; i++) {
            System.out.print("  ");
        }
        System.out.print("|_" + s + "\n");
    }

    private void classBody() throws IOException, Exception {
        indice++;
        arvore(indice, "classBody");
        lookahead = proximo_token();
        if (lookahead == ValueEnum.ABRE_CHAVE.getValue()) {
            Reconhecer(ValueEnum.ABRE_CHAVE.getValue());
            arvore(indice + 1, "{");
            
            classBodyDeclaration();

            Reconhecer(ValueEnum.FECHA_CHAVE.getValue());
            arvore(indice - 1, "}");
        }
    }

    private void classBodyDeclaration() throws IOException, Exception {
        indice++;
        arvore(indice + 1, "classBodyDeclaration");

        declarationList();
        methodDeclaration();
        indice--;
    }

    private void declarationList() throws IOException {
        indice++;
        arvore(indice, "declarationList");

        if (lookahead == ValueEnum.BOOLEAN.getValue() || lookahead == ValueEnum.CHAR.getValue()
                || lookahead == ValueEnum.FLOAT.getValue() || lookahead == ValueEnum.INT.getValue()
                || lookahead == ValueEnum.STRING.getValue() || lookahead == ValueEnum.VOID.getValue()) {

            declaration();
            Reconhecer(ValueEnum.PONTO_E_VIRGULA.getValue());
            arvore(indice + 1, ";");

            declarationList_L1();
        } else {
            declarationList_L1();
        }
    }

    private void methodDeclaration() throws IOException, Exception {
        indice++;
        arvore(indice, "methodDeclaration");
        lookahead = proximo_token();
        if (lookahead == ValueEnum.PUBLIC.getValue()) {
            methodName();
            methodDeclaration_L1();
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
    }

    private void declaration() throws IOException {
        indice++;
        arvore(indice, "declaration");
        type();
        identifierList();
    }

    public void methodName() throws Exception {
        indice++;
        arvore(indice, "methodName");
        Reconhecer(ValueEnum.PUBLIC.getValue());
        methodName_L1();
    }

    public void type() throws IOException {
        indice++;
        arvore(indice, "type");
        lookahead = proximo_token();
        switch (lookahead) {
            case 320: // boolean
                lookahead = ValueEnum.BOOLEAN.getValue();
                break;
            case 610: // char
                lookahead = ValueEnum.CHAR.getValue();
                break;
            case 292: // float
                lookahead = ValueEnum.FLOAT.getValue();
                break;
            case 400: // int
                lookahead = ValueEnum.INT.getValue();
                break;
            case 630: // string
                lookahead = ValueEnum.STRING.getValue();
                break;
            case 470: // void
                lookahead = ValueEnum.VOID.getValue();
                break;
            default:
                System.out.println(" *** VAZIO *** \n");

        }
        indice--;
    }

    private void identifierList() throws IOException {
        indice++;
        arvore(indice, "identifierList");
        lookahead = proximo_token();
        if (lookahead == ValueEnum.ID.getValue()) {
            Reconhecer(ValueEnum.ID.getValue());
            arvore(indice + 1, "ID");

            identifierList_L1();
        } else {
            error(ValueEnum.ID.getValue());
        }
    }

    private void identifierList_L1() throws IOException {
        indice++;
        arvore(indice, "identifierList_L1");
        lookahead = proximo_token();
        if (lookahead == ValueEnum.VIRGULA.getValue()) {
            Reconhecer(ValueEnum.VIRGULA.getValue());
            arvore(indice + 1, "virgula");

            Reconhecer(ValueEnum.ID.getValue());
            arvore(indice + 1, "ID");
            identifierList_L1();
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
    }

    private void methodDeclaration_L1() throws Exception {
        indice++;
        arvore(indice, "identifierList_L1");
        lookahead = proximo_token();

        if (lookahead == ValueEnum.PUBLIC.getValue()) {
            methodName();
            methodDeclaration_L1();
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
    }

    private void methodName_L1() throws IOException, Exception {
        indice++;
        arvore(indice, "methodName_L1");
        lookahead = proximo_token();
        if (lookahead == ValueEnum.STATIC.getValue()) {
            Reconhecer(ValueEnum.STATIC.getValue());
            arvore(indice + 1, "static");

            methodName_L2();
            methodName();
        } else {
            Reconhecer(ValueEnum.ID.getValue());
            arvore(indice + 1, "ID");

            Reconhecer(ValueEnum.ABRE_PAR.getValue());
            arvore(indice + 1, "(");

            formalParameters();
            Reconhecer(ValueEnum.FECHA_PAR.getValue());
            arvore(indice - 1, ")");

            Reconhecer(ValueEnum.ABRE_CHAVE.getValue());
            arvore(indice + 1, "{");

            methodBody();
        }
    }

    private void methodName_L2() throws IOException {
        indice++;
        arvore(indice, "methodName_L2");
        lookahead = proximo_token();
        if (lookahead == ValueEnum.VOID.getValue()) {
            Reconhecer(ValueEnum.VOID.getValue());
            arvore(indice + 1, "void");

            Reconhecer(ValueEnum.MAIN.getValue());
            arvore(indice + 1, "main");

            Reconhecer(ValueEnum.ABRE_PAR.getValue());
            arvore(indice + 1, "(");

            Reconhecer(ValueEnum.STRING.getValue());
            arvore(indice + 1, "string");

            Reconhecer(ValueEnum.ARG.getValue());
            arvore(indice + 1, "arg");

            Reconhecer(ValueEnum.FECHA_PAR.getValue());
            arvore(indice + 1, ")");

            Reconhecer(ValueEnum.ABRE_CHAVE.getValue());
            arvore(indice + 1, "{");

            methodBody();

            Reconhecer(ValueEnum.FECHA_PAR.getValue());
            arvore(indice - 1, ")");
        } else {
            type();
            Reconhecer(ValueEnum.ID.getValue());
            arvore(indice + 1, "ID");

            Reconhecer(ValueEnum.ABRE_PAR.getValue());
            arvore(indice + 1, "(");

            formalParameters();

            Reconhecer(ValueEnum.FECHA_PAR.getValue());
            arvore(indice + 1, ")");

            Reconhecer(ValueEnum.ABRE_CHAVE.getValue());
            arvore(indice + 1, "{");

            methodBody();

            Reconhecer(ValueEnum.FECHA_CHAVE.getValue());
            arvore(indice - 1, "}");
        }
    }

    private void formalParameters() throws IOException {
        indice++;
        arvore(indice, "formalParameters");
        lookahead = proximo_token();
        if (lookahead == ValueEnum.BOOLEAN.getValue() || lookahead == ValueEnum.CHAR.getValue()
                || lookahead == ValueEnum.FLOAT.getValue() || lookahead == ValueEnum.INT.getValue()
                || lookahead == ValueEnum.STRING.getValue() || lookahead == ValueEnum.VOID.getValue()) {
            formalParameter();
            formalParameters_L1();
        } else {
            formalParameters_L1();
        }
        indice--;
    }

    private void formalParameters_L1() throws IOException {
        indice++;
        arvore(indice, "formalParameters_L1");
        lookahead = proximo_token();
        if (lookahead == ValueEnum.VIRGULA.getValue()) {
            Reconhecer(ValueEnum.VIRGULA.getValue());
            arvore(indice + 1, "virgula");

            formalParameter();
            formalParameters_L1();
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
    }

    private void formalParameter() throws IOException {
        indice++;
        arvore(indice, "formalParameter");
        lookahead = proximo_token();
        type();
        Reconhecer(ValueEnum.ID.getValue());
        arvore(indice + 1, "ID");
        indice--;
    }

    private void methodBody() throws IOException {
        indice++;
        arvore(indice, "methodBody");
        lookahead = proximo_token();
        declarationList();
    }

    private void declarationList_L1() throws IOException {
        indice++;
        arvore(indice, "declarationList_L1");

        if (lookahead == ValueEnum.BOOLEAN.getValue() || lookahead == ValueEnum.CHAR.getValue()
                || lookahead == ValueEnum.FLOAT.getValue() || lookahead == ValueEnum.INT.getValue()
                || lookahead == ValueEnum.STRING.getValue() || lookahead == ValueEnum.VOID.getValue()) {

            declaration();
            Reconhecer(ValueEnum.PONTO_E_VIRGULA.getValue());
            arvore(indice + 1, ";");

            declarationList_L1();
        } else {
            System.out.println(" *** VAZIO ***\n");
        }
    }
}
