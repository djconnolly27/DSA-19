public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        long lo = 0;
        long hi = n;
        long mid = (hi - lo) / 2;
        while (isBadVersion.isFailingVersion(mid) == isBadVersion.isFailingVersion(mid - 1)) {
            if (isBadVersion.isFailingVersion(mid)) {
                hi = mid - 1;
                mid = lo + (hi - lo) / 2;
            } else {
                lo = mid + 1;
                mid = lo + (hi - lo) / 2;
            }

        }
        return mid;
    }
}
