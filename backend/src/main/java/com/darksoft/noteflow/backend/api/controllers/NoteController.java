package com.darksoft.noteflow.backend.api.controllers;

import com.darksoft.noteflow.backend.api.dtos.CreateNoteRequest;
import com.darksoft.noteflow.backend.api.dtos.DeleteNoteRequest;
import com.darksoft.noteflow.backend.application.usecases.createnote.*;
import com.darksoft.noteflow.backend.application.usecases.deletenote.DeleteNoteCommand;
import com.darksoft.noteflow.backend.application.usecases.deletenote.DeleteNoteUseCase;
import com.darksoft.noteflow.backend.application.usecases.editnote.EditNoteCommand;
import com.darksoft.noteflow.backend.application.usecases.editnote.EditNoteUseCase;
import com.darksoft.noteflow.backend.domain.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/notes", produces = "application/json")
public class NoteController {

    @Autowired
    private CreateNoteUseCase createUseCase;

    @Autowired
    private EditNoteUseCase editNoteUseCase;

    @Autowired
    private DeleteNoteUseCase deleteNoteUseCase;


    @PostMapping
    public ResponseEntity<Object> createNote(@RequestBody CreateNoteRequest request){
        var command = new CreateNoteCommand(
                request.getTitle(),
                request.getContent(),
                request.getTags()
        );

        var noteCreated = this.createUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(noteCreated);
    }

    @PatchMapping
    public ResponseEntity<Object> editNote(@RequestBody EditNoteCommand request){
        var command = new EditNoteCommand(
                request.getNoteId(),
                request.getNewContent(),
                request.getNewTitle(),
                request.getNewTags()
        );

        var noteUpdated = this.editNoteUseCase.execute(command);
        return ResponseEntity.ok(noteUpdated);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteNote(@RequestBody DeleteNoteRequest request){
        var command = new DeleteNoteCommand(request.getNoteId());
        this.deleteNoteUseCase.execute(command);
        return ResponseEntity.ok().build();
    }
}
