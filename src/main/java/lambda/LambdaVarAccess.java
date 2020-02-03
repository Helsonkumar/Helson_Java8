package lambda;

import java.util.function.IntPredicate;

// This is to demo that the variables in the lambda functional descriptor 
// can refer to INSTANCE variables or any type without any restriction.

// But restricted to use local variables.
// local variables declared in the lambda expression must be effectively or explicitly FINAL.
// this is to avoid the thread safety issue.

public class LambdaVarAccess {

	String ab = "This is ab";
	static int x = 15;

	public static void main(String[] args) {

		// this is effectively final since we dont alter this after this
		// int u = 100;

		x = 100;

		// See : we have not use the TYPE of the parm. Instead we simply used a
		// reference
		// variables alone.
		// This is how lambda does the type reference by matching the functional
		// descriptor with the lambda signature.
		IntPredicate p = (j) -> {
			return j > x;
		};

		IntPredicate q = (j) -> {
			return j % 2 == 0;
		};

		System.out.println(processor(p));

		System.out.println(processor(q));

	}

	public static boolean processor(IntPredicate p) {

		int j = 46;
		return (p.test(j));

	}

}
