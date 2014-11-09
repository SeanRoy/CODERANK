package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RacingChars
{
	public static int findPath( String line )
	{
		if ( line.contains( "C" ) )
		{
			return line.indexOf("C");
		}

		return line.indexOf("_");
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
				
				int gate = -1;
				
				while( ( line = bReader.readLine() ) != null )
				{
					int newGate = RacingChars.findPath(line);
					
					char turn = '|';
					
					if ( gate > -1 && newGate < gate )
					{
						turn = '/';
					}
					else if ( gate > -1 && newGate > gate )
					{
						turn = '\\';
					}
					
					gate = newGate;
					
					String left = line.substring(0, newGate);
					String right = turn + line.substring(newGate+1, line.length());
					
					System.out.println( left + right );
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
