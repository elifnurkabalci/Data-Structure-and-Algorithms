public class SelectionSort extends SortAlgorithm {
    /*
     * Time Complexity
     * n'2 - inner for loops
     * 
     * Space Complexity
     * 1 - constant
     */
    public SelectionSort(int input_array[]) {
        super(input_array);
    }

    @Override
    public void sort() {
        int min; /* it takes min number's index */
        for (int i = 0; i < arr.length - 1; i++) {
            min = i; /* i is the pivot index */
            for (int j = i + 1; j < arr.length; j++) {
                comparison_counter++;
                if (arr[j] < arr[min]) { /* if j's element is less than min's element */
                    min = j;
                }
            }
            swap(min, i);
        }
    }

    @Override
    public void print() {
        System.out.print("Selection Sort\t=>\t");
        super.print();
    }
}
