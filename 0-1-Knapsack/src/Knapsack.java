// 0/1 Knapsack with branch and bound

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Knapsack {

	public float branchAndBound(Data d[], int n, float capacity) {

		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		// float capacity = 16;
		float upperBound = 0, W = 0;

		for (int i = 0; i < n; i++) {
			upperBound += d[i].getB();
			W += d[i].getW();
			if (W > capacity) {
				W -= d[i].getW();
				upperBound -= d[i].getB();
				// System.out.println(((capacity-W)/w[i]));
				upperBound += ((capacity - W) / d[i].getW()) * d[i].getB();
				break;
			}
		}
		// System.out.println(upperBound);

		Node node = new Node(-1, 0, 0, upperBound);

		// System.out.println(node.getNextNode(true, b, w, capacity,
		// n).getNextNode(true, b, w, capacity, upperBound));
		pq.offer(node);
		while (true) {
			Node temp = pq.poll();
			if (temp.getLevel() == n - 1) {
				// System.out.println(temp.getBenefit());
				return temp.getBenefit();
				// break;
			} else {
				Node child = temp.getNextNode(true, d, capacity, 4);
				if (child.getWeight() <= capacity) {
					pq.offer(new Node(child));
				}
				child = temp.getNextNode(false, d, capacity, 4);
				if (child.getWeight() <= capacity) {
					pq.offer(new Node(child));
				}
			}
		}
	}

	public static void main(String[] args) {
		// Inputs must be in decreasing order of b[i]/w[i]
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		try {
			System.out.print("Enter N : ");
			int n = Integer.parseInt(reader.readLine());
			System.out.print("Enter capacity : ");
			int capacity = Integer.parseInt(reader.readLine());

			Data d[] = new Data[n];

			for (int i = 0; i < n; i++) {
				System.out.print("Enter benefit for item : " + (i + 1));
				int b = Integer.parseInt(reader.readLine());
				System.out.print("Enter benefit for item : " + (i + 1));
				int w = Integer.parseInt(reader.readLine());
				d[i] = new Data(b, w);
			}

			Arrays.sort(d);

			// Input 1
			// int n = 4;
			// float b[] = { 45, 30, 45, 10 }, w[] = { 3, 5, 9, 5 }, capacity =
			// 16;
			// Input 2

			// int n = 4;
			// float b[] = { 40, 30, 50, 10 }, w[] = { 2, 5, 10, 5 }, capacity =
			// 16;

			// Data d[] = new Data[n];
			//
			// for (int i = 0; i < n; i++) {
			// d[i] = new Data(b[i], w[i]);
			// }

			System.out.println(new Knapsack().branchAndBound(d, (int) n,
					capacity));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
