package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ComparePoints
{
	
	public static String direction(int origX, int origY, int destX, int destY)
	{
		String cardinal = getNorthOrSouth(destY - origY) + getEastOrWest(destX - origX);
		
		if ( cardinal.isEmpty() )
		{
			return "here";
		}
		
		return cardinal;
	}
	
	public static String getNorthOrSouth(int val)
	{
		if ( val < 0 )
			return "S";
		else if ( val > 0 )
			return "N";
		else
			return "";
	}
	
	public static String getEastOrWest(int val)
	{
		if ( val < 0 )
			return "W";
		else if ( val > 0 )
			return "E";
		else
			return "";
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
						String [] params = line.split("\\s+");
						
						int origX = Integer.parseInt(params[0]);
						int origY = Integer.parseInt(params[1]);
						int destX = Integer.parseInt(params[2]);
						int destY = Integer.parseInt(params[3]);
						
						System.out.println(direction(origX, origY, destX, destY));
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
