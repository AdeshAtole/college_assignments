public class Data implements Comparable<Data> {
	private float benefit, weight;

	public Data(float benefit, float weight) {
		super();
		this.benefit = benefit;
		this.weight = weight;
	}
	public float getBenefit() {
		return benefit;
	}

	public void setBenefit(float b) {
		this.benefit = b;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float w) {
		this.weight = w;
	}

	@Override
	public int compareTo(Data o) {
		// TODO Auto-generated method stub
		float f = ((o.getBenefit() / o.getWeight()) - (getBenefit() / getWeight()));
		if (f > 0.0f) {
			return 1;
		} else if (f < 0.0f) {
			return -1;
		}
		return 0;

	}

	@Override
	public String toString() {
		return "Data [b=" + benefit + ", w=" + weight + "]";
	}

}
