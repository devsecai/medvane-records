// MED-SAST-005: path traversal in /files/{name}.
package io.medvane;

import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.*;
import java.nio.file.*;

@RestController
@RequestMapping("/files")
public class FilesCtl {
    private static final Path ROOT = Paths.get("/var/medvane/archive");

    @GetMapping("/{name}")
    public Resource get(@PathVariable String name) throws Exception {
        // MED-SAST-005: ROOT.resolve(name) does not normalize against traversal.
        Path target = ROOT.resolve(name);
        return new FileSystemResource(target);
    }
}
