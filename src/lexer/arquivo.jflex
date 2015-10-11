package lexer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

%%

%class AnalisadorLexo
%public
%standalone
%unicode
%line
%column

    // Captura de Tokens
%{
  
 public static void token(String yytex) throws IOException {
        File file = new File("").getAbsoluteFile();
        //"/src/lexer/Arquivo.txt";  

        File arq = new File(file + "/src/lexer/Tokens.txt");

        //neste ponto criamos o arquivo fisicamente
        arq.createNewFile();

        //Devemos passar no construtor do FileWriter qual arquivo
        // vamos manipular.
        // Esse construtor aceita dois tipos de parâmetros,
        // File ou String.
        //O parâmetro true indica que reescrevemos no arquivo
        // sem apagar o que já existe.
        // O false apagaria o conteúdo do arquivo e escreveria
        // o novo conteúdo.
        // Se não usar o 2° parâmetro, ele por padrão será false.
        //O mais importante, essa linha abre o fluxo do arquivo 

        FileWriter fileWriter = new FileWriter(arq, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(yytex);
        //o método flush libera a escrita no arquivo
        printWriter.flush();

        //No final precisamos fechar o arquivo
        printWriter.close();

    }

%}

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
 boolean     {System.out.println(" BOOLEAN      -> " + yytext().toString());token(" BOOLEAN     -> " + yytext());}
 class       {System.out.println(" CLASS        -> " + yytext().toString());token(" CLASS       -> " + yytext());}
 do          {System.out.println(" DO           -> " +  yytext().toString());token(" DO         -> " + yytext());}
 else        {System.out.println(" ELSE         -> " + yytext().toString());token(" ELSE        -> " + yytext());}
 extends     {System.out.println(" EXTENDS      -> " + yytext().toString());token(" EXTENDS     -> " + yytext());}
 false       {System.out.println(" FALSE        -> " + yytext().toString());token(" FALSE       -> " + yytext());}
 for         {System.out.println(" FOR          -> " + yytext().toString());token(" FOR         -> " + yytext());}
 if          {System.out.println(" IF           -> " + yytext().toString());token(" IF          -> " + yytext());}
 int         {System.out.println(" INT          -> " + yytext().toString());token(" INT         -> " + yytext());}
 new         {System.out.println(" NEW          -> " + yytext().toString());token(" NEW         -> " + yytext());}
 public      {System.out.println(" PUBLIC       -> " + yytext().toString());token(" PUBLIC      -> " + yytext());}
 return      {System.out.println(" RETURN       -> " + yytext().toString());token(" RETURN      -> " + yytext());}
 static      {System.out.println(" STATIC       -> " + yytext().toString());token(" STATIC      -> " + yytext());}
 this        {System.out.println(" THIS         -> " + yytext().toString());token(" THIS        -> " + yytext());}
 true        {System.out.println(" TRUE         -> " + yytext().toString());token(" TRUE        -> " + yytext());}
 void        {System.out.println(" VOID         -> " + yytext().toString());token(" VOID        -> " + yytext());}
 while       {System.out.println(" WHILE        -> " + yytext().toString());token(" WHILE       -> " + yytext());}
 super       {System.out.println(" SUPER        -> " + yytext().toString());token(" SUPER       -> " + yytext());}
 instanceof  {System.out.println(" INSTANCEOF   -> " + yytext().toString());token(" INSTANCEOF  -> " + yytext());}
 implements  {System.out.println(" IMPLEMENTS   -> " + yytext().toString());token(" IMPLEMENTS  -> " + yytext());}
 interface   {System.out.println(" INTERFACE    -> " + yytext().toString());token(" INTERFACE   -> " + yytext());}
 break       {System.out.println(" BREAK        -> " + yytext().toString());token(" BREAK       -> " + yytext());}
 case        {System.out.println(" CASE         -> " + yytext().toString());token(" CASE        -> " + yytext());}
 default     {System.out.println(" DEFAULT      -> " + yytext().toString());token(" DEFAULT     -> " + yytext());}
 switch      {System.out.println(" SWITCH       -> " + yytext().toString());token(" SWITCH      -> " + yytext());}
 finally     {System.out.println(" FINALLY      -> " + yytext().toString());token(" FINALLY     -> " + yytext());}
 throw       {System.out.println(" TROW         -> " + yytext().toString());token(" TROW        -> " + yytext());}
 throws      {System.out.println(" TROWS        -> " + yytext().toString());token(" TROWS       -> " + yytext());}
 import      {System.out.println(" IMPORT       -> " + yytext().toString());token(" IMPORT      -> " + yytext());}
 package     {System.out.println(" PACKAGE      -> " + yytext().toString());token(" PACKAGE     -> " + yytext());}

// Delimitador

 "("    {System.out.println(" Abrepar           -> " + yytext().toString());token(" Abrepar     -> " + yytext());}   
 ")"    {System.out.println(" Fechapar          -> " + yytext().toString());token(" Fechapar    -> " + yytext());}
 "{"    {System.out.println(" Abrechave         -> " + yytext().toString());token(" Abrechave   -> " + yytext());}
 "}"    {System.out.println(" Fechachave        -> " + yytext().toString());token(" Fechachave  -> " + yytext());}
 "["    {System.out.println(" Abrecolc          -> " + yytext().toString());token(" Abrecolc    -> " + yytext());}
 "]"    {System.out.println(" Fechacolc         -> " + yytext().toString());token(" Fechacolc   -> " + yytext());}

// Operadores

 "||"   {System.out.println(" Op_ou             -> " + yytext().toString());token(" Op_ou       -> " + yytext());}
 "&&"   {System.out.println(" Op_e              -> " + yytext().toString());token(" Op_e        -> " + yytext());}
 "=="   {System.out.println(" Op_igual_a        -> " + yytext().toString());token(" Op_igual_a  -> " + yytext());}
 "!="   {System.out.println(" Op_diferente_de   -> " + yytext().toString());token(" Op_diferente_de -> " + yytext());}
 "<"    {System.out.println(" Op_menor_que      -> " +yytext().toString());token(" Op_menor_que  -> " + yytext());}
 "<="   {System.out.println(" Op_menor_igual_a  -> " + yytext().toString());token(" Op_menor_igual_a -> " + yytext());}
 ">"    {System.out.println(" Op_maior_que      -> " + yytext().toString());token(" Op_maior_que -> " + yytext());}
 ">="   {System.out.println(" Op_maior_igual_a  -> " + yytext().toString());token(" Op_maior_igual_a -> " + yytext());}
 "+"    {System.out.println(" Op_adção          -> " + yytext().toString());token(" Op_adção -> " + yytext());}
 "-"    {System.out.println(" Op_subtração      -> " + yytext().toString());token(" Op_subtração  -> " + yytext());}
 "*"    {System.out.println(" Op_multiplicação  -> " + yytext().toString());token(" Op_multiplicação -> " + yytext());}
 "/"    {System.out.println(" Op_divisão        -> " + yytext().toString());token(" Op_divisão    -> " + yytext());}
 "%"    {System.out.println(" Op_mod            -> " + yytext().toString());token(" Op_mod        -> " + yytext());}
 "!"    {System.out.println(" Op_diferente_de   -> " + yytext().toString());token(" Op_diferente_de -> " + yytext());}

// Outros

 ";"    {System.out.println(" Term_ponto_e_virgula -> " + yytext().toString());token(" Term_ponto_e_virgula -> " + yytext());}
 "."    {System.out.println(" Ponto             -> " + yytext().toString());token(" Ponto       -> " + yytext());}
 "="    {System.out.println(" Atrib_            -> " + yytext().toString());token(" Atrib_      -> " + yytext());}
 "+="   {System.out.println(" Atrib_            -> " + yytext().toString());token(" Atrib_      -> " + yytext());}
 "-="   {System.out.println(" Atrib_            -> " + yytext().toString());token(" Atrib_      -> " + yytext());}
 "*="   {System.out.println(" Atrib_            -> " + yytext().toString());token(" Atrib_      -> " + yytext());}
 "/="   {System.out.println(" Atrib_            -> " + yytext().toString());token(" Atrib_      -> " + yytext());}

{espaco}
{/* Fazer Nada */}

{comentario}
{System.out.println(" Comentario                -> " + yytext().toString());token(" Comentario  -> " + yytext());}

{inteiro}
{System.out.println(" inteiro                   -> " + yytext().toString());token(" inteiro     -> " + yytext());}

{float}
{System.out.println(" Float                     -> " + yytext().toString());token(" Float       -> " + yytext());}

{id}
{System.out.println(" id                        -> " + yytext().toString());token(" id          -> " + yytext());}