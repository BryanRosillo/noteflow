package com.darksoft.noteflow.backend.cucumber.steps;

import com.darksoft.noteflow.backend.cucumber.support.CreateNoteRequest;
import com.darksoft.noteflow.backend.cucumber.support.DeleteNoteRequest;
import com.darksoft.noteflow.backend.cucumber.support.EditNoteRequest;
import com.darksoft.noteflow.backend.cucumber.support.TestContext;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WhenSteps {

    private final TestContext context;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    public WhenSteps(TestContext context, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.context = context;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @When("the user saves the note")
    public void the_user_saves_the_note() throws Exception {
        var requestBody = new CreateNoteRequest(
                context.getNoteTitle(),
                context.getNoteContent(),
                context.getNoteTags()
        );

        mockMvc.perform(post("/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andDo(result ->
                        context.setResponse(new ResponseEntity<>(
                                result.getResponse().getContentAsString(),
                                HttpStatus.valueOf(result.getResponse().getStatus()))));
    }

    @When("the user tries to save the note without a title")
    public void the_user_tries_to_save_the_note_without_a_title() throws Exception {
        var requestBody = new CreateNoteRequest(
                context.getNoteTitle(),
                context.getNoteContent(),
                context.getNoteTags()
        );

        mockMvc.perform(post("/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isCreated())
                .andDo(result ->
                        context.setResponse(new ResponseEntity<>(
                                result.getResponse().getContentAsString(),
                                HttpStatus.valueOf(result.getResponse().getStatus()))));
    }

    @When("the user updates the note")
    public void the_user_updates_the_note() throws Exception {
        var request = new EditNoteRequest(
                context.getNoteId(),
                context.getNoteContent(),
                context.getNoteTitle(),
                null
        );

        mockMvc.perform(patch("/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(result ->
                        context.setResponse(new ResponseEntity<>(
                                result.getResponse().getContentAsString(),
                                HttpStatus.valueOf(result.getResponse().getStatus()))));

    }


    @When("the user deletes the note")
    public void the_user_deletes_the_note() throws Exception {
        var request = new DeleteNoteRequest(
                context.getNoteId()
        );

        mockMvc.perform(delete("/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(result ->
                        context.setResponse(new ResponseEntity<>(
                                result.getResponse().getContentAsString(),
                                HttpStatus.valueOf(result.getResponse().getStatus())
                        )));

    }
}
