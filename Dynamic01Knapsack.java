import java.util.ArrayList;

public class Dynamic01Knapsack {

    //solution of value in knapsack
    public static int result;

    //list of objects found in the knapsack
    public static ArrayList<Integer> objects = new ArrayList<Integer>();

    static int max(int left, int right)
    {
        return left<right ? right : left;

    }

    //This will return the most value that can be extracted from the item set
    static int knapsack01(int capacity, int items, int weights[], int val[])
    {
        int table[][] = new int[items + 1][capacity + 1];

        int weight, i;
        for (i = 0; i<= items; i++) {
            for (weight = 0; weight<= capacity; weight++) {
                if (i == 0 || weight == 0)
                    table[i][weight] = 0;
                else if (weights[i - 1]<= weight)
                    table[i][weight] = max(val[i - 1] + table[i - 1][weight - weights[i - 1]], table[i - 1][weight]);
                else
                    table[i][weight] = table[i - 1][weight];
            }
        }

        result = table[items][capacity];
        int value = result;

        //Adds the items found from traversing the table back
        weight = capacity;
        for (i = items-1; i >= 0 && value > 0; i--) {


            if (value == table[i][weight])
                continue;
            else {

                //add item and remove value and weight
                objects.add(weights[i]);
                value -= val[i];
                weight -= weights[i];
            }
        }
        return result;
    }
    public static void main(String args[])
    {
        int val[] = {40, 100, 50, 60};
        int wt[] = {20, 10, 40, 30};
        int W = 60;
        int n = val.length;
        knapsack01(W, n, wt, val);
        System.out.println(result);
        System.out.println(objects);
    }
}
