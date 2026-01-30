package com.darksoft.noteflow.backend.cucumber.hooks;


import com.darksoft.noteflow.backend.infrastructure.persistence.InMemoryNoteRepository;
import io.cucumber.java.Before;


public class Hooks {
    private InMemoryNoteRepository repository;

    public Hooks(InMemoryNoteRepository repository) {
        this.repository = repository;
    }

    @Before
    public void resetState(){
        this.repository.clear();
    }
}
