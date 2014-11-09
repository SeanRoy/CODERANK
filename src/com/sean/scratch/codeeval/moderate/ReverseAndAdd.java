package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReverseAndAdd
{
	public static String findPalindrome(int x, int tries)
	{
		String str = Integer.toString(x);
		
		if ( !palindrome(str) )
		{
			// reverse
			int reverse = reverseNumber(str);
			return findPalindrome(reverse + x, ++tries);
		}
		
		return tries + " " + x;
	}
	public static boolean palindrome(String str)
	{
		for( int i = 0, j = str.length() - 1; i <= j; i++,j-- )
		{
			if ( str.charAt(i) != str.charAt(j) )
			{
				return false;
			}
		}
		
		return true;
	}
	public static int reverseNumber(String x)
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i = x.length() - 1; i >= 0; i-- )
		{
			sb.append(x.charAt(i));
		}
		
		return Integer.parseInt(sb.toString());
	}
	
	// This doesn't work for this program because it ends up overloading the integer
	// several times, causing a false positive for 196, which doesn't have a reverse
	// and add palindrome solution yet. (80 291626192)
	public static int reverseNumberBad(String x)
	{
		// 195
		// 591
		int num = 0;
		
		for( int i = 0; i < x.length(); i++ )
		{
			num += ( x.charAt(i) - '0') * Math.pow(10, i);
		}
		
		return num;
	}
	public static void main(String [] args)
	{
		if ( args.length >= 1 )
		{
			String fileName = args[0];
			
			BufferedReader bReader = null;
			
			try
			{
				bReader = new BufferedReader(new FileReader(fileName) );
				
				String line = null;
				
				while( ( line = bReader.readLine() ) != null )
				{
					if (!line.equals("") )
					{
						System.out.println(ReverseAndAdd.findPalindrome(Integer.parseInt(line), 0));
					}
				}
			}
			catch(FileNotFoundException fnfe)
			{
				System.err.println("File not found: " + fnfe.getMessage());
			}
			catch(IOException ioe)
			{
				System.err.println("Error reading file: " + ioe.getMessage() );
			}
			finally
			{
				try
				{
					bReader.close();
				}
				catch(Exception e){}
			}
		}

	}
}
