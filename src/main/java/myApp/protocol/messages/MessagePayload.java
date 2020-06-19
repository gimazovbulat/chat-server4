package myApp.protocol.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myApp.protocol.AbstractPayload;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessagePayload extends AbstractPayload {
    private String text;
}
