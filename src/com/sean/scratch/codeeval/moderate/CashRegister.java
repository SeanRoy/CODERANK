package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class CashRegister
{
	/**
	 	'PENNY': .01,
		'NICKEL': .05,
		'DIME': .10,
		'QUARTER': .25,
		'HALF DOLLAR': .50,
		'ONE': 1.00,
		'TWO': 2.00,
		'FIVE': 5.00,
		'TEN': 10.00,
		'TWENTY': 20.00,
		'FIFTY': 50.00,
		'ONE HUNDRED': 100.00
	 */
	
	private static final double [] arr = { .01, .05, .10, .25, .50, 1, 2, 5, 10, 20, 50, 100 };
	public static final String [] words =
	{
		"PENNY",
		"NICKEL",
		"DIME",
		"QUARTER",
		"HALF DOLLAR",
		"ONE",
		"TWO",
		"FIVE",
		"TEN",
		"TWENTY",
		"FIFTY",
		"ONE HUNDRED"
	};
	
	public static final BigDecimal HUNDRED = new BigDecimal(100.0);
	
	public static String getChange(BigDecimal change)
	{
		StringBuilder sb = new StringBuilder();
		
		if ( change.compareTo(BigDecimal.ZERO) == 0 )
		{
			sb.append("ZERO");
		}
		else if ( change.compareTo(BigDecimal.ZERO) < 0 )
		{
			sb.append("ERROR");
		}
		else
		{
			while ( change.compareTo(BigDecimal.ZERO) > 0 )
			{
				int arrIndex = 0;
				
				if ( change.compareTo(HUNDRED) > 0 )
				{
					arrIndex = arr.length - 1;
				}
				else
				{
					for( int i = 0; i < arr.length; i++ )
					{
						BigDecimal value = new BigDecimal(arr[i]).setScale(2, BigDecimal.ROUND_DOWN);
						
						if ( change.compareTo(value) == 0 )
						{
							arrIndex = i;
							
							break;
						}
						else if ( change.compareTo(value) < 0 )
						{
							arrIndex = i - 1;
							
							break;
						}
					}
				}
				
				if ( sb.length() > 0 )
				{
					sb.append(",");
				}
				
				sb.append(words[arrIndex]);
				
				change = change.subtract(new BigDecimal(arr[arrIndex])).setScale(2, BigDecimal.ROUND_DOWN);
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
						String [] pieces = line.split(";");
						double price = Double.parseDouble(pieces[0]);
						double cash = Double.parseDouble(pieces[1]);
						BigDecimal change = new BigDecimal(cash - price);
						change = change.setScale(2, BigDecimal.ROUND_HALF_DOWN);
						
						System.out.println(CashRegister.getChange(change));
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
