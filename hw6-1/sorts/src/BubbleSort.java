public class BubbleSort extends SortAlgorithm {
    /*
     * Time Complexity
     * n'2 - inner for loops
     * 
     * Space Complexity
     * 1 - constant
     */
    public BubbleSort(int input_array[]) {
        super(input_array);

    }

    @Override
    public void sort() {
        boolean flag = true;
        for (int i = arr.length - 1; i >= 0; i--) {
            flag = false;
            for (int j = 1; j <= i; j++) {
                comparison_counter++;
                if (arr[j - 1] > arr[j]) {
                    swap(j - 1, j);
                    flag = true;
                }
            }
            if (!flag)
                break;
        }
    }

    @Override
    public void print() {
        System.out.print("Bubble Sort\t=>\t");
        super.print();
    }
}
