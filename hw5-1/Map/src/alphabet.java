import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class alphabet {
	private Set<Character> english_alphabet = new LinkedHashSet<Character>();
	private Map<Character, Map<Character, Character>> map = new HashMap<Character, Map<Character, Character>>();

	public alphabet() {
		// do not edit this method
		fill_english_alphabet();
		fill_map();
	}

	private void fill_english_alphabet() {
		// do not edit this method
		for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
			english_alphabet.add(c);
		}
	}

	private void fill_map() {
		// You must use the "english_alphabet" variable in this method, to fill the
		// "map" variable.
		// You can define 1 or 2 iterators to iterate through the set items.
		Iterator<Character> iterator_key = english_alphabet.iterator();

		int counter = 0;
		while (iterator_key.hasNext()) {
			Character key = iterator_key.next();
			map.put(key, new HashMap<>());

			Iterator<Character> iterator_element = english_alphabet.iterator();
			Iterator<Character> iterator_k = english_alphabet.iterator();

			for (int i = 0; i < counter; i++) {
				iterator_element.next();
			}

			while (iterator_element.hasNext()) {
				Character element = iterator_element.next();
				map.get(key).put(iterator_k.next(), element);
				// su an bütün satırlar a dan başlayıp z de bitiyo, kaydırmadım

			}
			iterator_element = english_alphabet.iterator();
			while (map.get(key).size() < english_alphabet.size()) {
				Character element = iterator_element.next();
				map.get(key).put(iterator_k.next(), element);
			}
			counter++;
		}
	}

	public void print_map() {
		// do not edit this method
		System.out.println("*** Viegenere Cipher ***\n\n");
		System.out.println("    " + english_alphabet);
		System.out.print("    ------------------------------------------------------------------------------");
		for (Character k : map.keySet()) {
			System.out.print("\n" + k + " | ");
			System.out.print(map.get(k).values());
		}
		System.out.println("\n");

	}

	public Map<Character, Map<Character, Character>> get_map() {
		return map;
	}
}