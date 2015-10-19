package Lexemas;

public class TestarToken {

    public static void main(String[] args) throws Exception {

        Token to = new Token();

        to.addSingle(new Token("public"));
        to.addSingle(new Token("static"));
        to.addSingle(new Token("void"));
        to.addSingle(new Token("main"));
        to.addSingle(new Token("("));
        to.addSingle(new Token("String"));
        to.add(new Token("temp", 43));
        to.add(new Token("a", 44));
        to.add(new Token("e", 45));
        to.add(new Token("a", 44));
        to.add(new Token("num", 46));
        to.add(new Token("temo", 39));

        for (Token tk : to.getTokens()) {
            System.out.print(" Token   - " + tk.getNome() + "\n");
            System.out.print(" ASC     - " + tk.getCodASC() + "\n");
            System.out.print(" Posicao - " + tk.getPosicao() + "\n");
            System.out.println("");
        }
    }
}
