public class FindFirstAndLastPosition {


    //Two binary searches. 
    //First one to find the first occurance.
    //  If the elements are same check the previous element if it lesser then we found the first occurance.
    //  if the elements are not same, then move the high to mid-1 
    //Second one to find the last occurance.
    //  If the elements are same check the next element if it lesser then we found the last occurance.
    //  if the elements are not same, then move the lwo to mid+1

    //TC: O(log N) where N is the number of elements.
    //SC: O(1) Not using anu auxilary space.
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[] { -1, -1 };
        if (nums == null || nums.length == 0)
            return result;

        if (target < nums[0] || target > nums[nums.length - 1])
            return new int[] { -1, -1 };
        int low = 0;
        int high = nums.length - 1;

        int left = -1;
        int right = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                if (mid == 0 || nums[mid] > nums[mid - 1]) { //Out of bound check. It should go below 0
                    left = mid;
                    break;
                } else {
                    high = mid - 1;
                }
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        low = 0;
        high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                if (mid == nums.length - 1 || nums[mid] < nums[mid + 1]) { //Out of bound check. It should go above lenght of the array.
                    right = mid;
                    break;
                } else {
                    low = mid + 1;
                }
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new int[] { left, right };
    }


    //TC: O(N) where N is number of elements in given array
    //SC: O(1) Not using any auxilary space.
    public int[] searchRangeBF(int[] nums, int target) {
        if(nums == null || nums.length == 0) return new int[]{-1,-1};
        
        int firstOccurance = -1;
        int secondOccurance = -1;
        for(int i=0;i<nums.length;i++) {
            if(nums[i] == target) {
                if(firstOccurance == -1){
                    firstOccurance = i;
                } else {
                    secondOccurance = i;
                }
            }      
        }
        if(firstOccurance == -1 || secondOccurance == -1) {
            int max = Math.max(firstOccurance, secondOccurance);
            return new int[]{max, max};
        }
        return new int[]{firstOccurance, secondOccurance};
    }

    public static void main(String[] args) {
        FindFirstAndLastPosition findFirstAndLastPosition = new FindFirstAndLastPosition();
        int[] result = findFirstAndLastPosition.searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 8);
        System.out.println("The result: " + result);
    }
}