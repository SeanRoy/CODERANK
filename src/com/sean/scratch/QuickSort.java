package com.sean.scratch;

import com.sean.scratch.utils.ArrayUtils;

// O(nlog(n))
public class QuickSort {
	
	public static int [] quickSort(int [] nums)
	{
		if ( nums.length >= 2 )
		{
			int pivot = nums[nums.length / 2];
			int leftCount = 0;
			int rightCount = 0;
			
			for( int i = 0; i < nums.length; i++ )
			{
				if ( nums[i] < pivot )
				{
					leftCount++;
				}
			}
			
			rightCount = nums.length - leftCount - 1; // minus one for the pivot.;
			
			int [] leftNums = new int[ leftCount ];
			int [] rightNums = new int[ rightCount ];
			
			int l = 0;
			int r = 0;
			
			for( int i = 0; i < nums.length; i++ )
			{
				if ( nums[i] < pivot )
				{
					leftNums[l++] = nums[i];
				}
				else if ( nums[i] > pivot )
				{
					rightNums[r++] = nums[i];
				}
			}
			
			leftNums = quickSort(leftNums);
			rightNums = quickSort(rightNums);
			
			System.arraycopy(leftNums, 0, nums, 0, leftNums.length);
			nums[leftNums.length]= pivot; 
			System.arraycopy(rightNums, 0, nums, leftNums.length + 1, rightNums.length);
		}
		
		return nums;
	}
	
	
	public static void main(String[] args) {
		int [] nums = {13, 1, 433, 3, 6, 35, 41, 400, 69, 30, 31, 66};

		ArrayUtils.printIntArray(nums);
		
		nums = QuickSort.quickSort(nums);
		
		ArrayUtils.printIntArray(nums);
	}

}
