package graph;

import java.util.ArrayList;

public class Dijkstra {
	
	public static int[] dijkstra(WeightedGraph g, int s) {
		final int[] dist = new int[g.size()];
		final int[] pred = new int[g.size()];
		final boolean[] visited = new boolean[g.size()];
		
		for(int i=0; i<dist.length; ++i)
			dist[i] = Integer.MAX_VALUE;
		
		dist[s] = 0;
		
		for(int i=0; i<dist.length; ++i) {
			final int next = minVertex(dist, visited);
			visited[next] = true;
			
			final int[] neigh = g.neighbors(next);
			for(int j=0; j<neigh.length; ++j) {
				final int v = neigh[j];
				final int d = dist[next] + g.getWeight(next, v);
				if(d < dist[v]) {
					dist[v] = d;
					pred[v] = next;
				}
			}
		}
		
		return pred;
		
	}

	private static int minVertex(int[] dist, boolean[] visited) {
		int x = Integer.MAX_VALUE;
		int y =-1;
		for(int i=0; i<dist.length; ++i) {
			if(!visited[i] && dist[i] < x) {
				y = i;
				x = dist[i];
			}
		}
		return y;
	}
	
	public static void printPath (WeightedGraph g, int[] pred, int sourceVertex, int targetVertex) {
		final ArrayList<Object> path = new ArrayList<Object>();
		int x = targetVertex;
		while (x != sourceVertex) {
			path.add(0,g.getLabel(x));
			x = pred[x];
		}
		path.add(0,g.getLabel(sourceVertex));
		for(Object s: path)
			System.out.print(s+" ");
	}

}
