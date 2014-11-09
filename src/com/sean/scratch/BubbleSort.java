package com.sean.scratch;

public class BubbleSort 
{

	public static int [] bubbleSort(int [] nums)
	{
		return bubbleSort(0, nums);
	}
	public static int [] bubbleSort(int start, int [] nums)
	{
		if ( start < nums.length - 1 )
		{
			for( int j = nums.length - 1; j > start; --j)
			{
				if ( nums[j] < nums[start] )
				{
					swap( nums, start, j );
				}
			}
			
			nums = BubbleSort.bubbleSort(++start, nums);
		}
		
		return nums;
	}
	
	public static void swap( int [] nums, int indexA, int indexB )
	{
		int temp = nums[indexA];
		
		nums[indexA] = nums[indexB];
		
		nums[indexB] = temp;
	}
	
	public static void main(String [] args)
	{
		int [] nums = {13, 1, 3, 6, 35, 0, 41, -400, 69, 30, 15, 16, 41};
		
		int [] result = BubbleSort.bubbleSort(nums);
		
		for( int i : result )
		{
			System.out.print(i + ", " );
		}
		
	}
}
