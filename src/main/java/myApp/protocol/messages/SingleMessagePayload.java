package myApp.protocol.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myApp.protocol.AbstractPayload;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleMessagePayload extends AbstractPayload {
    private String text;
    private String nickname;
    private Date date;
}
