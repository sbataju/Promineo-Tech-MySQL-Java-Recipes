package recipes;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import recipes.entity.Recipe;
//import recipes.dao.DbConnection;
import recipes.exception.DbException;
import recipes.service.RecipeService;

public class Recipes {
	private Scanner scanner = new Scanner(System.in);
	private RecipeService recipeService = new RecipeService();

	// @formatter:off
	private List<String> operations = List.of(
			"1) Create and populate all tables",
			"2) Add a recipe"
	);
	// @formatter:on

	// main
	public static void main(String[] args) {
		// DbConnection.getConnection();
		new Recipes().displayMenu();
	}

	private void displayMenu() {
		// TODO Auto-generated method stub
		boolean done = false;

		while (!done) {
			try {
				int operation = getOperation();

				switch (operation) {
				case -1:
					done = exitMenu();
					break;
				case 1:
					createTables();
					break;
				case 2:
					addRecipe();
					break;
				default:
					System.out.println("\n" + operation + " is not valid. Try again.");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("\nError: " + e.toString() + " Try again.");
			}
		}
	}

	private void addRecipe() {
		// TODO Auto-generated method stub
		String name = getStringInput("Enter recipe name");
		String notes = getStringInput("Enter recipe notes");
		Integer numServings = getIntInput("Enter number of servings");
		Integer prepMinutes = getIntInput("Enter prep time in minutes");
		Integer cookMinutes = getIntInput("Enter cook time in minutes");

		LocalTime preptime = minutesToLocalTime(prepMinutes);
		LocalTime cooktime = minutesToLocalTime(cookMinutes);

		Recipe recipe = new Recipe();
		
		recipe.setRecipeName(name);
		recipe.setNotes(notes);
		recipe.setNumServings(numServings);
		recipe.setPrepTime(preptime);
		recipe.setCookTime(cooktime);
		
		Recipe dbRecipe = recipeService.addRecipe(recipe);
		System.out.println("You added this recipe: \n" + dbRecipe);
	}

	private LocalTime minutesToLocalTime(Integer numMinutes) {
		// TODO Auto-generated method stub
		int min = Objects.isNull(numMinutes) ? 0 : numMinutes;
		int hours = min / 60;
		int minutes = min % 60;

		return LocalTime.of(hours, minutes);
	}

	private void createTables() {
		// TODO Auto-generated method stub
		recipeService.createAndPopulateTables();
		System.out.println("\nTables created and populated!");
	}

	private boolean exitMenu() {
		// TODO Auto-generated method stub
		System.out.println("\nExiting the menu. TTFN!");
		return true;
	}

	private int getOperation() {
		// TODO Auto-generated method stub
		printOperations();
		Integer op = getIntInput("\nEnter an operation number (Press Enter to quit)");

		return Objects.isNull(op) ? -1 : op;
	}

	private void printOperations() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("Here's what you can do:");

		operations.forEach(op -> System.out.println("   " + op));
	}

	private Integer getIntInput(String prompt) {
		// TODO Auto-generated method stub
		String input = getStringInput(prompt);
		if (Objects.isNull(input)) {
			return null;
		}

		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid number.");
		}
	}

	private Double getDoubleInput(String prompt) {
		// TODO Auto-generated method stub
		String input = getStringInput(prompt);
		if (Objects.isNull(input)) {
			return null;
		}

		try {
			return Double.parseDouble(input);
		} catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid number.");
		}
	}

	private String getStringInput(String prompt) {
		// TODO Auto-generated method stub
		System.out.print(prompt + ": ");
		String line = scanner.nextLine();

		return line.isBlank() ? null : line.trim();
	}

}
