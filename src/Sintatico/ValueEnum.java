package Sintatico;

public enum ValueEnum {
    
    MAIN(310), BOOLEAN(320), CLASS(330), DO(340), ELSE(350),
    EXTENDS(360), FALSE(370), FOR(380), IF(390), INT(400),
    NEW(410), PUBLIC(420), RETURN(430), STATIC(440), THIS(450),
    TRUE(460), VOID(470), WHILE(480), SUPER(490), INSTANCEOF(500),
    IMPLEMENTS(510), BREAK(520), CASE(530), DEFAULT(540), SWITCH(550),
    FINALLY(560), THROW(570), THROWS(580), IMPORT(590), PACKAGE(600),
    CHAR(610), CONTINUE(620), STRING(630), ARG(640), ARGS(650),
    NUM(290), FLOAT(292), ID(300), ABRE_PAR(40), FECHA_PAR(41), ABRE_CHAVE(123),
    FECHA_CHAVE(125), ABRE_COLCHETE(91), FECHA_COLCHETE(93), OP_RELACIONAL(280),
    OP_MULTIPLICATIVO(281), OP_ADITIVO(282), PONTO_E_VIRGULA(59), PONTO(46),
    DOIS_PONTOS(58), VIRGULA(44), ATRIB_(61), OP_LOGICO(283);
    
    private final int value;
    
    private ValueEnum(int value) {
        this.value = value;
    }
    
    /**
     * 
     * @return Valor do Tokens
     */
    public int getValue() {
        return value;
    }

    
}
