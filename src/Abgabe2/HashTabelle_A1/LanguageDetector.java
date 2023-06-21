package Abgabe2.HashTabelle_A1;

import java.lang.reflect.Array;

public class LanguageDetector {

	public static class HashMap<T> {

		public class Entry {
			String key;
			T value;

			Entry(String key, T value) {
				this.key = key;
				this.value = value;
			}
		}

		Entry[] table;
		int basis;
		// capacity = N
		int capacity;
		// number of Entries
		int count;

		public HashMap(int N, int basis) {
			table = (Entry[]) Array.newInstance(Entry[].class.getComponentType(), N);
			this.basis = basis;
			this.capacity = N;
			this.count = 0;
		}

		public double fillRatio() {
			return (double) count / (double) capacity;
		}

		public int hashCode(String s) {
			int result = 0;
			for (int i = 0; i < capacity; i++) {
				result = sondierung(i, s, result);
				if (table[result] == null) { // Wenn platz noch nicht besetzt, return result
					return result;
				}
			}
			return -1; // Wenn kein Platz frei return -1
		}

		private int sondierung(int i, String s, int result) {
			if (i == 0) { // g(0) = h(s)
				return hash(s);
			} else { // g(m) =( g(m) + 2 * m + 1) % N
				return (result + 2 * (i - 1) + 1) % capacity;
			}
		}

		private int hash(String s) {
			char[] cs = s.toCharArray(); // String zu CharArray
			double result = 0;
			for (int i = 0; i < cs.length; i++) { // Funktion in Aufgabenstellung angewandt
				result = ((result * basis + cs[i]) % capacity);
			}
			return (int) result;
		}

		public T get(String key) {
			Entry entry;
			int hashCode = 0;
			for (int i = 0; i < capacity; i++) {
				hashCode = sondierung(i, key, hashCode);
				entry = table[hashCode];
				if (entry == null) { // Wenn key nicht existent, return null
					return null;
				}
				if (entry.key == key) { // Wenn key übereinstimmt, return value
					return entry.value;
				} // sonst continue loop mit neuem hashCode
			}
			return null; // Key nicht gefunden, return null
		}

		public boolean add(String key, T value) {
			int hashCode = 0;
			for (int i = 0; i < capacity; i++) {
				hashCode = sondierung(i, key, hashCode);
				if (hashCode < 0) { // Wenn sondierung -1 -> kein Platz in HashMap, return false
					return false;
				}
				if (table[hashCode] == null // Entweder Key gibt es noch nicht
						|| table[hashCode].key == key) { // Oder Key gibt es schon (überschreibe Key)
					count++;
					table[hashCode] = new Entry(key, value);
					return true;
				}
			}
			return false;
		}
	}

	public static void main(String[] args) {
	}

}
