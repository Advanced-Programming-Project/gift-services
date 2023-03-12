package s9.apr.giftservices.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import s9.apr.giftservices.entities.StudentInternship;

import java.util.List;
@Data
@AllArgsConstructor
public class StudentDTO {
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String promotion;
    private List<StudentInternship> studentInternship;
}
