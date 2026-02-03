package com.darksoft.noteflow.backend.infrastructure.persistence.jpa;

import com.darksoft.noteflow.backend.domain.entities.Note;
import com.darksoft.noteflow.backend.domain.entities.Tag;
import com.darksoft.noteflow.backend.domain.valueobjects.NoteId;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NoteMapper {

    public static NoteEntity toEntity(Note note){
        return new NoteEntity(
                note.getId().id(),
                note.getContent(),
                note.getTitle(),
                Arrays.stream(note.getTags()).map(Tag::getTagName).collect(Collectors.toSet())
        );
    }

    public static Note toDomain(NoteEntity entity){
        return new Note(
                new NoteId(entity.getId()),
                entity.getTitle(),
                entity.getContent(),
                entity.getTags().stream().toArray(Tag[]::new)
        );

    }


}
