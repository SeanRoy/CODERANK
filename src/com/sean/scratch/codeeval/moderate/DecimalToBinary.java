package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class DecimalToBinary
{
	public static int log(int x, int base)
	{
	    return (int) (Math.log(x) / Math.log(base));
	}
	
	public static String printBinary(String x)
	{
		StringBuilder sb = new StringBuilder();
		
		int number = Integer.parseInt(x);
		
		if ( number < 2 )
		{
			return "" + number;
		}
		
		int places = log(number,2);
		
		int subtract = (int) Math.pow(2, places);
		
		int [] binaryString = new int[ places + 1 ];
		
		binaryString[0] = 1;
		
		printBinary( number - subtract, binaryString );
		
		for( int bs : binaryString )
		{
			sb.append(bs);
		}
		
		return sb.toString();
	}
	
	public static void printBinary(int number, int [] binaryString)
	{
		if ( number > 0 )
		{
			int places = log(number,2);
			
			int subtract = (int) Math.pow(2, places);
			
			binaryString[binaryString.length - places - 1] = 1;
			
			number -= subtract;
			
			printBinary(number, binaryString);
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
					
					if ( ! line.equals("") )
					{
						System.out.println(DecimalToBinary.printBinary(line));
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
