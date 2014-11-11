package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class WorkExperience
{
	public static class Exp implements Comparable<Exp>
	{
		private Date start;
		private Date end;
		
		public Exp(Date start, Date end)
		{
			this.start = start;
			this.end = end;
		}

		public Date getStart()
		{
			return start;
		}
		public long getStartTime()
		{
			return start.getTime();
		}

		public void setStart(Date start)
		{
			this.start = start;
		}

		public Date getEnd()
		{
			return end;
		}
		public long getEndTime()
		{
			return end.getTime();
		}

		public void setEnd(Date end)
		{
			this.end = end;
		}

		@Override
		public int compareTo(Exp arg0)
		{
			if ( this.start.getTime() < arg0.start.getTime() )
			{
				return -1;
			}
			else if ( this.start.getTime() > arg0.start.getTime() )
			{
				return 1;
			}
			return 0;
		}
		
		public String toString()
		{
			return start + " - " + end;
		}
		
	}
	public static final DateFormat format = new SimpleDateFormat( "MMM yyyy");
	public static long calculate( String [] exp ) throws Exception
	{
		boolean [] calendar = new boolean [360];
		
		for( String s : exp )
		{
			s = s.trim();
			
			String [] startAndEnd = s.split("-");
			
			Date start = (Date) format.parseObject(startAndEnd[0]);
			Date end = (Date) format.parseObject(startAndEnd[1]);
			
			int startIndex = 12 * (start.getYear() - 90) + start.getMonth();
			int endIndex = 12 * (end.getYear() - 90) + end.getMonth();
			
			for( ; startIndex <= endIndex; startIndex++ )
			{
				calendar[startIndex] = true;
			}
		}
		
		int x = 0;
		
		for( boolean b : calendar )
		{
			if ( b )
			{
				x++;
			}
		}
		
		return x / 12;
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
					String [] exp = line.split(";");
					
					System.out.println(WorkExperience.calculate(exp) );
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
			catch(Exception e)
			{
				e.printStackTrace();
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
