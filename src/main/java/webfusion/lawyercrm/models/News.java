package webfusion.lawyercrm.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "news")
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Column(length = 99999)
    private String text;
    private LocalDate date;
}
