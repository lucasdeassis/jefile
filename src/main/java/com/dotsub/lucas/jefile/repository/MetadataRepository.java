package com.dotsub.lucas.jefile.repository;

import org.springframework.data.repository.CrudRepository;

import com.dotsub.lucas.jefile.domain.Metadata;

public interface MetadataRepository extends CrudRepository<Metadata, Long> {
  Metadata findByName(String name);
  
  boolean existsByName(String name);
}
