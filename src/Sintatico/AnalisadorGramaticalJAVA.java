package Sintatico;

import Lexemas.Token;
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
        index++;
        if (lookahead == token) {
            lookahead = proximo_token();
            
             if (token == ValueEnum.ID.getValue()) {
             lookahead = proximo_token();
             }

             if (token == ValueEnum.NUM.getValue()) {
             lookahead = proximo_token();
             }

             if (token == ValueEnum.OP_ADITIVO.getValue()) {
             lookahead = proximo_token();
             }
        } else {
            error(token);
        }
    } // OK

    /**
     *
     * @throws IOException
     */
    public void classDeclaration() throws IOException, Exception {
        arvore(indice, "classDeclaration");
        index++; // aponta para o primeito Token da lista
        lookahead = proximo_token();
        if (lookahead == ValueEnum.PUBLIC.getValue()) {
            Reconhecer(ValueEnum.PUBLIC.getValue());
            arvore(indice + 1, "public");

            Reconhecer(ValueEnum.CLASS.getValue());
            arvore(indice + 1, "class");

            Reconhecer(ValueEnum.ID.getValue());
            arvore(indice + 1, "id");

            classBody();
        } else {
            error(ValueEnum.PUBLIC.getValue());
        }
    } // OK

    public void error(int token) {

        List<String> erros = new ArrayList<>();
        erros.add("" + token);

        int n = 0;
        for (String s : erros) {
            if (s.equals(token)) {
                n = s.indexOf(s);
            }
        }

        switch (erros.get(n)) {
            case "41":
                System.out.println("Error Token: " + token + ": Esperava-se um ')'" + " linha " + "xx");
                break;

            case "125":
                System.out.println("Error Token: " + token + ": Esperava-se um '}'" + " linha " + "xx");
                break;

            case "59":
                System.out.println("Error Token: " + token + ": Esperava-se um ';'" + " linha " + "xx");
                break;
        }
    }

    public int proximo_token() throws IOException {

        File arq = new File("/home/lucas/NetBeansProjects/Analisador Compiladores/src/lexer/Tokens.txt");
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
        System.out.print(" |__" + s + "\n");
    }

    private void classBody() throws IOException, Exception {
        indice++;
        arvore(indice, "classBody");
        if (lookahead == ValueEnum.ABRE_CHAVE.getValue()) {
            Reconhecer(ValueEnum.ABRE_CHAVE.getValue());
            arvore(indice + 1, "{");

            classBodyDeclaration();

            Reconhecer(ValueEnum.FECHA_CHAVE.getValue());
            arvore(indice - 1, "}");
        } else {
            error(ValueEnum.ABRE_CHAVE.getValue());
        }
        indice--;
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
        indice--;
    }

    private void methodDeclaration() throws IOException, Exception {
        indice++;
        arvore(indice, "methodDeclaration");
        if (lookahead == ValueEnum.PUBLIC.getValue()) {
            arvore(indice++, "public");
            methodName();
            methodDeclaration_L1();
        } else {
            error(ValueEnum.PUBLIC.getValue());
        }
        indice--;
    }

    private void declaration() throws IOException {
        indice++;
        arvore(indice, "declaration");
        type();
        identifierList();
        indice--;
    }

    public void methodName() throws Exception {
        indice++;
        arvore(indice, "methodName");
        Reconhecer(ValueEnum.PUBLIC.getValue());
        arvore(indice++, "public");
        methodName_L1();
        indice--;
    }

    private void methodName_L1() throws IOException, Exception {
        indice++;
        arvore(indice, "methodName_L1");
        if (lookahead == ValueEnum.STATIC.getValue()) {
            Reconhecer(ValueEnum.STATIC.getValue());
            arvore(indice + 1, "static");

            methodName_L2();
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

            Reconhecer(ValueEnum.FECHA_CHAVE.getValue());
            arvore(indice++, "}");
        }
        indice--;
    }

    private void methodName_L2() throws IOException {
        indice++;
        arvore(indice, "methodName_L2");
        if (lookahead == ValueEnum.VOID.getValue()) {
            Reconhecer(ValueEnum.VOID.getValue());
            arvore(indice + 1, "void");

            Reconhecer(ValueEnum.MAIN.getValue());
            arvore(indice + 1, "main");

            Reconhecer(ValueEnum.ABRE_PAR.getValue());
            arvore(indice + 1, "(");

            Reconhecer(ValueEnum.STRING.getValue());
            arvore(indice + 1, "String");

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
        indice--;
    }

    public void type() throws IOException {
        indice++;
        arvore(indice, "type");
        switch (lookahead) {
            case 320: // boolean
                Reconhecer(ValueEnum.BOOLEAN.getValue());
                arvore(indice++, "boolean");
                break;
            case 610: // char
                Reconhecer(ValueEnum.CHAR.getValue());
                arvore(indice++, "char");
                break;
            case 292: // float
                Reconhecer(ValueEnum.FLOAT.getValue());
                arvore(indice++, "float");
                break;
            case 400: // int
                Reconhecer(ValueEnum.INT.getValue());
                arvore(indice++, "int");
                break;
            case 630: // string
                Reconhecer(ValueEnum.STRING.getValue());
                arvore(indice++, "String");
                break;
            case 470: // void
                Reconhecer(ValueEnum.VOID.getValue());
                arvore(indice++, "void");
                break;
            default:
                System.out.println(" *** VAZIO *** \n");

        }
        indice--;
    }

    private void identifierList() throws IOException {
        indice++;
        arvore(indice, "identifierList");
        if (lookahead == ValueEnum.ID.getValue()) {
            Reconhecer(ValueEnum.ID.getValue());
            arvore(indice + 1, "ID");

            identifierList_L1();
        } else {
            error(ValueEnum.ID.getValue());
        }
        indice--;
    }

    private void identifierList_L1() throws IOException {
        indice++;
        arvore(indice, "identifierList_L1");
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

        if (lookahead == ValueEnum.PUBLIC.getValue()) {
            methodName();
            methodDeclaration_L1();
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
        indice--;
    }

    private void formalParameters() throws IOException {
        indice++;
        arvore(indice, "formalParameters");
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
        type();
        Reconhecer(ValueEnum.ID.getValue());
        arvore(indice + 1, "ID");
        indice--;
    }

    private void methodBody() throws IOException {
        indice++;
        arvore(indice, "methodBody");
        declarationList();
        statementList();
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
        indice--;
    }

    private void statementList() throws IOException {
        indice++;
        arvore(indice++, "statementList");
        if (lookahead == ValueEnum.ID.getValue() || lookahead == ValueEnum.IF.getValue() || lookahead == ValueEnum.WHILE.getValue()
                || lookahead == ValueEnum.RETURN.getValue() || lookahead == ValueEnum.BREAK.getValue()) {
            statement();
            statementList_L1();
        } else {
            statementList_L1();
        }
        indice--;
    }

    private void statement() throws IOException {
        indice++;
        arvore(indice++, "statementList");
        if (lookahead == ValueEnum.ID.getValue()) {
            Reconhecer(ValueEnum.ID.getValue());
            arvore(indice++, "ID");

            statement_L1();
        }
        if (lookahead == ValueEnum.IF.getValue()) {
            ifStatement();
        }
        if (lookahead == ValueEnum.WHILE.getValue()) {
            whileStatement();
        }
        if (lookahead == ValueEnum.RETURN.getValue()) {
            Reconhecer(ValueEnum.RETURN.getValue());
            arvore(indice++, "return");
            
            expression();
            
            Reconhecer(ValueEnum.PONTO_E_VIRGULA.getValue());
            arvore(indice++, ";");
        }
        if (lookahead == ValueEnum.BREAK.getValue()) {
            Reconhecer(ValueEnum.BREAK.getValue());
            arvore(indice++, "break");
        }
        indice--;
    }

    private void statementList_L1() throws IOException {
        indice++;
        arvore(indice++, "statementList_L1");
        
        if(lookahead == ValueEnum.ID.getValue() || lookahead == ValueEnum.IF.getValue() || lookahead == ValueEnum.WHILE.getValue()
                || lookahead == ValueEnum.RETURN.getValue() || lookahead == ValueEnum.BREAK.getValue()){
            statement();
            statementList_L1();
        }else{
            System.out.println(" *** VAZIO *** \n");
        }
        indice--;
    } // ok

    private void expressionList() throws IOException {
        indice++;
        arvore(indice++, "expressionList");

        if (lookahead == ValueEnum.ID.getValue() || lookahead == ValueEnum.NUM.getValue() || lookahead == ValueEnum.CHAR.getValue()
                || lookahead == ValueEnum.TRUE.getValue() || lookahead == ValueEnum.FALSE.getValue()) {
            expression();
            expressionList_L1();
        } else {
            expressionList_L1();
        }
        indice--;
    }

    private void expression() throws IOException {
        indice++;
        arvore(indice++, "expression");
        if (lookahead == ValueEnum.ID.getValue() || lookahead == ValueEnum.NUM.getValue() || lookahead == ValueEnum.CHAR.getValue()
                || lookahead == ValueEnum.TRUE.getValue() || lookahead == ValueEnum.FALSE.getValue()) {
            literal();
            expression_L1();
        }  if (lookahead == ValueEnum.ABRE_PAR.getValue()) {
            Reconhecer(ValueEnum.ABRE_PAR.getValue());
            arvore(indice++, "(");

            expression();

            Reconhecer(ValueEnum.FECHA_PAR.getValue());
            arvore(indice++, ")");
        }  if (lookahead == ValueEnum.OP_ADITIVO.getValue()) {
            Reconhecer(ValueEnum.OP_ADITIVO.getValue());
            arvore(indice++, "+/-");

            expression();
        } else {
            expression_L1();
        }
        indice--;
    }

    private void expressionList_L1() throws IOException {
        indice++;
        Reconhecer(ValueEnum.VIRGULA.getValue());
        arvore(indice++, "expressionList_L1");
        if (lookahead == ValueEnum.VIRGULA.getValue()) {
            Reconhecer(ValueEnum.VIRGULA.getValue());
            arvore(indice++, ",");

            expression();
            expressionList_L1();

        } else {
            System.out.println(" *** VAZIO *** \n");
        }
    }

    private void ifStatement() throws IOException {
        indice++;
        arvore(indice++, "ifStatement");

        Reconhecer(ValueEnum.IF.getValue());
        arvore(indice++, "if");

        Reconhecer(ValueEnum.ABRE_PAR.getValue());
        arvore(indice++, "(");

        expression();

        Reconhecer(ValueEnum.FECHA_PAR.getValue());
        arvore(indice++, ")");

        statementBlock();
        elseAlternative();
        indice--;
    }

    private void literal() throws IOException {
        indice++;
        arvore(indice++, "literal");

        if (lookahead == ValueEnum.ID.getValue()) {
            Reconhecer(ValueEnum.ID.getValue());
            arvore(indice++, "ID");
        } else if (lookahead == ValueEnum.NUM.getValue()) {
            Reconhecer(ValueEnum.NUM.getValue());
            arvore(indice++, "num");
        } else if (lookahead == ValueEnum.CHAR.getValue()) {
            Reconhecer(ValueEnum.CHAR.getValue());
            arvore(indice++, "char");
        } else if (lookahead == ValueEnum.TRUE.getValue()) {
            Reconhecer(ValueEnum.TRUE.getValue());
            arvore(indice++, "true");
        } else if (lookahead == ValueEnum.FALSE.getValue()) {
            Reconhecer(ValueEnum.FALSE.getValue());
            arvore(indice++, "false");
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
    }

    private void expression_L1() throws IOException {
        indice++;
        arvore(indice++, "expression_L1");
        if (lookahead == ValueEnum.OP_ADITIVO.getValue() || lookahead == ValueEnum.OP_MULTIPLICATIVO.getValue()
                || lookahead == ValueEnum.OP_RELACIONAL.getValue() || lookahead == ValueEnum.OP_LOGICO.getValue()) {

            if (lookahead == ValueEnum.OP_ADITIVO.getValue()) {
                Reconhecer(ValueEnum.OP_ADITIVO.getValue());
                arvore(indice++, "+/-");
                expression();
                expression_L1();
            }
            if (lookahead == ValueEnum.OP_MULTIPLICATIVO.getValue()) {
                Reconhecer(ValueEnum.OP_MULTIPLICATIVO.getValue());
                arvore(indice++, "op_mult");
                expression();
                expression_L1();
            }
            if (lookahead == ValueEnum.OP_RELACIONAL.getValue()) {
                Reconhecer(ValueEnum.OP_RELACIONAL.getValue());
                arvore(indice++, "op_relacional");
                expression();
                expression_L1();
            }
            if (lookahead == ValueEnum.OP_LOGICO.getValue()) {
                Reconhecer(ValueEnum.OP_LOGICO.getValue());
                arvore(indice++, "op_relacional");
                expression();
                expression_L1();
            }

        } else {
            System.out.println(" *** VAZIO *** \n");
        }
        indice--;
    }

    private void statementBlock() throws IOException {
        indice++;
        arvore(indice++, "statementBlock");
        if (lookahead == ValueEnum.ID.getValue() || lookahead == ValueEnum.IF.getValue() || lookahead == ValueEnum.WHILE.getValue()
                || lookahead == ValueEnum.RETURN.getValue() || lookahead == ValueEnum.BREAK.getValue()) {
            statement();
        } else if (lookahead == ValueEnum.ABRE_CHAVE.getValue()) {
            Reconhecer(ValueEnum.ABRE_CHAVE.getValue());
            arvore(indice++, "{");

            statementList();
            Reconhecer(ValueEnum.FECHA_CHAVE.getValue());
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
        indice--;
    }

    private void elseAlternative() throws IOException {
        indice++;
        arvore(indice++, "elseAlternative");
        Reconhecer(ValueEnum.ELSE.getValue());
        arvore(indice++, "else");
        statementBlock();
    }
    
    private void whileStatement() throws IOException {
        indice++;
        arvore(indice++, "whileStatement");
        Reconhecer(ValueEnum.WHILE.getValue());
        arvore(indice++, "while");

        Reconhecer(ValueEnum.ABRE_PAR.getValue());
        arvore(indice++, "(");

        expression();

        Reconhecer(ValueEnum.FECHA_PAR.getValue());
        arvore(indice++, ")");
        statementBlock();
        indice--;
    }

    private void statement_L1() throws IOException {
        indice++;
        arvore(indice++, "statement_L1");
        
        if (lookahead == ValueEnum.ATRIB_.getValue()) {
            Reconhecer(ValueEnum.ATRIB_.getValue());
            arvore(indice++, "op_atributo = ");

            expression();
            statement_L2();

        } else if (lookahead == ValueEnum.ABRE_PAR.getValue()) {
            Reconhecer(ValueEnum.ABRE_PAR.getValue());
            arvore(indice++, "(");

            expressionList();

            Reconhecer(ValueEnum.FECHA_PAR.getValue());
            arvore(indice++, ")");

            Reconhecer(ValueEnum.PONTO_E_VIRGULA.getValue());
            arvore(indice++, ";");
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
        indice--;
    } // ok

    private void statement_L2() throws IOException {
        indice++;
        arvore(indice++, "statement_L2");
        if (lookahead == ValueEnum.PONTO_E_VIRGULA.getValue()) {
            Reconhecer(ValueEnum.PONTO_E_VIRGULA.getValue());
            arvore(indice++, ";");
        } else if (lookahead == ValueEnum.ABRE_PAR.getValue()) {
            Reconhecer(ValueEnum.ABRE_PAR.getValue());
            arvore(indice++, ")");

            expressionList();

            Reconhecer(ValueEnum.FECHA_PAR.getValue());
            arvore(indice++, ")");

            Reconhecer(ValueEnum.PONTO_E_VIRGULA.getValue());
            arvore(indice++, ";");
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
    }
}