package streams;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Dish {
	private final String name;
	private final boolean vegetarian;
	private final int calories;
	private final Type type;

	public static void main(String[] args) {
		List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));

		// Look how we have chained the operations using Streams API
		// This would have needed a big boiler plate code in Java 7
		List<String> highCalorieDishes = menu.stream().filter(dish -> dish.getCalories() > 500)
				.sorted(comparing(Dish::getCalories)).map(Dish::getName).collect(toList());

		System.out.println(highCalorieDishes);

		Stream<Dish> dishStream = menu.stream();
		dishStream.forEach(System.out::println);

		// Note : We can consume a stream only once
		// It will throw illelgalStateExcetpion when the stream is reused.
		// Think of collections as DVD and stream as Internet stream of video.
		// Collection : Eagerly computed
		// Stream : Lazily computed on demand
		// ****dishStream.forEach(System.out::println);

	}

	public boolean isHighCalOrNot(Dish dish) {

		return dish.getCalories() > 500;
	}

	public Dish(String name, boolean vegetarian, int calories, Type type) {
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public int getCalories() {
		return calories;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return name;
	}

	public enum Type {
		MEAT, FISH, OTHER
	}

	public enum diet {
		LOW, HIGH, OTHER
	}

}
