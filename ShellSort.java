import java.util.Arrays;

/**
 * @author Liquid
 * @类名： ShellSort
 * @描述： 希尔排序 不稳定 时间复杂度O（n^2）
 * @date 2019/3/23
 */
public class ShellSort {
    public static void shellSort(int[] array) {

        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            //外层循环控制增量的减少
            for (int i = gap, length = array.length; i < length; i++) {
                //内一层循环控制分组
                int temp = array[i];
                int j = i;
                while (j >= gap && temp < array[j - gap]) {
                    //最底层循环控制排序
                    array[j] = array[j - gap];
                    j -= gap;

                }
                array[j] = temp;
            }
        }

    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        int[] ints = new int[]{5, 8, 4, 6, 1, 1, 7, 6, 3, 9};
        shellSort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
