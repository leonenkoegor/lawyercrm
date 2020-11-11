package webfusion.lawyercrm.models;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "files")
@Data
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mime;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] data;
}
