package com.sean.scratch.codeeval.hard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClosestPairs
{
	public static class Pair
	{
		private Point A;
		private Point B;
		
		public Pair()
		{
			
		}
		public Pair( Point A, Point B)
		{
			this.A = A;
			this.B = B;
		}
		
		public Point getA()
		{
			return A;
		}
		public void setA(Point a)
		{
			A = a;
		}
		public Point getB()
		{
			return B;
		}
		public void setB(Point b)
		{
			B = b;
		}
		public BigDecimal distance()
		{
			return Point.distance(A, B);
		}
		public String toString()
		{
			return A + " , " + B + "  " + distance();
		}
		
		public boolean empty()
		{
			return A == null || B == null;
		}
	}
	public static class Point
	{
		private double x, y;
		
		public Point(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
		
		public double getX()
		{
			return x;
		}
		
		public double getY()
		{
			return y;
		}
		
		public String toString()
		{
			return "X: " + x + " Y: " + y;
		}
		
		public static BigDecimal distance(Point a, Point b)
		{
			if ( b == null || a == null )
			{
				int x = 0;
			}
			double A2 = Math.pow(b.x - a.x, 2);
			double B2 = Math.pow(b.y - a.y, 2);
			
			return new BigDecimal(Math.sqrt(A2 + B2)).setScale(4, BigDecimal.ROUND_HALF_UP);
		}
	}
	
	
	public static Pair bruteForce(List<Point> points)
	{
		BigDecimal distance = null;
		Pair pair = new Pair();
		
		for( int i = 0; i < points.size() - 1; i++ )
		{
			for( int j = i + 1; j < points.size(); j++ )
			{
				BigDecimal result = Point.distance(points.get(i), points.get(j));
				
				if ( distance == null || result.compareTo(distance) < 0 )
				{
					distance = result;
					pair.setA(points.get(i));
					pair.setB(points.get(j));
				}
			}
		}
		
		return pair;
	}
	
	public static class XComparator implements Comparator
	{
		public int compare(Object arg0, Object arg1)
		{
			if ( ( ( Point ) arg0 ).getX() < ( ( Point ) arg1 ).getX() )
			{
				return -1;
			}
			else if ( ( ( Point ) arg0 ).getX() > ( ( Point ) arg1 ).getX() )
			{
				return 1;
			}
			
			return 0;
		}
	}
	
	public static class YComparator implements Comparator
	{
		public int compare(Object arg0, Object arg1)
		{
			if ( ( ( Point ) arg0 ).getY() < ( ( Point ) arg1 ).getY() )
			{
				return -1;
			}
			else if ( ( ( Point ) arg0 ).getY() > ( ( Point ) arg1 ).getY() )
			{
				return 1;
			}
			
			return 0;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static Pair findClosestPoints(List<Point> points)
	{
		List<Point> xSorted = new ArrayList<Point>(points);
		List<Point> ySorted = new ArrayList<Point>(points);
		
		Collections.sort(xSorted, new XComparator());
		Collections.sort(ySorted, new YComparator());
		
		//Pair pair = bruteForce(points);
		Pair pair = findClosestPoints(xSorted, ySorted);
		
		return pair;
	}
	
	@SuppressWarnings("unchecked")
	public static Pair findClosestPoints(List<Point> xSorted, List<Point> ySorted)
	{
		Pair pair = null;
		BigDecimal minDistance = BigDecimal.ZERO;
		
		if ( xSorted.size() <= 3 )
		{
			pair = bruteForce(xSorted);
		}
		else
		{
			int midIndex = xSorted.size() / 2;
			
			List<Point> xS1 = xSorted.subList(0, midIndex);
			List<Point> xS2 = xSorted.subList(midIndex + 1, xSorted.size());
			
			List<Point> yS1 = new ArrayList<Point>(xS1);
			List<Point> yS2 = new ArrayList<Point>(xS2);
			
			Collections.sort(yS1, new YComparator());
			Collections.sort(yS2, new YComparator());
			
			Pair leftPair = findClosestPoints(xS1, yS1);
			Pair rightPair = findClosestPoints(xS2, yS2);
			
			if ( leftPair.empty() )
			{
				pair = rightPair;
			}
			else
			{
				pair = leftPair;
			}
			
			minDistance = pair.distance();
			
			if ( !rightPair.empty() && rightPair.distance().compareTo(minDistance) < 0 )
			{
				pair = rightPair;
				minDistance = pair.distance();
			}
			
			List<Point> spanPoints = new ArrayList<Point>();
			
			// get the S1 S2 pairs crossing the midpoint
			// The first point in the x sorted right hand list (S2) will be closest to center.
			Point center = xS2.get(0);
			
			// Get all points within minDistance of center. This is the first step in building
			// sparsity box, in this case, the sides.
			for( Point point : ySorted )
			{
				if ( Math.abs(center.x - point.x) < minDistance.doubleValue() )
				{
					spanPoints.add(point);
				}
			}
			
			for( int i = 0; i < spanPoints.size() - 1; i++ )
			{
				for( int j = i + 1; j < spanPoints.size(); j++ )
				{
					double ydist = Math.abs(spanPoints.get(i).getY() - spanPoints.get(j).getY());
					
					// The top and bottom of the box
					if ( ydist < minDistance.doubleValue() )
					{
						BigDecimal dist = Point.distance(spanPoints.get(i),
								                         spanPoints.get(j));
						
						if ( dist.compareTo(minDistance) < 0 )
						{
							minDistance = dist;
							pair.setA(spanPoints.get(i));
							pair.setB(spanPoints.get(j));
						}
					}
				}
			}
		}
		
		return pair;
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
				
				String line = bReader.readLine();
				
				int testCases = Integer.parseInt(line);
				
				List<Point> points = new ArrayList<Point>(testCases);

				for( int i = 0; i < testCases; i++ )
				{
					line = bReader.readLine();
					
					String [] pointStrs = line.split("\\s++");
					
					points.add(new Point(new Double(pointStrs[0]),
							             new Double(pointStrs[1])));
				}
				
				if ( points.size() < 2 )
				{
					System.out.println("INFINITY");
				}
				else
				{
					Pair pair = ClosestPairs.findClosestPoints(points);
					
					BigDecimal distance = pair.distance();
					
					if ( distance.compareTo(new BigDecimal(10000) ) < 0 )
					{
						System.out.println(pair.distance());
					}
					else
					{
						System.out.println("INFINITY");
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
