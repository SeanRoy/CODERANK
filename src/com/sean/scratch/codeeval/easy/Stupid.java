package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Stupid
{
	public static Hashtable<Integer,String> table = new Hashtable<Integer,String>();
	
	/**
	 * If they're from 0 to 2 the child should be with parents print : 'Still in Mama's arms' 
If they're 3 or 4 and should be in preschool print : 'Preschool Maniac' 
If they're from 5 to 11 and should be in elementary school print : 'Elementary school' 
From 12 to 14: 'Middle school' 
From 15 to 18: 'High school' 
From 19 to 22: 'College'
From 23 to 65: 'Working for the man' 
From 66 to 100: 'The Golden Years' 
	 */
	static
	{
		for( int i = 0; i <= 100; i++ )
		{
			if ( i < 3 )
			{
				table.put(i, "Still in Mama's arms");
			}
			else if ( i < 5 )
			{
				table.put(i,  "Preschool Maniac");
			}
			else if ( i < 12 )
			{
				table.put(i,  "Elementary school");
			}
			else if ( i < 15 )
			{
				table.put(i, "Middle school");
			}
			else if ( i < 19 )
			{
				table.put(i,  "High school" );
			}
			else if ( i < 23 )
			{
				table.put(i, "College");
			}
			else if ( i < 66 )
			{
				table.put(i, "Working for the man");
			}
			else
			{
				table.put(i, "The Golden Years");
			}
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
					int age = Integer.parseInt(line);
					
					if ( age < 0 || age > 100 )
					{
						System.out.println("This program is for humans");
					}
					else
					{
						System.out.println(table.get(age));
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
