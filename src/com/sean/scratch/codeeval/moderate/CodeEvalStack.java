package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CodeEvalStack
{
	public static class Node
	{
		private int value;
		private Node parent = null;
		private Node child = null;
		
		public Node(int value)
		{
			this.value = value;
		}
		
		public int getValue()
		{
			return value;
		}
		
		public Node getParent()
		{
			return parent;
		}
		public void setParent(Node parent)
		{
			this.parent = parent;
		}
		
		public Node getChild()
		{
			return child;
		}
		public void setChild(Node child)
		{
			this.child = child;
		}
	}
	public static class Stack
	{
		private Node top = null;
		private int size = 0;
		
		public void push(int i)
		{
			Node n = new Node(i);
			
			push(n);
		}
		
		public void push(Node node)
		{
			if ( top == null )
			{
				top = node;
			}
			else
			{
				node.setChild(top);
				top.setParent(node);
				top = node;
			}
			
			size++;
		}
		
		public Node pop()
		{
			Node n = top;
			
			if ( top != null )
			{
				Node child = top.getChild();
				
				top = child;
				
				if ( child != null )
					child.setParent(null);		
				
				size--;
			}
			
			return n;
		}
		
		public int length()
		{
			return size;
		}
		
		public boolean empty()
		{
			return top == null;
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
				
				while( ( line = bReader.readLine() ) != null )
				{
					StringBuilder builder = new StringBuilder();
					
					String [] ints = line.split("\\s+");
					
					Stack stack = new Stack();
					
					for( String s : ints )
					{
						int i = Integer.parseInt(s);
						
						stack.push(i);
					}
					
					boolean printIt = true;
					
					while ( ! stack.empty() )
					{
						Node n = stack.pop();
						
						if ( printIt )
						{
							builder.append(n.getValue());
							
							if ( ! stack.empty() )
							{
								builder.append(" ");
							}
							
							printIt = false;
						}
						else
						{
							printIt = true;
						}
					}
					
					System.out.println( builder.toString() );
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
