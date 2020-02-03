package lambda;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.List;

// Demo for how to select the sort of the given element in a collection dynamically
public class DynamicSort {

	public static void main(String[] args) {
		List<Apple> list = new ArrayList<Apple>();

		list.add(new Apple(10, 71, "RED1"));
		list.add(new Apple(70, 47, "RED7"));
		list.add(new Apple(40, 54, "RED4"));
		list.add(new Apple(50, 85, "RED5"));
		list.add(new Apple(80, 28, "RED8"));
		list.add(new Apple(20, 32, "RED2"));
		list.add(new Apple(30, 93, "RED3"));
		list.add(new Apple(90, 69, "RED9"));
		list.add(new Apple(60, 16, "RED6"));

		for (Apple x : list) {
			System.out.print(x.getWeight() + " ");
		}

		System.out.println(" ");

//		list.sort((x, y) -> {
//			return x.getWeight().compareTo(y.getWeight());
//		});

		// Here the comparing method is passed in as static reference. Checks how we
		// have imported at the top.
		// Comparing consumes a function which gets the key to be used for sorting an
		// and returns a comparator which does the sorting using that key and that
		// comparator is passed in as input to
		// sort method which needs a comparator.

		// This is highly flexible when u want to change the keys dynamically on which a
		// list has to be sorted.
		list.sort(comparing(Apple::getSize));

		for (Apple x : list) {
			System.out.print(x.getSize() + " ");
		}

		//

	}

}

class Apple implements Comparable<Apple> {
	Integer weight;
	Integer size;
	String color;

	public Apple(int weight, int size, String color) {
		super();
		this.weight = weight;
		this.size = size;
		this.color = color;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public int compareTo(Apple o) {

		return this.getColor().compareTo(o.getColor());

	}

}