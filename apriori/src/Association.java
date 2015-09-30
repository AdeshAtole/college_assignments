import java.util.HashSet;

public class Association extends HashSet<String> {
	// HashSet<String> value;
	float confidence;

	// public Association(HashSet<String> value, float confidence) {
	// super();
	// this.value = value;
	// this.confidence = confidence;
	// }

	// public HashSet<String> getValue() {
	// return value;
	// }

	public float getConfidence() {
		return confidence;
	}

	public void setConfidence(float confidence) {
		this.confidence = confidence;
	}

}
