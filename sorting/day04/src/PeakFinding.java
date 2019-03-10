public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int peak = peakOneD(nums.length/2, nums);
        if (peak == 0) {
            return nums.length/2;
        } else if (peak == -1) {
            int[] arr = new int[nums.length/2];
            System.arraycopy(nums, 0, arr, 0, nums.length/2);
            return findOneDPeak(arr);
        } else {
            int[] arr = new int[nums.length - nums.length/2];
            System.arraycopy(nums, nums.length/2, arr, 0, nums.length - nums.length/2);
            return findOneDPeak(arr) + nums.length/2;
        }
    }

    public static int[] findTwoDPeak(int[][] nums) {
        int loX = 0;
        int loY = 0;
        int hiX = nums[0].length;
        int hiY = nums.length;
        int curr = 0;
        int end = nums.length*nums[0].length - 1;
        int midX, midY = 0;
        while (curr <= end) {
            midX = loX + (hiX - loX) / 2;
            int mY = maxYIndex(midX, loY, hiY, nums);
            int peakLR = peakX(midX, mY, nums);
            if (peakLR == 0) {
                return new int[] {mY, midX};
            }
            else if (peakLR == -1) hiX = midX;
            else if (peakLR == 1) {
                loX = midX;
            }
            midY = loY + (hiY - loY) / 2;
            int mX = maxXIndex(midY, loX, hiX, nums);
            int peakUD = peakY(mX, midY, nums);
            if (peakUD == 0) {
                return new int[] {midY, mX};
            }
            else if (peakUD == -1) {
                hiY = midY;
            }
            else if (peakUD == 1) {
                loY = midY;
            }
            curr++;
        }
        return new int[] {0, 0};

    }

}
