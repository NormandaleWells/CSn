import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputUtilities {

    public static int[] readAllInts(String filename) {
        ArrayList<Integer> a = new ArrayList<>();
        try
        {
            FileInputStream file = new FileInputStream(filename);
            Scanner sc = new Scanner(file);
            while (sc.hasNextInt()) {
                int i = sc.nextInt();
                a.add(i);
            }
            sc.close();
        }
        catch (FileNotFoundException ex)
        {
            // Yeah, this is a bit draconian, but given how we're
            // using this class, it's acceptable.
            System.out.println("File not found: " + filename);
            System.exit(0);
        }
        int[] b = new int[a.size()];
        for (int i = 0; i < b.length; i++)
            b[i] = a.get(i);
        return b;
    }
}
