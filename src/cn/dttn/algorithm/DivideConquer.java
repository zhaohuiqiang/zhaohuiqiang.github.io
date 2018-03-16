package cn.dttn.algorithm;

public class DivideConquer {
    public static void main(String[] args) {

    }

    public static int maxProduct(int[] nums) {
        return maxProduct(nums, 0, nums.length - 1);
    }

    public static int maxProduct(int[] nums, int low, int high) {
        if (low == high) return nums[low];
        int mid = (low + high) / 2;

        int leftMax = maxProduct(nums, low, mid);
        int rightMax = maxProduct(nums, mid + 1, high);
        int midP = 1, posMax = 0, mlMax = 0, mlMin = 0, midMax = 0, mrMax = 0, mrMin = 0, negMax = 0, pMax = 1;

        for (int i = mid; i >= low; i--) {
            midP *= nums[i];
            if (midP > mlMax)
                mlMax = midP;
            if (midP < mlMin)
                mlMin = midP;
        }
        midP = 1;
        for (int j = mid + 1; j <= high; j++) {
            midP *= nums[j];
            if (midP > mrMax)
                mrMax = midP;
            if (midP < mrMin)
                mrMin = midP;
        }

        if (mlMax > 0 && mrMax > 0)
            posMax = mlMax * mrMax;
        if (mlMin < 0 && mrMin < 0)
            negMax = mlMin * mrMin;

        midMax = Math.max(posMax, negMax);
        pMax = Math.max(Math.max(leftMax, rightMax), midMax);

        return pMax;

    }

}
