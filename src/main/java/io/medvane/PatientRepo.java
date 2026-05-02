// MED-SAST-001: SQL injection via raw JdbcTemplate string.
package io.medvane;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class PatientRepo {
    private final JdbcTemplate jdbc;
    public PatientRepo(DataSource ds) { this.jdbc = new JdbcTemplate(ds); }

    public List<Map<String, Object>> search(String surname) {
        // MED-SAST-001: surname concatenated into SQL.
        String sql = "SELECT id, mrn, given_name, family_name, dob FROM patients " +
                     "WHERE family_name LIKE '%" + surname + "%'";
        return jdbc.queryForList(sql);
    }
}
