package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * 
 * @author Sean
 *
 */
public class ChainInspection
{
	public static String chain(String [] pieces)
	{
		int visited = 0;
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		for( String piece : pieces )
		{
			String [] leftAndRight = piece.split("-");
			
			map.put(leftAndRight[0], leftAndRight[1]);
		}
		
		String value = map.get("BEGIN");
		
		while( value != null && visited <= pieces.length)
		{
			value = map.get(value);
			
			visited++;
		}
		
		return visited == pieces.length ? "GOOD" : "BAD";
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
						System.out.println(ChainInspection.chain(pieces));
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
