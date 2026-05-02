// MED-SAST-008: DES + ECB.
package io.medvane;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {
    public static byte[] encrypt(byte[] plain, byte[] key) throws Exception {
        Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");   // MED-SAST-008
        c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "DES"));
        return c.doFinal(plain);
    }
}
