package com.darksoft.noteflow.backend.cucumber.steps;

import com.darksoft.noteflow.backend.cucumber.support.TestContext;
import io.cucumber.java.en.And;
import org.assertj.core.api.AssertionsForClassTypes;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class AndSteps {

    private final TestContext context;
    private final ObjectMapper objectMapper;

    public AndSteps(TestContext context, ObjectMapper objectMapper) {
        this.context = context;
        this.objectMapper = objectMapper;
    }

    @And("the user provides valid content")
    public void the_user_provides_valid_content() {
        context.setNoteContent("I must end my math homework on typical operations.");
    }

    @And("the user assigns one or more tags")
    public void the_user_assigns_one_or_more_tags() {
        context.setNoteTags(new String[]{"Homework"});
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

    @And("the user does not provide tags")
    public void the_user_does_not_provide_tags() {
        context.setNoteTags(null);
    }

    @And("the user does not provide a title")
    public void the_user_does_not_provide_a_title() {
        context.setNoteTitle("");
        context.setNoteTags(new String[]{"Homework"});
    }

    @And("an error message indicates that the title is required")
    public void an_error_message_indicates_that_the_title_is_required() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        var message = json.get("message");

        assertThat(message).isNotNull();
        assertThat(message.asString()).isEqualTo("The title of the note cannot be empty.");
    }

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

    @And("the user provides a new title for the note")
    public void the_user_provides_a_new_title_for_the_note() {
        context.setNoteTitle("New title");
    }

    @And("the title of the note has been updated")
    public void the_title_of_the_note_has_been_updated() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        var title = json.get("title");
        AssertionsForClassTypes.assertThat(title.asString()).isEqualTo(context.getNoteTitle());
    }

    @And("the user provides a new content for the note")
    public void the_user_provides_a_new_content_for_the_note() {
        context.setNoteContent("Wow, this a new content :D");
    }

    @And("the content of the note has been updated")
    public void the_content_of_the_note_has_been_updated() {
        var json = objectMapper.readTree(context.getResponse().getBody());
        var content = json.get("content");
        AssertionsForClassTypes.assertThat(content.asString()).isEqualTo(context.getNoteContent());
    }
}
