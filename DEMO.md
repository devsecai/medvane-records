# Medvane — Seeded Findings (26 total)

## SAST (12)
| ID | CWE | File | Severity | Description |
|---|---|---|---|---|
| MED-SAST-001 | CWE-89   | `src/main/java/io/medvane/PatientRepo.java` | High | SQL injection (raw JdbcTemplate string) |
| MED-SAST-002 | CWE-611  | `src/main/java/io/medvane/FhirImport.java`  | High | XXE — DocumentBuilderFactory not hardened |
| MED-SAST-003 | CWE-502  | `src/main/java/io/medvane/AdminCtl.java`    | Critical | Java deserialization (ObjectInputStream) |
| MED-SAST-004 | CWE-798  | `src/main/resources/application.properties` | Critical | Hardcoded DB password |
| MED-SAST-005 | CWE-22   | `src/main/java/io/medvane/FilesCtl.java`    | High | Path traversal in download |
| MED-SAST-006 | CWE-918  | `src/main/java/io/medvane/Fetcher.java`     | High | SSRF (URL.openStream on user URL) |
| MED-SAST-007 | CWE-117  | `src/main/java/io/medvane/AuditLogger.java` | Medium | Log injection (CRLF) |
| MED-SAST-008 | CWE-327  | `src/main/java/io/medvane/Crypto.java`      | Medium | DES + ECB |
| MED-SAST-009 | CWE-352  | `src/main/java/io/medvane/SecCfg.java`      | High | CSRF disabled globally |
| MED-SAST-010 | CWE-79   | `src/main/resources/templates/profile.html` | Medium | Unescaped Thymeleaf `th:utext` |
| MED-SAST-011 | CWE-209  | `src/main/java/io/medvane/ErrorAdv.java`    | Low  | Stack trace returned in error body |
| MED-SAST-012 | CWE-330  | `src/main/java/io/medvane/Reset.java`       | Medium | java.util.Random for password reset tokens |

## IaC (6)
| ID | Class | File |
|---|---|---|
| MED-IAC-001 | s3-public            | `infra/terraform/main.tf` |
| MED-IAC-002 | rds-public           | `infra/terraform/main.tf` |
| MED-IAC-003 | rds-no-encrypt       | `infra/terraform/main.tf` |
| MED-IAC-004 | container-root       | `Dockerfile` |
| MED-IAC-005 | k8s-no-pod-security  | `infra/k8s/deployment.yaml` |
| MED-IAC-006 | k8s-host-path        | `infra/k8s/deployment.yaml` |

## SCA (5)
| ID | Artifact | Version | CVE |
|---|---|---|---|
| MED-SCA-001 | `org.apache.logging.log4j:log4j-core` | 2.14.1 | CVE-2021-44228 (Log4Shell) |
| MED-SCA-002 | `com.fasterxml.jackson.core:jackson-databind` | 2.9.10 | CVE-2019-14540 |
| MED-SCA-003 | `org.springframework:spring-core` | 5.3.18 | CVE-2022-22965 (Spring4Shell) |
| MED-SCA-004 | `org.yaml:snakeyaml` | 1.32 | CVE-2022-1471 |
| MED-SCA-005 | `commons-collections:commons-collections` | 3.2.1 | CVE-2015-7501 |

## Pipeline (3)
| ID | File | Description |
|---|---|---|
| MED-CI-001 | `.github/workflows/ci.yml` | Hardcoded GHCR token |
| MED-CI-002 | `.github/workflows/ci.yml` | Maven uses `http://` repo |
| MED-CI-003 | `.github/workflows/ci.yml` | `pull_request_target` + checkout head ref |
