package com.darksoft.noteflow.backend.application.usecases.editnote;

import com.darksoft.noteflow.backend.application.exceptions.NoteNotFoundException;
import com.darksoft.noteflow.backend.application.ports.INoteRepository;
import com.darksoft.noteflow.backend.domain.entities.Note;
import com.darksoft.noteflow.backend.domain.entities.Tag;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class EditNoteUseCase {

    private INoteRepository repository;

    public EditNoteUseCase(INoteRepository repository) {
        this.repository = repository;
    }

    public Note execute(EditNoteCommand command){
        Optional<Note> optionalNote = repository.findById(command.getNoteId());
        if(optionalNote.isEmpty()){
            throw new NoteNotFoundException("The note was not found");
        }

        var noteForUpdate = optionalNote.get();

        noteForUpdate.setContent(command.getNewContent());
        noteForUpdate.setTitle(command.getNewTitle());

        Tag[] newTags=null;
        if(command.getNewTags()!=null){
            newTags = Arrays.stream(command.getNewTags()).map(Tag::new).toArray(Tag[]::new);
        }
        noteForUpdate.setTags(newTags);

        return repository.save(noteForUpdate);
    }
}
