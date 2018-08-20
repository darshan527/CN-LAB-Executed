import java.util.*;

public class BellmanFord {

	private int dist[];
	private int num_ver;
	public static final int MAX = 99;
	int n = 0;

	public BellmanFord(int n) {
		this.n = n;
		dist = new int[n + 1];
	}

	public void eval(int s, int mat[][]) {
		for (int i = 1; i <= n; i++) {
			dist[i] = MAX;
		}
		dist[s] = 0;
		for (int k = 1; k <= n - 1; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (mat[i][j] != MAX) {
						if (dist[j] > dist[i] + mat[i][j])
							dist[j] = dist[i] + mat[i][j];
					}
				}
			}
		}
		for (int i = 1; i <= n; i++) {
			System.out.println("" + i + "\t" + i + " is " + dist[i]);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int s, n = 0;
		System.out.println("Enter number of vertices");
		n = sc.nextInt();
		int[][] adj = new int[n + 1][n + 1];

		System.out.println("Enter Weight matrix");
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				adj[i][j] = sc.nextInt();
				if(i==j) {
					adj[i][j] =0;
					continue;
				}
				if(adj[i][j]==0) {
					adj[i][j]=99;
				}
			}
		}
		System.out.println("Enter source vertex");
		s = sc.nextInt();
		BellmanFord bell = new BellmanFord(n);
		bell.eval(s, adj);
	}

}
