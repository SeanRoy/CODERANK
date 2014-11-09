package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RomanNumerals
{
	public static void convert(int value, StringBuilder sb)
	{
	//  IV (4), IX (9), XL (40), and XC (90). 
		
		if ( value < 4 )
		{
			while( value-- > 0 )
			{
				sb.append("I");
			}
		}
		else if ( value == 4 )
		{
			sb.append("IV");
		}
		else if ( value == 5 )
		{
			sb.append("V");
		}
		else if ( value > 5 && value < 9 )
		{
			sb.append("V");
			
			value -= 5;
			
			while( value-- > 0 )
			{
				sb.append("I");
			}
		}
		else if ( value == 9 )
		{
			sb.append("IX");
		}
		else if ( value < 50 )
		{
			if ( value == 40 )
			{
				sb.append("XL");
			}
			else
			{
				sb.append( "X" );
				
				value -= 10;
				
				convert(value, sb);
			}
		}
		else if ( value == 50 )
		{
			sb.append("L");
		}
		else if ( value < 90 )
		{
			sb.append("L");
			
			value -= 50;
			
			convert(value, sb);
		}
		else if ( value == 90 )
		{
			sb.append("XC");
		}
		else if ( value < 500 )
		{
			if ( value == 400 )
			{
				sb.append( "CD" );
			}
			else
			{
				sb.append( "C");
				
				value -= 100;
				
				convert(value, sb);
			}
		}
		else if ( value == 500 )
		{
			sb.append("D");
		}
		else if ( value > 500 )
		{
			if ( value < 900 )
			{
				sb.append("D");
				
				value -= 500;
				
				convert(value, sb);
			}
			else if ( value == 900 )
			{
				sb.append("CM");
			}
			else
			{
				sb.append("M");
				
				value -= 1000;
				
				convert(value, sb);
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
					StringBuilder sb = new StringBuilder();
					
					char [] chars = line.toCharArray();
					
					for( int i = 0; i < chars.length; i++ )
					{
						int decimalMultiplier = (int) Math.pow(10, chars.length - 1 - i);
						
						int digit = ( ( int ) chars[i] ) - ( ( int ) '0' );
						
						int value = digit * decimalMultiplier;
						
						convert(value, sb);
					}
					
					System.out.println(sb.toString());
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
