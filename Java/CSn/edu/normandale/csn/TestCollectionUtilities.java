package edu.normandale.csn;

import java.util.Scanner;

public class TestCollectionUtilities {

	private static void printCollection(String colType, Iterable<String> collection) {
		System.out.printf("\nFinal %s:\n", colType);
		for (String s : collection)
			System.out.printf("\t%s\n", s);
	}

	public static void testQueue(Queue<String> queue) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String s = scanner.next();
			if (s.equals("-"))
				System.out.printf("%s ", queue.dequeue());
			else
				queue.enqueue(s);
		}
		scanner.close();

		printCollection("queue", queue);
	}

	public static void testStack(Stack<String> stack) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String s = scanner.next();
			if (s.equals("-"))
				System.out.printf("%s ", stack.pop());
			else
				stack.push(s);
		}
		scanner.close();
		
		printCollection("stack", stack);
	}

	public static void testBag(Bag<String> bag) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String s = scanner.next();
			bag.add(s);
		}
		scanner.close();

		printCollection("bag", bag);
	}
}
