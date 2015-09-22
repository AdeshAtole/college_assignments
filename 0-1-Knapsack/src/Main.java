import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		// Input 1
		// float b[] = { 45, 30, 45, 10 }, w[] = { 3, 5, 9, 5 }, n = 4, capacity
		// = 16;
		// Input 2
		float b[] = { 40, 30, 50, 10 }, w[] = { 2, 5, 10, 5 }, n = 4, capacity = 16;

		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		// float capacity = 16;
		float upperBound = 0, W = 0;

		for (int i = 0; i < n; i++) {
			upperBound += b[i];
			W += w[i];
			if (W > capacity) {
				W -= w[i];
				upperBound -= b[i];
				// System.out.println(((capacity-W)/w[i]));
				upperBound += ((capacity - W) / w[i]) * b[i];
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
			if (temp.level == n - 1) {
				System.out.println(temp.getBenefit());
				break;
			} else {
				Node child = temp.getNextNode(true, b, w, capacity, 4);
				if (child.weight <= capacity) {
					pq.offer(new Node(child));
				}
				child = temp.getNextNode(false, b, w, capacity, 4);
				if (child.weight <= capacity) {
					pq.offer(new Node(child));
				}
			}
		}
	}

}
