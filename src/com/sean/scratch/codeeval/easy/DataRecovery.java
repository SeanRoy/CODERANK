package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataRecovery
{
	public static void decode(String line)
	{
		if ( line.contains(";"))
		{
			String [] strings = line.split(";");
			
			String codeStr = strings[0];
			String keyStr = strings[1];
			
			String [] code = codeStr.split("\\s+");
			String [] keys = keyStr.split("\\s+");
			
			StringBuilder sb = new StringBuilder();
			
			String [] textArray = new String[ code.length ];
			
			StringBuilder builder = new StringBuilder();
			
			for( int i = 0; i < keys.length; i++ )
			{
				String key = keys[i];
				
				if( keys != null && !key.equals("") )
				{
					int k = Integer.parseInt(key);
					
					textArray[k-1] = code[i];
				}
			}
			
			for( int i = 0; i < textArray.length; i++ )
			{
				if ( textArray[i] == null || textArray[i].equals("") )
				{
					builder.append(code[code.length-1]);
				}
				else
				{
					builder.append(textArray[i]);
				}
				
				builder.append(" ");
			}
			
			
			System.out.println(builder.toString());
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
					DataRecovery.decode(string);		
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
