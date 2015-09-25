import java.util.ArrayList;
import java.util.HashSet;

public class Apriori {

	ArrayList<ItemSet> data;

	public Apriori(ArrayList<ItemSet> data) {
		super();
		this.data = data;
	}

	public Apriori() {
		// TODO Auto-generated constructor stub
		data = new ArrayList<ItemSet>();
	}

	public void add(ItemSet is) {
		data.add(is);
	}

	private int getSupport(HashSet<String> set) {
		int count = 0;
		for (ItemSet dataSet : data) {
			if (dataSet.getSet().containsAll(set)) {
				count++;
			}
		}
		return count;
	}

	private HashSet<HashSet<String>> getNextSubsets(
			HashSet<HashSet<String>> ts, int minimumSupport) {
		ArrayList<HashSet<String>> sets = new ArrayList<HashSet<String>>(ts);
		HashSet<HashSet<String>> ret = new HashSet<HashSet<String>>();
		for (int i = 0; i < sets.size(); i++) {
			for (int j = i + 1; j < sets.size(); j++) {
				HashSet<String> s1 = new HashSet<String>(sets.get(i));
				HashSet<String> s2 = new HashSet<String>(sets.get(j));
				s1.removeAll(sets.get(j));
				s2.removeAll(sets.get(i));
				if (s1.size() == 1 && s2.size() == 1) {
					HashSet<String> temp = new HashSet<String>(sets.get(i));
					temp.addAll(sets.get(j));
					if (getSupport(temp) >= minimumSupport)
						ret.add(temp);
				}

			}
		}

		return ret;
	}

	public HashSet<HashSet<String>> getResult(int minimumSupport) {
		HashSet<HashSet<String>> ret;
		HashSet<HashSet<String>> initialSet = new HashSet<HashSet<String>>();

		for (ItemSet is : data) {
			for (String s : is.getSet()) {
				HashSet<String> temp = new HashSet<String>();
				temp.add(s);
				if (getSupport(temp) >= minimumSupport)
					initialSet.add(temp);
			}
		}

		// System.out.println(initialSet);

		ret = initialSet;
		while (true) {
			boolean allEqual = true;
			int prev = getSupport(ret.iterator().next());
			for (HashSet<String> hs : ret) {
				if (prev != getSupport(hs)) {
					allEqual = false;
					break;
				}
				prev = getSupport(hs);
			}
			if (allEqual) {
				break;
			}
//			System.out.println(ret);
			ret = getNextSubsets(ret, minimumSupport);
		}

		// Collections.
		// return null;
		return ret;
	}

	public static void main(String[] args) {

		Apriori apriori = new Apriori();

		// apriori.add(new ItemSet(new String[] { "M", "O", "N", "K", "E", "Y"
		// }));
		// apriori.add(new ItemSet(new String[] { "D", "O", "N", "K", "E", "Y"
		// }));
		// apriori.add(new ItemSet(new String[] { "M", "A", "K", "E" }));
		// apriori.add(new ItemSet(new String[] { "M", "U", "C", "K", "Y" }));
		// apriori.add(new ItemSet(new String[] { "C", "O", "O", "K", "I", "E"
		// }));

		// apriori.add(new ItemSet(new String[]{"1","2","5"}));
		// apriori.add(new ItemSet(new String[]{"2","4"}));
		// apriori.add(new ItemSet(new String[]{"2","3"}));
		// apriori.add(new ItemSet(new String[]{"1","2","4"}));
		// apriori.add(new ItemSet(new String[]{"1","3"}));
		// apriori.add(new ItemSet(new String[]{"2","3"}));
		// apriori.add(new ItemSet(new String[]{"1","3"}));
		// apriori.add(new ItemSet(new String[]{"1","2","3","5"}));
		// apriori.add(new ItemSet(new String[]{"1","2","3"}));

		apriori.add(new ItemSet(new String[] { "Mango", "Onion", "Jar",
				"Keychain", "Eggs", "Chocolate" }));
		apriori.add(new ItemSet(new String[] { "Nuts", "Onion", "Jar",
				"Keychain", "Eggs", "Chocolate" }));
		apriori.add(new ItemSet(new String[] { "Mango", "Apple", "Keychain",
				"Eggs", "-", "-" }));
		apriori.add(new ItemSet(new String[] { "Mango", "Toothbrush", "Corn",
				"Keychain", "Chocolate", "-" }));
		apriori.add(new ItemSet(new String[] { "Corn", "Onion", "Onion",
				"Keychain", "Knife", "Eggs" }));

		for (HashSet<String> hs : apriori.getResult(3)) {
			for (String s : hs) {
				System.out.print(s + " ");
			}
			System.out.println();
		}

	}
}
