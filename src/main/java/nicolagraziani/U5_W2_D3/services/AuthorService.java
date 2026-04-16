package nicolagraziani.U5_W2_D3.services;

import lombok.extern.slf4j.Slf4j;
import nicolagraziani.U5_W2_D3.entities.Author;
import nicolagraziani.U5_W2_D3.exceptions.BadRequestException;
import nicolagraziani.U5_W2_D3.exceptions.NotFoundException;
import nicolagraziani.U5_W2_D3.payloads.AuthorDTO;
import nicolagraziani.U5_W2_D3.repositories.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(AuthorDTO body) {
        if (this.authorRepository.existsByEmail(body.email())) {
            throw new BadRequestException("L'indirizzo mail " + body.email() + " è già in uso!");
        }
        Author newAuthor = new Author(body.name(), body.surname(), body.email(), body.dateOfBirth());
        this.authorRepository.save(newAuthor);
        log.info("L'autore {} {} è stato registrato correttamente", body.surname(), body.name());
        return newAuthor;
    }

    public Page<Author> findAll(int page, int size, String sortBy) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.authorRepository.findAll(pageable);
    }

    public Author findAuthorById(UUID authorId) {
        return this.authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    public Author findAuthorByIdAndUpdate(UUID authorId, AuthorDTO body) {
        Author found = this.findAuthorById(authorId);
        if (!found.getEmail().equals(body.email())) {
            if (this.authorRepository.existsByEmail(body.email()))
                throw new BadRequestException("L'indirizzo email " + body.email() + " è già in uso!");
        }
        found.setName(body.name());
        found.setSurname(body.surname());
        found.setEmail(body.email());
        found.setDateOfBirth(body.dateOfBirth());

        Author saved = this.authorRepository.save(found);

        log.info("L'autore {} {} è stato modificato con successo", saved.getSurname(), saved.getName());

        return saved;
    }

    public void findAuthorByIdAndDelete(UUID authorId) {
        Author found = this.findAuthorById(authorId);
        this.authorRepository.delete(found);
        log.info("L'autore {} {} è stato eliminato correttamente", found.getSurname(), found.getName());
    }
}
