import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        int[] given = generateSorted(10, 0, 100);
        Node root = getBST(given, 0, given.length);
        for(int x : given){
            System.out.printf("%d, ", x);
        }
        System.out.println();
        BTreePrinter.printNode(root);
    }

    public static int[] generateSorted(final int length, final int minVal, final int maxVal) {
        List<Integer> data = new ArrayList<>(length);

        Random rand = new Random(42);

        for (int i = 0; i < length; i++) {
            data.add(rand.nextInt(maxVal - minVal + 1) + minVal);
        }
        Collections.sort(data);

        return data.stream().mapToInt(i -> i).toArray();
    }

    public static Node getBST(int[] given, int start, int end){
        if(start == end){
            return null;
        }
        int mid = ((end + start)-1)/2;
        Node current = new Node(given[mid]);
        current.left = getBST(given, start, mid);
        current.right = getBST(given, mid+1, end);
        return current;
    }
}
