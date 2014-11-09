package com.sean.scratch.hackerrank;

import java.io.*;
import java.util.*;

public class Solution
{
    public static void almostSortedOld(int [] ar)
    {
        int count = 0;
        
        for( int i = 0; i < ar.length; i++ )
        {
            int last = ar[i];
            
            count++;
        
            System.out.println( "[" + last + "]" );
            
            String s = "" + last;
           
            for( int j = i + 1; j < ar.length; j++ )
            {
                if ( last < ar[j] )
                {
                    s += " " + ar[j];
                    
                    System.out.println("[" + s + "]");
                    
                    count++;
                    
                    last = ar[j];
                }
                else
                {
                    break;
                }
            }
        }
        
        System.out.println(count);
    }
    public static void almostSorted(int [] ar)
    {
        int count = 0;
        
        for( int i = 0; i < ar.length; i++ )
        {
            count++;
            
            //System.out.print( "[" + ar[i] + "] ");
            
            for( int j = i + 1; j < ar.length; j++ )
            {
            	if ( ar[j-1] < ar[j] )
            	{
            		count++;
            		
            		//StringBuilder b = new StringBuilder();
            		//b.append("[");
            		for( int x = i; x <= j; x++ )
            		{
            			//b.append( ar[x]  );
            			if ( x < j )
            			{
            				//b.append(" ");
            			}
            		}
            		////b.append("]");
            		
            	}
            	else
            	{
            		break;
            	}
            } 
        }
        
        System.out.println(count);
    }
    public static void main(String [] args)
    {
        //Scanner in = new Scanner(System.in);
    	
    	Scanner in = null;
    	try
    	{
    	 in = new Scanner( new FileInputStream(new File(args[0])));
    	}catch(Exception e){}
    	
        int arraySize = in.nextInt();
        
        int [] ar = new int[ arraySize ];
        
        for( int i = 0; i < arraySize; i++ )
        {
            ar[i] = in.nextInt();
        }
        
        Solution.almostSorted(ar);
    }
}