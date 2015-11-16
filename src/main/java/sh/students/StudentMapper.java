package sh.students;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by AShakhray on 16.11.2015.
 */
public class StudentMapper implements RowMapper<Student> {
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Student(
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("second_name"),
                rs.getString("group_name"),
                rs.getString("student_id"),
                rs.getString("data_of_transfer"),
                rs.getString("faculty")
        );
    }
}
