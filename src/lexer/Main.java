package lexer;

import java.io.File;

public class Main {

	public static void main(String[] args) {

		AnalisadorLexo.main(new String[] 
                { 
                    "/media/lucas/Data/Projects/workspace/Analasidor-master//res//test.java" 
                }
                        
                );

		new Patch();

		File f = new File("").getAbsoluteFile();
		System.out.println("\nEm caso de alteração no arquivo.java vá para.: ");
		System.out.println("\n" + f.toString().replace("/", "\\") + "\\res\\");

	}
}