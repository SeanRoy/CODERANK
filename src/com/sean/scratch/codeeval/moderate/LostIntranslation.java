package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class LostIntranslation
{
	/**
	   rbc vjnmkf kd yxyqci na rbc zjkfoscdd ew rbc ujllmcp
	   tc rbkso rbyr ejp mysljylc kd kxveddknmc re jsicpdrysi
       de kr kd eoya kw aej icfkici re zjkr
       
       the public is amazed by the quickness of the juggler
	   we think that our language is impossible to understand
	   so it is okay if you decided to quit
	 */
	public static final char [] dict =
	{
	//  'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
	//	'y','n','f','i','c','w','l','b','k','u','o','m','x','s','e','v','z','p','d','r','j','g','t','h','a','q'
		'y','h','e','s','o','c','v','x','d','u','i','g','l','b','k','r','z','t','n','w','j','p','f','m','a','q'
	};
	
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
					if ( ! line.equals("" ) )
					{
						StringBuilder sb = new StringBuilder();
						
						char [] text = line.toCharArray();
						
						for( char c : text )
						{
							int val = ( int ) c - (int) 'a';
							
							if ( val >= 0 && val < dict.length )
							{
								sb.append(dict[val]);
							}
							else
							{
								sb.append(c);
							}
						}
						
						System.out.println(sb.toString());
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
