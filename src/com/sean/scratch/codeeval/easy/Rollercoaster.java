package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Rollercoaster
{
	private static final int a = Character.getNumericValue('a');
	private static final int A = Character.getNumericValue('A');
	private static final int z = Character.getNumericValue('z');
	private static final int Z = Character.getNumericValue('Z');
	
	public static String rollercoaster(String str)
	{
		char [] chars = str.toCharArray();
		
		boolean upper = true;
		
		for( int i = 0; i < chars.length; i++ )
		{
			int val = Character.getNumericValue(chars[i]);
			
			if ( val >= a && val <= z )
			{
				if ( upper)
				{
					chars[i] = Character.toUpperCase(chars[i]);
					
					upper = false;
				}
				else
				{
					chars[i] = Character.toLowerCase(chars[i]);
					
					upper = true;
				}
			
			}
		}
		
		return new String(chars);
	}
	
	public static void main(String [] args)
	{
		if ( args.length >= 1 )
		{
			String fileName = args[0];
			
			BufferedReader bReader = null;
			
			try
			{
				bReader = new BufferedReader(new FileReader(fileName));
				
				String string = null;
				
				StringBuilder builder = new StringBuilder();
				
				while( (string = bReader.readLine()) != null )
				{
					builder.append(Rollercoaster.rollercoaster(string) + "\n" );		
				}
				
				System.out.println(builder.toString());

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
