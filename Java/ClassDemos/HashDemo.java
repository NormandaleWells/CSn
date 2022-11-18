import java.util.Scanner;

public class HashDemo {

    private static <T> int hash(T t, int m) {
        int h = t.hashCode();
        return (h & 0x7FFFFFFF) % m;
    }

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("For each input line, prints the hashcode and hash table index");
            System.out.println("usage: HashDemo <M>");
            System.out.println("    <M> is the hash table size");
            System.exit(0);
        }
        int m = Integer.parseInt(args[0]);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.printf("Hashcode %d, index %d\n", line.hashCode(), hash(line, m));
        }
        sc.close();
    }
}
