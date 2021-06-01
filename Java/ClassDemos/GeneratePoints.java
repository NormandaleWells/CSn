
import java.util.Random;

public class GeneratePoints {

	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("usage: java GeneratePoints <numpoints>");
			System.out.println("    points are written to stdout,");
			System.out.println("    preceded by numPoints");
			return;
		}
		
		int numPoints = Integer.parseInt(args[0]);
		System.out.println(numPoints);
		
		Random rand = new Random();
		for (int i = 0; i < numPoints; i++) {
			double x = rand.nextDouble();
			double y = rand.nextDouble();
			System.out.printf("%17.15f %17.15f\n", x, y);
		}
	}
}
