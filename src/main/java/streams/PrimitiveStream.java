package streams;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// Here we check for the way how to generate specialized streams of primitive data types.
// Stream<T> usually hold objects as their data type.
// We have convenient methods to generate Streams of Primitive Data Types. 
public class PrimitiveStream {

	public static void main(String[] args) {

		List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));

		IntStream intStream = IntStream.rangeClosed(1, 100);
		// System.out.println(intStream.sum());

		// This can be achieved even via reduce method of using Integer::sum
		// But this involves lots of auto boxing

		// Below demo is to get arrays of valid number which satisfies pythogores
		// theorem. [a,b,c] => so a*a + b*b = c*c;

		// Idea is to get a stream of int and pair them with another integer pair
		// boxed() => converts a primitive into Native Object
		// mapToObj => is an alternative to boxed().mapToObj()
		Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100).boxed()
						.map(b -> new double[] { a, b, Math.sqrt(a * a + b * b) }).filter(t -> t[2] % 1 == 0));

		// pythagoreanTriples2.forEach(t -> System.out.println(t[0] + "," + t[1] + "," +
		// t[2]));
		// System.out.println(pythagoreanTriples2.count());

		// Map a Stream of Integer to primitive Stream<int> to do the aggregate ops like
		// sum(),max(),etc
		// We have mapToDouble mapToLong
		// IntStream helps in avoiding auto-boxing
		OptionalInt intstm = menu.stream().mapToInt(Dish::getCalories).max();

	}
}
