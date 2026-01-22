package com.darksoft.noteflow.backend.cucumber.steps;

import com.darksoft.noteflow.backend.cucumber.support.CreateNoteRequest;
import com.darksoft.noteflow.backend.cucumber.support.EditNoteRequest;
import com.darksoft.noteflow.backend.cucumber.support.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class EditNoteSteps {
    private final TestContext context;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    public EditNoteSteps(TestContext context, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.context = context;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    // ======================
    // Scenario: Successfully edit the title of a note
    // ======================

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

    @And("the user provides a new title for the note")
    public void the_user_provides_a_new_title_for_the_note() {
        context.setNoteTitle("New title");
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

    @Then("the note is updated successfully")
    public void the_note_is_updated_successfully() {
        assertThat(context.getResponse().getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @And("the title of the note has been updated")
    public void the_title_of_the_note_has_been_updated() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        var title = json.get("title");
        assertThat(title.asString()).isEqualTo(context.getNoteTitle());
    }


    // ======================
    // Scenario: Successfully edit the content of a note
    // ======================

    @And("the user provides a new content for the note")
    public void the_user_provides_a_new_content_for_the_note() {
        context.setNoteContent("Wow, this a new content :D");
    }

    @And("the content of the note has been updated")
    public void the_content_of_the_note_has_been_updated() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        var content = json.get("content");
        assertThat(content.asString()).isEqualTo(context.getNoteContent());
    }

    // ======================
    // Scenario: Edit a non-existing note
    // ======================

    @Given("the note does not exist")
    public void the_note_does_not_exist() {
        context.setNoteId(UUID.randomUUID().toString());
    }

    @Then("the system returns an error indicating the note was not found")
    public void the_system_returns_an_error_indicating_the_note_was_not_found() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        var message = json.get("message");

        assertThat(message.asString()).isEqualTo("The note was not found");
        assertThat(context.getResponse().getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
