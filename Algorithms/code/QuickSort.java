package Algorithms.code;

public class QuickSort {
    public static void main(String[] args) {
        int[] list = {35, 33, 42, 10, 14, 19, 27, 44, 26, 31};

        quickSort(list, 0, list.length - 1);

        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + ", ");
        }

    }

    public static void quickSort(int[] list, int start, int end) {
        if(start >= end) return;

        int pivot = parition(list, start, end);
        quickSort(list, start, pivot - 1);
        quickSort(list, pivot + 1, end);
    }

    public static int parition(int[] list, int start, int end) {
        int left = start;
        int right = end-1; // end에서 end-1로 변경
        int pivot = list[end];

        while(left < right){
            while(list[left] < pivot && left < right) left++;
            while(list[right] >= pivot && left < right) right--;

            swap(list, left, right);    // 기준에 만족하니 서로 바꿔준다.
        }

        swap(list, end, right); // 피봇 기준을 right와 변경해준다.

        return right; // 이제 right가 새로운 pivot
    }

    public static void swap(int[] list, int left, int right) {
        int temp = list[left];
        list[left] = list[right];
        list[right] = temp;
    }
}
