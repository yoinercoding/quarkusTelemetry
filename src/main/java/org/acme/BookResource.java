package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    private static final String COUNT = "/count";
    private static final String ID = "{id}";

    @Inject
    BookRepository repository;

    @Inject
    Logger logger;

    @Inject
    Tracer tracer;

    @GET
    public List<Book> getAllBooks() {
        Span span = tracer.spanBuilder("getAllBooks").startSpan();
        try {
            logger.info("Returns All Books");
            return repository.getAllBooks();
        } finally {
            span.end();
        }
    }

    @GET
    @Path(COUNT)
    @Produces(MediaType.TEXT_PLAIN)
    public int countAllbooks() {
        Span span = tracer.spanBuilder("countAllBooks").startSpan();
        try {
            logger.info("Returns the number of books");
            return repository.getAllBooks().size();
        } finally {
            span.end();
        }
    }

    @GET
    @Path(ID)
    public Optional<Book> getBook(@PathParam("id") int id) {
        Span span = tracer.spanBuilder("getBook").startSpan();
        try {
            logger.info("Returns a single book with id " + id);
            return repository.getBook(id);
        } finally {
            span.end();
        }
    }
}