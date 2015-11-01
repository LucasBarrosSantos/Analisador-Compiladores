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
            setLookahead(proximo_token());
        }else{
            error(token);
        }
    }

    /**
     *
     * @throws IOException
     */
    public void classDeclaration() throws IOException {
        arvore(indice, "classDeclaration");
        index++;
        setLookahead(proximo_token());
        if (getLookahead() == ValueEnum.PUBLIC.getValue()) {
            Reconhecer(ValueEnum.PUBLIC.getValue());
            arvore(indice+1, "public");
            Reconhecer(ValueEnum.CLASS.getValue());
            arvore(indice+1, "class");
            Reconhecer(ValueEnum.ID.getValue());
            arvore(indice+1, "id");
            indice++;
            classBody();
        }else{
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
            System.out.print("    ");
        }
        System.out.print("|_" + s + "\n");
    }

    private void classBody() throws IOException {
        indice++;
        arvore(indice, "classBody");
        setLookahead(proximo_token());
        if(getLookahead() == ValueEnum.ABRE_CHAVE.getValue()){
            Reconhecer(ValueEnum.ABRE_CHAVE.getValue());
            arvore(indice+1, "{");
            classBodyDeclaration();
            Reconhecer(ValueEnum.FECHA_CHAVE.getValue());
            arvore(indice-1, "}");
        }
    }

    private void classBodyDeclaration() throws IOException {
        indice++;
        arvore(indice+1, "classBodyDeclaration");
        
        declarationList();  
        methodDeclaration(); 
        indice--;
    }

    private void declarationList() throws IOException {
        indice++;
        arvore(indice, "declarationList");
        setLookahead(proximo_token());
        declaration();
        if(getLookahead() == ValueEnum.PONTO_E_VIRGULA.getValue()){
            Reconhecer(ValueEnum.PONTO_E_VIRGULA.getValue());
            /**
             * Falta inplementar
             */
        }
        
    }

    private void methodDeclaration() throws IOException {
        indice++;
        arvore(indice, "methodDeclaration");
        setLookahead(proximo_token());
    }

    private void declaration() {

    }
}
