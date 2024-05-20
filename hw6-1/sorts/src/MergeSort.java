public class MergeSort extends SortAlgorithm {
    /*
     * Time Complexity
     * nlogn
     * in merge every while and for's is 'n',
     * sort divide into 2 pieces and call again - logn
     * every sort call merge -> nlogn
     * 
     * Space Complexity
     * n
     * merge method takes 2 new memory location in every loop
     */
    public MergeSort(int input_array[]) {
        super(input_array);
    }

    private void merge(int left_index, int middle_index, int right_index) {
        /* left and right part's number of elements */
        int left_part = middle_index - left_index + 1;
        int right_part = right_index - middle_index;

        /* For merging empty side arrays */
        int L[] = new int[left_part];
        int R[] = new int[right_part];

        /* write datas to template arrays */
        for (int i = 0; i < left_part; i++)
            L[i] = arr[left_index + i];
        for (int j = 0; j < right_part; j++)
            R[j] = arr[middle_index + 1 + j];

        int i = 0; /* left side counter */
        int j = 0; /* right side counter */

        int k = left_index; /* for counting the array's itself */

        while (i < left_part && j < right_part) { /* while left and right boundaries */
            comparison_counter++;
            if (L[i] <= R[j]) { /* compare the values after write min one */
                arr[k] = L[i];
                i++; /* written arrya's element's increase */
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* copy L array to main array to left side */
        while (i < left_part) {
            arr[k] = L[i];
            i++; /* L array's counter */
            k++; /* arr's counter */
        }
        /* copy R array to main array to right side */
        while (j < right_part) {
            arr[k] = R[j];
            j++; /* R array's counter */
            k++; /* arr's counter */
        }
    }

    private void sort(int left_index, int right_index) {
        if (left_index < right_index) {
            /* Middle index, calculate like this for avoid exceed memory boundaries */
            int middle_index = left_index + (right_index - left_index) / 2;

            /* divide into 2 parts the array */
            sort(left_index, middle_index);
            sort(middle_index + 1, right_index);

            /* merge sorted pieces */
            merge(left_index, middle_index, right_index);
        }
    }

    @Override
    public void sort() { /* send to sort method all arr */
        sort(0, arr.length - 1);
    }

    @Override
    public void print() {
        System.out.print("Merge Sort\t=>\t");
        super.print();
    }
}
