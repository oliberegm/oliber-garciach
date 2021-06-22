package com.wenance.challenge.repository;

import com.wenance.challenge.model.Instrument;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;

public interface InstrumentRepository extends MongoRepository<Instrument, ObjectId> {

    List<Instrument> findByTimeBetween(Long from, Long to);

}
