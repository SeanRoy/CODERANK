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
		int i = 0;
		
		Exp [] array = new Exp[exp.length];
		
		for( String s : exp )
		{
			s = s.trim();
			
			String [] startAndEnd = s.split("-");
			
			Date start = (Date) format.parseObject(startAndEnd[0]);
			Date end = (Date) format.parseObject(startAndEnd[1]);
			
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(end);
			endCal.set(Calendar.DAY_OF_MONTH, endCal.getActualMaximum(Calendar.DAY_OF_MONTH));
			end = endCal.getTime();
			
			array[i++] = new Exp(start, end);
		}
		
		Arrays.sort(array);
		
		long sum = 0;
		
		Exp start = array[0];
		Exp end = array[0];
		
		for( i = 1; i < array.length; i++ )
		{
			if ( array[i].getStartTime() < start.getEndTime() )
			{
				if ( start.getEndTime() > array[i].getEndTime() )
				{
					end = start;
				}
				else
				{
					end = array[i];
				}
			}
			else
			{
				sum += end.getEndTime() - start.getStartTime();
			
				start = array[i];
				end = array[i];
			}
		}
		
		sum += end.getEndTime() - start.getStartTime();
				
		return ( sum / 1000 ) / ( 60 * 60 * 24 * 365 );
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
