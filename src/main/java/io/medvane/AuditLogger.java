// MED-SAST-007: log injection — user input contains CRLF, written unmodified.
package io.medvane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditLogger {
    private static final Logger log = LoggerFactory.getLogger("audit");

    public static void event(String actor, String action) {
        // MED-SAST-007: sanitize CRLF characters to prevent log injection
        String sanitizedActor = sanitizeCRLF(actor);
        String sanitizedAction = sanitizeCRLF(action);
        log.info("audit actor={} action={}", sanitizedActor, sanitizedAction);
    }

    private static String sanitizeCRLF(String input) {
        if (input == null) {
            return null;
        }
        return input.replace("\r", "").replace("\n", "");
    }
}
