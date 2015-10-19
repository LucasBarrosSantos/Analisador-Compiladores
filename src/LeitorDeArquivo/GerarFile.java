package LeitorDeArquivo;

import java.io.File;

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
