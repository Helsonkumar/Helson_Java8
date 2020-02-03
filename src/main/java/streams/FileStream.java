package streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Demo to show how to read a file lines as stream
// We use java.nio.file.Files
public class FileStream {

	public static void main(String[] args) {

		try {
			Stream<String> lines = Files
					.lines(Paths.get("C:\\Users\\Helson\\Desktop\\Desktop-Files-08Aug2019\\Oauth2.txt"));
			lines.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Infinite Stream
		// Stream of Fibnocci numbers
		Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] }).limit(10)
				.forEach(t -> System.out.println(t[1]));
	}
}