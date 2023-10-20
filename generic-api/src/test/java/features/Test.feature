Feature: HomePage feature

  @Smoke
  Scenario: Successful course selection
    When user hits login api using "<username>" and "<password>"
      | username | password |
      | abc      | 123      |