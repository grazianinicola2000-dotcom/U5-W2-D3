package nicolagraziani.U5_W2_D3.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
public class AuthorPayload {
    private String name;
    private String surname;
    private String email;
    private LocalDate dateOfBirth;
}
