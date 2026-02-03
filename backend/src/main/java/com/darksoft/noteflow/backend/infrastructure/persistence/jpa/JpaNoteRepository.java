package com.darksoft.noteflow.backend.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaNoteRepository extends JpaRepository<NoteEntity, UUID> {
}
