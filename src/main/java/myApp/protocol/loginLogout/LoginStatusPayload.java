package myApp.protocol.loginLogout;

import lombok.Getter;
import lombok.NoArgsConstructor;
import myApp.protocol.AbstractPayload;

@NoArgsConstructor
public class LoginStatusPayload extends AbstractPayload {
    @Getter
    private String text = "Welcome!";
}
