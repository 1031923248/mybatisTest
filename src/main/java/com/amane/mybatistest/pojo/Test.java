package com.amane.mybatistest.pojo;

public class Test {
    private static void merge(int[] arr, int[] result, int start, int end) {
        if (start >= end) return;
        int center = (start + end) / 2;
        int start1 = start, end1 = center;
        int start2 = center + 1, end2 = end;
        merge(arr, result, start1, end1);
        merge(arr, result, start2, end2);
        int k = start1;
        while (start1 <= end1 && start2 <= end2) {
            if (arr[start1] < arr[start2]) {
                result[k++] = arr[start1++];
            } else {
                result[k++] = arr[start2++];
            }
        }
        while (start1 <= end1) {
            result[k++] = arr[start1++];
        }

        while (start2 <= end2) {
            result[k++] = arr[start2++];
        }
        for (k = start; k <= end; k++) {
            arr[k] = result[k];
        }
        for (int i = 0; i <= arr.length - 1; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        int[] arr = {13,31,45,65,77,89,12,58};
        int[] res = new int[8];
        merge(arr,res,0,7);
    }
}
