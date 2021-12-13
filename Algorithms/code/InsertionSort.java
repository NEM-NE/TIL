package Algorithms.code;

import java.io.*;
import java.util.*;

public class InsertionSort {
	public static void main(String[] args) throws IOException {		
        int[] ary = {29, 10, 14, 37, 13};

        for (int i = 1; i < ary.length; i++) {
            for(int j = i; j > 0; j--){
                if(ary[j] < ary[j-1]){
                    int temp = ary[j];
                    ary[j] = ary[j-1];
                    ary[j-1] = temp;
                }else break;
            }
        }

        for (int i = 0; i < ary.length; i++) {
            System.out.print(ary[i] + ", ");
        }
		
	}
}