package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameOfLife
{
	public static void printMatrix(char [][] matrix )
	{
		for( int row = 0; row < matrix.length; row++ )
		{
			for( int col = 0; col < matrix[row].length; col++ )
			{
				System.out.print(matrix[row][col]);
			}
			
			System.out.println();
		}
	}
	
	public static void deadOrAlive(char[][] matrix, int [] deadOrAlive, int row, int col)
	{
		if ( row >= 0 && row < matrix.length &&
			 col >= 0 && col < matrix[0].length )
		{
			if ( matrix[row][col] == '*' )
			{
				deadOrAlive[1]++;
			}
			else
			{
				deadOrAlive[0]++;
			}
		}
	}
	public static int[] getNeighbors(char [][] matrix, int row, int col)
	{
		int [] deadAndAlive = new int[2];
		
		// Northwest
		deadOrAlive(matrix, deadAndAlive, row-1, col-1);
		
		// North
		deadOrAlive(matrix, deadAndAlive, row-1, col );
		
		// Northeast
		deadOrAlive(matrix, deadAndAlive, row-1, col+1);
		
		// East
		deadOrAlive(matrix, deadAndAlive, row, col+1);
		
		// Southeast
		deadOrAlive(matrix, deadAndAlive, row+1, col+1);
		
		// South
		deadOrAlive(matrix, deadAndAlive, row+1, col);
		
		// Southwest
		deadOrAlive(matrix, deadAndAlive, row+1, col-1);
		
		// West
		deadOrAlive(matrix, deadAndAlive, row, col-1);
		
		return deadAndAlive;
	}
	
	/**
	 * 	Any live cell with fewer than two live neighbors dies, as if caused by under-population.
		Any live cell with two or three live neighbors lives on to the next generation.
		Any live cell with more than three live neighbors dies, as if by overcrowding.
		Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
	 * @param dOa
	 * @return
	 */
	public static char applyRules(int [] dOa, char cell)
	{
		char ret = '.';
		
		// Cell is alive
		if ( cell == '*' )
		{
			if ( dOa[1] < 2 )
			{
				ret = '.';
			}
			else if ( dOa[1] < 4 )
			{
				ret = '*';
			}
		}
		// Cell is dead
		else
		{
			if ( dOa[1] == 3 )
			{
				ret = '*';
			}
		}
		
		return ret;
	}
	
	public static char [][] generation(char [][] matrix, int generations)
	{
		for( int i = 0; i < generations; i++ )
		{
			char [][] newUniverse = new char[matrix.length][matrix[0].length];
			
			for( int row = 0; row < matrix.length; row++ )
			{
				for( int col = 0; col < matrix[row].length; col++ )
				{
					int [] dOa = getNeighbors(matrix, row, col);
					
					char cell = matrix[row][col];
					
					newUniverse[row][col] = applyRules(dOa, cell);
				}
			}
			
			matrix = newUniverse;
			
			printMatrix(matrix);
		}
		
		return matrix;
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
				
				List<char[]> universe = new ArrayList<char[]>();
				
				while( ( line = bReader.readLine() ) != null )
				{
					universe.add(line.toCharArray());		
				}
				
				char [][] matrix = new char[universe.size()][universe.get(0).length];
				matrix = universe.toArray(matrix);
				
				GameOfLife.printMatrix(GameOfLife.generation(matrix, 150));
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
