// MED-SAST-012: java.util.Random for password reset tokens.
package io.medvane;

import java.util.Random;

public class Reset {
    private static final Random rng = new Random();

    public static String token() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            sb.append((char) ('a' + rng.nextInt(26)));
        }
        return sb.toString();
    }
}
