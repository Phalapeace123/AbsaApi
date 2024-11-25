Feature: Verify specific dog breeds in the Dog API response

  Scenario: Verify "retriever" breed exists in the list of breeds
    Given the Dog API endpoint is available
    When I send a GET request to the "https://dog.ceo/api/breeds/list/all" endpoint
    Then the response status should be 200
    And the response should contain the "retriever" breed
    And the response should contain the list of retriever sub-breeds
    And the response should contain a valid image URL
