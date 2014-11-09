package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class ReverseWords
{

	private static Stack<String> stack = new Stack<String>();
	
	public static String reverse(String line)
	{
		StringBuilder builder = new StringBuilder();
		
		String [] word = line.split("\\s+");
		
		stack.clear();
		
		for( String w : word )
		{
			stack.push(w);
		}
		
		while( ! stack.empty() )
		{
			builder.append(stack.pop());
			
			if ( ! stack.empty() )
			{
				builder.append(" ");
			}
		}
		
		return builder.toString();
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
						System.out.println(ReverseWords.reverse(line));
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
