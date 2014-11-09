package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class RoadTrip
{
	public static String getDistances(int [] distances)
	{
		StringBuilder sb = new StringBuilder();
		
		Arrays.sort(distances);
		
		sb.append(distances[0]);
		
		for( int i = 1; i < distances.length; i++ )
		{
			sb.append("," + ( distances[i] - distances[i-1] ) );
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
					String [] pieces = line.split(";");
					
					int [] arr = new int [ pieces.length ];
					
					for( int i = 0; i < pieces.length; i++ )
					{
						String [] blah = pieces[i].split(",");
						
						arr[i] = Integer.parseInt(blah[1].trim());
					}
					
					System.out.println(RoadTrip.getDistances(arr));
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
