package edu.normandale.csn;

// This class contains some static functions that are just generally useful
// for Java programming.

public final class JavaUtils {

	// Don't allow an object of this type to be created.
	private JavaUtils() {
	}

	public static boolean checkEa() {
		try {
			assert false;
			System.out.println("This needs to be run with the -ea flag!");
			return false;
		}
		catch (AssertionError e) {
		}
		return true;
	}

}
