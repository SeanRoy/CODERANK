package com.sean.scratch;

import com.sean.scratch.utils.ArrayUtils;

public class SelectionSort {

	
	public void selectionSort(int [] nums)
	{
		selectionSort(0, nums);
	}
	
	public void selectionSort(int start, int [] nums)
	{
		if ( start < nums.length )
		{
			int lowestIndex = start;
			
			for( int i = start + 1; i < nums.length; i++ )
			{
				if ( nums[i] < nums[lowestIndex] )
				{
					lowestIndex = i;
				}
			}
			
			swap(nums, start, lowestIndex);
			
			selectionSort(++start, nums);
		}
	}
	
	public void swap(int [] nums, int a, int b)
	{
		int temp = nums[a];
		
		nums[a] = nums[b];
		
		nums[b] = temp;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SelectionSort ss = new SelectionSort();
		
		int [] nums = { 99, 324, 32, 1, 90, 234 };
		
		ArrayUtils.printIntArray(nums);
		
		ss.selectionSort(nums);
		
		ArrayUtils.printIntArray(nums);
	}

}
