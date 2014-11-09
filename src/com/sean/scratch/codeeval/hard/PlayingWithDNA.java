package com.sean.scratch.codeeval.hard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayingWithDNA
{
	public static String findMatches(String str, int maxDiffs, String sequence )
	{
		StringBuilder sb = new StringBuilder();
		
		List<String> [] list = new ArrayList[maxDiffs+1];
		
		for( int i = 0; i < sequence.length() - str.length(); i++ )
		{
			int mismatches = 0;
			
			StringBuilder little = new StringBuilder();
			
			for( int j = 0; j < str.length(); j++ )
			{
				if ( mismatches <= maxDiffs )
				{
					if ( str.charAt(j) != sequence.charAt(i + j) )
					{
						 mismatches++;
					}
					
					little.append(sequence.charAt(i + j));
				}
				else
				{
					break;
				}
			}
			
			if ( mismatches <= maxDiffs )
			{
				if ( list[mismatches] == null )
				{
					list[mismatches] = new ArrayList<String>();
				}
				list[mismatches].add(little.toString());
			}
		}
		
		for( int i = 0; i <= maxDiffs; i++ )
		{
			List<String> subList = list[i];
			
			if ( subList != null )
			{
				Collections.sort(subList);
				
				for( int j = 0; j < subList.size(); j++ )
				{
					sb.append(subList.get(j));
					
					sb.append(" ");
				}
			}
		}
		
		String ret = sb.toString().trim();
		
		if ( ret.length() == 0 )
		{
			return "No match";
		}
		else
		{
			return ret;
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
				
				int i = 0;
				while( ( line = bReader.readLine() ) != null )
				{
					if ( ! line.equals("") )
					{
						String [] strings = line.trim().split("\\s+");
						
						String string = strings[0];
						int maxDiff = Integer.parseInt(strings[1]);
						String sequence = strings[2];
						System.out.println(PlayingWithDNA.findMatches(string, maxDiff, sequence));
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
