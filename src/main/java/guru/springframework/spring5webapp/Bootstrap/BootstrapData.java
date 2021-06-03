package guru.springframework.spring5webapp.Bootstrap;

import guru.springframework.spring5webapp.Models.Author;
import guru.springframework.spring5webapp.Models.Book;
import guru.springframework.spring5webapp.Models.Publisher;
import guru.springframework.spring5webapp.Repositories.AuthorRepository;
import guru.springframework.spring5webapp.Repositories.BookRepository;
import guru.springframework.spring5webapp.Repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher su = new Publisher("publisher101", "jaggernath lachmonstraat", "kwatta", "paramaribo", "12345");
        publisherRepository.save(su);

        Author george = new Author("George", "RR Martin");
        Book got = new Book("Game Of Thrones", "123213");
        george.getBooks().add(got);
        got.getAuthors().add(george);
        got.setPublisher(su);
        su.getBooks().add(got);
        authorRepository.save(george);
        bookRepository.save(got);
        publisherRepository.save(su);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3648927464");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(su);
        su.getBooks().add(noEJB);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(su);

        System.out.println("Started Repo");
        System.out.println("Books: " + bookRepository.count());
        System.out.println("Authors: " + authorRepository.count());
        System.out.println("publisher: " +publisherRepository.count());
        System.out.println("Publisher number of books: " + su.getBooks().size());

    }
}
