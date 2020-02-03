package Collector;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import streams.Dish;
import streams.Dish.Type;
import streams.Dish.diet;

public class CollIntrfMethod {

	public static void main(String[] args) {

		List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));

		Map<String, List<String>> dishTags = new HashMap<>();
		dishTags.put("pork", asList("greasy", "salty"));
		dishTags.put("beef", asList("salty", "roasted"));
		dishTags.put("chicken", asList("fried", "crisp"));
		dishTags.put("french fries", asList("greasy", "fried"));
		dishTags.put("rice", asList("light", "natural"));
		dishTags.put("season fruit", asList("fresh", "natural"));
		dishTags.put("pizza", asList("tasty", "salty"));
		dishTags.put("prawns", asList("tasty", "roasted"));
		dishTags.put("salmon", asList("delicious", "fresh"));

		// Sum of Integer : If U want a primitive think of using the primitive methods
		// We have summingInt,summingDouble
		int sumval = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
		System.out.println(sumval);

		// Count the number of items
		Long countVal = menu.stream().collect(Collectors.counting());
		System.out.println(countVal);

		// maxBy , minBy : takes in a comparator
		Optional<Dish> max = menu.stream().collect(Collectors.maxBy(comparing(Dish::getCalories)));
		max.ifPresent(x -> System.out.println(x.getCalories()));

		// SummarizingInt : Gives the static of the given field Value
		IntSummaryStatistics summary = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
		System.out.println(summary);

		// Do the sum using coll.reduce method
		// This way o using reduce method does the accumalation of mutable object in
		// parallel fashion
		// this is diff than the tream.reduce method which is not thread safe
		int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
		System.out.println(totalCalories);

		// Grouping
		// The Classification function we pass in does the trick
		// It simply evaluates a given items and categories it and returns a value
		// The elements are grouped as per the returned value (key of the group)
		Map<Dish.Type, List<Dish>> grpMenu = menu.stream().collect(groupingBy(Dish::getType));
		System.out.println(grpMenu);

		// Groping by a key which is not given by the class Dish
		// Classification function simply evaluates an object and tags
		Map<Dish.diet, List<Dish>> grpDiet = menu.stream().collect(groupingBy(dish -> {
			if (dish.getCalories() > 400)
				return Dish.diet.HIGH;
			else if (dish.getCalories() < 400)
				return Dish.diet.LOW;
			else
				return Dish.diet.OTHER;

		}

		));
		// ystem.out.println(grpDiet);

		// nu.stream().groupingBy(Dish::getType, Collectors.filtering(dish ->
		// dish.getCalories() > 500, toList()));

		Map<Type, List<String>> caloricDishesByType = menu.stream()
				.collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));

		// System.out.println(caloricDishesByType);

		// Filtering and flatMapping are Java 9 Collectors class static methods.
		/*
		 * Map<Dish.Type, Set<String>> mapOfTags = menu.stream().collect(
		 * groupingBy(Dish::getType, flatMapping(dish ->
		 * dishTags.get(dish.getName()).stream(), toSet())));
		 */

		/*
		 * menu.stream().groupingBy(Dish::getType, Collectors.filtering(dish ->
		 * dish.getCalories() > 500, toList()));
		 */

		// MultiLevel Grouping : More than one groupBy Key
		Map<Dish.Type, Map<Dish.diet, List<Dish>>> grpMultiLevel = menu.stream()
				.collect(groupingBy(Dish::getType, groupingBy(dish -> {
					if (dish.getCalories() > 400)
						return diet.HIGH;
					else if (dish.getCalories() < 400)
						return diet.LOW;
					else
						return diet.OTHER;

				})));
		// System.out.println(grpMultiLevel);

		// ocllectingAndThen method usage
		// Used to avoid the wrapping of OPtional type and get the elements directly
		// Them optional resulting from maxBy operation is not useful

		Map<Dish.Type, Dish> grpByMaxCaloires = menu.stream().collect(
				groupingBy(Dish::getType, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));

		// System.out.println(grpByMaxCaloires);

		// partitioningBy : Only 2 groups of TRUE or FALSE since it returns Boolean
		// Similar to grp : but it only needs boolean value and special case of Grouping
		Map<Boolean, List<Dish>> partByVeg = menu.stream().collect(partitioningBy(Dish::isVegetarian));
		System.out.println(partByVeg);

	}
}
