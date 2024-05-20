
public class preprocessor {
	private String initial_string;
	private String preprocessed_string;

	public preprocessor(String str) {
		this.initial_string = str;
		// preprocess();
	}

	public void preprocess() {
		// do not edit this method
		capitalize();
		clean();
	}

	private void capitalize() {
		initial_string = initial_string.toUpperCase();
		String str = "";
		char[] chars = initial_string.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i]; /* check for characters that not english character */
			if (c == 'Ş' || c == 'Ö' || c == 'Ü' || c == 'Ğ' || c == 'Ç') {

			} else if (c == 'İ')
				str += 'I';
			else {
				str += chars[i];
			}
		}
		initial_string = str;
		// preprocessed_string = str;

	}

	private void clean() {
		/*
		 * 65 -A
		 * 90 -Z
		 */
		char[] arr = initial_string.toCharArray(); /* turn into array */
		String str = "";
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] <= 'Z' && arr[i] >= 'A') { /* if upper english character */
				str += arr[i]; /* add to string */
			}
		}
		preprocessed_string = str;

	}

	public String get_preprocessed_string() {
		return this.preprocessed_string;
	}
}