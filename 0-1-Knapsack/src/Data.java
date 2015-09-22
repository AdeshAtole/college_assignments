public class Data implements Comparable<Data> {
	private float b, w;

	public Data(float b, float w) {
		super();
		this.b = b;
		this.w = w;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	@Override
	public int compareTo(Data o) {
		// TODO Auto-generated method stub
		float f = ((o.getB() / o.getW()) - (getB() / getW()));
		if (f > 0.0f) {
			return 1;
		} else if (f < 0.0f) {
			return -1;
		}
		return 0;

	}

	@Override
	public String toString() {
		return "Data [b=" + b + ", w=" + w + "]";
	}

}
