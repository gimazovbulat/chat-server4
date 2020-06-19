package myApp.protocol.loginLogout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myApp.protocol.AbstractPayload;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginPayload extends AbstractPayload {
    private String email;
    private String password;
}
