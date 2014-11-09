package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Divisibility
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
					String [] parms = line.split("\\s+");
					
					int A = Integer.parseInt(parms[0]);
					int B = Integer.parseInt(parms[1]);
					int N = Integer.parseInt(parms[2]);
					
					StringBuilder builder = new StringBuilder();
					
					for( int i = 1, Ai = A, Bi = B; i <= N; i++ )
					{
						Ai--;
						Bi--;
						
						if ( Ai == 0 || Bi == 0 )
						{
							if ( Ai == 0 )
							{
								Ai = A;
								
								builder.append("F");
							}
							
							if ( Bi == 0 )
							{
								Bi = B;
								
								builder.append("B");
							}
						}
						else
						{
							builder.append(i);
						}
						
						if ( i < N )
						{
							builder.append(" ");
						}
					}
					
					System.out.println(builder.toString());
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
