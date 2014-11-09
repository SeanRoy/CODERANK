package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DeltaTime
{
	public static String timeDiff(String time1Str, String time2Str)
	{
		String [] time1Arr = time1Str.split(":");
		String [] time2Arr = time2Str.split(":");
		
		StringBuilder sb = new StringBuilder();
		
		int [] timeArray = new int[ 3 ];
		
		// Figure which string, if either, is larger than the other to
		// make the calculation easier.
		int comp1 = Integer.parseInt(time1Str.replaceAll(":", ""));
		int comp2 = Integer.parseInt(time2Str.replaceAll(":", ""));
		
		if ( comp1 < comp2 )
		{
			String [] tmp = time1Arr;
			time1Arr = time2Arr;
			time2Arr = tmp;
		}
		
		for( int i = 0; i < time1Arr.length; i++ )
		{
			int time1 = Integer.parseInt(time1Arr[i]);
			int time2 = Integer.parseInt(time2Arr[i]);
			
			int value = time1 - time2;
			
			if ( value < 0 )
			{
				if ( i == 0 )
				{
					value = Math.abs(value);
				}
				else
				{
					value += 60;
					
					timeArray[i - 1]--;
				}
			}
			
			timeArray[i] = value;
		}
		
		for( int i = 0; i < timeArray.length; i++ )
		{
			String zero = "";
			
			if ( timeArray[i] < 10 )
			{
				zero = "0";
			}
			
			sb.append(zero + timeArray[i]);
			
			if ( i < timeArray.length - 1 )
			{
				sb.append(":");
			}
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
					if ( ! line.equals("" ) )
					{
						String [] times = line.split("\\s+");
						
						System.out.println(DeltaTime.timeDiff(times[0], times[1]));
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
