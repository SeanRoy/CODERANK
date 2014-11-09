package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CycleDetectionAlgorithm
{
	public static String [] floyd(String [] seq)
	{
		String [] ret = null;
		
		if ( seq.length > 0 )
		{
			if ( seq.length == 1 )
			{
				ret = seq;
			}
			else if ( seq.length == 2 )
			{
				if ( seq[0].equals(seq[1] ) )
				{
					ret = new String [] { seq[0] };
				}
			}
			else if ( seq.length >= 3 )
			{
				ret = floyd(seq, 1, 2);
			}
		}
		
		return ret;
	}
	/**
	 * In the description of this algoritm in http://en.wikipedia.org/wiki/Cycle_detection, there's a mention
	 * of the hare running in a circle when determining mu, but the psuedo code doesn't actually do it.
	 * @param seq
	 * @param tortoise
	 * @param hare
	 * @return
	 */
	public static String [] floyd(String [] seq, int tortoise, int hare)
	{
		String [] cycle = null;
		
		while( !seq[tortoise].equals(seq[hare]))
		{
			tortoise++;

			if ( hare + 2 <= seq.length - 1 )
			{
				hare += 2;
			}
			else
			{
				hare = seq.length - 1;
			}
			
			if ( tortoise >= seq.length - 1)
			{
				return cycle;
			}
		}
		
		// lambda? wtf.
		// Record the distance so that we can calculate mu later.
		int distance = hare - tortoise;
		
		int mu = 0;
		tortoise = 0;
		
		while( !seq[tortoise].equals(seq[hare]))
		{
			tortoise++;
			
			hare++;
			
			/**
			 * http://en.wikipedia.org/wiki/Cycle_detection
			 * 
			 * In order for the hare to run in a circle when detecting cycles over a list of numbers, do the following:
			 * 1. above, when we find the hare/tortoise match, record the distance <dist> between the two indices.
			 * 2. when the hare runs off the end of the list, we need to simulate it running in a circle, so set it
			 *    dist indices ahead of the tortoise.  This will guarantee that we find the beginning of the cycle (mu)
			 *    as soon as the tortoise runs past all of the non cycle numbers, that is if the hare runs off the end
			 *    of the list before this happens.
			 */
			if ( hare >= seq.length )
			{
				hare = tortoise + distance;
			}
			
			mu++;
		}
		
		int lambda = 1;
		hare = tortoise + 1;
		
		while( !seq[tortoise].equals(seq[hare]))
		{
			hare++;

			if ( hare >= seq.length )
			{
				hare = hare - seq.length;
			}
			
			lambda++;
		}
		
		cycle = new String[lambda];
		
		for( int i = 0; i < lambda; i++ )
		{
			cycle[i] = seq[mu + i];
		}
		
		return cycle;
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
					if ( ! line.equals( "" ) )
					{
						String [] seq = line.split("\\s+");
						
						String [] sequence = CycleDetectionAlgorithm.floyd(seq);
						
						if ( sequence != null && sequence.length > 0 )
						{
							for( int i = 0; i < sequence.length; i++ )
							{
								System.out.print( sequence[i] );
								
								if ( i < sequence.length - 1 )
								{
									System.out.print( " " );
								}
								else
								{
									System.out.println();
								}
							}
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
