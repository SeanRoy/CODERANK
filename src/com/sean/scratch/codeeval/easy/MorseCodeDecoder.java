package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MorseCodeDecoder
{
	public static final MorseTree morseTree = new MorseTree();
	
	public static class Node
	{
		private Node left = null;
		private Node right = null;
		
		private Character value = null;
		
		public Node()
		{
			
		}
		public Node(char c)
		{
			value = c;
		}

		public Node getLeft()
		{
			return left;
		}

		public void setLeft(Node left)
		{
			this.left = left;
		}

		public Node getRight()
		{
			return right;
		}

		public void setRight(Node right)
		{
			this.right = right;
		}

		public char getValue()
		{
			return value;
		}

		public void setValue(char value)
		{
			this.value = value;
		}
	}
	
	
	public static final class MorseTree
	{
		private MorseTree tree = null;
		
		private Node START = new Node();
		private Node A = new Node('A');
		private Node B = new Node('B');
		private Node C = new Node('C');
		private Node D = new Node('D');
		private Node E = new Node('E');
		private Node F = new Node('F');
		private Node G = new Node('G');
		private Node H = new Node('H');
		private Node I = new Node('I');
		private Node J = new Node('J');
		private Node K = new Node('K');
		private Node L = new Node('L');
		private Node M = new Node('M');
		private Node N = new Node('N');
		private Node O = new Node('O');
		private Node P = new Node('P');
		private Node Q = new Node('Q');
		private Node R = new Node('R');
		private Node S = new Node('S');
		private Node T = new Node('T');
		private Node U = new Node('U');
		private Node V = new Node('V');
		private Node W = new Node('W');
		private Node X = new Node('X');
		private Node Y = new Node('Y');
		private Node Z = new Node('Z');
		private Node ONE = new Node('1');
		private Node TWO = new Node('2');
		private Node THREE = new Node('3');
		private Node FOUR = new Node('4');
		private Node FIVE = new Node('5');
		private Node SIX = new Node('6');
		private Node SEVEN = new Node('7');
		private Node EIGHT = new Node('8');
		private Node NINE = new Node('9');
		private Node ZERO = new Node('0');
		
		public MorseTree()
		{
			START.setLeft(T);
			T.setLeft(M);
			M.setLeft(O);
			O.setLeft(new Node());
			O.setRight(new Node());
			O.getLeft().setLeft(ZERO);
			O.getLeft().setRight(NINE);
			O.getRight().setRight(EIGHT);
			
			M.setRight(G);
			G.setLeft(Q);
			G.setRight(Z);
			Z.setRight(SEVEN);
			T.setRight(N);
			N.setLeft(K);
			K.setLeft(Y);
			K.setRight(C);
			N.setRight(D);
			D.setLeft(X);
			D.setRight(B);
			B.setRight(SIX);
			
			START.setRight(E);
			E.setLeft(A);
			A.setLeft(W);
			W.setLeft(J);
			J.setLeft(ONE);
			W.setRight(P);
			A.setRight(R);
			R.setRight(L);
			
			E.setRight(I);
			I.setLeft(U);;
			U.setLeft(new Node());
			U.getLeft().setLeft(TWO);
			U.setRight(F);
			
			I.setRight(S);
			S.setLeft(V);
			V.setLeft(THREE);
			S.setRight(H);
			H.setLeft(FOUR);
			H.setRight(FIVE);
		}
		
		public MorseTree getMorseTree()
		{
			if ( tree == null )
			{
				tree = new MorseTree();
			}
			
			return tree;
		}
		
		public char decode(String code)
		{
			Node cur = START;
			
			for( int i = 0; i < code.length(); i++ )
			{
				char c = code.charAt(i);
				
				if ( c == '-' )
				{
					cur = cur.getLeft();
				}
				else
				{
					cur = cur.getRight();
				}
			}
			
			return cur.getValue();
		}
	}
	public static String decodeWord(String code)
	{
		StringBuffer buffer = new StringBuffer();
		
		String [] letters = code.split("\\s+");
		
		for( String letter : letters )
		{
			buffer.append(MorseCodeDecoder.morseTree.decode(letter));
		}
		
		return buffer.toString();
	}
	public static String decodeSentence(String [] words)
	{
		StringBuffer ret = new StringBuffer();
		
		for( int i = 0; i < words.length; i++ )
		{	
			ret.append(decodeWord(words[i]));
			
			if ( i < words.length -1 )
			{
				ret.append(" ");
			}
		}
		
		return ret.toString();
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
					String [] words = line.split("\\s\\s");
					
					System.out.println(MorseCodeDecoder.decodeSentence(words));
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
