/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Your goal is to reach out of the indices in the minimum number of jumps.
Print out the path.
*/
import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        
        Scanner sc = new Scanner(System.in);
        List<Integer> inputList = new ArrayList<>();
        while (sc.hasNextInt()) {
            inputList.add(sc.nextInt());
        }
        sc.close();
        
        // input
        int[] nums = new int[inputList.size()];
        for (int i = 0; i < inputList.size(); i++) {
            nums[i] = inputList.get(i).intValue();
        }
        
        // corner cases
        if (nums.length == 0 || nums[0] <= 0) {
            System.out.println("failure");
            return;
        }
        
        
        int[] preIdx = new int[nums.length];
		Arrays.fill(preIdx, -1);
		List<Integer> res = new ArrayList<>();
		int high = 0, low = 0, preHigh = 0;
        
        while(high <= nums.length - 1){
             
            preHigh = high;
            
            // update range high, and update int[] preIdx
            for(int i = low; i <= preHigh; i++){
            	if (high < i + nums[i]) {
            		high = i + nums[i];
            		if (i + nums[i] <= nums.length - 1) {
            			for (int j = preHigh + 1; j <= i + nums[i]; j++) 
                            preIdx[j] = i;
            		} else {
                        
                        // finish
            			res.add(i);
            			while (preIdx[i] != -1) {
            				res.add(preIdx[i]); 
            				i = preIdx[i];
            			}
            		}
            	}
            }
            
            // can't move forward
            if (high == preHigh) {
            	System.out.println("failure");
            	return;
            }
            
            low = preHigh + 1;
        }
        
        // output result
        for (int i = res.size() - 1; i >= 0; i--) {
            System.out.print(res.get(i) + ", ");
        }
        
        System.out.print("out");
    }
}