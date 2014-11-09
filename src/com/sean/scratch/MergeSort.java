package com.sean.scratch;

import com.sean.scratch.utils.ArrayUtils;

// O(nlog(n))
public class MergeSort 
{
	public static int [] mergeSort( int [] data )
	{
		if ( data.length >= 2 )
		{
			int middle = data.length / 2;
			
			int [] left = new int[ middle ];
			int [] right = new int[data.length - middle];
			
			
			System.arraycopy(data, 0, left, 0, left.length);
			System.arraycopy(data, middle, right, 0, right.length);
			
			left = mergeSort(left);
			right = mergeSort(right);
			
			data = merge(data, left, right);

			ArrayUtils.printIntArray(data);
		}
		
		return data;
	}
	
	public static int [] merge(int [] data, int [] left, int [] right )
	{
		int lIndex = 0;
		int rIndex = 0;
		int dIndex = 0;
		
		while( lIndex < left.length && rIndex < right.length )
		{
			if ( left[lIndex] <= right[rIndex] )
			{
				data[dIndex++] = left[lIndex++];
			}
			else
			{
				data[dIndex++] = right[rIndex++];
			}
		}
		
		while( lIndex < left.length )
		{
			data[dIndex++] = left[lIndex++];
		}
		
		while( rIndex < right.length )
		{
			data[dIndex++] = right[rIndex++];
		}
		
		return data;
	}
	
	public static void main(String [] args) 
	{
		int [] nums = {13, 1, 3, 6, 35, 41, 400, 69, 30};
		
		ArrayUtils.printIntArray(nums);
		
		nums = MergeSort.mergeSort(nums);
		
		ArrayUtils.printIntArray(nums);
	}

}
