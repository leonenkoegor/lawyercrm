package webfusion.lawyercrm.models;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "pages")
@Data
public class Page {
    @Id
    private String pageName;
    @Column(length = 99999)
    private String text;
}
