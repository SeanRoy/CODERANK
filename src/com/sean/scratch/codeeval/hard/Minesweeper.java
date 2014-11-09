package com.sean.scratch.codeeval.hard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Minesweeper
{
	// This is the lazy version
	// TODO: don't create a field matrix first, get this information straight from line.
	public static String mineSweeper(int n, int m, String line)
	{
		char [][] field = new char[n][m];
		StringBuilder sb = new StringBuilder();
		
		int index = 0;
		
		for( int i = 0; i < n; i++ )
		{
			for( int j = 0; j < m; j++ )
			{
				field[i][j] = line.charAt(index++);
			}
		}
		
		for( int i = 0; i < n; i++ )
		{
			for( int j = 0; j < m; j++ )
			{
				int mines = 0;
				
				if ( field[i][j] == '*' )
				{
					sb.append("*");
				}
				else
				{
					if ( j - 1 >= 0 && field[i][j-1] == '*' )
					{
						mines++;
					}
					
					if ( i - 1 >= 0 && j - 1 >= 0 && field[i-1][j-1] == '*' )
					{
						mines++;
					}
					
					if ( i - 1 >= 0 && field[i-1][j] == '*' )
					{
						mines++;
					}
					
					if ( i - 1 >= 0 && j + 1 < m && field[i-1][j+1] == '*' )
					{
						mines++;
					}
					
					if ( j + 1 < m && field[i][j+1] == '*' )
					{
						mines++;
					}
					
					if ( i + 1 < n && j + 1 < m && field[i+1][j+1] == '*' )
					{
						mines++;
					}
					
					if ( i + 1 < n && field[i+1][j] == '*' )
					{
						mines++;
					}
					
					if ( i + 1 < n && j - 1 >= 0 && field[i+1][j-1] == '*' )
					{
						mines++;
					}
					
					sb.append(mines);
				}
			}
		}
		
		return sb.toString();
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
						String [] strings = line.split(";");
						String [] nums = strings[0].split(",");
						int n = Integer.parseInt(nums[0]);
						int m = Integer.parseInt(nums[1]);
						
						System.out.println(Minesweeper.mineSweeper(n, m, strings[1]));
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
