package com.sean.scratch.codeeval.easy;

import java.util.Formatter;
import java.util.Locale;

public class MultiplicationTable
{
	public static void main(String [] args)
	{
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb, Locale.US);
		
		for( int i = 1; i <= 12; i++ )
		{
			for( int j = 1; j <= 12; j++ )
			{
				formatter.format("%1$,4d", new Integer( j * i ) );
			}
			
			if ( i < 12 )
			{
				sb.append("\n");
			}
		}
		
		formatter.close();
		
		System.out.println(sb.toString());
	}
}
