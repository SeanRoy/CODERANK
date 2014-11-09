package com.sean.scratch;

import java.util.Iterator;
import java.util.Stack;

public class ObjectMatrix {

	private int [][] matrix;
	private boolean [][] visited;
	private Stack<Integer> stack = new Stack<Integer>();
	
	public ObjectMatrix(int [][] matrix)
	{
		this.matrix = matrix;
		
		visited = new boolean[matrix.length][matrix[0].length];
		
		
	}
	

	public int findObjects(int row, int col)
	{
		int ret = matrix[row][col];
		
		if ( !visited[row][col] )
		{
			visited[row][col] = true;

			for( int i = row; i < matrix.length; i++ )
			{
				for( int j = col; j < matrix[row].length; j++ )
				{
					int cell = matrix[i][j];
					
					int num = 0;
					
					if ( cell > 0 )
					{
						stack.push(matrix[i][j]);
						
						// No need to look up because we're starting at the top.
						// Left
						if ( j-1 >= 0 && matrix[i][j-1] > 0 )
						{
							ret += findObjects(i, j-1);
						}
						//Right
						if ( j+1 < matrix[i].length && matrix[i][j+1] > 0 )
						{
							ret += findObjects(i, j+1);
						}
						// Down
						if ( i + 1 < matrix.length - 1 )
						{
							ret += findObjects(i + 1, j);
						}
						
					}
					else
					{
						if ( ! stack.isEmpty() )
						{
							System.out.print("{");
							
							while( ! stack.isEmpty() )
							{
								System.out.print( stack.pop() );
								
								if ( ! stack.isEmpty() )
								{
									System.out.print(",");
								}
							}
							
							System.out.print("}");
						}
					}
				}
			}
		}
		
		return ret;
	}

	
	public void printObjects(int row, int col)
	{
		System.out.print(matrix[row][col]);
		visited[row][col] = true;
		
		
		if ( col-1 >= 0 && matrix[row][col-1] > 0 && !visited[row][col-1])
		{
			printObjects(row, col-1);
		}
		//Right
		if ( col+1 < matrix[row].length && matrix[row][col+1] > 0 && !visited[row][col+1])
		{
			printObjects(row, col+1);
		}
		// Down
		if ( row + 1 < matrix.length - 1 && matrix[row+1][col] > 0 && !visited[row+1][col])
		{
			printObjects(row + 1, col);
		}
	}
	
	public void findObjects()
	{
		for( int i = 0; i < matrix.length; i++ )
		{
			for( int j = 0; j < matrix[i].length; j++ )
			{
				if ( !visited[i][j] && matrix[i][j] > 0 )
				{
					System.out.print("{");
					printObjects(i, j);
					System.out.print("}");
				}
			}
		}
	}

	
	public static void main(String[] args) 
	{
		int [][] matrixOld =
		{
			{0, 1, 0, 0, 3},
			{0, 3, 3, 0, 0},
			{0, 0, 0, 0, 2},
			{0, 0, 1, 0, 2},
			{0, 0, 0, 0, 0}
		};
		
		int [][] matrix =
			{
				{1, 1, 0, 0, 3},
				{0, 3, 3, 0, 0},
				{0, 0, 0, 0, 2},
				{0, 0, 1, 0, 2},
				{0, 0, 0, 0, 0}
			};
		
		
		ObjectMatrix objMatrix = new ObjectMatrix(matrix);
		objMatrix.findObjects();
	}

}
