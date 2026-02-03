package com.darksoft.noteflow.backend.infrastructure.persistence.jpa;

import com.darksoft.noteflow.backend.application.ports.INoteRepository;
import com.darksoft.noteflow.backend.domain.entities.Note;
import com.darksoft.noteflow.backend.domain.valueobjects.NoteId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class DataBaseNoteRepository implements INoteRepository {

    private final JpaNoteRepository repository;

    public DataBaseNoteRepository(JpaNoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Note save(Note note) {
        NoteEntity entity = NoteMapper.toEntity(note);
        NoteEntity saved = this.repository.save(entity);
        return NoteMapper.toDomain(saved);
    }

    @Override
    public Optional<Note> findById(NoteId id) {
        return this.repository.findById(id.id()).map(NoteMapper::toDomain);
    }

    @Override
    public void deleteById(NoteId id) {
        this.repository.deleteById(id.id());
    }

    @Override
    public Map<String, Object> findAll(int size, int page) {
        PageRequest pageable = PageRequest.of(page,size);
        Page<NoteEntity> notesEntityPage = this.repository.findAll(pageable);
        Page<Note> notes = notesEntityPage.map(NoteMapper::toDomain);

        return Map.of("notes",notes.getContent(),
                "total-pages",notes.getTotalPages(),
                "total-elements",notes.getTotalElements());
    }
}
