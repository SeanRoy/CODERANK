package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class Sexagesimal
{
	public static final int HOURS = 0;
	public static final int MINUTES = 1;
	public static final int SECONDS = 2;
	public static final BigDecimal SIXTY = new BigDecimal(60);
	
	public static String convert(String line)
	{
		StringBuffer ret = new StringBuffer();
		
		return convert(line, ret, HOURS);
	}
	public static String convert(String line, StringBuffer ret, int WHICH)
	{
		if ( WHICH <= SECONDS )
		{
			String [] pieces = line.split("\\.");
			
			if ( WHICH != HOURS && Integer.parseInt(pieces[0]) < 10 )
			{
				pieces[0] = "0" + pieces[0];
			}
			ret.append(pieces[0]);
			
			switch( WHICH )
			{
				case HOURS:
					ret.append(".");
					break;
				case MINUTES:
					ret.append("'");
					break;
				case SECONDS:
					ret.append("\"");
					break;
			}
			
			if ( pieces.length == 1 )
			{
				convert("00", ret, ++WHICH);
			}
			else
			{
				BigDecimal section = ( new BigDecimal("." + pieces[1]) ).multiply(SIXTY);

				convert(section.toString(), ret, ++WHICH);
			}
		}
		
		return ret.toString();
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
					System.out.println(convert(line));
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
