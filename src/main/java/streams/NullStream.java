package streams;

import java.util.stream.Stream;

// How to process a null object in stream
public class NullStream {

	public static void main(String[] args) {

		// But in Java 9 we have Stream.nullable() static method whihc returns empty
		// string in case if null if detected
		Stream<String> stream1 = Stream.of("Helson", "Gladys").map(x -> System.getProperty(x) == null ? " " : x);
		stream1.forEach(System.out::println);

		// Returns Empty Stream
		Stream<String> empty = Stream.empty();

	}

}
