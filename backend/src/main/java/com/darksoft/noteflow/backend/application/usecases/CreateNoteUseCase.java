package com.darksoft.noteflow.backend.application.usecases;

import com.darksoft.noteflow.backend.application.ports.INoteRepository;
import com.darksoft.noteflow.backend.domain.entities.Note;
import com.darksoft.noteflow.backend.domain.entities.Tag;
import com.darksoft.noteflow.backend.domain.exceptions.DomainException;
import com.darksoft.noteflow.backend.domain.exceptions.EmptyTitleException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CreateNoteUseCase {

    private INoteRepository repository;

    public CreateNoteUseCase(INoteRepository repository) {
        this.repository = repository;
    }

    public Note execute(CreateNoteCommand command) throws DomainException {

        var tags = Arrays.stream(command.getTags()).map(Tag::new).toArray(Tag[]::new);

        Note note = new Note(
                command.getTitle(),
                command.getContent(),
                tags
        );

        return this.repository.save(note);
    }
}
