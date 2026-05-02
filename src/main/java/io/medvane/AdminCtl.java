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
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(raw))) {
            // MED-SAST-003: gadget-chain RCE via readObject.
            Object obj = ois.readObject();
            return "applied=" + obj.getClass().getSimpleName();
        }
    }
}
