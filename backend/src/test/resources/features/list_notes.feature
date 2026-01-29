Feature: List notes
  As a user
  I want to view all notes with their tags
  So that I can review my stored information

  Scenario: List existing notes
    Given notes exist in the system
    When the user requests the list of notes
    Then the system returns all notes
    And each note includes its tags

  Scenario: List notes when none exist
    Given no notes exist in the system
    When the user requests the list of notes
    Then the system returns an empty list