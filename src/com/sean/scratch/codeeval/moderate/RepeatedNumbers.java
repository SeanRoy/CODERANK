package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class RepeatedNumbers
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
					if ( ! line.equals("") )
					{
						String [] strings = line.split(";");
						
						int size = Integer.parseInt(strings[0]);
						
						int [] array = new int[ size ];
						
						strings = strings[1].split(",");
						
						for( int i = 0; i < size; i++ )
						{
							array[i] = Integer.parseInt( strings[i] );
						}
						
						Arrays.sort(array);
						
						int last = array[0];
						
						for( int i = 1; i < array.length; i++ )
						{
							if ( array[i] == last )
							{
								break;
							}
							
							last = array[i];
						}
						
						System.out.println(last);
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
