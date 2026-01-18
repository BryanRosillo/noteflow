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
                .andExpect(status().isCreated())
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
}
