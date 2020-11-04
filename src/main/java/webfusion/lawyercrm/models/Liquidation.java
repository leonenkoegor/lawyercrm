package webfusion.lawyercrm.models;

import lombok.Data;

import javax.persistence.*;

@Entity()
@Data
public class Liquidation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 99999)
    private String text;
}
