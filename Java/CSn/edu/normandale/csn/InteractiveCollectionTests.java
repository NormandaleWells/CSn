package edu.normandale.csn;

import java.util.Scanner;

public final class InteractiveCollectionTests {

	// Don't allow an object of this type to be created.
	private InteractiveCollectionTests() {
	}

	private static void printCollection(String colType, Iterable<String> collection) {
		for (String s : collection)
			System.out.printf("%s ", s);
		System.out.println();
	}

	private static void printTestQueueUsage() {
		System.out.println("Commands:");
		System.out.println("\tnqueue <data> (add, a, +)");
		System.out.println("\tdequeue       (remove, r, -)");
		System.out.println("\tsize          (s)");
		System.out.println("\tisEmpty       (e)");
		System.out.println("\tprint         (p)");
	}

	public static void testQueue(Queue<String> queue) {
		printTestQueueUsage();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String s = scanner.nextLine();
			String[] fields = s.split(" ");
			if (fields.length == 0)
				continue;

			String command = fields[0];
			String data = fields.length > 1 ? fields[1] : null;
			switch (command) {
				case "enqueue":
				case "add":
				case "a":
				case "+":
					queue.enqueue(data);
					break;
				case "dequeue":
				case "remove":
				case "r":
				case "-":
					data = queue.dequeue();
					System.out.println(data);
					break;
				case "size":
				case "s":
					System.out.println(queue.size());
					break;
				case "isEmpty":
				case "e":
					System.out.println(queue.isEmpty());
					break;
				case "print":
				case "p":
					printCollection("set", queue);
					break;
				default:
					printTestSetUsage();
			}
		}
		scanner.close();

		printCollection("queue", queue);
	}

	private static void printTestStackUsage() {
		System.out.println("Commands:");
		System.out.println("\tpush <data> (add, a, +)");
		System.out.println("\tpop         (remove, r, -)");
		System.out.println("\tpeek        (k)");
		System.out.println("\tsize        (s)");
		System.out.println("\tisEmpty     (e)");
		System.out.println("\tprint       (p)");
	}

	public static void testStack(Stack<String> stack) {
		printTestStackUsage();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String s = scanner.nextLine();
			String[] fields = s.split(" ");
			if (fields.length == 0)
				continue;

			String command = fields[0];
			String data = fields.length > 1 ? fields[1] : null;
			switch (command) {
				case "push":
				case "add":
				case "a":
				case "+":
					stack.push(data);
					break;
				case "pop":
				case "remove":
				case "r":
				case "-":
					data = stack.pop();
					System.out.println(data);
					break;
				case "peek":
				case "k":
					data = stack.peek();
					System.out.println(data);
					break;
				case "size":
				case "s":
					System.out.println(stack.size());
					break;
				case "isEmpty":
				case "e":
					System.out.println(stack.isEmpty());
					break;
				case "print":
				case "p":
					printCollection("set", stack);
					break;
				default:
					printTestSetUsage();
			}
		}
		scanner.close();
	}

	private static void printTestSetUsage() {
		System.out.println("Commands:");
		System.out.println("\tadd <data>    (a, +)");
		System.out.println("\tremove <data> (r, -)");
		System.out.println("\tsize          (s)");
		System.out.println("\tisEmpty       (e)");
		System.out.println("\tprint         (p)");
	}

	public static void testSet(Set<String> set) {
		printTestSetUsage();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String s = scanner.nextLine();
			String[] fields = s.split(" ");
			if (fields.length == 0)
				continue;

			String command = fields[0];
			String data = fields.length > 1 ? fields[1] : null;
			switch (command) {
				case "add":
				case "a":
				case "+":
					set.add(data);
					break;
				case "remove":
				case "r":
				case "-":
					set.remove(data);
					break;
				case "size":
				case "s":
					System.out.println(set.size());
					break;
				case "isEmpty":
				case "e":
					System.out.println(set.isEmpty());
					break;
				case "print":
				case "p":
					printCollection("set", set);
					break;
				default:
					printTestSetUsage();
			}
		}
		scanner.close();
	}
}
