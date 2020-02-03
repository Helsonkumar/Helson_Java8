package lambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

//Note : We have created 2 different functions with the functional
// interface(Function).
// So we change the behaviour of the method Processor by passing in a diff
// functions at runtime
// This is highly useful when the requirement of the client keeps varying.
// This is an example of Lambda feature in Java 8. We avoid verbose code using
// this.
// We use to do the same thing before Java 8 by sing anonymous function or with
// concrete classes.
// We can create our own interfaces as well.
// Look how the exceptions are handled in Lambda code below

public class FileProcessor {

	public static void main(String[] args) {

		Function<BufferedReader, String> lambda1 = (BufferedReader br) -> {
			try {
				return br.readLine() + br.readLine();

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		};

		Function<BufferedReader, String> lambda2 = (BufferedReader br) -> {
			try {
				return br.readLine() + br.readLine() + br.readLine() + " final";

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		};

		System.out.println(Processor(lambda1));

		System.out.println(Processor(lambda2));

	}

	public static String Processor(Function<BufferedReader, String> func) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		return func.apply(br);

	}

}
