package myApp.protocol.status;

import lombok.Data;
import myApp.protocol.AbstractPayload;

@Data
public class SuccessRespPayload extends AbstractPayload {
    private final String text = "Action successfully happened!";
}
