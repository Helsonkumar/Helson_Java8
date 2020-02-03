package streams;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamOps {

	public static void main(String[] args) {

		List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 2, 3);
		List<Integer> list2 = Arrays.asList(1, 4, 9, 16, 25);

		List<Integer> list3 = list1.stream().map(i -> i + 1).collect(toList());

		List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));

		// Look for flatMap => converts Stream<Stream<int[]>> to Stream<int[]>.
		// ie. converts individual stream into an amalgamated or flattened stream
		List<int[]> result = list1.stream().flatMap(i -> list2.stream().map(j -> new int[] { i, j })).collect(toList());

		// This can not be used because map would always want a Function<T,R> as input.
		// But here we are returning void.
		// **** result.stream().map(i -> System.out.println(i[0] + "," + i[1]));
		for (int[] x : result) {
			System.out.println(x[0] + " , " + x[1]);
		}

		// Gives the list of operations

		// filter
		list1.stream().filter(i -> i % 2 == 0).forEach(System.out::println);
		System.out.println("------------------");

		// distinct
		list1.stream().distinct().forEach(System.out::println);
		System.out.println("------------------");

		// takeWhile
		// reads the stream while the condition hold true
		// Stops once an element which fails the condition
		// But available only from Java 9
		// list1.stream().takeWhile(x -> x % 3 == 0);

		// dropWhile
		// ignores ghe stream element while the condition is true
		// Available in Java 9
		// list1.stream().dropWhile(x -> x % 3 == 0);

		// skip
		// Skips the first n items
		list1.stream().skip(2).forEach(System.out::println);
		System.out.println("------------------");

		// take
		// takes the first n numbers
		list1.stream().limit(4).forEach(System.out::println);
		System.out.println("------------------");

		// map
		// takes Function<T,R> as input and applies on each element
		// canot retrun void
		list1.stream().map(x -> x + 1).forEach(System.out::println);
		System.out.println("------------------");

		// flatmap

		// sorted
		list1.stream().sorted((x, y) -> x.compareTo(y)).forEach(System.out::println);
		System.out.println("------------------");

		// anyMatch
		boolean r = list1.stream().anyMatch(i -> i % 6 == 0);
		System.out.println(r);
		System.out.println("------------------");

		// allMatch
		boolean x = list1.stream().allMatch(i -> i % 6 == 0);
		System.out.println(x);
		System.out.println("------------------");

		// noneMatch
		boolean y = list1.stream().noneMatch(i -> i % 6 == 0);
		System.out.println(y);
		System.out.println("------------------");

		// findAny
		Optional<Integer> o = list1.stream().filter(i -> i == 2).findAny();
		o.ifPresent(System.out::println);
		System.out.println("------------------");

		// findFirst
		Optional<Integer> m = list1.stream().filter(i -> i % 2 == 0).findFirst();
		m.ifPresent(System.out::println);
		System.out.println("------------------");

		// *** reduce
		// they are fold operations as per functional programming.
		int sum = list1.stream().reduce(2, (u, v) -> u + v);
		int min = list1.stream().reduce(0, Integer::min);

		// Note : max_val is compared wit 1000. The max(max_val,1000) is returned
		int max = list1.stream().reduce(1000, Integer::max);

		// or we can do it otherwise
		int max2 = list1.stream().reduce(0, (i, j) -> i > j ? i : j);
		System.out.println(max2);
		System.out.println("------------------");

		// count
		System.out.println(list1.stream().count());
		System.out.println("------------------");

		// How to convert an Array into a Stream
		File[] file = new File(".").listFiles();
		Stream<File> fileStream = Arrays.stream(file);
		fileStream.filter(filex -> filex.getName().endsWith("xml")).forEach(System.out::println);

		Stream<File> fileStream2 = Stream.of(file);
		System.out.println("------------------");

		// *** generate
		// This method generates a stream of values
		// Used to generate infinite stream of constants or random number ,etc/
		// takes in a Supplier<() -> T>
		Stream<Integer> randomInt = Stream.generate(() -> {
			int rand = (int) (Math.random() * 99);
			return rand;
		});
		randomInt.limit(10).distinct().sorted().forEach(System.out::println);
		System.out.println("------------------");

		// *** Iterate
		// This method takes in a seed and applies that seed to a function.
		// iterate(seed,f(seed)) ->f(f(seed))
		// Then infinitely repeats applying that result to the same function again
		Stream<Integer> iterInt = Stream.iterate(10, u -> u + 1);
		iterInt.limit(20).forEach(System.out::println);
		System.out.println("------------------");

		// Stream.of
		// applies the given function on the stream and then returns a Stream<T>.
		// The stream is not closed once the function is applied.
		Stream<Dish> dishPeek = menu.stream().peek(k -> System.out.println(k + " * " + k));
		System.out.println("-----------------------");
		dishPeek.forEach(System.out::println);

	}

}
