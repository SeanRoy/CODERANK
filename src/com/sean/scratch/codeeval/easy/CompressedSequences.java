package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CompressedSequences
{
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
					StringBuilder sb = new StringBuilder();
					
					String [] pieces = line.split("\\s+");
					
					String current = pieces[0];
					
					int count = 1;
					
					for( int i = 1; i < pieces.length; i++ )
					{
						if ( pieces[i].equals(current) )
						{
							count++;
						}
						else
						{
							sb.append( count + " " + current );
							
							current = pieces[i];
							
							count = 1;
							
							if ( i < pieces.length )
							{
								sb.append(" ");
							}
						}
					}
					
					sb.append( count + " " + current );
					
					System.out.println(sb.toString());
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
