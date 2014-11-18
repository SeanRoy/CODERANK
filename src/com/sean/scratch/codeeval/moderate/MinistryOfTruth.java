package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MinistryOfTruth
{
	public static String bigBrother(String first, String second)
	{
		StringBuilder sb = new StringBuilder();
		
		String [] firstWords = first.split("\\s+");
		String [] secondWords = second.split("\\s+");
		
		int y = 0;
		
		for( int x = 0; x < firstWords.length; x++ )
		{
			String word = firstWords[x];
			String transform =  word.replaceAll(".", "_");
			
			if ( y < secondWords.length )
			{
				String check = secondWords[y];
				
				int index = word.indexOf(check);
				
				if ( index >= 0 )
				{
					char [] chars = transform.toCharArray();
					
					for( char c : check.toCharArray() )
					{
						chars[index++] = c;
					}
					
					transform = new String( chars );
					
					y++;
				}
			}
			
			sb.append(transform);
			
			if ( x < firstWords.length - 1 )
			{
				sb.append(" ");
			}
		}
		
		if ( y < secondWords.length)
		{
			return "I cannot fix history";
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
					if ( ! line.equals( "" ) )
					{
						String [] pieces = line.split(";");
						
						if ( pieces.length == 2 )
						{
							System.out.println(MinistryOfTruth.bigBrother(pieces[0], pieces[1]));
						}
						else
						{
							System.out.println(MinistryOfTruth.bigBrother(pieces[0], ""));
						}
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
