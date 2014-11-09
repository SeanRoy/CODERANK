package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Lowest common ancestor algorithm with BST as array implementation.
 * @author Sean
 *
 */
public class LowestCommonAncestor
{
	/**
	30
    |
  ____
  |   |
  8   52
  |
____
|   |
3  20
    |
   ____
  |   |
  10 29
	 */
	private static final Integer [] tree = new Integer[]
			{30, 8, 52, 3, 20, null, null, null, null, 10, 29 };
	
	public static Integer [] getChildren(int root)
	{
		Integer [] ret = null;
		
		for( int i = 0; i < tree.length; i++ )
		{
			if ( tree[i] == root )
			{
				Integer left = tree[i + i + 1];
				Integer right = tree[i + i + 2];
				
				ret = new Integer[]{ left, right };
				
				break;
			}
		}
		
		return ret;
	}
	public static Integer getParent(int child)
	{
		Integer ret = null;
		
		for( int i = tree.length - 1; i > 0; i-- )
		{
			if ( tree[i] != null && tree[i] == child )
			{
				int index = i / 2;
				
				if ( i % 2 == 0 )
				{
					index--;		
				}
				ret = tree[index];
				
				break;
			}
		}
		
		return ret;
	}
	public static int lca(int a, int b)
	{
		int ret = tree[0];
		
		if ( a != ret && b != ret )
		{
			int aParent = getParent(a);
			int bParent = getParent(b);

			if ( aParent == b )
			{
				ret = b;
			}
			else if ( bParent == a )
			{
				ret = a;
			}
			else if ( aParent > bParent )
			{
				ret = lca(aParent, b);
			}
			else if ( bParent > aParent )
			{
				ret = lca(a, bParent);
			}
			else
			{
				ret = aParent;
			}
		}
		
		return ret;
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
					
					System.out.println(LowestCommonAncestor.lca( Integer.parseInt(ints[0]),
							  					 Integer.parseInt(ints[1])));
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
