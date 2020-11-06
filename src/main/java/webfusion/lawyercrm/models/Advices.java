package webfusion.lawyercrm.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "advices")
@Data
public class Advices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private String title;
    @Column(length = 99999)
    private String text;

}
