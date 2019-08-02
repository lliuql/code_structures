package com.liu.learn_01_sparsearray;

import java.io.*;
import java.math.BigDecimal;

/**
 * @description:
 * @author: Lqh
 * @time: 2019/8/2 15:07
 */
public class SaveArray {
    public static void main(String[] args) throws IOException {
        int n = 5;  //N*N数组
        double[][] arr = new double[n][n]; //插入的数组
        double[][] arr2 = new double[n][n];
        ;  //读取出的数组

        //数组初始化，随机生成的[0,100)之间的double数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = new BigDecimal(Math.random() * 100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() ;
                System.out.println(arr[i][j]);
            }
        }

//        InputStream resourceAsStream = SaveArray.class.getClassLoader().getResourceAsStream("/array.txt");

//        OutputStreamWriter inputStreamReader = new OutputStreamWriter(resourceAsStream);

//        inputStreamReader
        String path = SaveArray.class.getResource("").getPath();
        File file = new File(path + "array.txt");

        FileWriter out = new FileWriter(file);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.write(arr[i][j] + "\t");
            }
            out.write("\r\n");
        }
        out.close();
    }

    /**
     * @description:
     * @author: Lqh
     * @time: 2019/8/2 14:19
     */
    public static class SparseArray {

        public static void main(String[] args) {
    //  步骤:
    //      1. 遍历原始数组，等到有效的数据个数 sum
    //      2. 根据创建稀疏数组sparseArr int [sum+1][3]
    //      3. 将二维有效数组放入到稀疏数组中
            int chessArr1[][] = new int [11][11];
            chessArr1[1][2] = 1;
            chessArr1[2][3] = 2;
            chessArr1[4][5] = 2;

            // 输出原始的二维数组
            System.out.println("原始的二维数组~~");
            for (int[] row : chessArr1) {
                for (int data : row) {
                    System.out.printf("%d\t", data);
                }
                System.out.println();
            }


            int sum = 0;
    //     1. 遍历原始数组，等到有效的数据个数

            for (int i = 0; i < chessArr1.length; i++) {
                for (int j = 0; j < chessArr1[i].length; j++) {
                    if (chessArr1[i][j] > 0 ) sum ++;
                }
            }
    //      2. 根据创建稀疏数组sparseArr int [sum+1][3]
            int[][] sparseArr = new int[sum + 1][3];
    //      3. 将二维有效数组放入到稀疏数组中
            sparseArr[0][0] = 11;
            sparseArr[0][1] = 11;
            sparseArr[0][2] = sum;

            int count = 1;
            for (int i = 0; i < chessArr1.length; i++) {
                for (int j = 0; j < chessArr1[i].length; j++) {
                    if (chessArr1[i][j] > 0 ) {
                        sparseArr[count][0] = i;
                        sparseArr[count][1] = j;
                        sparseArr[count][2] = chessArr1[i][j];
                        count ++;
                    }
                }
            }

            // 输出稀疏数组的形式
            System.out.println();
            System.out.println("得到稀疏数组为~~~~");
            for (int i = 0; i < sparseArr.length; i++) {
                System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
            }
            System.out.println();


            // 通过稀疏数组转成 普通数组
            int[][] ints = new int[sparseArr[0][0]][sparseArr[0][1]];

            for (int i = 1; i < sparseArr.length; i++) {
                ints[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
            }
    // 输出原始还原的二维数组
            System.out.println("还原的二维数组~~");
            for (int[] row : ints) {
                for (int data : row) {
                    System.out.printf("%d\t", data);
                }
                System.out.println();
            }
        }
    }
}
