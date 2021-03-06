package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;


public class PostfixCalculatorNOT_SOLVED
{
	public static final HashMap<Character,Integer> precedence = new HashMap<Character,Integer>();
	static
	{
		precedence.put('^', 10);
		precedence.put('*', 9);
		precedence.put('/', 9);
		precedence.put('+', 8);
		precedence.put('-', 8);
	};
	public static class Node
	{
		private static int total = 0;
		private Node parent;
		private Node right;
		private Node left;
		private String value = null;
		private int id = 0;
		
		public Node()
		{
			id = ++total;
		}
		public Node getRight()
		{
			return getRight(true);
		}
		public Node getRight(boolean create)
		{
			if ( create && right == null )
			{
				setRight(new Node());
			}
			return right;
		}
		public void setRight(Node right)
		{
			if ( right != null )
				right.setParent(this);
			this.right = right;
		}
		public Node getLeft()
		{
			return getLeft(true);
		}
		public Node getLeft(boolean create)
		{
			if ( create && left == null )
			{
				setLeft(new Node());
			}
			
			return left;
		}
		public boolean leftNull()
		{
			return left == null;
		}
		public boolean rightNull()
		{
			return right == null;
		}
		public void setLeft(Node left)
		{
			if ( left != null )
				left.setParent(this);
			this.left = left;
		}
		public String getValue()
		{
			return value;
		}
		public void setValue(String value)
		{
			this.value = value;
		}	
		public void setParent(Node p)
		{
			this.parent = p;
		}
		public Node getParent()
		{
			return getParent(true);
		}
		public Node getParent(boolean create)
		{
			if (create && parent == null )
			{
				Node n = new Node();
				n.setLeft(this);
				parent = n;
			}
			return parent;
		}
		
		public String toString()
		{
			StringBuilder b = new StringBuilder();
			
			if ( left != null )
				b.append(left.toString() + " ");
			if ( right != null )
				b.append(right.toString() + " ");
			if ( value != null )
				b.append(value + " ");
			
			return b.toString();
		}
		
		public boolean equals(Object obj)
		{
			if ( obj == null || ! (obj instanceof Node) )
			{
				return false;
			}
			return ( ( Node) obj ).id == this.id;
		}
		
		public double calculate()
		{
			return calculate(this);
		}
		private static double calculate( Node n )
		{	
			double result = 0;
			
			if ( n != null )
			{
				if ( n.getValue() != null )
				{
					switch( n.getValue() )
					{
						case "+":
							result = calculate(n.getLeft(false)) + calculate(n.getRight(false));
							break;
						case "-":
							result = calculate(n.getLeft(false)) - calculate(n.getRight(false));
							break;
						case "*":
							result = calculate(n.getLeft(false)) * calculate(n.getRight(false));
							break;
						case "/":
							result = calculate(n.getLeft(false)) / calculate(n.getRight(false));
							break;	
						case "^":
							result = Math.pow(calculate(n.getLeft(false)), calculate(n.getRight(false)));
							break;
						default:
							if ( n.getValue() != null )
								result = Double.parseDouble(n.getValue());
							break;
					}
				}
				else if ( n.getLeft(false) != null )
				{
					result = calculate(n.getLeft(false));
				}
				else if ( n.getRight(false) != null )
				{
					result = calculate(n.getRight(false));
				}
			}

			return result;
		}
	}
	
	
	
	public static Node load(String line)
	{
		Node current = new Node();
		
		char [] cs = line.toCharArray();
		
		boolean MINUS = false;
		
		for ( int i = 0; i < cs.length; i++ )
		{
			char c = cs[i];
			
			switch(c)
			{
				case '(':					
					if (current.leftNull())
					{
						current = current.getLeft();
					}
					else
					{
						current = current.getRight();
					}
				
					break;
				case ')':
					current = current.getParent();
					
					while( current.getValue() != null )
					{
						current = current.getParent();
					}
					
					current.setValue("" + current.calculate());
					current.setLeft(null);
					current.setRight(null);
					current = current.getParent();
					break;
				case '+': case '-': case '/': case '*': case '^':
					if ( c == '-' && ("" + c).matches("\\d+") )
					{
						MINUS = true;
					}
					else 
					{
						if ( current.getValue() != null )
						{
							Node n = new Node();
							
							if ( precedence.get(c) <= precedence.get(current.getValue().toCharArray()[0]))
							{	
								n.setLeft(current);
							}
							else
							{
								n.setLeft(current.getRight());
								current.setRight(n);
							}
							
							current = n;
						}
						
						current.setValue("" + c);
						
						current = current.getRight();
					}

					break;
				default:
					
					if ( ("" + c).matches("[\\d+|\\.]") )
					{
						StringBuilder s = new StringBuilder();
						
						if ( MINUS )
						{
							s.append("-");
							MINUS = false;
						}
						
						while( ("" + c).matches("[\\d+|\\.]") )
						{
							s.append(c);
							
							if ( i + 1 < cs.length && ("" + cs[i + 1]).matches("[\\d+|\\.]") )
							{
								c = cs[++i];
							}
							else
							{
								break;
							}
						}
						
						
						current.setValue(s.toString());
						current = current.getParent();
					}
					
					break;
			}
		}
		
		return current;
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
				//int i = 0;
				while( ( line = bReader.readLine() ) != null )
				{
					if ( ! line.equals( "" ) )
					{
						Node root = PostfixCalculatorNOT_SOLVED.load( line );
						
						while( root.getParent(false) != null )
						{
							root = root.getParent(false);
						}
						
						//System.out.print(++i + " ");
						//System.out.println(root);
						
						double d = root.calculate();
						
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
