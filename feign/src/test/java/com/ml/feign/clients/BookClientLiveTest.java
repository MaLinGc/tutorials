package com.ml.feign.clients;

import com.ml.feign.BookControllerFeignClientBuilder;
import com.ml.feign.models.Book;
import com.ml.feign.models.BookResource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@Slf4j
@RunWith(JUnit4.class)
public class BookClientLiveTest {

    private BookClient bookClient;

    @Before
    public void setUp() {
        BookControllerFeignClientBuilder feignClientBuilder = new BookControllerFeignClientBuilder();
        bookClient = feignClientBuilder.getBookClient();
    }

    @Test
    public void givenBookClient_shouldRunSuccessfully() throws Exception {
        List<Book> books = bookClient.findAll().stream().map(BookResource::getBook).collect(Collectors.toList());
        assertTrue(books.size() > 2);
        log.info("{}", books);
    }

    @Test
    public void givenBookClient_shouldFindOneBook() throws Exception {
        Book book = bookClient.findByIsbn("0151072558").getBook();
        assertThat(book.getAuthor(), containsString("Orwell"));
        log.info("{}", book);
    }

    @Test
    public void givenBookClient_shouldPostBook() throws Exception {
        String isbn = UUID.randomUUID().toString();
        Book book = new Book(isbn, "Me", "It's me!", null, null);
        bookClient.create(book);

        book = bookClient.findByIsbn(isbn).getBook();
        assertThat(book.getAuthor(), is("Me"));
        log.info("{}", book);
    }
}
