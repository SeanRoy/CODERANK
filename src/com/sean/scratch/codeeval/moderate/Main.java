package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main
{
	public static int findPath(ArrayList<int []> tree, int row, int col)
	{
		int sum = 0;
		
		for( ; row < tree.size(); row++ )
		{
			int [] numbers = tree.get(row);
			
			sum += numbers[col];
			
			if ( row < tree.size() - 1 )
			{
				int left = findPath(tree, row + 1, col);
				int right = findPath(tree, row + 1, col + 1);
				
				if ( right > left )
				{
					col++;
				}
				else if ( right == left )
				{
					if ( right > left )
					{
						col++;
					}
				}
			}
		}
		
		return sum;
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
				
				ArrayList<int []> tree = new ArrayList<int[]>();
				
				while( ( line = bReader.readLine() ) != null )
				{
					String [] pieces = line.split("\\s+");
					int [] numbers = new int[ pieces.length];
					
					int i = 0;
					for( String s : pieces )
					{
						numbers[i++] = Integer.parseInt(s);
					}
					
					tree.add(numbers);
				}
				
				System.out.println(Main.findPath(tree, 0, 0));
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
