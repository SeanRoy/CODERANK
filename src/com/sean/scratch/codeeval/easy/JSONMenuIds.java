package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONMenuIds
{
	
	public static String [] getItems(String line)
	{
		String [] pieces = line.split("\"items\": ");
		
		String itemString = pieces[1].substring(pieces[1].indexOf('[') + 1, pieces[1].indexOf(']')).replace("null, ", "");
		
		pieces = itemString.split("}, ");
		
		return pieces;
	}
	public static int getValidId(String item)
	{
		int id = 0;
		
		if ( item.contains("\"label\":"))
		{
			String [] pieces = item.split(",");
			
			for( String piece : pieces )
			{
				if ( piece.contains("\"id\":") )
				{
					String [] idVal = piece.split(":");
					
					id = Integer.parseInt(idVal[1].trim());
					
					break;
				}
			}
		}
		
		return id;
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
					if ( !line.equals("") )
					{
						String [] items = JSONMenuIds.getItems(line);
						
						int sum = 0;
						
						for( String item : items )
						{
							sum += JSONMenuIds.getValidId(item);
						}
						
						System.out.println(sum);
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
