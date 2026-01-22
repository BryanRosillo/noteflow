package com.darksoft.noteflow.backend.application.usecases.createnote;

import com.darksoft.noteflow.backend.application.ports.INoteRepository;
import com.darksoft.noteflow.backend.domain.entities.Note;
import com.darksoft.noteflow.backend.domain.entities.Tag;
import com.darksoft.noteflow.backend.domain.valueobjects.NoteId;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
public class CreateNoteUseCase {

    private INoteRepository repository;

    public CreateNoteUseCase(INoteRepository repository) {
        this.repository = repository;
    }

    public Note execute(CreateNoteCommand command){
        Tag[] tags=null;

        if(command.getTags()!=null){
            tags = Arrays.stream(command.getTags()).map(Tag::new).toArray(Tag[]::new);
        }

        Note note = new Note(
                new NoteId(UUID.randomUUID()),
                command.getTitle(),
                command.getContent(),
                tags
        );

        return this.repository.save(note);
    }
}
