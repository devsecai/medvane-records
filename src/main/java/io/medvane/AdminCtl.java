// MED-SAST-003: Java deserialisation via ObjectInputStream on request body.
package io.medvane;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.Base64;

@RestController
@RequestMapping("/admin")
public class AdminCtl {
    @PostMapping("/import-config")
    public String importConfig(@RequestBody String b64) throws Exception {
        byte[] raw = Base64.getDecoder().decode(b64);
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(raw)) {
            @Override
            protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
                // Whitelist only safe configuration classes
                String className = desc.getName();
                if (className.equals("java.util.HashMap") ||
                    className.equals("java.util.ArrayList") ||
                    className.equals("java.lang.String") ||
                    className.equals("java.lang.Integer") ||
                    className.equals("java.lang.Boolean") ||
                    className.equals("java.lang.Long") ||
                    className.equals("java.lang.Double") ||
                    className.startsWith("[L") || // Arrays of whitelisted types
                    className.startsWith("io.medvane.config.")) { // Application config classes
                    return super.resolveClass(desc);
                }
                throw new InvalidClassException("Unauthorized deserialization attempt: " + className);
            }
        }) {
            // MED-SAST-003: gadget-chain RCE via readObject.
            Object obj = ois.readObject();
            return "applied=" + obj.getClass().getSimpleName();
        }
    }
}
