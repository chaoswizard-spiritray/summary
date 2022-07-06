/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

/**
 *
 * @author Chen
 */
class Passer {

    static final int x = 5;
    void go(int x) {
        System.out.print(x++);
    }
     public static void main(String[] args) {
        new Passer().go(x);
        System.out.print(x);
        int s[]=new int[10];
    }
}
