package myApp.protocol.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myApp.protocol.AbstractPayload;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class CommandPayload extends AbstractPayload {
    private String command;
}
