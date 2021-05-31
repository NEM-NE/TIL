package BaekJoon;

import java.io.*;
import java.util.*;

public class Test {
	// 2 : 01

	static void sort(int[] ary, int start, int mid, int end) {
		int left = start;
		int right = mid+1;
		int index = start;
		int[] temp = new int[ary.length];
		
		while(left <= mid && right <= end) {
			if(ary[left] > ary[right]) {
				temp[index++] = ary[right++];
			}else temp[index++] = ary[left++];
		}
		
		while(left <= mid) temp[index++] = ary[left++];
		while(right <= mid) temp[index++] = ary[right++];
		
		for(int i = start; i < index; i++)
			ary[i] = temp[i];
	}
	
	static void mergeSort(int[] ary, int start, int end) {
		if(start < end) {
			int mid = (start + end) / 2;
			mergeSort(ary, start, mid);
			mergeSort(ary, mid+1, end);
			sort(ary, start, mid, end);
		}
	}
	
	public static void main(String[] args) throws IOException {
		int[] ary = {4, 77, 12, 34, 1, 67, 4, 20, 3, 2, 7, 5, 10};
		
		mergeSort(ary, 0, ary.length-1);
		
		for(int i = 0; i < ary.length; i++) {
			System.out.println(ary[i]);
		}
	}

}