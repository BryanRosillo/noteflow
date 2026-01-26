Feature: Delete notes
  As a user
  I want to delete notes
  So that I can remove information I no longer need

  Scenario: Delete an existing note
    Given an existing note is stored in the system
    When the user deletes the note
    Then the note is permanently removed from the system

  Scenario: Delete a non-existing note
    Given the note does not exist
    When the user deletes the note
    Then the system returns an error indicating the note was not found