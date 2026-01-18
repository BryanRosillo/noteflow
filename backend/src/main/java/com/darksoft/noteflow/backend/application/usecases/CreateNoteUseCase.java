package com.darksoft.noteflow.backend.application.usecases;

import com.darksoft.noteflow.backend.application.ports.INoteRepository;
import com.darksoft.noteflow.backend.domain.entities.Note;
import com.darksoft.noteflow.backend.domain.entities.Tag;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CreateNoteUseCase {

    private INoteRepository repository;

    public CreateNoteUseCase(INoteRepository repository) {
        this.repository = repository;
    }

    public void execute(CreateNoteCommand command){

        var tags = Arrays.stream(command.getTags()).map(Tag::new).toArray(Tag[]::new);

        Note note = new Note(
                command.getTitle(),
                command.getContent(),
                tags
        );

        this.repository.save(note);
    }
}
