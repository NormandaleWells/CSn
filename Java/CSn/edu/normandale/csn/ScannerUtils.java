package edu.normandale.csn;

import java.util.ArrayList;
import java.util.Scanner;

public class ScannerUtils {

	public static Integer[] readAllIntegers(Scanner scanner) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		while (scanner.hasNextInt()) {
			a.add(scanner.nextInt());
		}
		Integer[] ia = new Integer[a.size()];
		return a.toArray(ia);
	}
	
	public static String[] readAllStrings(Scanner scanner) {
		ArrayList<String> a = new ArrayList<String>();
		while (scanner.hasNext()) {
			a.add(scanner.next());
		}
		String[] sa = new String[a.size()];
		return a.toArray(sa);
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Integer[] a = ScannerUtils.readAllIntegers(scanner);
		System.out.println(a.length);
	}
}
