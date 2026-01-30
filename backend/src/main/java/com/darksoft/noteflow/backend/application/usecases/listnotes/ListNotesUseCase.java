package com.darksoft.noteflow.backend.application.usecases.listnotes;

import com.darksoft.noteflow.backend.application.exceptions.PaginationException;
import com.darksoft.noteflow.backend.application.ports.INoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListNotesUseCase {

    private INoteRepository repository;

    public ListNotesUseCase(INoteRepository repository) {
        this.repository = repository;
    }

    public ListNotesResult execute(int size, int page){
        if(size<=0||page<0){
            throw new PaginationException("The page specification or its size is not valid.");
        }

        var query = this.repository.findAll(size,page);

        return new ListNotesResult(
                (List<Object>) query.get("notes"),
                (Integer) query.get("total-pages"),
                (Integer) query.get("total-elements")
        );


    }
}
