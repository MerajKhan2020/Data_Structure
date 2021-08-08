package graph;

public class WeightedGraph {

	private int[ ][ ] edges;
	private Object[ ] labels;

	public WeightedGraph(int n)
	{
		edges = new int[n][n];  // All values initially false
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) 
				edges[i][j]= Integer.MAX_VALUE;

		labels = new Object[n];     // All values initially null
	}

	public void addEdge(int source, int target, int weight)   
	{
		edges[source][target] = weight;
	}

	public void setLabel(int vertex, Object newLabel)
	{
		labels[vertex] = newLabel;
	} 

	public Object getLabel(int vertex)
	{
		return labels[vertex];
	}

	public int getWeight(int source, int target)
	{
		return edges[source][target];
	}
	public int size( )
	{
		return labels.length;
	}

	public int[ ] neighbors(int vertex)
	{
		int i;
		int count;
		int[ ] answer;

		// First count how many edges have the vertex as their source
		count = 0;
		for (i = 0; i < labels.length; i++)
		{
			if (edges[vertex][i] < Integer.MAX_VALUE)
				count++;
		}

		// Allocate the array for the answer
		answer = new int[count];

		// Fill the array for the answer
		count = 0;
		for (i = 0; i < labels.length; i++)
		{
			if (edges[vertex][i] < Integer.MAX_VALUE)
				answer[count++] = i;
		}

		return answer;
	}

}
