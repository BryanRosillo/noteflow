package com.darksoft.noteflow.backend.infrastructure.persistence.jpa;

import com.darksoft.noteflow.backend.domain.entities.Note;
import com.darksoft.noteflow.backend.domain.entities.Tag;
import com.darksoft.noteflow.backend.domain.valueobjects.NoteId;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class NoteMapper {

    public static NoteEntity toEntity(Note note){
        return new NoteEntity(
                note.getId().id(),
                note.getContent(),
                note.getTitle(),
                Optional.ofNullable(note.getTags())
                        .map(tags -> Arrays.stream(tags)
                                .map(Tag::getTagName)
                                .collect(Collectors.toSet()))
                        .orElse(Collections.emptySet())
        );
    }

    public static Note toDomain(NoteEntity entity){
        return new Note(
                new NoteId(entity.getId()),
                entity.getTitle(),
                entity.getContent(),
                Optional.ofNullable(entity.getTags())
                        .orElse(Collections.emptySet())
                        .stream()
                        .toArray(Tag[]::new)
        );
    }


}
