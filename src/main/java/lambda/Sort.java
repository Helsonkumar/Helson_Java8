package lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//This is a demo to show performing Sort in Java8 using lambdas

// Shows the diff ways to sort a given collection like List
// Do in reverse order as well
public class Sort {
	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(4, 6, 8, 1, 23, 4, 5, 89, 34, 2, 56, 7, 90, 1, 0, 6);

		// **** Sort typically use MERGE SORT as its core *******

		// Note : What is natural ordering
		// If an element is a "comparable"(Interface) then the natural ordering
		// is the way how its compraeTo() method is implemented

		// Reverse a given list as it is
		// Here no natural ordering is followed.
		// **** Collections.reverse(list);

		// Simply sorts a given list in ascending order by default.
		// Given collections elements must be "comparable"
		// Does the natural order sorting
		// Return 1 => parent > child
		// Return -1 => parent < child
		// Return 0 => parent == child
		// **** Collections.sort(list);

		// Sorts a given list in the descending order of its natural ordering
		// Here Collections.reverseOrder() simply returns a Comparator object which does
		// the reverse order of the natural ordering of the given comparable elements in
		// the collection.
		// **** Collections.sort(list, Collections.reverseOrder());

		// But if the elements have not implemented the Comparable interface
		// Then we can use Comparator interface to do sort a given elements
		// Comparator enables use to provide diff sorting order for a given collection
		// apart from it natural order

		// sorts a list by passing in a custom Comparator object
//		list.sort(new Comparator<Integer>() {
//			public int compare(Integer x, Integer y) {
//				return x.compareTo(y);
//			}
//		});

		// Same with lambda
//		list.sort((x, y) -> {
//			return x.compareTo(y);
//		});

		// Sorts the list like above but in the reverseOrder
//		list.sort(new Comparator<Integer>() {
//			public int compare(Integer x, Integer y) {
//				return x.compareTo(y);
//			}
//		}.reversed());

		Comparator<Integer> comp = (x, y) -> {
			return x.compareTo(y);
		};

		// list.sort(Collections.reverseOrder());
		list.sort(comp.reversed());

		for (Integer x : list) {
			System.out.print(x + " ");
		}

		// Collections.sort(list, (Integer i, Integer j) -> i.compareTo(j));

		/*
		 * Collections.sort(list, new Comparator<Integer>() { public int compare(Integer
		 * x, Integer y) { return x.compareTo(y); } });
		 */

		/*
		 * list.sort((
		 * 
		 * Integer i, Integer j) -> i.compareTo(j));
		 */

		// list.sort(comparing(Integer.));

		/*
		 * for (Integer x : list) { System.out.print(x + " "); }
		 */
	}

}
