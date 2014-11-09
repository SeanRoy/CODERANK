package com.sean.scratch.codeeval.hard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class TextDollar
{
	public static final String ZERO = "ZERO";
	public static final String ONE = "One";
	public static final String TWO = "Two";
	public static final String THREE = "Three";
	public static final String FOUR = "Four";
	public static final String FIVE = "Five";
	public static final String SIX = "Six";
	public static final String SEVEN = "Seven";
	public static final String EIGHT = "Eight";
	public static final String NINE = "Nine";
	public static final String TEN = "Ten";
	public static final String ELEVEN = "Eleven";
	public static final String TWELVE = "Twelve";
	public static final String THIRTEEN = "Thirteen";
	public static final String FOURTEEN = "Fourteen";
	public static final String FIFTEEN = "Fifteen";
	public static final String SIXTEEN = "Sixteen";
	public static final String SEVENTEEN = "Seventeen";
	public static final String EIGHTEEN = "Eighteen";
	public static final String NINETEEN = "Nineteen";
	public static final String TWENTY = "Twenty";
	public static final String THIRTY = "Thirty";
	public static final String FORTY = "Forty";
	public static final String FIFTY = "Fifty";
	public static final String SIXTY = "Sixty";
	public static final String SEVENTY = "Seventy";
	public static final String EIGHTY = "Eighty";
	public static final String NINETY = "Ninety";
	public static final String HUNDRED = "Hundred";
	public static final String THOUSAND = "Thousand";
	public static final String MILLION = "Million";
	public static final String BILLION = "Billion";
	
	public static final Hashtable<Integer,String> table = new Hashtable<Integer,String>();
	
	static
	{
		table.put(0, "");
		table.put(1, ONE);
		table.put(2, TWO);
		table.put(3, THREE);
		table.put(4, FOUR);
		table.put(5, FIVE);
		table.put(6, SIX);
		table.put(7, SEVEN);
		table.put(8, EIGHT);
		table.put(9, NINE);
		table.put(10, TEN);
		table.put(11, ELEVEN);
		table.put(12, TWELVE);
		table.put(13, THIRTEEN);
		table.put(14, FOURTEEN);
		table.put(15, FIFTEEN);
		table.put(16, SIXTEEN);
		table.put(17, SEVENTEEN);
		table.put(18, EIGHTEEN);
		table.put(19, NINETEEN);
		table.put(20, TWENTY);
		table.put(30, THIRTY);
		table.put(40, FORTY);
		table.put(50, FIFTY);
		table.put(60, SIXTY);
		table.put(70, SEVENTY);
		table.put(80, EIGHTY);
		table.put(90, NINETY);
		table.put(100, HUNDRED);
		table.put(1000, THOUSAND);
		table.put(1000000, MILLION);
		table.put(1000000000, BILLION);
	};

	public static String stringify(int number)
	{
		StringBuilder sb = new StringBuilder();
			
		stringify(number, sb );
		
		return sb.toString();
	}
	public static void stringify(int number, StringBuilder sb)
	{
		if ( number > 0 )
		{
			String numString = Integer.toString(number);
			
			int power = (int) Math.pow(10, Integer.toString(number).length() - 1);
			
			int msd = ( ( int ) numString.charAt(0) - ( int ) '0' );
			
			if ( power < 100 )
			{	
				if ( number < 20 )
				{
					sb.append(table.get(number));return;
				}
				else
				{
					sb.append(table.get(msd*power) );
				}
			}
			else if ( power < 1000 )
			{
				sb.append(table.get(msd));
				sb.append(table.get(power));
			}
			else
			{
				if ( power < 1000000 )
				{
					power = 1000;
				}
				else if ( power < 1000000000 )
				{
					power = 1000000;
				}
				else
				{
					power = 1000000000;
				}
				
				int block = number / power;
				
				msd = block;
				
				stringify(msd, sb);
				sb.append(table.get(power));
			}
			
			
			number -= msd * power;
			
			stringify(number, sb);
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
						System.out.println(TextDollar.stringify(Integer.parseInt(line)) + "Dollars");
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
