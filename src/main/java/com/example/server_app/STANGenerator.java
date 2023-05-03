package com.example.server_app;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class STANGenerator {

    private static final int STAN_MIN_VALUE = 000000;
    private static final int STAN_MAX_VALUE = 999999;
    private static final Set<Integer> usedSTANs = new HashSet<>();

    public static int generateSTAN() {
        int stan = 0;
        boolean stanAlreadyUsed = true;
        while (stanAlreadyUsed) {
            stan = new Random().nextInt(STAN_MAX_VALUE - STAN_MIN_VALUE + 1) + STAN_MIN_VALUE;
            stanAlreadyUsed = usedSTANs.contains(stan);
        }
        usedSTANs.add(stan);
        return stan;
    }
    public static void main(String[] args) {
         generateSTAN();
    }
}
