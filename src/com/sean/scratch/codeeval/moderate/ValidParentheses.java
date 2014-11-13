package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

/**
 * https://www.codeeval.com/open_challenges/68/
 * @author Sean
 *
 */
public class ValidParentheses
{
	private static HashMap<Character,Character> map = new HashMap<Character,Character>();
	private static Set<Character> closers;
	private static Collection<Character> openers;
	static
	{
		map.put(']', '[');
		map.put(')', '(');
		map.put('}', '{');
		
		closers = map.keySet();
		openers = map.values();
	}
	private static Stack<Character> stack = new Stack<Character>();
	
	public static String parens(String line)
	{
		stack.clear();
		
		for( Character c : line.toCharArray() )
		{
			if ( openers.contains(c) )
			{
				stack.push(c);
			}
			else if ( closers.contains(c) )
			{
				if ( !stack.empty() && map.get(c) == stack.peek() )
				{
					stack.pop();
				}
				else
				{
					return "False";
				}
			}
		}
		
		return stack.empty() ? "True" : "False";
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
					if ( !line.equals("") )
					{
						System.out.println(ValidParentheses.parens(line));
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
