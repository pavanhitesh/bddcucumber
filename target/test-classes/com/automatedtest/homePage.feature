Feature: Validate todo list application

  @validateAdd
  Scenario: As toDoMVC user able to add to item  to the list
    Given I navigates to HomePage
    When I add "running" todo item to list
    Then todo list is displayed with following
      | running |
    And todo list is displayed with 1 items
    And todo item list is displayed as "1 item left"

  @validateRemove
  Scenario: As toDoMVC user able to add and remove to items to the list
    Given I navigates to HomePage
    When I add todo items to list
      | running | jogging | eating |
    And I remove following items from list
      | jogging |
    Then todo list is displayed with following
      | running | eating |
    And todo list is displayed with 2 items
    And todo item list is displayed as "2 items left"

  @validateCheckDoneItems
  Scenario: As toDoMVC user able to add to items to the list and mark completed
    Given I navigates to HomePage
    When I add todo items to list
      | running | jogging | eating |
    And I mark as completed with following in todo list items
      | running |
    Then todo list is displayed with following
      | jogging | eating |
    And search bar displayed done items in list
      | running |
    And todo list is displayed with 2 items
    And todo item list is displayed as "2 items left"

  @validateToDoAddCount
  Scenario Outline: As todoMVC user add <add> todo items
    Given I navigates to HomePage
    When I add <add> todo items to list
    Then todo list is displayed with <final> items
    And I click on All filter and validate count of items as <final>
    And I click on Active and validate count of items as <final>

    Examples: 
      | add | final |
      |   6 |     6 |
      |  15 |    15 |

  @validateTodoCount
  Scenario Outline: As todoMVC user add <add> todo items and remove <remove> todo items
    Given I navigates to HomePage
    When I add <add> todo items to list
    And I remove <remove> todo items to list
    Then todo list is displayed with <final> items
    And I click on All filter and validate count of items as <final>
    And I click on Active and validate count of items as <final>

    Examples: 
      | add | remove | final |
      |   6 |      3 |     3 |
      |  15 |      3 |    12 |

  @validateTodoCountCompleted
  Scenario Outline: As todoMVC user add <add> todo items , remove <remove> todo items and mark completed <completed> todoItems
    Given I navigates to HomePage
    When I add <add> todo items to list
    And I remove <remove> todo items to list
    And I mark completed <completed> todo items
    Then todo list is displayed with <final> items
    And I click on Active and validate count of items as <final>
    And I click on Completed and validate count of items as <completed>

    Examples: 
      | add | remove | completed | final |
      |   6 |      3 |         2 |     1 |
      |  15 |      3 |         2 |    10 |

  @validateTodoEdit
  Scenario: As todoMVC user change existing todo items
    Given I navigates to HomePage
    When I add todo items to list
      | food | money | wallet |
    And I edit existing todo items to list
      | food->water | money->digital money | wallet->e-wallet |
    Then todo list is displayed with following
      | water | digital money | e-wallet |
