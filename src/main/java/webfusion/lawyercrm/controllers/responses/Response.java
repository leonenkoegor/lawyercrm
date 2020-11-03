package webfusion.lawyercrm.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response {
    private String status;
    private String message;
    private Object data;
}
