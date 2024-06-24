Feature: Math Operations

  Scenario Outline: Addition and subtraction with different numbers
    Given <num1>; <num2>
    When adding numbers
    Then addition result: <addResult>
    When subtracting numbers
    Then subtraction result: <subResult>

    Examples:
      | num1 | num2 | addResult | subResult |
      | 5    | 3    | 8         | 2         |
      | 11   | 8    | 19        | 3         |
      | 8    | -1    | 7        | 9         |