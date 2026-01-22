Feature: Edit notes
  As a user
  I want to edit an existing note
  So that I can update its information

  Scenario: Successfully edit the title of a note
    Given an existing note is stored in the system
    And the user provides a new title for the note
    When the user updates the note
    Then the note is updated successfully
    And the title of the note has been updated

  Scenario: Successfully edit the content of a note
    Given an existing note is stored in the system
    And the user provides a new content for the note
    When the user updates the note
    Then the note is updated successfully
    And the content of the note has been updated

  Scenario: Edit a non-existing note
    Given the note does not exist
    And the user provides a new title for the note
    When the user updates the note
    Then the system returns an error indicating the note was not found