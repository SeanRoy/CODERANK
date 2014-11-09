package com.sean.scratch.codeeval.hard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://www.codeeval.com/open_challenges/164/
 * Solved with minimum spanning tree.
 * @author Sean
 *
 */
public class MarsNetwork
{
	public static class Vertex
	{
		private double x;
		private double y;
		private boolean inTree = false;
		
		public Vertex(String [] XandY)
		{
			x = Double.parseDouble(XandY[0]);
			y = Double.parseDouble(XandY[1]);
		}
		public double getX()
		{
			return x;
		}
		public void setX(double x)
		{
			this.x = x;
		}
		public double getY()
		{
			return y;
		}
		public void setY(double y)
		{
			this.y = y;
		}
		public boolean isInTree()
		{
			return inTree;
		}
		public void setInTree(boolean inTree)
		{
			this.inTree = inTree;
		}
	}
	public static class Edge
	{
		int srcV = -1;
		int destV = -1;
		double distance;
		
		public Edge(int srcV, int destV, double distance)
		{
			this.srcV = srcV;
			this.destV = destV;
			this.distance = distance;
		}

		public int getSrcV()
		{
			return srcV;
		}

		public void setSrcV(int srcV)
		{
			this.srcV = srcV;
		}

		public int getDestV()
		{
			return destV;
		}

		public void setDestV(int destV)
		{
			this.destV = destV;
		}

		public double getDistance()
		{
			return distance;
		}

		public void setDistance(double distance)
		{
			this.distance = distance;
		}
	}
	
	public static class PriorityQ
	{
		private int SIZE;
		private Edge[] queueArray;
		private int size;
		
		public PriorityQ(int size)
		{
			this.SIZE = size;
			queueArray = new Edge[ SIZE ];
			this.size = 0;
		}
		
		public void insert(Edge edge)
		{
			int j = 0;
			
			for( ; j < size; j++ )
			{
				if ( edge.distance >= queueArray[j].distance )
				{
					break;
				}
			}
			
			for( int k = size-1; k >= j; k-- )
			{
				queueArray[k+1] = queueArray[k];
			}
			
			queueArray[j] = edge;
			
			size++;
		}
		
		public Edge removeMin()
		{
			return queueArray[--size];
		}
		
		public void removeN(int n)
		{
			for( int j = n; j < size - 1; j++ )
			{
				queueArray[j] = queueArray[j+1];
			}
			
			size--;
		}
		
		public Edge peekMin()
		{
			return queueArray[size - 1];
		}
		
		public int size()
		{
			return size;
		}
		
		public boolean isEmpty()
		{
			return size == 0;
		}
		
		public Edge peekN(int n)
		{
			return queueArray[n];
		}
		
		public int find(int index)
		{
			for( int j = 0; j < size; j++ )
			{
				if ( queueArray[j].getDestV() == index)
				{
					return j;
				}
			}
			
			return -1;
		}
	}
	public static double[][] buildAdjMatrix(Vertex [] vertices)
	{
		double[][] adjMatrix = new double[vertices.length][vertices.length];
		
		for( int i = 0; i < vertices.length; i++ )
		{			
			Vertex A = vertices[i];
			
			for( int j = 0; j < vertices.length; j++ )
			{			
				Vertex B = vertices[j];
				
				double distance = Math.sqrt( ( Math.pow( ( B.getX() - A.getX() ), 2 ) +
						                       Math.pow( ( B.getY() - A.getY() ), 2 ) ) );
				
				adjMatrix[i][j] = distance;
				adjMatrix[j][i] = distance;
			}
		}
		
		return adjMatrix;
	}
	
	public static class Graph
	{
		private Vertex [] vertexList;
		private double[][] adjMat;
		private int currentVert;
		private PriorityQ priorityQ;
		private int nVerts;
		private int nTree;
		
		public Graph(String line)
		{
			String [] strings = line.split("\\s+");
			vertexList = new Vertex[ strings.length ];
			nTree = 0;
			nVerts = vertexList.length;
			
			for( int i = 0; i < vertexList.length; i++ )
			{
				vertexList[i] = new Vertex(strings[i].split(","));
			}
			
			adjMat = buildAdjMatrix(vertexList);
			
			priorityQ = new PriorityQ(vertexList.length);
			
		}

		public Vertex[] getVertexList()
		{
			return vertexList;
		}

		public void setVertexList(Vertex[] vertexList)
		{
			this.vertexList = vertexList;
		}

		public double[][] getAdjMat()
		{
			return adjMat;
		}

		public void setAdjMat(double[][] adjMat)
		{
			this.adjMat = adjMat;
		}

		public int getCurrentVert()
		{
			return currentVert;
		}

		public void setCurrentVert(int currentVert)
		{
			this.currentVert = currentVert;
		}
		
		public void putInPQ(int newVert, double newDist)
		{
			int queueIndex = priorityQ.find(newVert);
			
			if ( queueIndex != -1 )
			{
				Edge tempEdge = priorityQ.peekN(queueIndex);
				double oldDist = tempEdge.distance;
				
				if ( oldDist > newDist)
				{
					priorityQ.removeN(queueIndex);;
					Edge edge = new Edge( currentVert, newVert, newDist);
					priorityQ.insert(edge);;
				}
			}
			else
			{
				Edge edge = new Edge(currentVert, newVert, newDist);
				priorityQ.insert(edge);
			}
		}
		
		public int mstw()
		{
			currentVert = 0;
			double ret = 0;
			
			while( nTree < nVerts - 1 )
			{
				vertexList[currentVert].setInTree(true);
				nTree++;
				
				for( int j = 0; j < nVerts; j++ )
				{
					if ( j == currentVert || vertexList[j].isInTree() )
					{
						continue;
					}
					
					double distance = adjMat[currentVert][j];
					
					putInPQ(j, distance);
				}
				
				Edge edge = priorityQ.removeMin();
				int sourceVert = edge.getSrcV();
				currentVert = edge.getDestV();
				
				ret += adjMat[sourceVert][currentVert];
			}
			
			return (int) Math.ceil(ret);
		}
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
					if ( ! line.equals("") )
					{
						Graph graph = new Graph(line);
						
						System.out.println(graph.mstw());
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
