package mfk.util;

import java.util.Random;

/**
 * Утилитный класс
 * 
 * @author kkonyshev
 *
 */
public class Utils {
	
	protected Utils() {
	}

	public static Long randLong(int min, int max) {
	    Random rand = new Random();
	    return new Long(rand.nextInt((max - min) + 1) + min);
	}
	
	public static Integer randInteger(int min, int max) {
	    Random rand = new Random();
	    return rand.nextInt((max - min) + 1) + min;
	}
}
