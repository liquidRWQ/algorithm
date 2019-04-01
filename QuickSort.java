import java.util.Arrays;

/**
 * @author Liquid
 * @类名： QuickSort
 * @描述： 快速排序 不稳定 时间复杂度最坏O(n^2)，平均(nlogn)
 * @date 2019/3/24
 */
public class QuickSort {
    public static void quickSort(int[] array, int newLeft, int newRight) {
        //两个哨兵重合(新一轮的左哨兵和右哨兵相遇)这轮排序结束。
        if (newLeft >= newRight) {
            return;
        }
        int left = newLeft;
        int right = newRight;
        int pivot = array[(newLeft + newRight) / 2];
        //保证两个哨兵一边循环后不能重合，要交换左右位置
        while (left <= right) {
            //找出左边比支点小的数
            while (pivot > array[left]) {
                left++;
            }
            //找出右边比支点小的数
            while (pivot < array[right]) {
                right--;
            }
            //两个哨兵相等时，各自前进一位并退出循环
            if (left <= right) {
                if (left != right) {
                    //位运算交换交换(相等时不交换)
                    array[left] = array[left] ^ array[right];
                    array[right] = array[left] ^ array[right];
                    array[left] = array[left] ^ array[right];
                }
                left++;
                right--;
            }

        }
        //左边递归快排
        quickSort(array, newLeft, right);
        //右边递归快排
        quickSort(array, left, newRight);

    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        int[] ints = new int[]{1, 8, 4, 6, 1, 5, 7, 6};
        quickSort(ints, 0, ints.length - 1);
        System.out.println(Arrays.toString(ints));

    }
}
