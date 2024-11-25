import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static io.restassured.RestAssured.*;
import static org.apache.commons.lang3.Validate.matchesPattern;
import static org.hamcrest.CoreMatchers.*;

import io.restassured.response.Response;

public class StepDef {

    private Response response;

    @Given("the Dog API endpoint is available")
    public void theDogApiEndpointIsAvailable() {
        baseURI = "https://dog.ceo/api";
    }

    @When("I send a GET request to the {string} endpoint")
    public void iSendAGETRequestToTheEndpoint(String endpoint) {
        response = get(endpoint);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain the {string} breed")
    public void theResponseShouldContainTheBreed(String breed) {
        response.then()
                .body("message.keySet()", hasItem(breed));
    }

    @Then("the response should contain the list of retriever sub-breeds")
    public void theResponseShouldContainTheListOfRetrieverSubBreeds() {
        response.then()
                .body("message", hasItems("chesapeake", "curly", "flatcoated", "golden"));
    }

    @Then("the response should contain a valid image URL")
    public void theResponseShouldContainAValidImageURL() {
        response.then()
                .body("message", matchesPattern("https://.*\\.(jpg|jpeg|png)"))
                .body("status", equalTo("success"));
    }
}