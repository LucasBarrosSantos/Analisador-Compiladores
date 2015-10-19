package lexer;

import java.io.File;

/**
 * Class responsável por gerar o lex. 
 * Especificações do JFlex
 * Mais informações consultar <a href="http://jflex.de/"> Aqui </a>
 */
public final class Patch {
	
	public Patch() {
		gerarPatch();
	}
	
	public void gerarPatch(){
		
		File  f = new File("").getAbsoluteFile();
		
		String patch = f + "\\src\\lexer\\arquivo.jflex".replace("\\", "//");
		
		jflex.Main.generate(new File(patch));
	}
	
}
