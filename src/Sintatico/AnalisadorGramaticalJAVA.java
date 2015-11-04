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
        printArvore(indice, "classDeclaration");
        index++; // aponta para o primeito Token da lista
        lookahead = proximo_token();
        if (lookahead == ValueEnum.PUBLIC.getValue()) {
            Reconhecer(ValueEnum.PUBLIC.getValue());
            printArvore(indice + 1, "public");

            Reconhecer(ValueEnum.CLASS.getValue());
            printArvore(indice + 1, "class");

            Reconhecer(ValueEnum.ID.getValue());
            printArvore(indice + 1, "id");

            classBody();
        } else {
            error(ValueEnum.PUBLIC.getValue());
        }
    } // OK

    public void error(int token) {

        List<String> erros = new ArrayList<>();
        erros.add("" + token);

        switch (erros.get(0)) {
            
            case "300":
                System.out.println("Error Token: " + token + ": Esperava-se um 'ID'" + " linha " + "xx");
                break;
            
            case "41":
                System.out.println("Error Token: " + token + ": Esperava-se um ')'" + " linha " + "xx");
                break;

            case "125":
                System.out.println("Error Token: " + token + ": Esperava-se um '}'" + " linha " + "xx");
                break;

            case "59":
                System.out.println("Error Token: " + token + ": Esperava-se um ';'" + " linha " + "xx");
                break;

            case "310":
                System.out.println("Error Token: " + token + ": Esperava-se um 'main'" + " linha " + "xx");
                break;

            case "320":
                System.out.println("Error Token: " + token + ": Esperava-se um 'boolean'" + " linha " + "xx");
                break;

            case "330":
                System.out.println("Error Token: " + token + ": Esperava-se um 'class'" + " linha " + "xx");
                break;

            case "350":
                System.out.println("Error Token: " + token + ": Esperava-se um 'else'" + " linha " + "xx");
                break;

            case "640":
                System.out.println("Error Token: " + token + ": Esperava-se um 'arg'" + " linha " + "xx");
                break;
        }
    } // Terminar...

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
    } // ok 

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

    private void printArvore(int indice, String s) {
        for (int i = 0; i <= indice; i++) {
            System.out.print(" ");
        }
        System.out.print(" |__" + s + "\n");
    } // ok 

    private void classBody() throws IOException, Exception {
        indice++;
        printArvore(indice, "classBody");
        if (lookahead == ValueEnum.ABRE_CHAVE.getValue()) {
            Reconhecer(ValueEnum.ABRE_CHAVE.getValue());
            printArvore(indice, "Abre Chave => '{'");

            classBodyDeclaration();

            Reconhecer(ValueEnum.FECHA_CHAVE.getValue());
            printArvore(indice - 1, "Fecha Chave => '}'");
        } else {
            error(ValueEnum.ABRE_CHAVE.getValue());
        }
        indice--;
    } // ok

    private void classBodyDeclaration() throws IOException, Exception {
        indice++;
        printArvore(indice + 1, "classBodyDeclaration");

        declarationList();
        methodDeclaration();
        indice--;
    } // ok

    private void declarationList() throws IOException {
        indice++;
        printArvore(indice, "declarationList");

        if (lookahead == ValueEnum.BOOLEAN.getValue() || lookahead == ValueEnum.CHAR.getValue()
                || lookahead == ValueEnum.FLOAT.getValue() || lookahead == ValueEnum.INT.getValue()
                || lookahead == ValueEnum.STRING.getValue() || lookahead == ValueEnum.VOID.getValue()) {

            declaration();
            Reconhecer(ValueEnum.PONTO_E_VIRGULA.getValue());
            printArvore(indice + 1, "Ponto e Vírgula => ';'");

            declarationList_L1();
        } else {
            declarationList_L1();
        }
        indice--;
    } // ok

    private void declarationList_L1() throws IOException {
        indice++;
        printArvore(indice, "declarationList_L1");

        if (lookahead == ValueEnum.BOOLEAN.getValue() || lookahead == ValueEnum.CHAR.getValue()
                || lookahead == ValueEnum.FLOAT.getValue() || lookahead == ValueEnum.INT.getValue()
                || lookahead == ValueEnum.STRING.getValue() || lookahead == ValueEnum.VOID.getValue()) {

            declaration();
            Reconhecer(ValueEnum.PONTO_E_VIRGULA.getValue());
            printArvore(indice + 1, "Ponto e Vírgula => ';'");

            declarationList_L1();
        } else {
            System.out.println(" *** VAZIO ***\n");
        }
        indice--;
    } // ok

    private void methodDeclaration() throws IOException, Exception {
        indice++;
        printArvore(indice, "methodDeclaration");
        if (lookahead == ValueEnum.PUBLIC.getValue()) {
            printArvore(indice++, "public");
            methodName();
            methodDeclaration_L1();
        } else {
            error(ValueEnum.PUBLIC.getValue());
        }
        indice--;
    } // ok

    private void declaration() throws IOException {
        indice++;
        printArvore(indice, "declaration");
        type();
        identifierList();
        indice--;
    } // ok

    private void methodDeclaration_L1() throws Exception {
        indice++;
        printArvore(indice, "identifierList_L1");

        if (lookahead == ValueEnum.PUBLIC.getValue()) {
            methodName();
            methodDeclaration_L1();
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
        indice--;
    } // ok 

    public void methodName() throws Exception {
        indice++;
        printArvore(indice, "methodName");
        Reconhecer(ValueEnum.PUBLIC.getValue());
        printArvore(indice++, "public");
        methodName_L1();
        indice--;
    } // ok

    private void methodName_L1() throws IOException, Exception {
        indice++;
        printArvore(indice, "methodName_L1");
        if (lookahead == ValueEnum.STATIC.getValue()) {
            Reconhecer(ValueEnum.STATIC.getValue());
            printArvore(indice + 1, "static");

            methodName_L2();
        } else {
            Reconhecer(ValueEnum.ID.getValue());
            printArvore(indice + 1, "Identificador => 'ID'");

            Reconhecer(ValueEnum.ABRE_PAR.getValue());
            printArvore(indice + 1, "Abre Par => '('");

            formalParameters();

            Reconhecer(ValueEnum.FECHA_PAR.getValue());
            printArvore(indice--, "Fecha Par => ')'");

            Reconhecer(ValueEnum.ABRE_CHAVE.getValue());
            printArvore(indice + 1, "Abre Cha => '{'");

            methodBody();

            Reconhecer(ValueEnum.FECHA_CHAVE.getValue());
            printArvore(indice - 1, "Fecha Cha => '}'");
        }
        indice--;
    } // ok 

    private void methodName_L2() throws IOException, Exception {
        indice++;
        printArvore(indice, "methodName_L2");
        if (lookahead == ValueEnum.VOID.getValue()) {
            Reconhecer(ValueEnum.VOID.getValue());
            printArvore(indice + 1, "Metod => 'void'");

            Reconhecer(ValueEnum.MAIN.getValue());
            printArvore(indice + 1, "Metod => 'main'");

            Reconhecer(ValueEnum.ABRE_PAR.getValue());
            printArvore(indice + 1, "Abre Par => '('");

            Reconhecer(ValueEnum.STRING.getValue());
            printArvore(indice + 1, "Cadeia de String => 'String'");

            Reconhecer(ValueEnum.ARG.getValue());
            printArvore(indice + 1, "Argumento => 'arg'");

            Reconhecer(ValueEnum.FECHA_PAR.getValue());
            printArvore(indice + 1, "Fecha Par => ')'");

            Reconhecer(ValueEnum.ABRE_CHAVE.getValue());
            printArvore(indice--, "Fecha Chave => '{'");

            methodBody();

            Reconhecer(ValueEnum.FECHA_CHAVE.getValue());
            printArvore(indice--, "Fecha Cha => '}'");
        } else {
            type();
            Reconhecer(ValueEnum.ID.getValue());
            printArvore(indice++, "Identificador => 'ID'");

            Reconhecer(ValueEnum.ABRE_PAR.getValue());
            printArvore(indice++, "Abre Par => '('");

            formalParameters();

            Reconhecer(ValueEnum.FECHA_PAR.getValue());
            printArvore(indice++, "Fecha Par => ')'");

            Reconhecer(ValueEnum.ABRE_CHAVE.getValue());
            printArvore(indice++, "Abre Chave => '{'");

            methodBody();

            Reconhecer(ValueEnum.FECHA_CHAVE.getValue());
            printArvore(indice--, "Fecha Cha => '}'");
        }
        indice--;
    } // ok 

    public void type() throws IOException {
        indice++;
        printArvore(indice, "type");
        switch (lookahead) {
            case 320: // boolean
                Reconhecer(ValueEnum.BOOLEAN.getValue());
                printArvore(indice++, "Lógico => 'boolean'");
                break;
            case 610: // char
                Reconhecer(ValueEnum.CHAR.getValue());
                printArvore(indice++, "Constante => 'char'");
                break;
            case 292: // float
                Reconhecer(ValueEnum.FLOAT.getValue());
                printArvore(indice++, "Ponto Flutuante => 'float'");
                break;
            case 400: // int
                Reconhecer(ValueEnum.INT.getValue());
                printArvore(indice++, "Inteiro => 'int'");
                break;
            case 630: // string
                Reconhecer(ValueEnum.STRING.getValue());
                printArvore(indice++, "Cadeia de String => 'String'");
                break;
            case 470: // void
                Reconhecer(ValueEnum.VOID.getValue());
                printArvore(indice++, "Tipo =>  'void'");
                break;
            default:
                System.out.println(" *** VAZIO *** \n");

        }
        indice--;
    } // ok 

    private void identifierList() throws IOException {
        indice++;
        printArvore(indice, "identifierList");
        if (lookahead == ValueEnum.ID.getValue()) {
            Reconhecer(ValueEnum.ID.getValue());
            printArvore(indice++, "Identificador => 'ID'");

            identifierList_L1();
        } else {
            error(ValueEnum.ID.getValue());
        }
        indice--;
    } // ok

    private void identifierList_L1() throws IOException {
        indice++;
        printArvore(indice, "identifierList_L1");
        if (lookahead == ValueEnum.VIRGULA.getValue()) {
            Reconhecer(ValueEnum.VIRGULA.getValue());
            printArvore(indice++, "virgula => ','");

            Reconhecer(ValueEnum.ID.getValue());
            printArvore(indice++, "Identificador => 'ID'");
            identifierList_L1();
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
    }

    private void formalParameters() throws IOException {
        indice++;
        printArvore(indice, "formalParameters");
        if (lookahead == ValueEnum.BOOLEAN.getValue() || lookahead == ValueEnum.CHAR.getValue()
                || lookahead == ValueEnum.FLOAT.getValue() || lookahead == ValueEnum.INT.getValue()
                || lookahead == ValueEnum.STRING.getValue() || lookahead == ValueEnum.VOID.getValue()) {
            formalParameter();
            formalParameters_L1();
        } else {
            formalParameters_L1();
        }
        indice--;
    } // ok

    private void formalParameters_L1() throws IOException {
        indice++;
        printArvore(indice, "formalParameters_L1");
        if (lookahead == ValueEnum.VIRGULA.getValue()) {
            Reconhecer(ValueEnum.VIRGULA.getValue());
            printArvore(indice + 1, "virgula => ','");

            formalParameter();
            formalParameters_L1();
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
    } // ok

    private void formalParameter() throws IOException {
        indice++;
        printArvore(indice, "formalParameter");
        type();
        Reconhecer(ValueEnum.ID.getValue());
        printArvore(indice + 1, "Identificador => 'ID'");
        indice--;
    } // ok

    private void methodBody() throws IOException, Exception {
        indice++;
        printArvore(indice, "methodBody");
        declarationList();
        statementList();
    } // ok 

    private void statementList() throws IOException, Exception {
        indice++;
        printArvore(indice++, "statementList");
        if (lookahead == ValueEnum.ID.getValue() || lookahead == ValueEnum.IF.getValue() || lookahead == ValueEnum.WHILE.getValue()
                || lookahead == ValueEnum.RETURN.getValue() || lookahead == ValueEnum.BREAK.getValue()) {
            statement();
            statementList_L1();
        } else {
            statementList_L1();
        }
        indice--;
    } // ok

    private void statement() throws IOException, Exception {
        indice++;
        printArvore(indice++, "statementList");
        if (lookahead == ValueEnum.ID.getValue()) {
            Reconhecer(ValueEnum.ID.getValue());
            printArvore(indice++, "Identificador => 'ID'");

            statement_L1();
        } else if (lookahead == ValueEnum.IF.getValue()) {
            ifStatement();
        } else if (lookahead == ValueEnum.WHILE.getValue()) {
            whileStatement();
        } else if (lookahead == ValueEnum.RETURN.getValue()) {
            Reconhecer(ValueEnum.RETURN.getValue());
            printArvore(indice++, "Token => 'return'");

            expression();

            Reconhecer(ValueEnum.PONTO_E_VIRGULA.getValue());
            printArvore(indice++, ";");
        } else if (lookahead == ValueEnum.BREAK.getValue()) {
            Reconhecer(ValueEnum.BREAK.getValue());
            printArvore(indice++, "Token => 'break'");

            Reconhecer(ValueEnum.PONTO_E_VIRGULA.getValue());
            printArvore(indice++, "Ponto e Vírgula => ';'");
        }
        indice--;
    } // ok

    private void statementList_L1() throws IOException, Exception {
        indice++;
        printArvore(indice++, "statementList_L1");

        if (lookahead == ValueEnum.ID.getValue() || lookahead == ValueEnum.IF.getValue() || lookahead == ValueEnum.WHILE.getValue()
                || lookahead == ValueEnum.RETURN.getValue() || lookahead == ValueEnum.BREAK.getValue()) {
            statement();
            statementList_L1();
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
        indice--;
    } // ok

    private void expressionList() throws IOException, Exception {
        indice++;
        printArvore(indice++, "expressionList");

        if (lookahead == ValueEnum.ID.getValue() || lookahead == ValueEnum.NUM.getValue() || lookahead == ValueEnum.CHAR.getValue()
                || lookahead == ValueEnum.TRUE.getValue() || lookahead == ValueEnum.FALSE.getValue()) {
            expression();
            expressionList_L1();
        } else {
            expressionList_L1();
        }
        indice--;
    } // ok

    private void expression() throws IOException, Exception {
        indice++;
        printArvore(indice++, "expression");
        if (lookahead == ValueEnum.ID.getValue() || lookahead == ValueEnum.NUM.getValue() || lookahead == ValueEnum.CHAR.getValue()
                || lookahead == ValueEnum.TRUE.getValue() || lookahead == ValueEnum.FALSE.getValue()) {
            literal();
            expression_L1();
        } else if (lookahead == ValueEnum.ABRE_PAR.getValue()) {
            Reconhecer(ValueEnum.ABRE_PAR.getValue());
            printArvore(indice++, "Abre Par => '('");

            expression();

            Reconhecer(ValueEnum.FECHA_PAR.getValue());
            printArvore(indice++, "Fecha Par => ')'");

        } else if (lookahead == ValueEnum.OP_ADITIVO.getValue()) {
            Reconhecer(ValueEnum.OP_ADITIVO.getValue());
            printArvore(indice++, "Aditivo Aritimético => +/-");

            expression();
        } else {
            expression_L1();
        }

        indice--;
    } // ok

    private void expressionList_L1() throws IOException, Exception {
        indice++;
        printArvore(indice++, "expressionList_L1");
        if (lookahead == ValueEnum.VIRGULA.getValue()) {
            Reconhecer(ValueEnum.VIRGULA.getValue());
            printArvore(indice++, "Vírgula => ','");

            expression();
            expressionList_L1();

        } else {
            System.out.println(" *** VAZIO *** \n");
        }
    } // ok

    private void ifStatement() throws IOException, Exception {
        indice++;
        printArvore(indice++, "ifStatement");

        Reconhecer(ValueEnum.IF.getValue());
        printArvore(indice++, "if");

        Reconhecer(ValueEnum.ABRE_PAR.getValue());
        printArvore(indice++, "Abre Par => '('");

        expression();

        Reconhecer(ValueEnum.FECHA_PAR.getValue());
        printArvore(indice++, "Fecha Par => ')'");

        statementBlock();
        elseAlternative();
        indice--;
    } // ok

    private void literal() throws IOException {
        indice++;
        printArvore(indice++, "literal");

        if (lookahead == ValueEnum.ID.getValue()) {
            Reconhecer(ValueEnum.ID.getValue());
            printArvore(indice++, "Identificador => 'ID'");
        } else if (lookahead == ValueEnum.NUM.getValue()) {
            Reconhecer(ValueEnum.NUM.getValue());
            printArvore(indice++, "Inteiro => 'num'");
        } else if (lookahead == ValueEnum.CHAR.getValue()) {
            Reconhecer(ValueEnum.CHAR.getValue());
            printArvore(indice++, "Constante char => 'char'");
        } else if (lookahead == ValueEnum.TRUE.getValue()) {
            Reconhecer(ValueEnum.TRUE.getValue());
            printArvore(indice++, "true");
        } else if (lookahead == ValueEnum.FALSE.getValue()) {
            Reconhecer(ValueEnum.FALSE.getValue());
            printArvore(indice++, "false");
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
    } // ok

    private void expression_L1() throws IOException, Exception {
        indice++;
        printArvore(indice++, "expression_L1");
        if (lookahead == ValueEnum.OP_ADITIVO.getValue() || lookahead == ValueEnum.OP_MULTIPLICATIVO.getValue()
                || lookahead == ValueEnum.OP_RELACIONAL.getValue() || lookahead == ValueEnum.OP_LOGICO.getValue()) {

            if (lookahead == ValueEnum.OP_ADITIVO.getValue()) {
                Reconhecer(ValueEnum.OP_ADITIVO.getValue());
                printArvore(indice++, "Operador Aritimético => '+/-'");
                expression();
                expression_L1();
            }
            if (lookahead == ValueEnum.OP_MULTIPLICATIVO.getValue()) {
                Reconhecer(ValueEnum.OP_MULTIPLICATIVO.getValue());
                printArvore(indice++, "op_mult => *");
                expression();
                expression_L1();
            }
            if (lookahead == ValueEnum.OP_RELACIONAL.getValue()) {
                Reconhecer(ValueEnum.OP_RELACIONAL.getValue());
                printArvore(indice++, "op_relacional => '&&/||'");
                expression();
                expression_L1();
            }

            if (lookahead == ValueEnum.OP_LOGICO.getValue()) {
                Reconhecer(ValueEnum.OP_LOGICO.getValue());
                printArvore(indice++, "op_relacional");
                expression();
                expression_L1();
            }

        } else {
            System.out.println(" *** VAZIO *** \n");
        }
        indice--;
    } // ok

    private void statementBlock() throws IOException, Exception {
        indice++;
        printArvore(indice++, "statementBlock");
        if (lookahead == ValueEnum.ID.getValue() || lookahead == ValueEnum.IF.getValue() || lookahead == ValueEnum.WHILE.getValue()
                || lookahead == ValueEnum.RETURN.getValue() || lookahead == ValueEnum.BREAK.getValue()) {
            statement();
        } else if (lookahead == ValueEnum.ABRE_CHAVE.getValue()) {
            Reconhecer(ValueEnum.ABRE_CHAVE.getValue());
            printArvore(indice++, "Abre Cha => '{'");

            statementList();

            Reconhecer(ValueEnum.FECHA_CHAVE.getValue());
            printArvore(indice--, "Fecha Cha => '}'");
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
        indice--;
    }

    private void elseAlternative() throws IOException, Exception {
        indice++;
        printArvore(indice++, "elseAlternative");
        Reconhecer(ValueEnum.ELSE.getValue());
        printArvore(indice++, "else");
        statementBlock();
    } // ok

    private void whileStatement() throws IOException, Exception {
        indice++;
        printArvore(indice++, "whileStatement");
        Reconhecer(ValueEnum.WHILE.getValue());
        printArvore(indice++, "while");

        Reconhecer(ValueEnum.ABRE_PAR.getValue());
        printArvore(indice++, "Abre Par => '('");

        expression();

        Reconhecer(ValueEnum.FECHA_PAR.getValue());
        printArvore(indice++, "Fecha Par => ')'");
        statementBlock();
        indice--;
    } // ok

    private void statement_L1() throws IOException, Exception {
        indice++;
        printArvore(indice++, "statement_L1");

        if (lookahead == ValueEnum.ATRIB_.getValue()) {
            Reconhecer(ValueEnum.ATRIB_.getValue());
            printArvore(indice++, "op_atributo => '=' ");

            expression();
            statement_L2();

        } else if (lookahead == ValueEnum.ABRE_PAR.getValue()) {
            Reconhecer(ValueEnum.ABRE_PAR.getValue());
            printArvore(indice++, "Abre Pa => '('");

            expressionList();

            Reconhecer(ValueEnum.FECHA_PAR.getValue());
            printArvore(indice - 1, "Fecha Par => ')'");

            Reconhecer(ValueEnum.PONTO_E_VIRGULA.getValue());
            printArvore(indice - 1, "Ponto e Vírgula => ';'");
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
        indice--;
    } // ok

    private void statement_L2() throws IOException, Exception {
        indice++;
        printArvore(indice++, "statement_L2");
        if (lookahead == ValueEnum.PONTO_E_VIRGULA.getValue()) {
            Reconhecer(ValueEnum.PONTO_E_VIRGULA.getValue());
            printArvore(indice++, "Ponto e Vírgula ';'");
        } else if (lookahead == ValueEnum.ABRE_PAR.getValue()) {
            Reconhecer(ValueEnum.ABRE_PAR.getValue());
            printArvore(indice++, "Abre Par => '('");

            expressionList();

            Reconhecer(ValueEnum.FECHA_PAR.getValue());
            printArvore(indice++, "Fecha Par => ')'");

            Reconhecer(ValueEnum.PONTO_E_VIRGULA.getValue());
            printArvore(indice++, "Ponto e Vírgula => ';'");
        } else {
            System.out.println(" *** VAZIO *** \n");
        }
    } // ok
}
