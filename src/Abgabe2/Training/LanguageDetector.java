package Abgabe2.Training;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.LinkedList;


public class LanguageDetector {


    public static class HashMap<T> {

	// FIXME: copy solution from Exercise 1 here.
		
    }	

    // n = the length of n-grams to use
    int n;

    // N = the size of the language-specific hash tables ("Tabelle 2")
    int N; 
	
    public LanguageDetector(int n, int N) {
	this.n = n;
	this.N = N;
	// FIXME
    }
	
    public void learnLanguage(String language, String text) {
	// FIXME
    }

    public int getCount(String ngram, String language) {
	// FIXME
        return -1;
    }
	
	
    public static void main(String[] args) {
    }
	
}
