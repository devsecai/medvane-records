// MED-SAST-002: XXE — DocumentBuilderFactory not hardened.
package io.medvane;

import org.springframework.stereotype.Component;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.InputStream;

@Component
public class FhirImport {
    public Document parse(InputStream xml) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // MED-SAST-002: should disable doctype + external entities.
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(xml);
    }
}
