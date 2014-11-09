package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RemoveCharacters
{
	public static String scrub(String str, String remove)
	{
		StringBuilder sb = new StringBuilder();
		/*
		for( int i = 0; i < remove.length(); i++ )
		{
			str = str.replace(remove.charAt(i), (char) 0 );
		}
		
		return str.trim();
		*/
		for( int i = 0; i < str.length(); i++ )
		{
			boolean add = true;
			for( int j = 0; j < remove.length(); j++ )
			{
				if ( str.charAt(i) == remove.charAt(j) )
				{
					add = false;
					break;
				}
			}
			
			if ( add )
			{
				sb.append(str.charAt(i));
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
					String [] pieces = line.split(",");
					
					if ( pieces.length > 0 )
					{
						if ( pieces.length < 2 )
						{
							System.out.println(pieces[0]);
						}
						else
						{
							System.out.println(RemoveCharacters.scrub(pieces[0].trim(), pieces[1].trim()));
						}
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
