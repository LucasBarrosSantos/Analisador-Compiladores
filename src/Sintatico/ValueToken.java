package Sintatico;

import Lexemas.Token;

public class ValueToken {
        
    /**
     * Construtor Token
     */
    public ValueToken() {
    }

    /**
     *
     * @param t Token Objeto
     * @return Valor inteiro do Token
     */
    public int Token(Token t) {

        switch (t.getNome().trim()) {
            case "main":
                return ValueEnum.MAIN.getValue();

            case "boolean":
                return ValueEnum.BOOLEAN.getValue();

            case "class":
                return ValueEnum.CLASS.getValue();

            case "do":
                return ValueEnum.DO.getValue();

            case "else":
                return ValueEnum.ELSE.getValue();

            case "extends":
                return ValueEnum.EXTENDS.getValue();

            case "false":
                return ValueEnum.FALSE.getValue();

            case "for":
                return ValueEnum.FOR.getValue();

            case "if":
                return ValueEnum.IF.getValue();

            case "int":
                return ValueEnum.INT.getValue();

            case "new":
                return ValueEnum.NEW.getValue();

            case "public":
                return ValueEnum.PUBLIC.getValue();

            case "return":
                return ValueEnum.RETURN.getValue();

            case "static":
                return ValueEnum.STATIC.getValue();

            case "this":
                return ValueEnum.THIS.getValue();

            case "true":
                return ValueEnum.TRUE.getValue();

            case "void":
                return ValueEnum.VOID.getValue();

            case "while":
                return ValueEnum.WHILE.getValue();

            case "super":
                return ValueEnum.SUPER.getValue();

            case "instanceof":
                return ValueEnum.INSTANCEOF.getValue();

            case "implements":
                return ValueEnum.IMPLEMENTS.getValue();

            case "break":
                return ValueEnum.BREAK.getValue();

            case "case":
                return ValueEnum.CASE.getValue();

            case "default":
                return ValueEnum.DEFAULT.getValue();

            case "switch":
                return ValueEnum.SWITCH.getValue();

            case "finally":
                return ValueEnum.FINALLY.getValue();

            case "throw":
                return ValueEnum.THROW.getValue();

            case "throws":
                return ValueEnum.THROWS.getValue();

            case "import":
                return ValueEnum.IMPORT.getValue();

            case "package":
                return ValueEnum.PACKAGE.getValue();

            case "char":
                return ValueEnum.CHAR.getValue();

            case "continue":
                return ValueEnum.CONTINUE.getValue();

            case "String":
                return ValueEnum.STRING.getValue();

            case "arg":
                return ValueEnum.ARG.getValue();

            case "args":
                return ValueEnum.ARGS.getValue();

            case "id":
                return ValueEnum.NUM.getValue();
             
            case "float":
                return ValueEnum.FLOAT.getValue();
             
            case "num":
                return ValueEnum.NUM.getValue();
            
            case "op_aditivo":
                return ValueEnum.OP_ADITIVO.getValue();
             
            case "op_multiplicativo":
                return ValueEnum.OP_MULTIPLICATIVO.getValue();
                
            case "operador_relacional":
                return ValueEnum.OP_RELACIONAL.getValue();
                
            default:
                return 0;
        }
    }
}
