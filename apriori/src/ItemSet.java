import java.util.HashSet;

public class ItemSet{
	HashSet<String> set;

	public ItemSet(HashSet<String> set) {
		super();
		this.set = set;
	}

	public ItemSet(String[] str) {
		// TODO Auto-generated constructor stub
		set = new HashSet<String>();
		for (String s : str) {
			set.add(s);
		}
	}

	public HashSet<String> getSet() {
		return set;
	}
	
	
}
