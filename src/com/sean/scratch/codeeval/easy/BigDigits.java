package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BigDigits
{
	public static final String [] FONT = 
	{
		"-**----*--***--***---*---****--**--****--**---**--",
		"*--*--**-----*----*-*--*-*----*-------*-*--*-*--*-",
		"*--*---*---**---**--****-***--***----*---**---***-",
		"*--*---*--*-------*----*----*-*--*--*---*--*----*-",
		"-**---***-****-***-----*-***---**---*----**---**--",
		"--------------------------------------------------"
	};
	
	public static final int FONT_WIDTH = 5;
	public static final int FONT_HEIGHT = 6;
	
	public static void bigDigits(char [] cs)
	{
		
		StringBuilder sb = new StringBuilder();
		
		for( int i = 0; i < FONT_HEIGHT; i++ )
		{		
			for( char c : cs )
			{
				int index = ( c - (int) '0' ) * FONT_WIDTH;
				
				sb.append( FONT[i].substring(index, index + FONT_WIDTH));
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
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
					if ( ! line.equals("" ) )
					{
						BigDigits.bigDigits(line.replaceAll("\\D", "").toCharArray());
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
