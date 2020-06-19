package myApp.protocol.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myApp.models.Message;
import myApp.protocol.AbstractPayload;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessagesResponsePayload extends AbstractPayload {
    private List<Message> messages;
}
