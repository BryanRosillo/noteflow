package com.darksoft.noteflow.backend.application.usecases.listnotes;

import java.util.List;

public class ListNotesResult {

    private List<Object> notes;
    private int totalPages;
    private int totalElements;

    public ListNotesResult(List<Object> notes, int totalPages, int totalElements) {
        this.notes = notes;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<Object> getNotes() {
        return notes;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }
}
