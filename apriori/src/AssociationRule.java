import java.util.HashSet;

public class AssociationRule {
	HashSet<String> key;
	HashSet<String> value;
	float confidence;

	public AssociationRule(HashSet<String> key, HashSet<String> value,
			float confidence) {
		super();
		this.key = key;
		this.value = value;
		this.confidence = confidence;
	}

	public HashSet<String> getKey() {
		return key;
	}

	public HashSet<String> getValue() {
		return value;
	}

	public float getConfidence() {
		return confidence;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return key + " -> " + value + " = " + confidence;
	}
}
