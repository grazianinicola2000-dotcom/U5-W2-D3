package nicolagraziani.U5_W2_D3.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AuthorDTO(
        @NotBlank(message = "Il nome è un campo obbligatorio e non può essere una Stringa vuota")
        @Size(min = 2, max = 30, message = "Il nome deve essere compreso tra 2 e 30 caratteri")
        String name,
        @NotBlank(message = "Il cognome è un campo obbligatorio e non può essere una Stringa vuota")
        @Size(min = 2, max = 30, message = "Il cognome deve essere compreso tra 2 e 30 caratteri")
        String surname,
        @NotBlank(message = "L'email è un campo obbligatorio e non può essere una Stringa vuota")
        @Email(message = "L'email inserita non è nel formato corretto")
        String email,
        @NotNull(message = "La data di nascita è obbligatoria")
        @Past
        LocalDate dateOfBirth
//        @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{4,}$", message = "La password deve contenere almeno una maiuscola, una minuscola,....")
) {
}
