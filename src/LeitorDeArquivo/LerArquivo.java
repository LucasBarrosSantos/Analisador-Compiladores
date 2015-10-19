package LeitorDeArquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import lexer.AnalisadorLexo;

/**
 * Leitura do arquivo que. 
 * </p> <h2>Lê o arquivo de Entrada.</h2>
 */
public class LerArquivo {

	public static void main(String[] args) {
		/**
                 * A Class GerarFile, tem um construtor onde recupera o Arquivo de Entrada.txt
                 */
		GerarFile gerar = new GerarFile();
		
		String nome = gerar.File();
		String conteudo = new String();
		
		System.out.printf("\n Conteúdo do arquivo texto: \n");
		try {
                    try (FileReader arq = new FileReader(nome)) {
                        BufferedReader lerArq = new BufferedReader(arq);
                        
                        String linha = lerArq.readLine();
                        while (linha != null) {
                            conteudo += linha + "\n";
                            System.out.printf("%s\n", linha);
                            
                            linha = lerArq.readLine();
                        }
                        System.out.println();
                        AnalisadorLexo lexico = new AnalisadorLexo(new StringReader(conteudo));
                        lexico.yylex();
                    }
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",e.getMessage());
		}
	}
}