package Abgabe2.Anwendung_A3;

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

        Abgabe2.LanguageDetector.HashMap.Entry[] table;
        int basis;
        // capacity = N
        int capacity;
        // number of Entries
        int count;


        public HashMap(int N, int basis) {
            table = (Abgabe2.LanguageDetector.HashMap.Entry[]) Array.newInstance(Abgabe2.LanguageDetector.HashMap.Entry[].class.getComponentType(), N);
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
                if (table[result] == null) {                        // Wenn platz noch nicht besetzt, return result
                    return result;
                }
            }
            return -1;                                                // Wenn kein Platz frei return -1
        }

        private int sondierung(int i, String s, int result) {        // kollision behandeln
            if (i == 0) {                                            // g(0) = h(s)
                return hash(s);
            } else {                                                // g(m) =( g(m) + 2 * m + 1) % N
                return (result + 2 * (i - 1) + 1) % capacity;        //neuen Platz suchen
            }
        }

        private int hash(String s) {                                // hash pos bestimmen
            char[] cs = s.toCharArray();                            // String zu CharArray
            double result = 0;
            for (int i = 0; i < cs.length; i++) {                    // Funktion in Aufgabenstellung angewandt
                result = ((result * basis + cs[i]) % capacity);        //ASCII wert des strings
            }
            return (int) result;
        }

        public T get(String key) {
            Abgabe2.LanguageDetector.HashMap.Entry entry;
            int hashCode = 0;                                        //position
            for (int i = 0; i < capacity; i++) {
                hashCode = sondierung(i, key, hashCode);            //position errechnen
                entry = table[hashCode];
                if (entry == null) {                                // Wenn key nicht existent, return null
                    return null;
                }
                if (entry.key == key || entry.key.equals(key)) {    // Wenn key übereinstimmt, return value
                    return entry.value;
                }                                                    // sonst continue loop mit neuem hashCode
            }
            return null;                                            // Key nicht gefunden, return null
        }

        public boolean add(String key, T value) {
            int hashCode = 0;                                        //position
            for (int i = 0; i < capacity; i++) {
                hashCode = sondierung(i, key, hashCode);            //position errechnen
                if (hashCode < 0) {                                // Wenn sondierung -1 -> kein Platz in HashMap, return false
                    return false;
                }
                if (table[hashCode] == null                        // Entweder Key gibt es noch nicht
                        || table[hashCode].key == key                // Oder Key gibt es schon
                        || table[hashCode].key.equals(key)) {
                    if (table[hashCode] == null) {
                        count++;                                    // größe der hashmap
                    }
                    table[hashCode] = new Entry(key, value);        // (überschreibe Key)
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

    public LanguageDetector(int n, int N) {
        this.n = n;
        this.N = N;
        // FIXME: copy solution from Exercise 2 here.
    }

    public void learnLanguage(String language, String text) {
        // FIXME: copy solution from Exercise 2 here.
    }

    public int getCount(String ngram, String language) {
        // FIXME: copy solution from Exercise 2 here.
        return -1;
    }

    public HashMap<Integer> apply(String text) {
        // FIXME
        return null;
    }

    public static void main(String[] args) {
    }

}
