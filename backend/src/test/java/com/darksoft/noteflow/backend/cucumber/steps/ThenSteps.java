package com.darksoft.noteflow.backend.cucumber.steps;

import com.darksoft.noteflow.backend.cucumber.support.TestContext;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import org.assertj.core.api.AssertionsForClassTypes;
import org.springframework.http.HttpStatus;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class ThenSteps {

    private final TestContext context;
    private final ObjectMapper objectMapper;

    public ThenSteps(TestContext context, ObjectMapper objectMapper) {
        this.context = context;
        this.objectMapper = objectMapper;
    }

    @Then("the system rejects the creation")
    public void the_system_rejects_the_creation() {
        assertThat(context.getResponse().getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Then("the note is created successfully")
    public void the_note_is_created_successfully() {
        assertThat(context.getResponse().getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Then("the note is updated successfully")
    public void the_note_is_updated_successfully() {
        assertThat(context.getResponse().getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Then("the system returns an error indicating the note was not found")
    public void the_system_returns_an_error_indicating_the_note_was_not_found() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        var message = json.get("message");

        assertThat(message.asString()).isEqualTo("The note was not found");
        assertThat(context.getResponse().getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


    @Then("the note is permanently removed from the system")
    public void the_Note_is_permanently_removed_from_the_system() {
        assertThat(context.getResponse().getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Then("the system returns all notes in 2 pages with 10 elements each one")
    public void the_system_returns_all_notes_in_2_pages_with_10_elements_each_one() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        var numPages = json.get("pages").asInt();

        assertThat(json.size()).isEqualTo(10);
        assertThat(numPages).isEqualTo(2);
    }

    @Then("the system returns an empty list")
    public void the_system_returns_an_empty_list() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        assertThat(json.size()).isEqualTo(0);
    }
}
