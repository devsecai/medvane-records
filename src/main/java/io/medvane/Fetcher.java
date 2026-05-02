// MED-SAST-006: SSRF — URL.openStream on caller-supplied URL.
package io.medvane;

import org.springframework.stereotype.Component;
import java.net.URL;
import java.io.InputStream;

@Component
public class Fetcher {
    public byte[] fetch(String url) throws Exception {
        try (InputStream is = new URL(url).openStream()) {
            return is.readAllBytes();
        }
    }
}
