package com.github.jvanheesch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {
    @LocalServerPort
    int port;
    @Autowired
    NoteRepository noteRepository;

    @Test
    void testChecked() {
        Note note = new Note();
        note.setId(1L);
        note.setText("checked");
        new RestTemplate().postForObject(
                "http://localhost:" + port + "/notes/checked",
                note,
                Void.class
        );

        assertThat(noteRepository.findById(1L))
                .isNotEmpty()
                .map(Note::getText)
                .contains("checked");
    }

    @Test
    void testUnchecked() {
        Note note = new Note();
        note.setId(2L);
        note.setText("unchecked");
        new RestTemplate().postForObject(
                "http://localhost:" + port + "/notes/unchecked",
                note,
                Void.class
        );

        assertThat(noteRepository.findById(2L))
                .isEmpty();
    }
}
