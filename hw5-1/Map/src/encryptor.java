import java.util.Map;
import java.util.Iterator;

public class encryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text;
	private String cipher_text = "";

	public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		preprocessor p = new preprocessor(text);
		p.preprocess();
		plain_text = p.get_preprocessed_string(); // managed text
		this.map = _map;
		preprocessor p1 = new preprocessor(_key);
		p1.preprocess();
		this.key = p1.get_preprocessed_string();
	}

	public void encrypt() {
		// do not edit this method
		generate_keystream();
		generate_cipher_text();
	}

	private void generate_keystream() {
		if (plain_text.length() == 0 || key.length() == 0) { /* size control */
			keystream = "";
		} else if (plain_text.length() == key.length()) { /* equal size */
			keystream = key;
		} else if (plain_text.length() > key.length()) { /* text's size greater than key */
			char[] arr = key.toCharArray();
			for (int i = 0; i < plain_text.length(); i++) {
				keystream += arr[i % key.length()]; /* write key's character again and again with mod */
			}
		} else if (plain_text.length() < key.length()) { /* key's size greater than text */
			keystream = key.substring(0, plain_text.length()); /* take substring from key */
		}
	}

	private void generate_cipher_text() {
		/* String's arrays with toSChar Array */
		char[] keyArr = keystream.toCharArray();
		char[] plaintTextArr = plain_text.toCharArray();
		char[] cipherArr = new char[keyArr.length];

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
					if (plaintTextArr[i] == k) { /* text's index element if equal with alphabet element */
						cipherArr[i] = c; /* assign value to cipher */
						break;
					}

				}
			}
		}
		for (int i = 0; i < cipherArr.length; i++) { /* turn array to string */
			cipher_text += cipherArr[i];
		}
	}

	public String get_keystream() { /* getter keystream */
		return keystream;
	}

	public String get_cipher_text() { /* getter cipher text */
		return cipher_text;
	}
}
