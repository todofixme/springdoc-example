package de.codecentric.javaspring.persistence;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final Map<UUID, Author> store = new ConcurrentHashMap<>();

    public Author get(UUID id) {
        if (!store.containsKey(id)) {
            throw new NotFoundException("Author", id);
        }
        return store.get(id);
    }

    public Author save(Author author) {
        store.put(author.id(), author);
        return author;
    }

    public void delete(UUID id) {
        if (!store.containsKey(id)) {
            throw new NotFoundException("Author", id);
        }
        store.remove(id);
    }

    public PaginatedResult<Author> list(String search, int page, int perPage) {
        final List<Author> allAuthors = List.copyOf(store.values());
        final Stream<Author> filteredAuthors;
        if (search == null) {
            filteredAuthors = allAuthors.stream();
        } else {
            filteredAuthors = allAuthors.stream()
                    .filter(author -> author.firstName().startsWith(search) || author.lastName().startsWith(search));
        }
        final List<Author> sortedAuthors = filteredAuthors.sorted(Comparator.comparing(Author::lastName)).toList();

        int totalAuthors = sortedAuthors.size();
        int totalPages = (int) Math.ceil((double) totalAuthors / perPage);

        int start = (page - 1) * perPage;
        int end = Math.min(start + perPage, totalAuthors);

        final List<Author> paginatedAuthors =
                sortedAuthors.subList(Math.min(start, totalAuthors), Math.min(end, totalAuthors));

        return new PaginatedResult<>(paginatedAuthors, totalAuthors, totalPages);
    }
}
