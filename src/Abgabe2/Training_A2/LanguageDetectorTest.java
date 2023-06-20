package Abgabe2.Training_A2;

import static org.junit.Assert.*;
import org.junit.Test;

public class LanguageDetectorTest {

	double tolerance = 0.01;

	@Test
	public void testLearnLanguage1() {
		// 2-grams
		LanguageDetector ld = new LanguageDetector(2, 1001);
		ld.learnLanguage("ape", "banana banana");
		assertEquals(4, ld.getCount("an", "ape"));
		assertEquals(2, ld.getCount("ba", "ape"));
		assertEquals(1, ld.getCount("a ", "ape"));
		assertEquals(4, ld.getCount("na", "ape"));
		assertEquals(0, ld.getCount("bu", "ape"));
	}

	@Test
	public void testLearnLanguage2() {
		// 3-grams
		LanguageDetector ld = new LanguageDetector(3, 1001);
		ld.learnLanguage("ape", "banana banana ");
		assertEquals(4, ld.getCount("ana", "ape"));
		assertEquals(2, ld.getCount("ban", "ape"));
		assertEquals(2, ld.getCount("na ", "ape"));
		assertEquals(2, ld.getCount("nan", "ape"));
		assertEquals(0, ld.getCount("bu", "ape"));
	}

}
