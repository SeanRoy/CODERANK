package com.sean.scratch.codeeval.hard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;
/**
 * https://www.codeeval.com/open_challenges/155/
 * 
 * 1. Build a frequency table of every character and assuming that the character of
 *    highest frequency is a space. 
 * 2. Break up sentence by the assumed space character. The segments should correspond
 *    to words.
 * 3. Loop through words, looking for first one that is of size 'length.'
 * 4. Subloop until you find the matching pair, if one isn't found, there should be another
 *    pair of length 'length.'
 * @author Sean
 *
 */
public class AsciiDecode
{
	public static Hashtable analysis(String [] message )
	{
		Hashtable<String, Integer> table = new Hashtable<String, Integer>();
		
		for( String m : message )
		{
			if ( table.get(m) != null)
			{
				table.put(m, table.get(m) + 1 );
			}
			else
			{
				table.put(m,  1);
			}
		}
		
		return table;
	}
	public static String findWord(int length, String [] spacedMessage)
	{
		for( int i = 0; i < spacedMessage.length - 1; i++ )
		{
			int wordLength = spacedMessage[i].split("\\s+").length;
			
			if ( wordLength == length )
			{
				for( int j = i + 1; j < spacedMessage.length; j++ )
				{
					if ( spacedMessage[j].equals(spacedMessage[i] ) )
					{
						return spacedMessage[i];
					}
				}
			}
		}
		
		return null;
	}
	
	public static String getHighestFrequency(Hashtable<String, Integer> table)
	{
		String str = null;
		int most = 0;
		
		Set<String> keys = table.keySet();
		
		for( String s : keys )
		{
			if (table.get(s) > most )
			{
				most = table.get(s);
				str = s;
			}
		}
		
		return str;
	}
	public static String decode(int length, char letter, String message)
	{
		StringBuffer buf = new StringBuffer();
		
		String [] brokenMessage = message.split("\\s+");
		
		String space = getHighestFrequency(analysis(brokenMessage));
		
		String [] spacedMessage = message.replaceAll(" " + space + " ", ",").split(",");
		
		String word = findWord(length, spacedMessage);
		
		String [] brokenWord = word.split("\\s+");
		
		int letterAscii = (int) letter;
		int lastLetterAscii = Integer.parseInt(brokenWord[brokenWord.length - 1]);
		int diff = lastLetterAscii - letterAscii;
		
		for( int i = 0; i < brokenMessage.length; i++ )
		{
			int code = Integer.parseInt(brokenMessage[i]);
			int ascii = code - diff;
			
			System.out.print((char) ascii);
		}
		
		return buf.toString();
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
					String [] pieces = line.split("\\|");
					
					int length = Integer.parseInt(pieces[0].trim());
					char lastLetter = pieces[1].trim().charAt(0);
					String msg = pieces[2].trim();
					
					System.out.println(AsciiDecode.decode(length, lastLetter, msg));
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
