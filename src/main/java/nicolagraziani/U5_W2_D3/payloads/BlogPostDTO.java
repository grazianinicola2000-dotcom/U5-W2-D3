package nicolagraziani.U5_W2_D3.payloads;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record BlogPostDTO(
        @NotBlank(message = "La categoria è obbligatoria e non può essere una stringa vuota")
        String category,
        @NotBlank(message = "Il titolo è obbligatorio e non può essere una stringa vuota")
        @Size(min = 1, max = 100, message = "Il titolo deve avere essere compreso tra 1 e 100 caratteri")
        String title,
        @NotBlank(message = "Il contenuto è obbligatorio e non può essere una stringa vuota")
        @Size(min = 10, message = "Il contenuto deve avere almeno 10 caratteri")
        String content,
        @Min(value = 1, message = "Il tempo di lettura non può essere meno di 1 minuto")
        @Max(value = 60, message = "Il tempo di lettura non può superare i 60 minuti")
        int readingTime,
        @NotNull(message = "L'Id dell'autore è obbligatorio")
        UUID authorId
) {
}
