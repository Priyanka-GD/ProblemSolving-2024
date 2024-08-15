package org.question.tiktok;

import java.io.IOException;
import java.util.Arrays;

public class ServerUpgrade {

        public static void main(String args[])throws IOException {
            int money[] = {8,9};
            int numServers[] = {4,3};
            int upgrade[] = {4,5};
            int sell[] = {4, 2};
            int count[] = maximizeUpgradingServerCount(money, numServers, upgrade, sell);

            for(int idx = 0; idx < count.length; idx++)
                System.out.println("Server " + idx + " : " + count[idx]);
        }

        private static int[] maximizeUpgradingServerCount(int[] money, int[] numServers, int[] upgrade, int[] sell) {
            int size = numServers.length;
            int result[] = new int[size];

            for(int idx = 0; idx < size; idx++) {
                // Calculate how many servers can be upgraded with the initial funds
                int numberOfServerToUpgradeFromFund = Math.min(numServers[idx], money[idx] / upgrade[idx]);

                // Calculate additional funds if one server is sold
                int additionalFundIfServerSold = money[idx] + sell[idx];
                int numberOfServerToUpgradeFromNewFund = Math.min(numServers[idx] - 1, additionalFundIfServerSold / upgrade[idx]);

                // Maximize the number of upgraded servers
                result[idx] = Math.max(numberOfServerToUpgradeFromFund, numberOfServerToUpgradeFromNewFund);
            }
            return result;
        }
    }

