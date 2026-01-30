Feature: List notes
  As a user
  I want to view all notes with their tags
  So that I can review my stored information

  Scenario: List existing notes
    Given 20 notes exist in the system
    And the user provides a limit of 10 notes per page
    When the user requests the list of notes
    Then the system returns all notes in 2 pages with 10 elements each one
    And in the response will be the information about pages and total elements

  Scenario: List notes when none exist
    Given no notes exist in the system
    When the user requests the list of notes
    Then the system returns an empty list