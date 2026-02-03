package com.darksoft.noteflow.backend.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="notes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NoteEntity {

    @Id
    private UUID id;

    private String title;
    private String content;

    @ElementCollection
    private Set<String> tags;

}
