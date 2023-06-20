package Abgabe2.Evaluation_A4;

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
		// FIXME: copy solution from Exercise 3 here.
		return null;
	}

	private static String read(String filename) {
		// !!! NO NEED TO CHANGE !!!
		// open a file, read its lines, return them as an array.
		try {

			StringBuilder sb = new StringBuilder();

			Reader in = new InputStreamReader(new FileInputStream(filename),
					"UTF-8");

			BufferedReader reader = new BufferedReader(in);

			String s;
			while ((s = reader.readLine()) != null) {
				// Ignoriere Leerzeilen und Kommentare
				if (s.length() != 0 && s.charAt(0) != '#') {
					sb.append(s);
				}
			}

			reader.close();

			return sb.toString();

		} catch (IOException e) {
			String msg = "I/O-Fehler bei " + filename + "\n" + e.getMessage();
			throw new RuntimeException(msg);
		}
	}

	public static LanguageDetector runTest(String BASE, int n, int N) {

		// FIXME: implement
		// 1. read "Alice in Wonderland" in 6 languages using read(), see above
		// 2. learn the 6 languages with language detector
		// 3. apply language detector to the below 57 sample sentences.
		// measure accuracy, fillratio, execution time.
		// 4. run for different n-gram lengths + table sizes, and report.

		String[] sentences = new String[] {

				// 11 x English
				"I'm going to make him an offer he can't refuse.",
				"Toto, I've got a feeling we're not in Kansas anymore.",
				"May the Force be with you.",
				"If you build it, he will come.",
				"I'll have what she's having.",
				"A martini. Shaken, not stirred.",
				"Some people can’t believe in themselves until someone else believes in them first.",
				"I feel the need - the need for speed!",
				"Carpe diem. Seize the day, boys. Make your lives extraordinary.",
				"Nobody puts Baby in a corner.",
				"I'm king of the world!",

				// 11 x Deutsch
				"Aber von jetzt an steht ihr alle in meinem Buch der coolen Leute.",
				"Wäre, wäre, Fahradkette",
				"Sehe ich aus wie jemand, der einen Plan hat?",
				"Erwartet mein Kommen, beim ersten Licht des fünften Tages.",
				"Du bist terminiert!",
				"Ich hab eine Wassermelone getragen.",
				"Einigen wir uns auf Unentschieden!",
				"Du wartest auf einen Zug, ein Zug der dich weit weg bringen wird.",
				"Ich bin doch nur ein Mädchen, das vor einem Jungen steht, und ihn bittet, es zu lieben.",
				"Ich genoss seine Leber mit ein paar Fava-Bohnen, dazu einen ausgezeichneten Chianti.",
				"Dumm ist der, der Dummes tut.",

				// 9 x Esperanto
				"Al du sinjoroj samtempe oni servi ne povas.",
				"Al la fiŝo ne instruu naĝarton.",
				"Fiŝo pli granda malgrandan englutas.",
				"Kia patrino, tia filino.",
				"La manĝota fiŝo estas ankoraŭ en la rivero.",
				"Ne kotas besto en sia nesto.",
				"Ne singardema kokino fidas je vulpo.",
				"Por sperto kaj lerno ne sufiĉas eterno.",
				"Unu hako kverkon ne faligas.",

				// 8 x Finnisch
				"Hei, hauska tavata.",
				"Olen kotoisin Suomesta.",
				"Yksi harrastuksistani on lukeminen.",
				"Nautin musiikin kuuntelusta.",
				"Juhannusperinteisiin kuuluu juhannussauna tuoreiden saunavihtojen kera, sekä pulahtaminen järveen.",
				"Aamu on iltaa viisaampi.",
				"Työ tekijäänsä neuvoo.",
				"Niin metsä vastaa, kuin sinne huudetaan.",

				// 9 x Französisch
				"Franchement, ma chère, c’est le cadet de mes soucis.",
				"À tes beaux yeux.",
				"Si j’aurais su, j’aurais pas v’nu!",
				"Merci la gueuse. Tu es un laideron mais tu es bien bonne.",
				"Vous croyez qu’ils oseraient venir ici?",
				"La barbe ne fait pas le philosophe.",
				"Inutile de discuter.",
				"Paris ne s’est pas fait en un jour!",
				"Quand on a pas ce que l’on aime, il faut aimer ce que l’on a.",

				// 9 x Italienisch
				"Azzurro, il pomeriggio è troppo azzurro e lungo per me",
				"Con te, cos lontano e diverso Con te, amico che credevo perso ",
				"è restare vicini come bambini la felicità",
				"Buongiorno, Principessa!",
				"Ho Ucciso Napoleone.",
				"L’amore vince sempre.",
				"La semplicità è l’ultima sofisticazione.",
				"Una cena senza vino e come un giorno senza sole.",
				"Se non hai mai pianto, i tuoi occhi non possono essere belli."
		};

		String[] labels = new String[] {

				"english", "english", "english", "english", "english", "english", "english", "english", "english",
				"english", "english",
				"german", "german", "german", "german", "german", "german", "german", "german", "german", "german",
				"german",
				"esperanto", "esperanto", "esperanto", "esperanto", "esperanto", "esperanto", "esperanto", "esperanto",
				"esperanto",
				"finnish", "finnish", "finnish", "finnish", "finnish", "finnish", "finnish", "finnish",
				"french", "french", "french", "french", "french", "french", "french", "french", "french",
				"italian", "italian", "italian", "italian", "italian", "italian", "italian", "italian", "italian"

		};

		assert sentences.length == labels.length;
		return null;
	}

	public static void main(String[] args) {
	}

}
