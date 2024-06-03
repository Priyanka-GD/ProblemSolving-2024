package org.question.amazon;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FindSubStringCountWithKOrMoreFreq {
    public static void main(String[] args) throws IOException {
        String input = "ceccca";
        int k = 3;
        int countOfSubString = findCountOfSubString(input, k);
        System.out.println("Count of substrings: " + countOfSubString); // Output should match the expected count
    }

    public static int findCountOfSubString(String input, int k) {
        Map<Character, Integer> charFreqMap = new HashMap<>();
        int count = 0;
        int left = 0;

        for (int right = 0; right < input.length(); right++) {
            char ch = input.charAt(right);
            charFreqMap.put(ch, charFreqMap.getOrDefault(ch, 0) + 1);

            while (charFreqMap.get(ch) >= k) {
                count += input.length() - right;
                char charLeft = input.charAt(left);
                charFreqMap.put(charLeft, charFreqMap.get(charLeft) - 1);
                left++;
            }
        }
        return count;
    }
}
