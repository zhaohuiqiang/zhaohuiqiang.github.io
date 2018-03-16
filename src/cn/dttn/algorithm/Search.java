package cn.dttn.algorithm;

public class Search {
    public static void main(String[] args) {

    }

    public static <T extends Comparable<T>> int rotatedArrFind(T[] arr, T target) {
        int low = 0;
        int high = arr.length - 1;
        int mid = (low + high) / 2;

        while (low <= high) {
            if (target.compareTo(arr[mid]) == 0) return mid;
            //method1:
            /*else if (target < arr[mid]) {
                if (arr[mid] > arr[low])
                    if (target >= arr[low])
                        return BS(arr, target, low, mid - 1);
                    else
                        low = mid + 1;
                else high = mid - 1;
            } else {
                if (arr[mid] < arr[high])
                    if (target <= arr[high])
                        return BS(arr, target, mid + 1, high);
                    else
                        high = mid - 1;
                else low = mid + 1;
            }*/

            //method2:
            if (arr[mid].compareTo(arr[low]) > 0)
                if (target.compareTo(arr[low]) >= 0 && target.compareTo(arr[mid]) < 0)
                    return sortedBS(arr, target, low, mid - 1);
                else
                    low = mid + 1;
            else {
                if (target.compareTo(arr[mid]) > 0 && target.compareTo(arr[high]) <= 0)
                    return sortedBS(arr, target, mid + 1, high);
                else
                    high = mid - 1;
            }
            mid = (low + high) / 2;
        }

        return -1;
    }

    public static <T extends Comparable<T>> int sortedBS(T[] arr, T target) {
        return sortedBS(arr, target, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> int sortedBS(T[] arr, T target, int low, int high) {
        int mid = (low + high) / 2;
        while (low <= high) {
            if (arr[mid].compareTo(target) == 0) return mid;
            else if (target.compareTo(arr[mid]) < 0)
                high = mid - 1;
            else
                low = mid + 1;
            mid = (low + high) / 2;
        }
        return -1;
    }
}
