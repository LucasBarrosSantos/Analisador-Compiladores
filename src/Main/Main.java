package Main;

import java.io.File;
import lexer.AnalisadorLexo;

import lexer.Patch;
import lexer.TokensAll;

public class Main {    
    
    public static void main(String[] args) throws Exception {

        File f = new File("").getAbsoluteFile();

        System.out.println(" TABELA DE TOKENS \n");

        AnalisadorLexo.main(new String[]{
            f + "/src/lexer/Soma.java".trim()
        }
        );
        
//        new Patch();       

        System.out.println("\nEm caso de alteração no arquivo.java vá para.: ");
        System.out.println("\n" + f.toString().replace("/", "\\") + "\\res\\");
    }
}
