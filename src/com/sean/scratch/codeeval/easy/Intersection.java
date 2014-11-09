package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Intersection
{
	public static Set<Integer> toSet(String line)
	{
		Set<Integer> set = new TreeSet<Integer>();
		
		String [] digits = line.split(",");
		
		for( int i = 0; i < digits.length; i++ )
		{
			set.add(Integer.parseInt(digits[i]));
		}
		
		return set;
	}
	public static String intersect(Set s1, Set s2)
	{
		StringBuilder sb = new StringBuilder();
		
		s1.retainAll(s2);
		
		Iterator<Integer> iter = s1.iterator();
		
		while( iter.hasNext() )
		{
			sb.append(iter.next());
			
			if ( iter.hasNext() )
			{
				sb.append(",");
			}
		}
		
		return sb.toString();
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
					String [] pieces = line.split(";");
					
					System.out.println(Intersection.intersect(toSet(pieces[0]), toSet(pieces[1])));
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
