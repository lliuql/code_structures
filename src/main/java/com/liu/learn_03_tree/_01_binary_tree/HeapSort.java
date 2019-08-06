package com.liu.learn_03_tree._01_binary_tree;

import java.util.Arrays;

/**
 * @description:
 * @author: Lqh
 * @time: 2019/8/6 10:32
 */
public class HeapSort {
    public static void main(String[] args) {
        int arr[] = {4, 6, 8, 22,11,33,5, 9};
        heapSort(arr);
    }

    public static void heapSort(int arr[]){
        System.out.println("堆排序");

        int length = arr.length;
        while (length > 1) {
            for (int i = length / 2 - 1;i >= 0 ; i--) {
                adjustHeap(arr, i, length);
            }

            length --;
            int temp = arr[0];
            arr[0] = arr[length];
            arr[length] = temp;
            System.out.println("第"+(arr.length-length)+"次排序开始");
            System.out.println(Arrays.toString(arr));
            System.out.println("第"+(arr.length-length)+"次排序结束");

        }

    }

    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i];

        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k = k +1;
            }

            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }

        }

        arr[i] = temp;


//        Math.max()

//        找到左右节点进行比较 如果比当前大进行置换。
        // 置换结束后要进行 递归调用 子节点。
        // 如果子节点左右 不符合 大顶堆需要继续置换。 并对置换的结果继续递归调用。
        // 什么时候结束递归。要调整的左右节点均为 超过返回，结束递归。
    }
}
