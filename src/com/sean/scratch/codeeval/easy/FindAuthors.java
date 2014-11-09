package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FindAuthors
{
	public static void decode(String string)
	{
		if ( string.contains("|"))
		{
			String [] strings = string.split("\\|");
			
			String codeStr = strings[0];
			String keyStr = strings[1];
			
			String [] keys = keyStr.split("\\s+");
			char [] code = codeStr.toCharArray();
			
			StringBuilder sb = new StringBuilder();
			
			for( String key : keys )
			{
				if( key != null && !key.equals("") )
				{
					int k = Integer.parseInt(key);
					
					sb.append(code[k-1]);
				}
			}
			
			System.out.println(sb.toString());
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
				bReader = new BufferedReader(new FileReader(fileName));
				
				String string = null;
				
				while( (string = bReader.readLine()) != null )
				{
					FindAuthors.decode(string);		
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
