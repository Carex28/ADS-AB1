package Abgabe2.HashTabelle;

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

	public HashMap(int N, int basis) {
	    table = (Entry[]) Array.newInstance(Entry[].class.getComponentType(), N);
	    this.basis = basis;
	}

	public double fillRatio() {
	    // FIXME: implement
	}

	public int hashCode(String s) {
	    // FIXME: implement
	}
		
	public T get(String key) {
	    // FIXME: implement
	}

	public boolean add(String key, T value) {
	    // FIXME: implement
	}
		
    }	

	
    public static void main(String[] args) {
    }
	
}
