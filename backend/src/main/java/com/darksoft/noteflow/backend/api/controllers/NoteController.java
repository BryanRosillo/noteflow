package com.darksoft.noteflow.backend.api.controllers;

import com.darksoft.noteflow.backend.api.dtos.CreateNoteRequest;
import com.darksoft.noteflow.backend.application.usecases.CreateNoteCommand;
import com.darksoft.noteflow.backend.application.usecases.CreateNoteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private CreateNoteUseCase createUseCase;

    @Autowired
    public NoteController(CreateNoteUseCase createUseCase){
        this.createUseCase = createUseCase;
    }


    @PostMapping
    public ResponseEntity<Object> createNote(@RequestBody CreateNoteRequest request){
        var command = new CreateNoteCommand(
                request.getTitle(),
                request.getContent(),
                request.getTags()
        );

        this.createUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body("Note created successfully.");
    }
}
