public class Node implements Comparable<Node> {
	private float benefit, weight;
	private float upperBound;
	private int level;

	public Node(int level, float benefit, float weight, float upperBound) {
		this.level = level;
		this.benefit = benefit;
		this.weight = weight;
		this.upperBound = upperBound;
	}

	public Node(Node node) {
		this.level = node.level;
		this.benefit = node.benefit;
		this.weight = node.weight;
		this.upperBound = node.upperBound;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return (int) (o.upperBound - upperBound);

	}

	Node getNextNode(boolean taking, Data d[], float capacity,
			int n) {
		float benefit = 0, weight = 0, upperBound;
		int level;
		level = this.level + 1;
		if (taking) {
			benefit = d[level].getB() + this.benefit;
			weight = d[level].getW() + this.weight;
			upperBound = benefit;
			float W = weight;
			for (int i = level + 1; i < n; i++) {
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
		} else {
			benefit = this.benefit;
			weight = this.weight;
			upperBound = benefit;
			float W = weight;
			for (int i = level + 1; i < n; i++) {
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
		}
		return new Node(level, benefit, weight, upperBound);
	}

	public float getBenefit() {
		return benefit;
	}

	public float getWeight() {
		return weight;
	}

	public float getUpperBound() {
		return upperBound;
	}

	public int getLevel() {
		return level;
	}

	@Override
	public String toString() {
		return "Node [level=" + level + ", benefit=" + benefit + ", weight="
				+ weight + ", upperBound=" + upperBound + "]";
	}

}
