package Abgabe2.Training_A2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.LinkedList;

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
                if (entry.key == key || entry.key.equals(key)) { // Wenn key übereinstimmt, return value
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
                        || table[hashCode].key == key || table[hashCode].key.equals(key)) { // Oder Key gibt es schon
                                                                                            // (überschreibe Key)
                    count++;
                    table[hashCode] = new Entry(key, value);
                    return true;
                }
            }
            return false;
        }
    }

    // n = the length of n-grams to use
    int n;

    // N = the size of the language-specific hash tables ("Tabelle 2")
    int N;

    // hashmap for learning the languages
    HashMap<HashMap<Integer>> languages;

    public LanguageDetector(int n, int N) {
        this.n = n;
        this.N = N;
        languages = new HashMap<>(N, 31);
    }

    // Hilfsfunktion
    private String[] getNGrams(String text) {
        String[] ngrams = new String[text.length() - (this.n - 1)]; // Länge des Textes - (länge n-grams - 1), da der
                                                                    // Index first n stellen vor dem ende aufhört, minus
                                                                    // 1 weil arrays mit 0 anfangen
        int first = 0;
        int second = this.n;
        int index = 0;
        while (index < ngrams.length) {
            ngrams[index++] = text.substring(first++, second++); // inkrementiere um 1, bis array befüllt ist
        }
        return ngrams;
    }

    public void learnLanguage(String language, String text) {
        if (languages.get(language) == null) { // Wenn Sprache noch nicht in der Map
            languages.add(language, new HashMap<>(N, 31)); // Füge neue Map für die Sprache hinzu
        }

        String[] ngrams = getNGrams(text); // Holt die n-grams

        for (int i = 0; i < ngrams.length; i++) { // Zähl jeden ngram
            if (languages.get(language).get(ngrams[i]) == null) { // Wenn ngram noch nicht angelegt
                languages.get(language).add(ngrams[i], 1); // Leg an und zähl auf 1
            } else { // Wenn ngram schon angelegt, nimm alten wert und zähle eins hoch
                languages.get(language).add(ngrams[i], languages.get(language).get(ngrams[i]) + 1);
            }
        }
    }

    public int getCount(String ngram, String language) {
        var map = languages.get(language);
        if (map == null || map.get(ngram) == null) {
            return 0;
        }
        return map.get(ngram); // Wie oft das n-gram in der Sprache vorkam
    }

    public static void main(String[] args) {
    }

}
