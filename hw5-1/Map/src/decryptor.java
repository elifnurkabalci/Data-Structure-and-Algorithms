import java.util.Map;
import java.util.Iterator;

public class decryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text = "";
	private String cipher_text;

	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		this.map = _map;
		preprocessor p = new preprocessor(text);
		p.preprocess();
		cipher_text = p.get_preprocessed_string();

		preprocessor p1 = new preprocessor(_key);
		p1.preprocess();
		key = p1.get_preprocessed_string();
	}

	public void decrypt() {
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}

	private void generate_keystream() {
		if (cipher_text.length() == key.length()) { /* equal size */
			keystream = key;
		} else if (cipher_text.length() > key.length()) { /* text's size greater than key */
			char[] arr = key.toCharArray();
			for (int i = 0; i < cipher_text.length(); i++) {
				keystream += arr[i % key.length()]; /* write key's character again and again with mod */
			}
		} else if (cipher_text.length() < key.length()) { /* key's size greater than text */
			keystream = key.substring(0, cipher_text.length()); /* take substring from key */
		}
	}

	private void generate_plain_text() {
		// You must use map.get(x).keySet() with an iterator in this method

		/* String's arrays with toSChar Array */
		char[] keyArr = keystream.toCharArray();
		char[] cipherArr = cipher_text.toCharArray();
		char[] plaintTextArr = new char[keyArr.length];

		for (int i = 0; i < keyArr.length; i++) {
			/* this for work for keystream's every character */
			if (map.containsKey(keyArr[i])) { /* check before assign */
				Map<Character, Character> set = map.get(keyArr[i]); /* key values, alphabet */ /* row values */

				/* Iterators */
				Iterator<Character> iterator = set.values().iterator();
				Iterator<Character> alphabIterator = set.keySet().iterator();

				/* Search in alphabet and row in the same time */
				while (iterator.hasNext()) {
					Character c = iterator.next();
					Character k = alphabIterator.next();
					if (c == cipherArr[i]) { /* cipher index element if equal with row value */
						plaintTextArr[i] = k; /* assign col number, alphaber index */
					}
				}
			}
		}

		for (int i = 0; i < plaintTextArr.length; i++) {
			plain_text += plaintTextArr[i];
		}
	}

	public String get_keystream() {
		return keystream;
	}

	public String get_plain_text() {
		return plain_text;
	}
}
