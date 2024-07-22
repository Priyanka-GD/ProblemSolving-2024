
package org.question.amazon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubRange {
    public static void main(String args[]) throws IOException
    {
        //int input[] = {9,3,1,2,3,9,10};
        int input[] = {9,3,3,3,9};
        int count = getSubRangeCount(input);
        System.out.println(count);
    }
    public static int getSubRangeCount(int[] input)
    {
        int count = 0;
        int len = input.length;
        int prefixSum[] = new int[len + 1];
        Map<Integer, List<Integer>> numIdxMap = new HashMap<>();

        //Calculate the prefix sum
        prefixSum[0] = input[0];
        for(int idx = 1; idx < len; idx++)
            prefixSum[idx] = prefixSum[idx - 1] + input[idx];

        for(int idx = 0; idx < len; idx++) {
            numIdxMap.putIfAbsent(input[idx], new ArrayList<>());
            numIdxMap.get(input[idx]).add(idx);
        }

        for(int idx = 2; idx < len; idx++)
        {
            List<Integer> indices = numIdxMap.get(input[idx]);
            for(int index : indices)
            {
                if(index < idx)
                {
                    if(idx - 1 > 0) {
                        int sum = prefixSum[idx - 1] - prefixSum[index];
                        if(sum == input[idx])
                            count++;
                    }
                }
            }
        }
        return count;
    }
}