package lexer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Teste {

    public static void saidaArquivo(String yytex) throws IOException {

        File file = new File("").getAbsoluteFile();
        FileWriter arquivo;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file + "/src/lexer/Arquivo.txt"))) {
            if (leitor(file.toString() + "/src/lexer/Arquivo.txt", yytex)) {
                bw.append(yytex);
            }
        }
    }

    public static Boolean leitor(String path, String yytex) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));

        while (true) {
            if (yytex != null) {
                return true;
            }
            yytex = buffRead.readLine();
        }

    }

    public static void main(String[] args) throws Exception {

        saidaArquivo(" A ");
        saidaArquivo(" B ");
        saidaArquivo(" C ");

    }
}
