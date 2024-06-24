import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.*;

public class StepDefinitions {
    private int varFirst;
    private int varSecond;
    private int addNumber;
    private int subtractNumber;

    @Given("{int}; {int}")
    public void show_numbers(int numberFirst, int numberSecond) {
        this.varFirst = numberFirst;
        this.varSecond = numberSecond;
    }

    @When("adding numbers")
    public void add_numbers() {
        this.addNumber = this.varFirst + this.varSecond;
    }

    @When("subtracting numbers")
    public void subtract_numbers() {
        this.subtractNumber = this.varFirst - this.varSecond;
    }

    @Then("addition result: {int}")
    public void addition_result(int correctAdditionRes) {
        assertThat(this.addNumber).isEqualTo(correctAdditionRes);
    }

    @Then("subtraction result: {int}")
    public void subtraction_result(int correctSubtrRes) {
        assertThat(this.subtractNumber).isEqualTo(correctSubtrRes);
    }
}