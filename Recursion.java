/**
 *
 * @author Mansur Ismailov 
 * Erzeugen eines binaeren Baumes mit Rekursion
 *
 */


public class Recursion {
    public static void main(String[] args) {

        Tree t = new Tree(7);
        int[] numbers = {4, 6, 5, 2, 1, 3, 15, 19, 11, 9, 13, 8};

        for (int number : numbers) {
            t.insert(number);
        }

        System.out.println(t);
        System.out.println(String.format("Tree is degenerate ? %b", t.isDegenerate()));
        System.out.println(String.format("Min: %d, Max: %d, Height: %d", t.min(), t.max(), t.height()));

        boolean allSmallerTwenty = t.forAll(v -> v < 20);
        System.out.println(String.format("All < 20: %b", allSmallerTwenty));
        System.out.println(t.printT(0));
    }
}
