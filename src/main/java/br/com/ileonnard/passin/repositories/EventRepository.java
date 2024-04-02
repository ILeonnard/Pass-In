package br.com.ileonnard.passin.repositories;

import br.com.ileonnard.passin.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
}
