package s9.apr.giftservices.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String startingDate;
    @Column(name = "ending_date", nullable = false)
    private String endingDate;
    @Column(name = "comment")
    private String comment;
    @Column(name = "company_address", nullable = false)
    private String companyAddress;
    @Column(name="specifications", columnDefinition = "boolean default false")
    private boolean specifications;
    @Column(name="visit_form", columnDefinition = "boolean default false")
    private boolean visitForm;
    @Column(name="evaluation_form", columnDefinition = "boolean default false")
    private boolean evaluationForm;
    @Column(name="web_survey", columnDefinition = "boolean default false")
    private boolean webSurvey;
    @Column(name="report_sent", columnDefinition = "boolean default false")
    private boolean reportSent;
    @Column(name="oral_presentation", columnDefinition = "boolean default false")
    private boolean oralPresentation;
    @Column(name="visit_planned", columnDefinition = "boolean default false")
    private boolean visitPlanned;
    @Column(name="visit_done", columnDefinition = "boolean default false")
    private boolean visitDone;
}
