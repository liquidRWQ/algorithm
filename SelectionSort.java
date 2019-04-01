import java.util.Arrays;

/**
 * @author Liquid
 * @类名： SelectionSort
 * @描述： 选择排序 不稳定 时间复杂度O(n^2)
 * @date 2019/3/23
 */
public class SelectionSort {

    public static void selectionSort(int[] array) {

        for (int i = 0, length = array.length - 1; i < length; i++) {
            //外循环控制选择的次数
            int min = array[i];
            int minIndex = i;
            for (int j = i + 1; j <= length; j++) {
                //内循环控制每趟选择的比较次数
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                //选到了最小的就进行交换
                array[i] = array[i] ^ array[minIndex];
                array[minIndex] = array[i] ^ array[minIndex];
                array[i] = array[i] ^ array[minIndex];
            }
        }

    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        int[] ints = new int[]{1, 8, 4, 6, 1, 5, 7, 6};
        selectionSort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
