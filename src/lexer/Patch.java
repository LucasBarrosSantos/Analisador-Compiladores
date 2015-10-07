package lexer;

import java.io.File;

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
