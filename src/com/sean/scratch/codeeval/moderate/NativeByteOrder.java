package com.sean.scratch.codeeval.moderate;

import java.nio.ByteOrder;

public class NativeByteOrder
{
	public static void main(String [] args)
	{
		
		if ( ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN )
		{
			System.out.println("BigEndian");
		}
		else
		{
			System.out.println("LittleEndian");
		}

	}
}
