class AssertionExample {
   
	private static boolean checkEa() {
		try {
			assert false;
			System.out.println("This needs to be run with the -ea flag!");
			return false;
		}
		catch (AssertionError e) {
		}
		return true;
	}

	public static int add1(int n) {
		return n+1;
	}
   
	public static int subtract1(int n) {
		return n-1;
	}
   
	public static void main(String[] args) {

		if (!checkEa()) return;

		assert add1(0) == 1;
		assert add1(1) == 2;
		assert add1(42) == 43;
		assert add1(-42) == -41;
		  
		assert subtract1(0) == -1;
		assert subtract1(1) == 0;
		assert subtract1(42) == 41;
		assert subtract1(-42) == -43;
	}
}
