Feature:
  User should be able to search for a recipe using search option

  @regression
  Scenario Outline: User should be able to find a recipe using text search
    Given I'm on the landing page
    When I search for "<searchQuery>"
    Then I should se displayed results containing "<searchQuery>"

    Examples:
      | searchQuery |
      | Pizza       |
      | Blueberry   |


  @regression
  Scenario Outline: User should be able to see the ingredients and instruction for a recipe
    Given I'm on the landing page
    When I search for "<searchQuery>"
    Then I should see displayed the following ingredients "<ingredients>"

    Examples:
      | searchQuery | ingredients                                                                                             |
      | Cranberry   | acorn squash,boiling water,apples chopped into 1.4 inch pieces,dried cranberries,cinnamon,melted butter |
