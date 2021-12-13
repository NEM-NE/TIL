package Algorithms.code;

import java.io.*;
import java.util.*;

public class BubbleSort {
	public static void main(String[] args) throws IOException {		
        int[] ary = {5, 1, 9, 7, 2, 3};

        for (int i = 0; i < ary.length - 1; i++) {
            for(int j = 0; j < ary.length - 1; j++){
				if(ary[j] > ary[j+1]){
                    int temp = ary[j];
                    ary[j] = ary[j+1];
                    ary[j+1] = temp;
                }
            }
        }

        for (int i = 0; i < ary.length; i++) {
            System.out.print(ary[i] + ", ");
        }
		
	}
}