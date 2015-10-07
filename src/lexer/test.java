package lexer;

import java.util.Random;

public class test {
	
	/* Meu Comentário  :) */

	public static void main(String args[]){
		
		Random r = new Random();
		int entrada = r.nextInt(10);
		
		System.out.println("Fatorial de "+ entrada + " eh.: "+ fatorial(entrada));
	}
	
	public static int fatorial(int n){
		int valor = 1;
		
		if(n == 0)
			return 1;
		
		for(int i = 1; i <= n; i++){
			valor *= i;
		}
		
		return valor;
	}
	
}