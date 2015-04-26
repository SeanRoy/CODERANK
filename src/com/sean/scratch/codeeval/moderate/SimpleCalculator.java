package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Stack;

public class SimpleCalculator
{
	public static final HashMap<Character,Integer> precedence = new HashMap<Character,Integer>();
	static
	{
		precedence.put('(', 11);
		precedence.put('^', 10);
		precedence.put('*', 9);
		precedence.put('/', 9);
		precedence.put('+', 8);
		precedence.put('-', 8);
	};
	public static double solve(String line)
	{
		Stack<String> stack = new Stack<String>();
		
		String [] arr = line.split("\\s+");
		for( int i = 0; i < arr.length; i++ )
		{
			String piece = arr[i];
			
			if ( piece.matches("^-?\\d+(\\.\\d+)?$"))
			{
				stack.push(piece);
			}
			else
			{
				double interim = 0;
				double op2 = Double.parseDouble(stack.pop());
				double op1 = Double.parseDouble(stack.pop());
				
				switch(piece)
				{
					case "+":
						interim = op1 + op2;
						break;
					case "-":
						interim = op1 - op2;
						break;
					case "*":
						interim = op1 * op2;
						break;
					case "/":
						interim = op1 / op2;
						break;
					case "^":
						interim = Math.pow(op1, op2);
						break;
				}
				
				stack.push(Double.toString(interim));
			}
		}
		
		return Double.parseDouble(stack.pop());
	}
	
	public static String prepareStack(Stack<Character> cs)
	{
		StringBuilder output = new StringBuilder();
		Stack<Character> operatorStack = new Stack<Character>();
		int operands = 0;
		boolean MINUS = false;
				
		while( ! cs.empty() )
		{
			char c = cs.pop();
			
			switch(c)
			{
				case ')':
					if ( !operatorStack.empty() )
						output.append(operatorStack.pop());
					
					/*
					double x = solve(output.toString());
					if ( MINUS )
					{
						MINUS = false;
						x *= -1;
					}
					return Double.toString(x);
					*/
					
					//return Double.toString(solve(output.toString()));
					return output.toString();
				case '(':
					
					output.append(prepareStack(cs) + " ");
					
					break;
				case '+': case '-': case '^': case '/': case '*':
					
					if ( c == '-' && ( "" + cs.peek() ).matches("[\\d\\(]"))
					{
						MINUS = true;
					}
					else
					{
						while( !operatorStack.empty() && precedence.get(operatorStack.peek()) >= precedence.get(c))
						{
							output.append(operatorStack.pop().toString() + " ");
						}
						
						operatorStack.push(c);
					}
					break;
				default:
					if ( ("" + c).matches("\\d") )
					{
						StringBuilder sb = new StringBuilder();
						
						sb.append(c);
						
						while( !cs.empty() && ( "" + cs.peek() ).matches("[\\d\\.]"))
						{
							sb.append(cs.pop());
						}
						
						double d = Double.parseDouble(sb.toString());
						
						if ( MINUS )
						{
							MINUS = false;
							
							d *= -1;
						}
						
						output.append(d + " ");
						
					}
					
					break;
			}
		}
		
		if ( !operatorStack.empty() )
			output.append(operatorStack.pop());
		
		return output.toString();
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
					if ( ! line.equals( "" ) )
					{
						Stack<Character> stack = new Stack<Character>();
						char [] chars = line.toCharArray();
						for( int i = chars.length - 1; i >= 0; i-- )
						{
							stack.push(chars[i]);
						}
						
						double d = SimpleCalculator.solve(SimpleCalculator.prepareStack(stack));
						
						if ( d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY )
						{
							System.out.println("Infinity");
						}
						else if ( d == (int) d)
						{
							System.out.println((int) d);
						}
						else
						{
							System.out.println(new DecimalFormat("#.#####").format(d) );
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
