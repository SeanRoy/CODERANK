package com.sean.scratch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UtopianTree {
	
	public static void runit()
	{
		Scanner in = new Scanner(System.in);
		
		int numberOfTests = in.nextInt();
		int numOfCycles = 0;
		
		while( numberOfTests-- > 0 )
		{
			numOfCycles = in.nextInt();
			
			System.out.println(numOfCycles);
		}
	}
	public static void main(String [] args)
	{
		UtopianTree.runit();
	}

}
