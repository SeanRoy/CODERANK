package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CityFlyOver
{
	public static boolean DEBUG = false;
	
	public static class Point
	{
		private double x;
		private double y;
		
		public Point(double x, double y)
		{
			this.x = x;
			this.y = y;
		}

		public double getX()
		{
			return x;
		}

		public void setX(double x)
		{
			this.x = x;
		}

		public double getY()
		{
			return y;
		}

		public void setY(double y)
		{
			this.y = y;
		}
		
		public String toString()
		{
			return "(" + this.x + "," + this.y + ")";
		}
		
	}
	public static class Block
	{
		private Point start;
		private Point end;
		
		public Block(Point start, Point end)
		{
			this.start = start;
			this.end = end;
		}

		public Point getStart()
		{
			return start;
		}

		public void setStart(Point start)
		{
			this.start = start;
		}

		public Point getEnd()
		{
			return end;
		}

		public void setEnd(Point end)
		{
			this.end = end;
		}
		
		public double getStartX()
		{
			return start.getX();
		}
		public double getEndX()
		{
			return end.getX();
		}
		public double getStartY()
		{
			return start.getY();
		}
		public double getEndY()
		{
			return end.getY();
		}
		public boolean contains(Point p)
		{
			
			boolean ret =  ( p.getX() >= start.getX() && p.getX() <= end.getX() &&
					  		 p.getY() >= start.getY() && p.getY() <= end.getY() );
			
			if (DEBUG) System.out.println( "CONTAINS " + this + "    " + p + ":" + ret);
			
			return ret;
		}
		
		public boolean overlaps(Block b)
		{
			return getStartX() <= b.getEndX() &&
				   getEndX() >= b.getStartX() &&
				   getStartY() <= b.getEndY() &&
				   getEndY() >= b.getStartY();
		}
		
		public String toString()
		{
			return start + "  " + end;
		}
	}
	public static int [] toIntArray(String [] arr)
	{
		int [] intArray = new int[ arr.length ];
		
		for(int i = 0; i < arr.length; i++ )
		{
			intArray[i] = Integer.parseInt( arr[i] );
		}
		
		return intArray;
	}
	public static Map<Integer,Block[]> createBlocks(int [] x, int [] y)
	{
		Map<Integer, Block[]> blocks = new HashMap<Integer, Block[]>();
		
		for( int i = 0; i < x.length-1; i++ )
		{
			Block [] blockList = new Block[y.length-1];
			
			for( int j = 0; j < y.length-1; j++ )
			{
				Point start = new Point(x[i], y[j]);
				Point end = new Point(x[i+1], y[j+1]);
				
				blockList[j] = new Block(start,end);
				
			}
			
			blocks.put(i + 1, blockList);
		}
	
		
		return blocks;
	}
	public static Block [] createBlocksNew(int [] x, int [] y )
	{
		Block [] blocks = new Block[ x.length * y.length ];
		
		int index = 0;
		
		for( int i = 0; i < x.length-1; i++ )
		{
			for( int j = 0; j < y.length-1; j++ )
			{
				Point start = new Point(x[i], y[j]);
				Point end = new Point(x[i+1], y[j+1]);
				
				blocks[index] = new Block(start,end);
				
			}
		}
		
		return blocks;
	}
	public static int solve(int [] xs, int [] ys)
	{
		double slope = ( (double) ys[ ys.length - 1 ] - ys[ 0 ] ) / ( (double) xs[ xs.length - 1 ] - xs[ 0 ] );
		
		Map<Integer, Block []> map = createBlocks(xs,ys);
		
		int sum = 0;
		
		double yIntercept = 0;
		
		for( int i = 1; i < xs.length; i++ )
		{
			Block [] arr = map.get(i);
			
			double nextYIntercept = slope * ( double ) xs[i];
			
			Block boundingBlock = new Block( new Point(xs[i-1], yIntercept),
			   								 new Point(xs[i], nextYIntercept));
			
			for( int j = 0; j < arr.length; j++ )
			{
				if ( ! (arr[j].getEndX() == xs[i] && arr[j].getStartY() == nextYIntercept) &&
					 ! (arr[j].getStartX() == xs[i-1] && arr[j].getEndY() == yIntercept ) )
				{
					if ( boundingBlock.overlaps(arr[j]) )
					{
						sum++;
					}
				}
			}
			
			
			yIntercept = nextYIntercept;
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
				
				while( ( line = bReader.readLine() ) != null )
				{
					if ( !line.equals("") )
					{
						String [] strings = line.replaceAll("\\(", "").replaceAll("\\)", "").split("\\s+");
						String [] sstreets = strings[0].split(",");
						String [] saves = strings[1].split(",");
						
						System.out.println( CityFlyOver.solve( CityFlyOver.toIntArray(sstreets), CityFlyOver.toIntArray(saves) ) );
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
