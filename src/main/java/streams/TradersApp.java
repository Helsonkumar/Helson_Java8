package streams;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TradersApp {

	public static void main(String[] args) {

		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Trader> trader = Arrays.asList(raoul, mario, alan, brian);

		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

		// All txn in 2011 and sorted them by value
		List<Transaction> list2011 = transactions.stream().filter(x -> x.getYear() == 2011)
				.sorted(comparing(Transaction::getValue)).collect(toList());
		list2011.stream().forEach(System.out::println);
		System.out.println("----------------------------");

		// Unique city in which traders work
		List<String> uniqCity = trader.stream().map(trdr -> trdr.getCity()).distinct().collect(toList());
		uniqCity.stream().forEach(System.out::println);
		System.out.println("----------------------------");

		// Find all traders from Cambridge and sort them by name.
		List<Trader> CamTrader = trader.stream().filter(x -> "Cambridge".equals(x.getCity()))
				.sorted(comparing(Trader::getName)).collect(toList());
		CamTrader.stream().forEach(System.out::println);
		System.out.println("----------------------------");

		// Return a string of all traders’ names sorted alphabetically.
		String traderName = trader.stream().map(Trader::getName).sorted().collect(toList()).toString();
		System.out.println(traderName);
		System.out.println("----------------------------");

		// Are any traders based in Milan?
		boolean traderMilan = trader.stream().anyMatch(x -> x.getCity() == "Milan");
		System.out.println(traderMilan);
		System.out.println("----------------------------");

		// Print the values of all transactions from the traders living in Cambridge.
		/*
		 * trader.stream().filter(x -> x.getCity() == "Cambridge") .flatMap(x ->
		 * transactions.stream().filter(y -> y.getTrader() == x)) .forEach(x ->
		 * System.out.println(x.getValue()));
		 */

		transactions.stream().filter(x -> x.getTrader().getName().equals("Cambridge")).map(Transaction::getValue)
				.forEach(System.out::println);
		System.out.println("----------------------------");

		// What’s the highest value of all the transactions?
		Optional<Integer> maxval = transactions.stream().map(Transaction::getValue).distinct().reduce(Integer::max);
		maxval.ifPresent(System.out::println);
		System.out.println("----------------------------");

		// Find the transaction with the smallest value.
		/*
		 * Optional<Transaction> smallVal =
		 * transactions.stream().sorted(comparing(Transaction::getValue)).findFirst();
		 */

		Optional<Transaction> smallTxn = transactions.stream().reduce((a, b) -> a.getValue() < b.getValue() ? a : b);
		smallTxn.ifPresent(System.out::println);
		System.out.println("----------------------------");
		;

	}

}

class Trader {
	private final String name;
	private final String city;

	public Trader(String n, String c) {
		this.name = n;
		this.city = c;
	}

	public String getName() {
		return this.name;
	}

	public String getCity() {
		return this.city;
	}

	public String toString() {
		return "Trader:" + this.name + " in " + this.city;
	}
}

class Transaction {
	private final Trader trader;
	private final int year;
	private final int value;

	public Transaction(Trader trader, int year, int value) {
		this.trader = trader;
		this.year = year;
		this.value = value;
	}

	public Trader getTrader() {
		return this.trader;
	}

	public int getYear() {
		return this.year;
	}

	public int getValue() {
		return this.value;
	}

	public String toString() {
		return "{" + this.trader + ", " + "year: " + this.year + ", " + "value:" + this.value + "}";
	}
}