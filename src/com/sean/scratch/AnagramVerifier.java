package com.sean.scratch;

public class AnagramVerifier {

	// O(n/2) -> O(n)
	public static boolean verify(String str)
	{
		int length = str.length();
		
		char [] strArray = str.toCharArray();
		
		int mid1 = length / 2;
		int mid2 = mid1;
		
		if ( length % 2 > 0 )
		{
			mid2++;
		}
		
		for( int i = 0, j = length - 1; i <= mid2 && j >= mid1; i++,j-- )
		{
			if ( strArray[i] != strArray[j] )
			{
				return false;
			}
		}
		
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str = "racecar";
		
		
		System.err.println( str + " is an anagram: " + AnagramVerifier.verify(str) );
	}

}
