package com.sean.scratch;

import java.util.Arrays;

import com.sean.scratch.utils.ArrayUtils;

public class Permutation {

	public static char [] permutations(char [] arr, final char [] orig)
	{
		return permutations(arr, orig, 0);
	}
	public static char [] permutations(char [] arr, final char [] orig, int index)
	{
		if ( arr.length == 1 )
		{
			System.out.println(arr);
		}
		for( int i = 0; i < arr.length - 1; i++ )
		{
			char temp = arr[i];
			
			arr[i] = arr[i + 1];
			
			arr[i + 1] = temp;
			
			System.out.println(arr);
		}
		
		if ( ++index < arr.length )
		{
			Permutation.permutations(arr, orig, index);
		}
		
		return arr;
	}

	public static void main(String[] args) 
	{
		String str = "seanroy";
		
		char [] arr = str.toCharArray();
		char [] orig = str.toCharArray();
		Permutation.permutations(arr, orig);
	}

}
