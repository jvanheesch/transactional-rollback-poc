package com.github.jvanheesch;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/notes")
@RestController
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/checked")
    public void checked(@RequestBody Note note) {
        try {
            noteService.saveCheckedException(note);
        } catch (Exception e) {
            //
        }
    }

    @PostMapping("/unchecked")
    public void unchecked(@RequestBody Note note) {
        try {
            noteService.saveUncheckedException(note);
        } catch (Exception e) {
            //
        }
    }
}
