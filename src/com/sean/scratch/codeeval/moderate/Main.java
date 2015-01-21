package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;

public class Main
{
	/*
	private static final BitSet ZERO = new BitSet(8);
	private static final BitSet ONE = new BitSet(8);
	private static final BitSet TWO = new BitSet(8);
	private static final BitSet THREE = new BitSet(8);
	private static final BitSet FOUR = new BitSet(8);
	private static final BitSet FIVE = new BitSet(8);
	private static final BitSet SIX = new BitSet(8);
	private static final BitSet SEVEN = new BitSet(8);
	private static final BitSet EIGHT = new BitSet(8);
	private static final BitSet NINE = new BitSet(8);
	private static final BitSet DEC = new BitSet(8);
	*/
	
	// ZERO = 1111 1100
	private static final int ZERO = 0xFC;
	// ONE = 0110 0000
	private static final int ONE = 0x60;
	// TWO = 1101 1010
	private static final int TWO = 0xDA;
	// THREE = 1101 0010
	private static final int THREE = 0xF2;
	// FOUR = 0110 0110
	private static final int FOUR = 0x66;
	// FIVE = 1011 0110
	private static final int FIVE = 0xB6;
	// SIX = 1011 1110
	private static final int SIX = 0xBE;
	// SEVEN = 1110 0000
	private static final int SEVEN = 0xE0;
	// EIGHT = 1111 1110
	private static final int EIGHT = 0xFE;
	// NINE = 1111 0111
	private static final int NINE = 0xF7;
	// DEC = 0000 0001
	private static final int DEC = 0x01;
	
	
	
	/**
	 *  ***
	 * *   *
	 * *   *
	 *  ***
	 * *   *
	 * *   *
	 *  ***
	 *  0000 0000
	 * @param number
	 * @return
	 */
	public static void printNumber(int num)
	{	
		boolean space = false;
		
		// 0
		if ( (num & 0x80) > 0 )
		{
			System.out.println(" *** ");
		}
		
		// 5
		if ( ( num & 0x04 ) > 0 )
		{
			System.out.print("*");
			
			space = true;
		}
		
		// 1
		if ( (num & 0x40 ) > 0 )
		{
			if ( !space )
			{
				System.out.print(" ");
			}
			space = false;
			
			System.out.print("   *");
		}
		
		System.out.println();
		
		// 5
		if ( ( num & 0x04 ) > 0 )
		{
			System.out.print("*");
			
			space = true;
		}
		
		// 1
		if ( (num & 0x40 ) > 0 )
		{
			if ( !space )
			{
				System.out.print(" ");
			}
			space = false;
			
			System.out.print("   *");
		}
		
		System.out.println();
		
		// 6
		if ( ( num & 0x02 ) > 0 )
		{
			System.out.println( " *** " );
		}
		
		// 4
		if ( ( num & 0x08 ) > 0 )
		{
			space = true;
			System.out.print( "*" );
		}
		
		// 2
		if ( ( num & 0x20 ) > 0 )
		{
			if ( !space )
			{
				System.out.print(" ");
				
			}
			space = false;
			System.out.print("   *");
		}
		
		System.out.println();
		
		// 4
		if ( ( num & 0x08 ) > 0 )
		{
			space = true;
			System.out.print( "*" );
		}
		
		// 2 
		if ( ( num & 0x20 ) > 0 )
		{
			if ( !space )
			{
				System.out.print(" ");				
			}
			space = false;
			System.out.print("   *");
		}
		
		System.out.println();
		
		// 3
		if ( ( num & 0x10 ) > 0 )
		{
			System.out.println(" *** ");
		}
		
		
		// .
		if ( ( num & 0x01 ) > 0 )
		{
			System.out.println("    *");
		}
		
		System.out.println();
	}
	
	
	public static BitSet convert(String bits)
	{
		BitSet b = new BitSet(8);
		
		
		
		
		return b;
	}
	
	public static void main(String [] args)
	{
		if ( args.length >= 1 )
		{
			String fileName = args[0];
			
			BufferedReader bReader = null;
			
			Main.printNumber(ZERO);
			Main.printNumber(ONE);
			Main.printNumber(TWO);
			Main.printNumber(THREE);
			Main.printNumber(FOUR);
			Main.printNumber(FIVE);
			Main.printNumber(SIX);
			Main.printNumber(SEVEN);
			Main.printNumber(EIGHT);
			Main.printNumber(NINE);
			Main.printNumber(DEC);
			
			try
			{
				bReader = new BufferedReader(new FileReader(fileName) );
				
				String line = null;
				
				while( ( line = bReader.readLine() ) != null )
				{
					if ( ! line.equals( "" ) )
					{
						
						//System.out.println();
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
