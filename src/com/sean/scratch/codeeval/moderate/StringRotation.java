package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StringRotation
{
	public static String shiftRight(String s)
	{
		return s.substring(1) + s.charAt(0);
	}
	
	public static String rotation(String aStr, String bStr)
	{		
		String a = aStr;
		
		for( int i = 0; i < aStr.length(); i++ )
		{
			if ( a.equals(bStr) )
			{
				return "True";
			}
			
			a = shiftRight(a);
		}
		
		return "False";
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
					if ( ! line.equals( "" ) )
					{
						String [] ps = line.split(",");
						
						System.out.println(StringRotation.rotation(ps[0], ps[1]));
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
