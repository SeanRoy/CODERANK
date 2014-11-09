package com.sean.scratch.codeeval.hard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Sean
 *
 */
public class StringSearch
{
	public static String [] getWildcard(String wildcardExpr )
	{
		String [] wildcard = { "", "" };
		
		int i = 0;
		
		StringBuffer prefix = new StringBuffer();
		StringBuffer suffix = new StringBuffer();
		
		char [] chars = wildcardExpr.toCharArray();
		
		boolean asterix = false;
		
		for( ; i < chars.length; i++ )
		{
			if ( chars[i] == '\\' )
			{
				if ( chars[i + 1] == '*' )
				{
					i++;
					
					if ( ! asterix )
					{
						prefix.append("*");
					}
					else
					{
						suffix.append("*");
					}
				}
			}
			else if ( !asterix && chars[i] != '*' )
			{
				prefix.append(chars[i]);
			}	
			else if ( ! asterix )
			{
				asterix = true;
			}
			else
			{
				suffix.append(chars[i]);
			}
		}
			
		wildcard[0] = prefix.toString();
		wildcard[1] = suffix.toString();
		
		return wildcard;
	}
	public static int searchWild( char [] string, String wildcardExpr )
	{
		String [] wildcard = getWildcard(wildcardExpr);
		
		char [] prefix = wildcard[0].toCharArray();
		char [] suffix = wildcard[1].toCharArray();
		
		int i = 0;
		
		if ( prefix.length > 0 )
		{
			i = search(string, prefix);
		}
		
		if ( i >= 0 && suffix.length > 0 )
		{
			i = search(i, string, suffix);
		}

		return i;
		
	}
	public static int search(char [] string, char [] expr )
	{
		return search(0, string, expr);
	}
	public static int search( int i, char [] string, char [] expr)
	{		
		if ( string.length >= expr.length )
		{
			for( ; i < string.length; i++ )
			{
				for( int j = 0; j < expr.length && i < string.length; j++,i++ )
				{
					if ( j == expr.length - 1 && expr[j] == string[i] )
					{
						return i;
					}
					else if ( expr[j] != string[i] )
					{
						break;
					}
				}
			}
		}
		
		return -1;
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
					String [] lines = line.split(",");
					
					if( lines == null || lines.length < 1 )
					{
						System.out.println( false );
					}
					else if ( lines[0] == null || lines[0].length() < 1 )
					{
						System.out.println(false);
					}
					else if ( lines[1] == null || lines[1].length() < 1 )
					{
						System.out.println(true);
					}
					else if ( lines[1].contains("*"))
					{
						System.out.println
							(searchWild(lines[0].toCharArray(), lines[1]) >= 0);
					}
					else
					{
						System.out.println(
								search(lines[0].toCharArray(),
									   lines[1].toCharArray()) >= 0);
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
