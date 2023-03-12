package s9.apr.giftservices.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_internship")
public class StudentInternship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "mission", nullable = false)
    private String mission;
    @Column(name = "company_name", nullable = false)
    private String companyName;
    @Column(name = "company_tutor_name", nullable = false)
    private String companyTutorName;
    @Column(name = "starting_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate startingDate;
    @Column(name = "ending_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate endingDate;
    @Column(name = "comment")
    private String comment;
}
