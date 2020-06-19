package myApp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDto extends ResponseDto {
    private String nickname;
    private String email;
    private Long id;
    private String token;

    public UserDto(Type type, String nickname, String email, Long id, String token) {
        super("user", type);
        this.nickname = nickname;
        this.email = email;
        this.id = id;
        this.token = token;
    }
}
