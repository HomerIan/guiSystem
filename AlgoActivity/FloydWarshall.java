package AlgoActivity;

import java.util.Arrays;

import javax.swing.JOptionPane;

public class FloydWarshall {

	static final int INF = 99999;
	
	public static void floydwarshall(int[][] graph) {
		
		int[][] temp = new int[graph.length][graph.length];
		int i,j,k;
		
		for(i = 0;i<graph.length;i++) {
			for(j = 0;j<graph[i].length;j++) {
				temp[i][j] = graph[i][j];
				
			}
		}//end loop
		
		for(k = 0;k<temp.length;k++) {
			for(i = 0;i<temp.length;i++) {
				for(j = 0;j<temp.length;j++) {
					
					if(temp[i][j] > temp[i][k] + temp[k][j]) {
						temp[i][j] = temp[i][k] + temp[k][j];
					}//end if state.
				}
			}
			print(temp);
			System.out.println();
		}//end loop
	}
	
	public static void print(int[][] dist) {
		
		for(int i = 0;i<dist.length;i++) {
			for(int j = 0;j<dist[i].length;j++) {
				if(dist[i][j] == INF) {
					System.out.print("INF ");
				}else {
					System.out.print(dist[i][j]+ " ");
				}
			}
			System.out.println();
		}//end loop
	}
	
	public static void main(String[] args) {
		
		
		
		int[][] graph  = {{0,INF,3,INF},
						  {2,0,INF,INF},
						  {INF,7,0,1},
						  {6,INF,INF,0}};
		
		
		
		floydwarshall(graph);
		
	}

}
