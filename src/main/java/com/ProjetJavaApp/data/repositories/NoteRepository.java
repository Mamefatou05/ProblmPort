package com.ProjetJavaApp.data.repositories;

import com.ProjetJavaApp.data.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
