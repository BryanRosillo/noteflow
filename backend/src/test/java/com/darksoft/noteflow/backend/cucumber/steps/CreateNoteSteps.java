package com.darksoft.noteflow.backend.cucumber.steps;

import com.darksoft.noteflow.backend.cucumber.support.CreateNoteRequest;
import com.darksoft.noteflow.backend.cucumber.support.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateNoteSteps {

    private final TestContext context;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    public CreateNoteSteps(TestContext context, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.context = context;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    // ======================
    // Scenario: Create a valid note with tags
    // ======================

    @Given("the user provides a valid title")
    public void the_user_provides_a_valid_title() {
        context.setNoteTitle("Do my homework");
    }

    @And("the user provides valid content")
    public void the_user_provides_valid_content() {
        context.setNoteContent("I must end my math homework on typical operations.");
    }

    @And("the user assigns one or more tags")
    public void the_user_assigns_one_or_more_tags() {
        context.setNoteTags(new String[]{"Homework"});
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

    @Then("the note is created successfully")
    public void the_note_is_created_successfully() {
        assertThat(context.getResponse().getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @And("the note contains the assigned tags")
    public void the_note_contains_the_assigned_tags() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        var tags = json.get("tags");

        assertThat(tags).isNotNull();
        assertThat(tags.get(0).get("tagName").asString()).isEqualTo("Homework");
    }

    @And("the creation date is automatically set")
    public void the_creation_date_is_automatically_set() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        var createdDate = json.get("creationDate");
        assertThat(createdDate).isNotNull();
    }

    // ======================
    // Scenario: Create a valid note without tags
    // ======================

    @And("the user does not provide tags")
    public void the_user_does_not_provide_tags() {
        context.setNoteTags(null);
    }

    // ======================
    // Scenario: Create a note without a title
    // ======================

    @And("the user does not provide a title")
    public void the_user_does_not_provide_a_title() {
        context.setNoteTitle("");
        context.setNoteTags(new String[]{"Homework"});
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

    @Then("the system rejects the creation")
    public void the_system_rejects_the_creation() {
        assertThat(context.getResponse().getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @And("an error message indicates that the title is required")
    public void an_error_message_indicates_that_the_title_is_required() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        var message = json.get("message");

        assertThat(message).isNotNull();
        assertThat(message.asString()).isEqualTo("The title of the note cannot be empty.");
    }

    // ======================
    // Scenario: Create a note with empty content
    // ======================

    @And("the user does not provide a content")
    public void the_user_does_not_provide_a_content(){
        context.setNoteContent("");
        context.setNoteTags(new String[]{"Homework"});
    }

    @And ("an error message indicates that the content is required")
    public void an_error_message_indicates_that_the_content_is_required(){
        var json = objectMapper.readTree(context.getResponse().getBody());
        var message = json.get("message");

        assertThat(message).isNotNull();
        assertThat(message.asString()).isEqualTo("The content of the note cannot be empty.");
    }


    // ======================
    // Scenario: Create a note with content longer than 500 characters
    // ======================

    @And("the content exceeds 500 characters")
    public void the_content_exceeds_500_haracters() {
        context.setNoteContent("a".repeat(500+1));
        context.setNoteTags(new String[]{"Homework"});
    }

    @And("an error message indicates the content length limit")
    public void an_error_message_indicates_the_content_length_limit() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        var message = json.get("message");

        assertThat(message).isNotNull();
        assertThat(message.asString()).isEqualTo("The content of the note cannot exceed 500 characters.");
    }

    // ======================
    // Scenario: Create a note with content longer than 100 characters
    // ======================

    @And("the title exceeds 100 characters")
    public void the_title_exceeds_characters() {
        context.setNoteTitle("a".repeat(100+1));
        context.setNoteTags(new String[]{"Homework"});
    }

    @And("an error message indicates the title length limit")
    public void an_error_message_indicates_the_title_length_limit() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        var message = json.get("message");

        assertThat(message).isNotNull();
        assertThat(message.asString()).isEqualTo("The title of the note cannot exceed 100 characters.");
    }

    // ======================
    // Scenario: Create a note with a tag longer than 30 characters
    // ======================

    @And("the tag provided exceeds 30 characters")
    public void the_tag_provided_exceeds_characters() {
        context.setNoteTags(new String[]{"a".repeat(30+1)});
    }


    @And("an error message indicates the tag length limit")
    public void an_error_message_indicates_the_tag_length_limit() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        var message = json.get("message");

        assertThat(message).isNotNull();
        assertThat(message.asString()).isEqualTo("The tag length cannot exceed 30 characters.");
    }

}
