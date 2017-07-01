import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

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

	public HashSet<HashSet<String>> getFrequentSet(int minimumSupport) {
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
			// System.out.println(ret);
			ret = getNextSubsets(ret, minimumSupport);
		}

		// Collections.
		// return null;
		return ret;
	}

	private ArrayList<HashSet<String>> getAllSubsets(HashSet<String> set) {
		// ArrayList<te>
		ArrayList<HashSet<String>> ret = new ArrayList<HashSet<String>>();
		int lim = (int) Math.pow(2, set.size()) - 1;
		for (int i = 1; i < lim; i++) {
			int j = 1;
			Iterator<String> iterator = set.iterator();
			HashSet<String> tmp = new HashSet<String>();
			while (iterator.hasNext()) {
				String s = iterator.next();
				// System.out.println(j + " "+i);
				if ((j & i) == j) {
					tmp.add(s);
					// System.out.println("Added " + s + "tmp =  " + tmp);
				} else {
					// System.out.println("skipped " + s);
				}
				j <<= 1;
			}
			// System.out.println(tmp);
			ret.add(tmp);
		}

		return ret;
	}

	public HashMap<HashSet<String>, Association> getAssociationRules(
			HashSet<HashSet<String>> set, float minimumConfidence) {
		// ArrayList<AssociationRule> ret = new ArrayList<AssociationRule>();
		HashMap<HashSet<String>, Association> ret = new HashMap<HashSet<String>, Association>();
		// HashMap<HashSet<String>, HashSet<String>>();
		for (HashSet<String> hs : set) {
			for (HashSet<String> key : getAllSubsets(hs)) {
				for (HashSet<String> value : getAllSubsets(hs)) {
					HashSet<String> tValue, tKey;
					tValue = new HashSet<String>(value);
					tKey = new HashSet<String>(key);
					tValue.removeAll(key);
					tKey.removeAll(value);

					if (tValue.size() == value.size()
							&& tKey.size() == key.size()) {
						float numerator = 0, denominator = 0;
						for (ItemSet is : data) {
							if (is.getSet().containsAll(key)) {
								denominator++;
								if (is.getSet().containsAll(value)) {
									numerator++;
								}
							}
						}
						float confidence = (numerator / denominator) * 100;
						// System.out.println(minimumConfidence);
						if (confidence > minimumConfidence) {
							// if()
							// ret.add(new AssociationRule(key, value,
							// confidence));

							Association finalValue = new Association();
							finalValue.setConfidence(confidence);
							finalValue.addAll(value);
							try {
								finalValue.addAll(ret.get(key));
							} catch (NullPointerException e) {

							}
							ret.put(key, finalValue);
						}
					}
				}
			}
		}
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

		for (HashSet<String> hs : apriori.getFrequentSet(3)) {
			for (String s : hs) {
				System.out.print(s + " ");
			}
			System.out.println();
		}

		// System.out.println(apriori.getAllSubsets(apriori.getFrequentSet(3)
		// .iterator().next()));

		HashMap<HashSet<String>, Association> map = apriori
				.getAssociationRules(apriori.getFrequentSet(3), 60);

		// map.ke
		for (HashSet<String> set : map.keySet()) {
			System.out.println(set + " -> " + map.get(set) + " "
					+ map.get(set).getConfidence());
		}

	}
}
