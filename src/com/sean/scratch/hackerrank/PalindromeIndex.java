package com.sean.scratch.hackerrank;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class PalindromeIndex {

    public static int solve(String line)
    {
        char [] chars = line.toCharArray();
        int ret = -1;

        for( int i = 0,j = line.length() - 1; j >= i; i++, j--)
        {
                if ( chars[i] != chars[j] )
                {                   
                	String before = line.substring(0, i);
		            String after = line.substring(i + 1, line.length());
		            if ( checkPalindrome(before + after) )
		            {
		            	return i;
		            }
		            else
		            {
		            	return j;
		            }
                }
        }
        return ret;
    }

    public static boolean checkPalindrome(String str)
    {
    	for( int i = 0, j = str.length() - 1; j >= i; i++,j-- )
    	{
    		if ( str.charAt(i) != str.charAt(j) )
    		{
    			return false;
    		}
    	}
    	return true;
    }
    public static void main(String[] args) {
    	
    	File file = new File(args[0]);
    	
    	FileInputStream fr = null;
    	
    	try
    	{
    			
    		fr = new FileInputStream(file);
    	
	        Scanner in = new Scanner(fr);
	        
	        int tests = in.nextInt();
	        
	        in.nextLine();
	        
	        for( int i = 0; i < tests; i++ )
	        {
	            String line = in.nextLine().trim();
	            
	            System.out.println(PalindromeIndex.solve(line));
	        }
    	}
    	catch( Exception e)
    	{
    		e.printStackTrace();
    	}
    	finally
    	{
    		try
    		{
    			fr.close();
    		}catch(Exception ex){}
    		
    		/**
    		 * 16654
			   20495
			   861
			   4559
			   29796
    		 */
    	}
    }
}