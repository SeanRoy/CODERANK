package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CalculateDistance
{
	public static int getPoints(String line)
	{
		line = line.replaceAll("\\s+", "");
		String [] pieces = line.split("\\)\\(");
		String first = pieces[0].replaceAll("\\(", "");
		String second = pieces[1].replaceAll("\\)", "");
		
		String [] firstXY = first.split(",");
		String [] secondXY = second.split(",");
		
		int x1 = Integer.parseInt(firstXY[0]);
		int y1 = Integer.parseInt(firstXY[1]);
		
		int x2 = Integer.parseInt(secondXY[0]);
		int y2 = Integer.parseInt(secondXY[1]);
		
		double d = Math.sqrt( ( Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2) ) );
		
		return (int) d;
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
					System.out.println(CalculateDistance.getPoints(line));
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
