package com.sean.scratch.codeeval.hard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * https://www.codeeval.com/open_challenges/38/
 * @author Sean
 *
 */
public class StringCombinations
{
	public static void permutations( String str, int length )
	{
		Hashtable<Character, Integer> table = new Hashtable<Character, Integer>();
		
		for( int i = 0; i < str.length(); i++ )
		{
			table.put(str.charAt(i), 1);
		}
	
		Set<Character> keys = table.keySet();
		
		char [] chars = new char[ keys.size() ];
		
		int i = 0;
		for( Character c : keys )
		{
			chars[i++] = c;
		}
		
		int [] num = new int [length];
		
		for( i = 0; i < length; i++ )
		{
			num[i] = 0;
		}

		List<String> list = new ArrayList<String>((int) Math.pow(chars.length, length));
		
		permutations(list, chars, num, 1 );
		
		Collections.sort(list);
		
		StringBuffer out = new StringBuffer();
		
		for( i = 0; i < list.size(); i++ )
		{
			out.append(list.get(i));
			
			if ( i < list.size() - 1 )
			{
				out.append(",");
			}
		}
		
		System.out.println(out.toString());
	}
	
	/**
	 * @param chars
	 * @param length
	 * @param index
	 */
	public static void permutations( List<String> list, char [] chars, int [] num, int index )
	{
		if ( index <= 1 )
		{	
			list.add(print(chars, num));
			
			for( int i = num.length - 1; i >= 0; i-- )
			{
				for( int j = 0; j < chars.length; j++ )
				{
					num[i]++;
					
					if ( num[i] % chars.length == 0 )
					{
						num[i] = 0;
						break;
					}
					
					permutations(list, chars, num, index++);
				}
			}
		}
	}
	
	public static String print(char [] chars, int [] num)
	{
		StringBuffer buf = new StringBuffer();
		
		for( int i = 0; i < num.length; i++ )
		{
			buf.append(chars[num[i]]);
		}
		
		return buf.toString();
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
					String [] pieces = line.split(",");
					
					StringCombinations.permutations(pieces[1], Integer.parseInt(pieces[0]));
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
