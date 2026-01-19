Feature: Create notes
  As a user
  I want to create notes with content and tags
  So that I can store organized information

  Scenario: Create a valid note with tags
    Given the user provides a valid title
    And the user provides valid content
    And the user assigns one or more tags
    When the user saves the note
    Then the note is created successfully
    And the note contains the assigned tags
    And the creation date is automatically set

  Scenario: Create a note without a title
    Given the user provides valid content
    And the user does not provide a title
    When the user saves the note
    Then the system rejects the creation
    And an error message indicates that the title is required

  Scenario: Create a note with empty content
    Given the user provides a valid title
    And the user does not provide a content
    When the user saves the note
    Then the system rejects the creation
    And an error message indicates that the content is required