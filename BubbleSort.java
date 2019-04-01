import java.util.Arrays;

/**
 * @author Liquid
 * @类名： BubbleSort
 * @描述： 冒泡排序 时间复杂度O(n^2) 稳定(两两交换)
 * @date 2019/3/23
 */
public class BubbleSort {
    public static void bubbleSort(int[] array) { ;
        for (int i = array.length - 1; i > 0; i--) {
            //外循环控制排序的冒泡点（下标i）
            int flag=0;
            for (int j = 0; j < i; j++) {

                //内循环控制每趟排序的次数
                if (array[j] > array[j + 1]) {
                    flag=j+1;
                    array[j] = array[j] ^ array[j + 1];
                    array[j + 1] = array[j] ^ array[j + 1];
                    array[j] = array[j] ^ array[j + 1];
                }
            }
            //记录上一次排序的最后一次交换的点，下次交换到该点就行。因为后面的都判断过了，不需要交换。
            i=flag;
        }

    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        int[] ints = new int[]{1, 8, 4, 6, 1, 5, 7, 6};
        bubbleSort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
