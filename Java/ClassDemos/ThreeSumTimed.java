
public class ThreeSumTimed {

	public static int count(int[] a) {
		int N = a.length;
		int count = 0;
		for (int i = 0; i < N; i++)
		{
			int ai = a[i];
			for (int j = i + 1; j < N; j++)
			{
				int aj = a[j];
				for (int k = j + 1; k < N; k++)
					if (ai + aj + a[k] == 0)
//					if (a[i] + a[j] + a[k] == 0)
						count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a = InputUtilities.readAllInts(args[0]);
		Timer stopwatch = new Timer();
		int c = count(a);
		double time = stopwatch.elapsed();
		System.out.println(c);
		System.out.println("elapsed time " + time);
	}
}
