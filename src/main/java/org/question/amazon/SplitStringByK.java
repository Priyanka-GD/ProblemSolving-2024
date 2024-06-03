package org.question.amazon;

import java.util.HashMap;
import java.util.Map;

public class SplitStringByK {

    public static int countValidSplits(String s, int k) {
        int n = s.length();
        int validSplits = 0;

        if (n == 0)
            return 0;

        // Initialize the maps for prefix and suffix character counts
        Map<Character, Integer> prefixMap = new HashMap<>();
        Map<Character, Integer> suffixMap = new HashMap<>();

        // Populate the suffix map with the entire string's character counts
        for (char ch : s.toCharArray()) {
            suffixMap.put(ch, suffixMap.getOrDefault(ch, 0) + 1);
        }

        // Iterate through the string to count valid splits
        for (int idx = 0; idx < n - 1; idx++) {
            char currentChar = s.charAt(idx);

            // Update prefix and suffix maps
            prefixMap.put(currentChar, prefixMap.getOrDefault(currentChar, 0) + 1);
            suffixMap.put(currentChar, suffixMap.get(currentChar) - 1);

            // Remove the character from the suffix map if its count drops to zero
            if (suffixMap.get(currentChar) == 0) {
                suffixMap.remove(currentChar);
            }

            // Count common characters between prefix and suffix maps
            int countOfCharsCommon = findDistinctCharCountCommonInString(prefixMap, suffixMap);

            if (countOfCharsCommon > k) {
                validSplits++;
            }
        }

        return validSplits;
    }

    private static int findDistinctCharCountCommonInString(Map<Character, Integer> prefixMap,
                                                           Map<Character, Integer> suffixMap) {
        int countOfCharsCommon = 0;
        for (Map.Entry<Character, Integer> entry : prefixMap.entrySet()) {
            if (suffixMap.containsKey(entry.getKey())) {
                countOfCharsCommon++;
            }
        }
        return countOfCharsCommon;
    }

    public static void main(String[] args) {
        String s = "abbcac";
        int k = 1;
        System.out.println("Number of splits: " + countValidSplits(s, k)); // Output should be 3
    }
}
