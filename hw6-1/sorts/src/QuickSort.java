public class QuickSort extends SortAlgorithm {
    /*
     * Time Complexity
     * nlogn
     * in parititon for is 'n',
     * sort divide into 2 pieces and call again - logn
     * call partition for sort callings -> nlogn
     * 
     * Space Complexity
     * logn
     * Memory divides into 2 pieces in every sort calling -logn
     * 
     */
    public QuickSort(int input_array[]) {
        super(input_array);
    }

    private int partition(int left_index, int right_index) {
        /* element that compared other elements */
        int pivot = arr[right_index];

        /* indicator for smaller elements */
        int counter = (left_index - 1);

        for (int j = left_index; j < right_index; j++) { /* from left to right that entered */
            comparison_counter++;
            if (arr[j] < pivot) { /* comparison btw pivot */
                /*
                 * swapped index is counter, searching index is j, so they took in different
                 * variables
                 * if condition is true, counter and j increase together.
                 * if condition is false, j search element that less than pivot
                 */
                counter++;
                swap(counter, j);
            }
        }

        swap(counter + 1, right_index);
        return (counter + 1);
    }

    private void sort(int left_index, int right_index) {
        if (left_index < right_index) { /* while left is left, right is right */
            int pi = partition(left_index, right_index); /* for detecting pivot point */

            sort(left_index, pi - 1);
            /* sort up to pivot */

            sort(pi + 1, right_index);
            /* sort after pivot */
        }
    }

    @Override
    public void sort() {
        sort(0, arr.length - 1);
    }

    @Override
    public void print() {
        System.out.print("Quick Sort\t=>\t");
        super.print();
    }
}
