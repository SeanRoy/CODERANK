package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EmailValidator
{
	public static boolean validate(String str)
	{
		boolean ret = str.matches(
			"^(\\w|\\d|!|#|$|%|&|'|\\*|\\+|-|/|=|\\?|^|_|`|\\{\\|\\}|~|" +
			"\\.\")+@(\\w|\\d|\\.|(\\D))+");
		
		System.out.println( str + " " + ret);
		return ret;
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
					if ( !line.equals(""))
					{
						System.out.println(EmailValidator.validate(line));
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
