package com.sean.scratch.codeeval.hard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Sean
 *
 */
public class Permutations
{

	public static void permutations(List<String> list, String line)
	{
		permutations(list, "", line);
	}
	// math mtah maht mtha
	public static void permutations(List<String> list,String prefix, String line)
	{
		if ( line.length() == 0 )
		{
			list.add(prefix);
		}
		else
		{
			for( int i = 0; i < line.length(); i++ )
			{
				permutations(list, prefix + line.charAt(i), line.substring(0, i) + line.substring(i + 1, line.length() ));
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
					StringBuilder b = new StringBuilder();
					
					List<String> list = new ArrayList<String>();
					
					boolean [] used = new boolean[ line.length() ];
					
					Permutations.permutations(list, line);
					
					Collections.sort(list);
					
					for( int i = 0; i < list.size(); i++ )
					{
						b.append(list.get(i));
						
						if ( i < list.size() - 1 )
						{
							b.append(",");
						}
					}
					
					System.out.println(b.toString());
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
