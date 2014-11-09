package com.sean.scratch.codeeval.hard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SpiralPrint
{
	public static String sprialPrint( int rows, int cols, String [] chars )
	{
		StringBuilder sb = new StringBuilder();
		
		int count = rows * cols;
		
		int row = 0;
		int col = 0;
		
		for( ; row < rows; row++ )
		{
			for( ; col < cols; col++ )
			{
				if ( row == rows - 1 )
				{
					// go backwards
				}
				else if ( row > 0 )
				{
					sb.append(chars[cols-1]);
				}
				else
				{
					sb.append(chars[col] + " ");
				}
			}
				
		}
		
		return sb.toString().trim();
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
					
					System.out.println(SpiralPrint.sprialPrint(
							Integer.parseInt(pieces[0]),Integer.parseInt(pieces[1]),pieces[2].split("\\s+")));
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
