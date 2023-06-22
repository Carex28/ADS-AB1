package Abgabe2;

import java.io.*;
import java.lang.reflect.Array;

public class LanguageDetector {


    HashMap<HashMap<Integer>> languages;

    // n = the length of n-grams to use
    int n;

    // N = the size of the language-specific hash tables ("Tabelle 2")
    int N;

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
            Entry entry;
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

        public LanguageDetector(int n, int N) {
            this.n = n;
            this.N = N;
            languages = new HashMap<HashMap<Integer>>(N, 31);
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
                ngrams[index++] = text.substring(first++, second++);    // inkrementiere um 1, bis array befüllt ist
            }
            return ngrams;
        }

        public void learnLanguage(String language, String text) {
            if (languages.get(language) == null) {                      // Wenn Sprache noch nicht in der Map
                languages.add(language, new HashMap<>(N, 31));     // Füge neue Map für die Sprache hinzu
            }

            String[] ngrams = getNGrams(text);                          // Holt die n-grams

            for (int i = 0; i < ngrams.length; i++) {                   // Zähl jeden ngram
                if (languages.get(language).get(ngrams[i]) == null) {   // Wenn ngram noch nicht angelegt
                    languages.get(language).add(ngrams[i], 1);          // Leg an und zähl auf 1
                } else {                                                // Wenn ngram schon angelegt, nimm alten wert und zähle eins hoch
                    languages.get(language).add(ngrams[i], languages.get(language).get(ngrams[i]) + 1);
                }
            }
        }

        public int getCount(String ngram, String language) {
            var map = languages.get(language);
            if (map == null || map.get(ngram) == null) {
                return 0;
            }
            return map.get(ngram);                                          // Wie oft das n-gram in der Sprache vorkam
        }

    public HashMap<Integer> apply(String text) {
        String[] ngrams = getNGrams(text);
        HashMap<Integer> result = new HashMap<>(N, 31);

        for (String ngram : ngrams) {
            int maxCount = 0;
            String maxLanguage = null;

            for (HashMap<HashMap<Integer>>.Entry languageMap : languages.table) {
                if (languageMap != null) {
                    Integer count = languageMap.value.get(ngram);
                    if (count != null && count > maxCount) {
                        maxCount = count;
                        maxLanguage = languageMap.key;
                    }
                }
            }

            if (maxLanguage != null) {
                result.add(maxLanguage, maxCount);
            }
        }

        return result;
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
