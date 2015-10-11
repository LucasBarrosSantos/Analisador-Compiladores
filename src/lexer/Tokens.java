package lexer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Tokens {

    private String yytex;

    private List<Tokens> escrever;

    public List<Tokens> getEscrever() {
        return escrever;
    }

    private String yytext;

    public Tokens() {

    }

    public Tokens(String t) {
        this.yytext = t;
    }

    public String getYytext() {
        return yytext;
    }

    public void setYytext(String yytext) {
        this.yytext = yytext;
    }

    public void setEscrever(List<Tokens> tks) {

        File file = new File("").getAbsoluteFile();
        //"/src/lexer/Arquivo.txt";  

        File arq = new File(file + "/src/lexer/Arquivo.txt");

        try {

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

            //Agora vamos escrever no arquivo com o método  println(),
            // que nos permite escrever linha a linha no arquivo
            try ( //Agora vamos usar a classe PrintWriter para escrever
                    //fisicamente no arquivo.
                    //Precisamos passar o objeto FileReader em seu construtor
                    PrintWriter printWriter = new PrintWriter(fileWriter)) {
                //Agora vamos escrever no arquivo com o método  println(),
                // que nos permite escrever linha a linha no arquivo

                for (Tokens u : tks) {
                    printWriter.println(u.getYytext());
                }

                //o método flush libera a escrita no arquivo
                printWriter.flush();

                //No final precisamos fechar o arquivo
            }

        } catch (IOException e) {
        }

    }

    public static void token(String yytex) throws IOException {
        
        File file = new File("").getAbsoluteFile();
        //"/src/lexer/Arquivo.txt";  

        File arq = new File(file + "/src/lexer/Arquivo.txt");
        FileWriter fileWriter = new FileWriter(arq, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        printWriter.println(yytex);
        //o método flush libera a escrita no arquivo
        printWriter.flush();

        //No final precisamos fechar o arquivo
        printWriter.close();

    }

}
