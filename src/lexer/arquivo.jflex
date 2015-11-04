package lexer;
import Lexemas.Token;



%%

%class AnalisadorLexo
%public
%final
%standalone
%unicode
%line
%column

%{
    
   Token to = new Token();

%}

 // Tokens
operador_relacional = "<" | ">" | "<=" | ">=" | "==" | "!="  
op_aditivo = ("+" | "-")
op_logic = ("||" | "&&")
op_multiplicativo = (" * " | " / " | " % " | " && ")
comentario = "/*" ~"*/"
espaco = [ \n\t\r ]
letra = [A-Z]|[a-z]
digito = [0-9]
num = ({digito} | [1-9]{digito}+)
inteiro = {num}
float = {inteiro}"."{digito}+(("E" | "e"){op_aditivo}?{num}?)?
id = ({letra} | _ )({letra} | {digito} | _ )*


%%

 // Palavra_Reservada (Tokens)
 main        {System.out.println(" MAIN         -> " + yytext());to.addSingle(new Token(yytext()));}
 boolean     {System.out.println(" BOOLEAN      -> " + yytext());to.addSingle(new Token(yytext()));}
 class       {System.out.println(" CLASS        -> " + yytext());to.addSingle(new Token(yytext()));}
 do          {System.out.println(" DO           -> " +  yytext());to.addSingle(new Token(yytext()));}
 else        {System.out.println(" ELSE         -> " + yytext());to.addSingle(new Token(yytext()));}
 extends     {System.out.println(" EXTENDS      -> " + yytext());to.addSingle(new Token(yytext()));}
 false       {System.out.println(" FALSE        -> " + yytext());to.addSingle(new Token(yytext()));}
 for         {System.out.println(" FOR          -> " + yytext());to.addSingle(new Token(yytext()));}
 if          {System.out.println(" IF           -> " + yytext());to.addSingle(new Token(yytext()));}
 int         {System.out.println(" INT          -> " + yytext());to.addSingle(new Token(yytext()));}
 new         {System.out.println(" NEW          -> " + yytext());to.addSingle(new Token(yytext()));}
 public      {System.out.println(" PUBLIC       -> " + yytext());to.addSingle(new Token(yytext()));}
 return      {System.out.println(" RETURN       -> " + yytext());to.addSingle(new Token(yytext()));}
 static      {System.out.println(" STATIC       -> " + yytext());to.addSingle(new Token(yytext()));}
 this        {System.out.println(" THIS         -> " + yytext());to.addSingle(new Token(yytext()));}
 true        {System.out.println(" TRUE         -> " + yytext());to.addSingle(new Token(yytext()));}
 void        {System.out.println(" VOID         -> " + yytext());to.addSingle(new Token(yytext()));}
 while       {System.out.println(" WHILE        -> " + yytext());to.addSingle(new Token(yytext()));}
 super       {System.out.println(" SUPER        -> " + yytext());to.addSingle(new Token(yytext()));}
 instanceof  {System.out.println(" INSTANCEOF   -> " + yytext());to.addSingle(new Token(yytext()));}
 implements  {System.out.println(" IMPLEMENTS   -> " + yytext());to.addSingle(new Token(yytext()));}
 interface   {System.out.println(" INTERFACE    -> " + yytext());to.addSingle(new Token(yytext()));}
 break       {System.out.println(" BREAK        -> " + yytext());to.addSingle(new Token(yytext()));}
 case        {System.out.println(" CASE         -> " + yytext());to.addSingle(new Token(yytext()));}
 default     {System.out.println(" DEFAULT      -> " + yytext());to.addSingle(new Token(yytext()));}
 switch      {System.out.println(" SWITCH       -> " + yytext());to.addSingle(new Token(yytext()));}
 finally     {System.out.println(" FINALLY      -> " + yytext());to.addSingle(new Token(yytext()));}
 throw       {System.out.println(" TROW         -> " + yytext());to.addSingle(new Token(yytext()));}
 throws      {System.out.println(" TROWS        -> " + yytext());to.addSingle(new Token(yytext()));}
 import      {System.out.println(" IMPORT       -> " + yytext());to.addSingle(new Token(yytext()));}
 package     {System.out.println(" PACKAGE      -> " + yytext());to.addSingle(new Token(yytext()));}
 char        {System.out.println(" CHAR         -> " + yytext());to.addSingle(new Token(yytext()));}
 continue    {System.out.println(" CONTINUE     -> " + yytext());to.addSingle(new Token(yytext()));}
 String      {System.out.println(" STRING       -> " + yytext());to.addSingle(new Token(yytext()));}
 arg         {System.out.println(" ARG       -> " + yytext());to.addSingle(new Token(yytext()));}
 args        {System.out.println(" ARGS       -> " + yytext());to.addSingle(new Token(yytext()));}
// Delimitador

 "("    {System.out.println(" Abrepar           -> " + yytext());to.token("40" + "\n");}   
 ")"    {System.out.println(" Fechapar          -> " + yytext());to.token("41" + "\n");}
 "{"    {System.out.println(" Abrechave         -> " + yytext());to.token("123" + "\n");}
 "}"    {System.out.println(" Fechachave        -> " + yytext());to.token("125" + "\n");}
 "["    {System.out.println(" Abrecolc          -> " + yytext());to.token("91" + "\n");}
 "]"    {System.out.println(" Fechacolc         -> " + yytext());to.token("93" + "\n");}

// Operadores Relacionais

{operador_relacional} 
{System.out.println(" Op_relacional   -> " + yytext());to.token("280" + "\n");}

// Outros

{op_logic}
{System.out.println(" Op_logico           -> " + yytext());to.token("283" + "\n");}

{op_multiplicativo}   
{System.out.println(" Op_multiplicativo   -> " + yytext());to.token("281" + "\n");}

{op_aditivo}
{System.out.println(" op_aditivo          -> " + yytext());to.token("282" + "\n");}

 ";"    {System.out.println(" Term_ponto_e_virgula -> " + yytext());to.token("59" + "\n");}
 "."    {System.out.println(" Ponto             -> " + yytext());to.token("46" + "\n");}
 ":"    {System.out.println(" Dois Ponto        -> " + yytext());to.token("58" + "\n");}
 ","    {System.out.println(" VÍRGULA           -> " + yytext());to.token("44" + "\n");}
 "="    {System.out.println(" Atrib_            -> " + yytext());to.token("61" + "\n");}

{espaco}
{/* Fazer Nada */}

{comentario}
{System.out.println(" Comentario                -> " + yytext());}

{num}
{System.out.println(" inteiro                   -> " + yytext());to.token("290" + " " + yytext() + "\n");}

{float}
{System.out.println(" Float                     -> " + yytext());to.token("292" + " " + yytext() + "\n");}

{id}
{System.out.println(" id                        -> " + yytext());to.add(new Token(yytext(), 300));}
