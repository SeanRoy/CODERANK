package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MatrixRotation
{
	/**
	 * Where width = Math.sqrt(chars.length), rotates a conceptual width x width
	 * matrix 90 degrees and returns the one dimensional array representation.
	 * 
	 * @param chars
	 * @return
	 */
	public static String [] rotate(String [] chars)
	{
		String [] ret = new String[ chars.length ];
		
		int width = (int) Math.sqrt(chars.length);
		int row = -1;

		for( int i = 0 ; i < chars.length; i++ )
		{
			if ( i % width == 0 )
			{
				row++;
			}
			
			int place = (i % width) * width + (width - 1) - row;
			
			
			ret[place] = chars[i];
		}
		
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
					if ( ! line.equals( "" ) )
					{
						String [] chars = line.split("\\s+");
						
						chars = MatrixRotation.rotate(chars);
						
						StringBuilder sb = new StringBuilder();
						
						for( int i = 0; i < chars.length; i++ )
						{
							sb.append( chars[i] );
							
							if ( i < chars.length - 1 )
							{
								sb.append(" ");
							}
						}
						
						System.out.println(sb.toString());
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
