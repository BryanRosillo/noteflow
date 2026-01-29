package com.darksoft.noteflow.backend.cucumber.steps;

import com.darksoft.noteflow.backend.cucumber.support.CreateNoteRequest;
import com.darksoft.noteflow.backend.cucumber.support.TestContext;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class GivenSteps {

    private final TestContext context;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    public GivenSteps(TestContext context, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.context = context;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Given("the user provides a valid title")
    public void the_user_provides_a_valid_title() {
        context.setNoteTitle("Do my homework");
    }

    @Given("an existing note is stored in the system")
    public void an_existing_note_is_stored_in_the_system() throws Exception {
        CreateNoteRequest request = new CreateNoteRequest(
                "BDD",
                "Bryan, did you test the scenarios?"
                , new String[]{"Test"}
        );

        MvcResult result = mockMvc.perform(post("/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andReturn();

        JsonNode json = objectMapper.readTree(result.getResponse().getContentAsString());
        context.setNoteId(json.get("id").get("id").asString());
    }

    @Given("the note does not exist")
    public void the_note_does_not_exist() {
        context.setNoteId(UUID.randomUUID().toString());
    }


    @Given("notes exist in the system")
    public void notes_exist_in_the_system() throws Exception {
        var createNoteRequest01 = new CreateNoteRequest(
                "BDD",
                "Bryan, did you test the scenarios?"
                , new String[]{"Test"}
        );

        var createNoteRequest02 = new CreateNoteRequest(
                "Deploy",
                "Bryan, where NoteFlow will be deployed?"
                , new String[]{"Deploy"}
        );

         mockMvc.perform(post("/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createNoteRequest01)))
                .andReturn();

        mockMvc.perform(post("/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createNoteRequest02)))
                .andReturn();
    }

    @Given("no notes exist in the system")
    public void no_notes_exist_in_the_system() {
        // The system must be completely clean, therefore, no prior action is performed.
    }
}
