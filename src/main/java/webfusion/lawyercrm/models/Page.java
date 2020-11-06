package webfusion.lawyercrm.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "pages")
@Data
public class Page {
    @Id
    private String pageName;
    @Column(length = 99999)
    private String text;
}
