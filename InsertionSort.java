
import java.util.Arrays;

/**
 * @author Liquid
 * @类名： InsertionSort
 * @描述： 插入排序  稳定(从前往后插入，从后往前比较，相同的元素不会进行交换)
 * 时间复杂度O（n^2）
 * 空间复杂度O(n)
 * @date 2019/3/23
 */
public class InsertionSort {
    public static void insertionSort(int[] array) {

        //插入排序
        for (int i = 1, length = array.length; i < length; i++) {
            //外层循环控制准备插入的数array[j+1]

            for (int j = i - 1; j >= 0; j--) {
                //内层循环控制比较次数
                if (array[j] > array[j + 1]) {
                    //用异或交换
                    array[j] = array[j] ^ array[j + 1];
                    array[j + 1] = array[j] ^ array[j + 1];
                    array[j] = array[j] ^ array[j + 1];
                }
            }

        }

    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        int[] ints = new int[]{1, 8, 4, 6, 1, 5, 7, 6};
      insertionSort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
