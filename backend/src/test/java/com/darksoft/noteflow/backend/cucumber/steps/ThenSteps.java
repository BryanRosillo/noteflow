package com.darksoft.noteflow.backend.cucumber.steps;

import com.darksoft.noteflow.backend.cucumber.support.TestContext;
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
        AssertionsForClassTypes.assertThat(context.getResponse().getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Then("the system returns an error indicating the note was not found")
    public void the_system_returns_an_error_indicating_the_note_was_not_found() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        var message = json.get("message");

        AssertionsForClassTypes.assertThat(message.asString()).isEqualTo("The note was not found");
        AssertionsForClassTypes.assertThat(context.getResponse().getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


}
