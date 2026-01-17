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