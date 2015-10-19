package LeitorDeArquivo;

import java.io.File;

/**
 * Devolve o endereco onde está o arquivo de Entrada.
 */
public class GerarFile {
	private String vfinal;
	
	public GerarFile() {
		
	}
	
	public String File(){
		File file = new File("").getAbsoluteFile();
		vfinal = file.toString().replace("\\", "//") + "\\src\\Imput\\Entrada.txt".replace("\\", "//");
		return vfinal;
	}
}
