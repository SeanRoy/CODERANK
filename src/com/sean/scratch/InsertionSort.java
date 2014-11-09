package com.sean.scratch;

public class InsertionSort {

    private static void printArray(int[] ar) {
        for(int n: ar){
           System.out.print(n+" ");
        }
          System.out.println("");
     }
    
    public static void sort(int[] ar)
    {
    	int count = 0;
    	
        for( int i = 1; i < ar.length; i++ )
        {
            int j = i - 1;
            
            int value = ar[i];
            
            int shifts = 0;
            
            while( j >= 0 && ar[j] > value )
            {
                ar[ j + 1 ] = ar[j]; 
                j--;
                
                shifts++;
            }
            
            System.out.println(shifts);
            
            count += shifts;
            
            ar[j + 1] = value;
            
           // printArray(ar);
        }
        
        System.out.println( "----\n" + count );
    }
	public static void main(String[] args) 
	{
		int [] a1 = new int [] {1, 1, 1, 2, 2 }; 
		//int [] a2 = new int [] {10, 2, 1, 3, 1, 2, 9 }; 
		int [] a2 = new int[] { 3, 1, 2 };	
		InsertionSort.sort(a2);
	}

}
