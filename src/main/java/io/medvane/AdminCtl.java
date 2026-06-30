// MED-SAST-003: Java deserialisation via ObjectInputStream on request body.
package io.medvane;

import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminCtl {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/import-config")
    public String importConfig(@RequestBody String b64) throws Exception {
        byte[] raw = Base64.getDecoder().decode(b64);
        // Use JSON deserialization instead of Java serialization to prevent RCE
        String jsonString = new String(raw, "UTF-8");
        Map<String, Object> config = objectMapper.readValue(jsonString, Map.class);
        return "applied=" + config.getClass().getSimpleName();
    }
}
