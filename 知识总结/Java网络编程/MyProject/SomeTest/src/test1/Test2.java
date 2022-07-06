/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chen
 */
class Average {

    public static void main(String args[]){
        int score[] = new int[30];
        /* 以下输入30个学生的成绩存入数组score中 */
        BufferedReader br =  new BufferedReader(new InputStreamReader(
                System.in));

        for (int k = 0; k < 5; k++) {
            System.out.print("输入一个学生成绩：");

            String s=null;
            try {
                s = br.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            score[k] = Integer.parseInt(s);
        }
        /*   以下计算平均成绩  */
        int sum = 0;
        for (int k = 0; k < 5; k++) {
            sum += score[k];
        }
        System.out.println("平均成绩为：" + sum / 5);

    }
}
