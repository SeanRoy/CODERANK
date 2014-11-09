package com.sean.scratch.codeeval.easy;

import java.io.File;

public class FileLength
{
	public static void main(String [] args)
	{
		if ( args.length >= 1 )
		{
			String fileName = args[0];
			File f = null;
			
			f = new File(fileName);
				
			System.out.println(f.length());
				
			
		}

	}
}
