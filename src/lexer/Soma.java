/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

public class Soma {

    public static void main(String arg[]) {
        int a, b, c;
        b = 10;
        c = 3;
        a = func(b, b + c);
    }

    public static int func(int x, int y) {
        int ret;
        ret = x + y;
        return ret;
    }
}
