package cn.dttn.algorithm;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;

public class Sort {

    public static <T extends Comparable<T>> void insertSort(T[] arr) {
        insertSort(arr, 0, arr.length);
    }

    public static <T extends Comparable<T>> void insertSort(T[] arr, int fromIndex, int toIndex) {
        for (int i = fromIndex + 1; i < toIndex; i++) {
            T x = arr[i];
            int j = i - 1;
            while (j >= fromIndex && x.compareTo(arr[j]) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = x;
        }
    }

    public static <T extends Comparable<T>> void selectSort(T[] arr) {
        selectSort(arr, 0, arr.length);
    }

    public static <T extends Comparable<T>> void selectSort(T[] arr, int fromIndex, int toIndex) {
        for (int i = fromIndex; i < toIndex - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < toIndex; j++)
                if (arr[j].compareTo(arr[minIndex]) < 0)
                    minIndex = j;
            if (minIndex != i) {
                T t = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = t;
            }
        }
    }

    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        bubbleSort(arr, 0, arr.length);
    }

    public static <T extends Comparable<T>> void bubbleSort(T[] arr, int fromIndex, int toIndex) {
        for (int i = fromIndex; i < toIndex - 1; i++)
            for (int j = toIndex - 1; j > i; j--)
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    T t = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = t;
                }
    }

    public static <T extends Comparable<T>> void mergeSort(T[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(T[] arr, int low, int high) {
        if (low == high) return;
        int mid = (low + high) / 2;

        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);
        merge(arr, low, mid, high);

    }

    private static <T extends Comparable<T>> void merge(T[] arr, int low, int mid, int high) {
        T[] t = (T[]) Array.newInstance(arr[0].getClass(), high - low + 1);
        int i = low, j = mid + 1, k = 0;

        while (i <= mid && j <= high)
            if (arr[i].compareTo(arr[j]) < 0)
                t[k++] = arr[i++];
            else
                t[k++] = arr[j++];

        while (i <= mid)
            t[k++] = arr[i++];
        while (j <= high)
            t[k++] = arr[j++];

        for (int l = low; l <= high; l++) {
            arr[l] = t[l - low];
        }
    }

    public static <T extends Comparable<T>> void quickSort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] arr, int low, int high) {
        if (low >= high) return;
        int p = partition(arr, low, high);
        quickSort(arr, low, p - 1);
        quickSort(arr, p + 1, high);
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int low, int high) {
        int j = low, i = low - 1;
        while (j < high) {
            if (arr[j].compareTo(arr[high]) <= 0) {
                i++;
                T t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                /*swap(arr[i], arr[j]);*/
            }
            j++;
        }
        T t = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = t;
        /*swap(arr[i + 1], arr[high]);*/

        return i + 1;
    }

    /*private static <T extends Comparable<T>> void swap(T t1, T t2) {
        T t = t1;
        t1 = t2;
        t2 = t;
    }*/

    public static <T> void radixSort(T[] arr, int k, Character[] radix) {
        radixSort(arr, 0, arr.length, k, radix);
    }

    private static <T> void radixSort(T[] arr, int fromIndex, int toIndex, int k, Character[] radix) {
        Map<Character, Integer> radix2Index = new HashMap<>();
        for (int i = 0; i < radix.length; i++)
            radix2Index.put(radix[i], i);

        for (int i = 0; i < k; i++) {
            List<T>[] lists = new ArrayList[radix.length];
            for (int j = fromIndex; j < toIndex; j++) {
                Character ci;
                try {
                    ci = new StringBuilder(arr[j].toString()).reverse().charAt(i);
                } catch (Exception e) {
                    ci = radix[0];
                }
                Integer indexOfCi = radix2Index.get(ci);
                List<T> list = lists[indexOfCi];
                if (list == null)
                    list = lists[indexOfCi] = new ArrayList<>();
                list.add(arr[j]);
            }

            int j = 0;
            for (List<T> list : lists)
                if (list != null)
                    for (T item : list)
                        arr[j++] = item;
        }
    }

    public static void countSort(Integer[] arr, int bound) {
        countSort(arr, 0, arr.length, bound);
    }

    public static void countSort(Integer[] arr, int fromIndex, int toIndex, int bound) {
        Integer[] countArr = new Integer[bound];
        Arrays.fill(countArr, 0);
        for (Integer anArr : arr)
            countArr[anArr]++;

        int j = toIndex - 1;
        for (int i = bound - 1; i >= 0; i--) {
            while (countArr[i] != 0 && j >= fromIndex) {
                arr[j--] = i;
                countArr[i]--;
            }
        }
    }

    public static <T extends Comparable<T>> void heapSort(T[] arr) {
        heapSort(arr, 0, arr.length);
    }

    public static <T extends Comparable<T>> void heapSort(T[] arr, int fromIndex, int toIndex) {
        for (int i = (toIndex - 2) / 2; i >= fromIndex; i--)
            shift(arr, i, toIndex - 1);

        for (int i = toIndex - 1; i > fromIndex; i--) {
            T t = arr[i];
            arr[i] = arr[fromIndex];
            arr[fromIndex] = t;
            shift(arr, fromIndex, i - 1);
        }

    }

    private static <T extends Comparable<T>> void shift(T[] arr, int i, int to) {
        int l = 2 * i + 1;
        int r = 2 * (i + 1);

        if (l > to || //
                (l == to && arr[i].compareTo(arr[l]) >= 0) || //
                (arr[i].compareTo(arr[l]) >= 0 && arr[i].compareTo(arr[r]) >= 0))
            return;

        if (r <= to && arr[r].compareTo(arr[l]) > 0) {
            T t = arr[i];
            arr[i] = arr[r];
            arr[r] = t;
            shift(arr, r, to);
        } else {
            T t = arr[i];
            arr[i] = arr[l];
            arr[l] = t;
            shift(arr, l, to);
        }
    }

    public static <T extends Comparable<T>> void bucketSort(T[] arr) {
        bucketSort(arr, 0, arr.length);
    }

    public static <T extends Comparable<T>> void bucketSort(T[] arr, int fromIndex, int toIndex) {
        Character[] radix = new Character[10];
        for (int i = 0; i < radix.length; i++)
            radix[i] = Character.forDigit(i, 10);

        bucketSort(arr, fromIndex, toIndex, radix);

    }

    private static <T extends Comparable<T>> void bucketSort(T[] arr, int fromIndex, int toIndex, Character[] radix) {
        Map<Character, Integer> radix2Index = new HashMap<>();
        List<T>[] buckets = new ArrayList[radix.length];
        for (int i = 0; i < radix.length; i++) {
            radix2Index.put(radix[i], i);
            buckets[i] = new ArrayList<>();
        }

        for (int i = fromIndex; i < toIndex; i++) {
            Character initial = arr[i].toString().charAt(0);
            List<T> bucket = buckets[radix2Index.get(initial)];
            bucket.add(arr[i]);
        }

        int k = fromIndex;
        for (List<T> bucket : buckets) {
            for (T t : bucket) {
                arr[k++] = t;
            }
            insertSort(arr, 0, k);
        }
    }

    public static <T extends Comparable<T>> void sort(Consumer<T[]> consumer, T[] arr) {
        consumer.accept(arr);
    }

}
