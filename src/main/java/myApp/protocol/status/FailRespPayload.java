package myApp.protocol.status;

import lombok.Data;
import myApp.protocol.AbstractPayload;

@Data
public class FailRespPayload extends AbstractPayload {
    private final String text = "You don't have enough authorities :(";
}
