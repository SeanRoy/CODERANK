package com.sean.scratch.codeeval.hard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

/**
 * @author Sean
 *
 */
public class PrefixEval
{
	private static final Stack<String> stack = new Stack<String>();
	
	public static int operate(String operator, int x, int y )
	{
		int ret = 0;
		
		switch(operator.charAt(0))
		{
			case '*':
				ret = x * y;
				break;
			case '+':
				ret = x + y;
				break;
			case '/':
				ret = x / y;
				break;
		}
		
		return ret;
	}
	
	public static boolean numberAtTopOfStack()
	{
		try
		{
			Integer.parseInt(stack.peek());
		}
		catch(NumberFormatException e)
		{
			return false;
		}
		
		return true;
	}
	public static String evaluate( String [] line )
	{
		stack.clear();
		
		for( String x : line )
		{
			try
			{
				int operandB = Integer.parseInt(x);
				
				if ( stack.size() > 0 && numberAtTopOfStack() )
				{
					int operandA = Integer.parseInt(stack.pop());
					
					String operator = stack.pop();
					
					int result = operate(operator, operandA, operandB);
					
					stack.push(Integer.toString(result));
				}
				else
				{
					stack.push(x);
				}
			}
			catch(NumberFormatException nfe)
			{
				stack.push(x);
			}
		}
		
		return stack.pop();
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
					System.out.println(PrefixEval.evaluate(line.split("\\s+")));
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
