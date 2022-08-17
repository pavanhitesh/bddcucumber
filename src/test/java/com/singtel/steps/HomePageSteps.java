package com.singtel.steps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import com.singtel.pages.HomePage;

import java.util.ArrayList;
import java.util.List;

public class HomePageSteps {


	public HomePage homePage;
	public List<String> items;

	public HomePageSteps() throws Exception {
		this.homePage = new HomePage();
	}

	@Given("I navigates to HomePage")
	public void a_todoMVC_user_navigates_to_HomePage() {
		this.homePage.goToHomePage();
	}

	@When("I add {int} todo items to list")
	public void user_add_todo_items_to_list(Integer int1) {
		this.homePage.addItem(int1);
	}

	@When("I remove {int} todo items to list")
	public void user_remove_todo_items_to_list(Integer int1) {
		this.homePage.removeItem(int1);
	}



	@Then("todo list is displayed with {int} items")
	public void search_bar_is_displayed_items_in_list(Integer expectedCountOfItems) {
		Assert.assertEquals(expectedCountOfItems, this.homePage.getToDoItemCount());
	}

	@When("I add todo items to list")
	public void user_add_todo_items_to_list(DataTable dataTable) {
		List<String> items = dataTable.asList();
		for (String item : items) {
			this.homePage.addItem(item);
		}
	}

	@When("I edit existing todo items to list")
	public void user_edit_todo_items_to_list(DataTable dataTable) {
		List<String> oldToNewToDoItemPair = dataTable.asList();
		List<String> oldToDoItem = new ArrayList<>();
		List<String> newToDoItem = new ArrayList<>();
		for (String pair : oldToNewToDoItemPair) {
			oldToDoItem.add(pair.split("->")[0]);
			newToDoItem.add(pair.split("->")[1]);
		}
		for (int i = 0; i < oldToDoItem.size(); i++) {
			this.homePage.editItems(oldToDoItem.get(i), newToDoItem.get(i));
		}
	}


	@Then("search bar displayed done items in list")
	public void search_bar_displayed_done_items_in_list(DataTable dataTable) {
		List<String> expectedDoneItemNames = dataTable.asList();
		List<String> actualDoneItemNames = this.homePage.getDoneItems();
		for (int i = 0; i < expectedDoneItemNames.size(); i++) {
			Assert.assertEquals(expectedDoneItemNames.get(i), actualDoneItemNames.get(i));
		}
	}

	@Then("todo list is displayed with following")
	public void todo_list_is_displayed_with_following(DataTable dataTable) {
		List<String> expectedToDoItemNames = dataTable.asList();
		List<String> actualToDoItemNames = this.homePage.getToDoItems();
		for (int i = 0; i < expectedToDoItemNames.size(); i++) {
			Assert.assertEquals(expectedToDoItemNames.get(i), actualToDoItemNames.get(i));
		}
	}


	@When("I mark as completed with following in todo list items")
	public void i_mark_as_completed_with_following_in_todo_list_items(DataTable dataTable) {
		List<String> doneItems = dataTable.asList();
		for (String doneItemName : doneItems) {
			this.homePage.checkDoneItems(doneItemName);
		}
	}

	@When("I add {string} todo item to list")
	public void i_add_todo_item_to_list(String itemName) {
		this.homePage.addItem(itemName);
	}


	@When("I remove following items from list")
	public void i_remove_following_items_from_list(DataTable dataTable) {
		List<String> items = dataTable.asList();
		for (String item : items) {
			this.homePage.removeItem(item);
		}
	}


	@Then("todo item list is displayed as {string}")
	public void todo_item_list_is_displayed_as(String itemName) {
		Assert.assertEquals(itemName, this.homePage.toDoCount.getText());
	}

	@When("I mark completed {int} todo items")
	public void i_mark_completed_todo_items(Integer int1) {
		this.homePage.markComplete(int1);
	}
	
	@Then("I click on All filter and validate count of items as {int}")
	public void i_click_on_All_filter_and_validate_count_of_items_as(Integer expectedCountOfItems) {
	    this.homePage.clickAllFilter();
	    Assert.assertEquals(expectedCountOfItems, this.homePage.getAllItemCount());
	}

	@Then("I click on Active and validate count of items as {int}")
	public void i_click_on_Active_and_validate_count_of_items_as(Integer expectedCountOfItems) {
		 this.homePage.clickActiveFilter();
		Assert.assertEquals(expectedCountOfItems, this.homePage.getAllItemCount());
	}

	@Then("I click on Completed and validate count of items as {int}")
	public void i_click_on_Completed_and_validate_count_of_items_as(Integer expectedCountOfItems) {
		 this.homePage.clickCompletedFilter();
		Assert.assertEquals(expectedCountOfItems, this.homePage.getAllItemCount());
	}


}
