package lexer;

%%

%class AnalisadorLexo
%public
%standalone
%unicode
%line
%column

/*%{
  
 public static String saidaArquivo(String yytex){
        
        String textoQueSeraEscrito = yytex + "\n";
        File file = new File("").getAbsoluteFile();
        
        FileWriter arquivo;  
        
        try {
            arquivo = new FileWriter(new File(file + "/src/lexer/Arquivo.txt"));
            arquivo.write(textoQueSeraEscrito);
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        return null;
    }
%}*/

comentario = "/*" ~"*/"
espaco = [ \n\t\r]
letra = [A-Z]|[a-z]
digito = [0-9]
sinais = ( "-" | "+" | "*" | "/")
natural = ({digito} | [1-9]{digito}+)
inteiro = {sinais}{natural}
float = {inteiro}"."{digito}+(("E" | "e"){sinais}?{natural}?)?
id = ({letra} | _ )({letra} | {digito} | _ )*


%%

 // Palavra_Reservada (Tokens)
 boolean     {System.out.println(" BOOLEAN -> " + yytext().toString());}
 class       {System.out.println(" CLASS -> " + yytext().toString());}
 do          {System.out.println(" DO -> " + yytext().toString());}
 else        {System.out.println(" ELSE -> " + yytext().toString());}
 extends     {System.out.println(" EXTENDS -> " + yytext().toString());}
 false       {System.out.println(" FALSE -> " + yytext().toString());}
 for         {System.out.println(" FOR -> " + yytext().toString());}
 if          {System.out.println(" IF -> " + yytext().toString());}
 int         {System.out.println(" INT -> " + yytext().toString());}
 new         {System.out.println(" NEW -> " + yytext().toString());}
 public      {System.out.println(" PUBLIC -> " + yytext().toString());}
 return      {System.out.println(" RETURN -> " + yytext().toString());}
 static      {System.out.println(" STATIC -> " + yytext().toString());}
 this        {System.out.println(" THIS -> " + yytext().toString());}
 true        {System.out.println(" TRUE -> " + yytext().toString());}
 void        {System.out.println(" VOID -> " + yytext().toString());}
 while       {System.out.println(" WHILE -> " + yytext().toString());}
 super       {System.out.println(" SUPER -> " + yytext().toString());}
 instanceof  {System.out.println(" INSTANCEOF -> " + yytext().toString());}
 implements  {System.out.println(" IMPLEMENTS -> " + yytext().toString());}
 interface   {System.out.println(" INTERFACE -> " + yytext().toString());}
 break       {System.out.println(" BREAK -> " + yytext().toString());}
 case        {System.out.println(" CASE -> " + yytext().toString());}
 default     {System.out.println(" DEFAULT -> " + yytext().toString());}
 switch      {System.out.println(" SWITCH -> " + yytext().toString());}
 finally     {System.out.println(" FINALLY -> " + yytext().toString());}
 throw       {System.out.println(" TROW -> " + yytext().toString());}
 throws      {System.out.println(" TROWS -> " + yytext().toString());}
 import      {System.out.println(" IMPORT -> " + yytext().toString());}
 package     {System.out.println(" PACKAGE -> " + yytext().toString());}

// Delimitador

 "("    {System.out.println(" Abrepar -> " + yytext().toString());}   
 ")"    {System.out.println(" Fechapar -> " + yytext().toString());}
 "{"    {System.out.println(" Abrechave -> " + yytext().toString());}
 "}"    {System.out.println(" Fechachave -> " + yytext().toString());}
 "["    {System.out.println(" Abrecolc -> " + yytext().toString());}
 "]"    {System.out.println(" Fechacolc -> " + yytext().toString());}

// Operadores

 "||"   {System.out.println(" Op_ou -> " + yytext().toString());}
 "&&"   {System.out.println(" Op_e -> " + yytext().toString());}
 "=="   {System.out.println(" Op_igual_a -> " + yytext().toString());}
 "!="   {System.out.println(" Op_diferente_de -> " + yytext().toString());}
 "<"    {System.out.println(" Op_menor_que -> " + yytext().toString());}
 "<="   {System.out.println(" Op_menor_igual_a -> " + yytext().toString());}
 ">"    {System.out.println(" Op_maior_que -> " + yytext().toString());}
 ">="   {System.out.println(" Op_meior_igual_a -> " + yytext().toString());}
 "+"    {System.out.println(" Op_adção -> " + yytext().toString());}
 "-"    {System.out.println(" Op_subtração -> " + yytext().toString());}
 "*"    {System.out.println(" Op_multiplicação -> " + yytext().toString());}
 "/"    {System.out.println(" Op_divisão -> " + yytext().toString());}
 "%"    {System.out.println(" Op_mod -> " + yytext().toString());}
 "!"    {System.out.println(" Op_diferente_de -> " + yytext().toString());}

// Outros

 ";"    {System.out.println(" Term_ponto_e_virgula -> " + yytext().toString());}
 "."    {System.out.println(" Ponto -> " + yytext().toString());}
 "="    {System.out.println(" Atrib_ -> " + yytext().toString());}
 "+="   {System.out.println(" Atrib_ -> " + yytext().toString());}
 "-="   {System.out.println(" Atrib_ -> " + yytext().toString());}
 "*="   {System.out.println(" Atrib_ -> " + yytext().toString());}
 "/="   {System.out.println(" Atrib_ -> " + yytext().toString());}

{espaco}
{/* Fazer Nada */}

{comentario}
{System.out.println(" Comentario -> " + yytext().toString());}

{inteiro}
{System.out.println(" inteiro -> " + yytext().toString());}

{float}
{System.out.println(" Float -> " + yytext().toString());}

{id}
{System.out.println(" id -> " + yytext().toString());}