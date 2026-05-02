// MED-SAST-011: stack trace leaked in error body.
package io.medvane;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.io.*;

@ControllerAdvice
public class ErrorAdv {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> any(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return ResponseEntity.status(500).body(sw.toString());
    }
}
