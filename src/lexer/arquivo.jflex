package lexer;

%%
 
%class AnalisadorLexo
%public
%standalone
%unicode
%line
%column

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
boolean
| class
| do
| else
| extends
| false
| for
| if
| int
| new
| public
| return
| static
| this
| true
| void
| while
| for
| instanceof
{System.out.println("Palavra Reservada: " + yytext().toString());}

// Delimitador
"("
| ")"
| "{"
| "}"
| "["
| "]"
{System.out.println("Delimitador: " + yytext().toString());}

// Operadores
"||"
| "&&"
| "=="
| "!="
| "<"
| "<="
| ">"
| ">="
| "+"
| "-"
| "*"
| "/"
| "%"
| "!"
{System.out.println("Operador: " + yytext().toString());}

// Outros
";"
| "."
| "="
| "+="
| "-="
| "*="
| "/="
{System.out.println("Outros: " + yytext().toString());}

{espaco}
{/* Fazer Nada */}

{comentario}
{System.out.println("Comentario: " + yytext().toString());}

{inteiro}
{System.out.println("inteiro: " + yytext().toString());}

{float}
{System.out.println("Float: " + yytext().toString());}

{id}
{System.out.println("id: " + yytext().toString());}