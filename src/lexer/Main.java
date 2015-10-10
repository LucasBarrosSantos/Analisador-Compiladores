package lexer;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        File f = new File("").getAbsoluteFile();
        
        System.out.println(" TABELA DE TOKENS \n");
        
        AnalisadorLexo.main(new String[]{
            f + "/res/test.java"
        }
        );

//        new Patch();
        System.out.println("\nEm caso de alteração no arquivo.java vá para.: ");
        System.out.println("\n" + f.toString().replace("/", "\\") + "\\res\\");

    }
}
