package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BitPositions
{
	public static boolean same(int num, int x, int y)
	{
		String binary = Integer.toBinaryString(num);
		
		return binary.charAt(binary.length() - x) == binary.charAt(binary.length() - y);
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
					String [] pieces = line.split(",");
					
					int num = Integer.parseInt(pieces[0]);
					int x = Integer.parseInt(pieces[1]);
					int y = Integer.parseInt(pieces[2]);
					
					System.out.println(BitPositions.same(num, x, y));
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
