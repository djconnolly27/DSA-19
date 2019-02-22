
public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        // TODO

        // You could change all the 0s to -1s, then use a for loop to go through the
        // whole array. As you go through, record the points where the sum of all
        // elements prior to the current element (inclusive I think) is equal to zero. You
        // do this by hashing the value at the current index and then taking the
        // cumulative sum (sum of all elements up to that point). Any time that the hashmap
        // contains two or more values hashed to the same key, you have a subarray to
        // consider. Then look at the difference between those subarray sizes and pick the point where the
        // difference between the indices of the points that sum to zero is greatest.
        return new int[]{0, 0};
    }
}
