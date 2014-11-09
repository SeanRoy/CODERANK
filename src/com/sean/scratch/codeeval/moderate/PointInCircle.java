package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PointInCircle
{
	public static class Point
	{
		double x;
		double y;
		
		public Point(String string)
		{
			String [] pieces = 
					string.substring(string.indexOf("(")+1, string.indexOf(")")).split(",");
			
			x = Double.parseDouble(pieces[0]);
			y = Double.parseDouble(pieces[1]);
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
		
		public double distance(Point p)
		{
			return Math.sqrt( ( Math.pow((p.getX() - x), 2) + Math.pow((p.getY() - y), 2) ) );
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
					if ( ! line.equals("") )
					{
						String [] strings = line.split(";");
						
						Point center = new Point(strings[0]);
						Double radius = Double.parseDouble(strings[1].trim().split("\\s+")[1]);
						Point point = new Point(strings[2]);
						
						Double distance = center.distance(point);
						
						if ( distance > radius )
						{
							System.out.println(false);
						}
						else
						{
							System.out.println(true);
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
