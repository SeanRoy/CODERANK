package com.sean.scratch.codeeval.hard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * https://www.codeeval.com/open_challenges/154/
 * @author Sean
 *
 */
public class IPHeaderCorrect
{
	public static final int CHECKSUM_IDX = 10;
	public static final int SRC_ADDR_IDX = 12;
	
	public static String convertIPAddrToHex(String addr)
	{
		StringBuffer buf = new StringBuffer();
		
		String [] pieces = addr.split("\\.");
		
		for( int i = 0; i < pieces.length; i++ )
		{
			String str = Integer.toHexString(Integer.parseInt(pieces[i]));
			
			if ( str.length() == 1 )
			{
				str = "0" + str;
			}
			
			buf.append(str);
			
			if ( i < pieces.length - 1 )
			{
				buf.append( " " );
			}
		}
		
		return buf.toString();
	}
	public static void calculateChecksum( String [] header )
	{
		// reinitialize checksum so it doesn't figure in calculation.
		header[CHECKSUM_IDX]   = "00";
		header[CHECKSUM_IDX+1] = "00";
		
		int checksum = 0;
		
		// Add all 16 bit words in header.
		for( int i = 0; i < header.length - 1; i = i + 2 )
		{
			String octetString = header[i] + header[i+1];
			
			checksum += Integer.parseInt(octetString, 16);
			
			octetString = Integer.toHexString(checksum);
			
			if ( octetString.length() > 4 )
			{
				String carry = octetString.substring(0, 1);
				
				String balance = octetString.substring(1);
				
				checksum = Integer.parseInt(carry, 16) + Integer.parseInt(balance, 16);
			}
		}
		
		// Take the one's complement of the sum, this is the checksum
		String hexString = Integer.toHexString(~checksum);
		
		String firstOctet = hexString.substring(4,6);
		String secondOctet = hexString.substring(6);
		
		header[CHECKSUM_IDX]   = firstOctet;
		header[CHECKSUM_IDX+1] = secondOctet;
	}
	public static String fix( String src, String dest, String header )
	{
		StringBuffer buf = new StringBuffer();
		
		String [] srcPieces = convertIPAddrToHex(src).split("\\s+");
		String [] destPieces = convertIPAddrToHex(dest).split("\\s+");
		
		String [] headerPieces = header.split("\\s+");
		
		for( int i = 0; i < srcPieces.length + destPieces.length; i++ )
		{
			int index = SRC_ADDR_IDX + i;
			
			if ( i < srcPieces.length )
			{
				headerPieces[index] = srcPieces[i];
			}
			else
			{
				headerPieces[index] = destPieces[i - srcPieces.length];
			}
		}
		
		calculateChecksum(headerPieces);
		
		for( int i = 0; i < headerPieces.length; i++ )
		{
			buf.append(headerPieces[i]);
			
			if ( i < headerPieces.length - 1 )
			{
				buf.append(" ");
			}
		}
		
		return buf.toString();
	}
	public static void main(String [] args)
	{
		if ( args.length >= 1 )
		{
			String fileName = args[0];
			
			BufferedReader bReader = null;
			String line = null;
			
			
			try
			{
				bReader = new BufferedReader(new FileReader(fileName) );
				
				char [] buffer = new char[ 100 ];
				while ( ( bReader.read(buffer) ) != -1 )
				{
					String src;
					String dest;
					
					int i = 0;
					boolean found = false;
					for( ; i < buffer.length; i++ )
					{
						if ( buffer[i] == ' ' )
						{
							if ( !found )
								found = true;
							else
								break;
						}
					}
					
					char [] ips = Arrays.copyOfRange(buffer, 0, i);
					char [] header = Arrays.copyOfRange(buffer, i+1, i + 1 + 59);
					
					String ipStrings = new String(ips);
					String headerString = new String(header);
					
					String [] srcDest = ipStrings.split("\\s+");
					
					System.out.println(IPHeaderCorrect.fix(srcDest[0], srcDest[1], headerString) );
					
					bReader.readLine();
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
