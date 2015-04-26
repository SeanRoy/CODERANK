package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FlaviusJosephus
{
	public static String execute(int n, int m)
	{
		boolean [] executed = new boolean[ n ];
		
		StringBuilder sb = new StringBuilder();
		
		int dead = 0;
		int i = -1;
		
		while( dead < n )
		{
			int x = 0;
			while( x < m )
			{
				i = (i + 1) % n;
				
				if ( !executed[i] )
				{
					x++;
				}
			}
			
			executed[i] = true;
			sb.append(i);
			dead++;
			
			if ( dead < n )
			{
				sb.append(" ");
			}
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
						String [] pieces = line.split(",");
						
						System.out.println(FlaviusJosephus.execute(Integer.parseInt(pieces[0]), 
								                        Integer.parseInt(pieces[1])));
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
