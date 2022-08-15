package com.github.jvanheesch;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Transactional
    public Note saveCheckedException(Note note) throws MyCheckedException {
        noteRepository.save(note);
        throw new MyCheckedException();
    }

    @Transactional
    public Note saveUncheckedException(Note note) {
        noteRepository.save(note);
        throw new MyUncheckedException();
    }

    static class MyCheckedException extends Exception {
    }

    static class MyUncheckedException extends RuntimeException {
    }

}
