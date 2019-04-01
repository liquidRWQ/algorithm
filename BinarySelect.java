/**
 * @author Liquid
 * @类名： BinarySelect
 * @描述：二分查找
 * @date 2019/3/28
 */
public class BinarySelect {

    public static int binarySelect(int[] array, int target) {
        int start = 0;
        int end = array.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (array[mid] < target) {
                start = mid + 1;
            } else if (array[mid] > target) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
