package com.darksoft.noteflow.backend.application.usecases.editnote;

import com.darksoft.noteflow.backend.application.exceptions.NoteNotFoundException;
import com.darksoft.noteflow.backend.application.ports.INoteRepository;
import com.darksoft.noteflow.backend.domain.entities.Note;
import com.darksoft.noteflow.backend.domain.entities.Tag;
import com.darksoft.noteflow.backend.domain.valueobjects.NoteId;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class EditNoteUseCase {

    private INoteRepository repository;

    public EditNoteUseCase(INoteRepository repository) {
        this.repository = repository;
    }

    public Note execute(EditNoteCommand command){

        var noteForUpdate = repository.findById(new NoteId(UUID.fromString(command.getNoteId())))
                .orElseThrow(()->new NoteNotFoundException("The note was not found"));

        if(command.getNewContent()!=null){
            noteForUpdate.changeContent(command.getNewContent());
        }

        if(command.getNewTitle()!=null){
            noteForUpdate.changeTitle(command.getNewTitle());
        }

        Tag[] newTags=null;
        if(command.getNewTags()!=null){
            newTags = Arrays.stream(command.getNewTags()).map(Tag::new).toArray(Tag[]::new);
        }
        noteForUpdate.changeTags(newTags);

        return repository.save(noteForUpdate);
    }
}
