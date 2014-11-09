package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DSLQuery
{
	public static int matrix [][] = new int[256][256];
	
	public static void setRow(int row, int value)
	{
		for( int i = 0; i < 256; i++ )
		{
			matrix[row][i] = value;
		}
	}
	public static void setCol(int col, int value)
	{
		for( int i = 0; i < 256; i++ )
		{
			matrix[i][col] = value;
		}
	}
	public static void queryRow( int row )
	{
		int sum = 0;
		
		for( int i = 0; i < 256; i++ )
		{
			sum += matrix[row][i];
		}
		
		System.out.println( sum );
	}
	public static void queryCol(int col)
	{
		int sum = 0;
		
		for( int i = 0; i < 256; i++ )
		{
			sum += matrix[i][col];
		}
		
		System.out.println(sum);
	}
	public static void parseCommand( String line )
	{
		String [] ops = line.split("\\s+");
		
		if ( ops[0].equals("SetRow") )
		{
			int row = Integer.parseInt(ops[1]);
			int value = Integer.parseInt(ops[2]);
			
			setRow(row, value);
		}
		else if ( ops[0].equals("SetCol") )
		{
			int col = Integer.parseInt(ops[1]);
			int value = Integer.parseInt(ops[2]);
			
			setCol(col, value);
		}
		if ( ops[0].equals("QueryRow") )
		{
			int row = Integer.parseInt(ops[1]);
			
			queryRow(row);
		}
		if ( ops[0].equals("QueryCol") )
		{
			int col = Integer.parseInt(ops[1]);
			
			queryCol(col);
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
					DSLQuery.parseCommand(line);
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
