package s9.apr.giftservices.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tutor")
public class Tutor extends Intern{

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tutor_id")
    private final List<Student> students = new ArrayList<>();
}
