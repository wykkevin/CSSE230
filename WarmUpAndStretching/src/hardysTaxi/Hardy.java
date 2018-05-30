package hardysTaxi;

import static hardysTaxi.TaxicabNumber.cube;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the static method hardySolutionsLessThan(N) which finds all
 * "taxicab numbers" that are less than n.  These are represented as TaxicabNumber objects:
 * each includes the sum and two different ways of writing that sum as
 * a sum of two cubes.
 * 
 * @author anderson  November, 2010.
 *
 */
public class Hardy {
	
	/**
	 * Returns floor of the cube root of n.
	 * Can you see why this method is useful for this problem?
	 * This is not a very efficient implementation.  
	 * (Not required) Can you think of a more efficient approach?
	 * 
	 * @param n a positive integer
	 * @return integer cube root of n
	 */
	public static int cubeRootFloor(int n){
		// Very inefficient, but quick to write.
		int i=0;
		while (cube(i) <= n)
			i++;
		return i-1; 
	}
		
	/**
	 * Computes and returns a sorted list of all taxicab numbers less than n.
	 * @param n a positive integer
	 * @return a List<TaxicabNumber>  object.  Each object contains the sum and two different ways to write it as a sum of cubes.
	 */
	
	public static List<TaxicabNumber> hardySolutionsLessThan(int n) {
		List<TaxicabNumber> result = new ArrayList<TaxicabNumber>();  // You are to populate this list.
		int limit = cubeRootFloor(n);
		/* DONE: fill in the calculations */
		int a = 0, b = 0, c = 0, d = 0;
		double s = 0;
		for (b = 0; b <= limit; b++) {
			for (a = 0; a < b; a++) {
				s = (Math.pow(a, 3) + Math.pow(b, 3));
				if (s < n) {
					for (d = b + 1; d <= limit; d++) {
						for (c = 0; c < d; c++) {
							if (s == (Math.pow(c, 3) + Math.pow(d, 3)) && c != a && d != b) {
								TaxicabNumber output = new TaxicabNumber((int) s, a, b, c, d);
								result.add(output);
							}
						}
					}
				}
			}
		}
		java.util.Collections.sort(result);
		return result;
	}
	
	/**
	 * main() is provided in case you want to test your code other than 
	 * with unit tests. Just put try various arguments in the method call below.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(hardySolutionsLessThan(1730));
	}

}
