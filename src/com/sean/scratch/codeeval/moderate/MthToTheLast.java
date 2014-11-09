package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MthToTheLast
{
	public static void getMthFromLast(String [] chars, int m)
	{
		if ( m > 0 && m <= chars.length )
		{
			System.out.println(chars[chars.length - m]);
		}
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
					String [] tokens = line.split("\\s++");
					
					int m = Integer.parseInt(tokens[tokens.length - 1]);
					
					String [] chars = Arrays.copyOfRange(tokens, 0, tokens.length - 1);
					
					MthToTheLast.getMthFromLast(chars, m);
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
