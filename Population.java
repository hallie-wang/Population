import java.util.List;
/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	
 *	@since	
 */
 
import java.util.Scanner;
import java.util.ArrayList;

public class Population {
	
	// List of cities
	private ArrayList<City> cities = new ArrayList<City>();
	SortMethods sort = new SortMethods();
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	/**
	 * Takes in the data file and assigns the elements to an ArrayList 
	 * of type City
	 */
	public void readInput()
	{
		Scanner infile = FileUtils.openToRead(DATA_FILE);
		infile.useDelimiter("[\t\n]");
		while (infile.hasNext())
		{
			String state = infile.next();
			String name = infile.next();
			String type = infile.next();
			int pop = Integer.parseInt(infile.next());
			City city = new City(state, name, type, pop);
			cities.add(city);
		}
		run();
	}
	/**
	 * Prompts the user for a choice and performs the correct sort
	 */
	public void run()
	{
		int choice = Prompt.getInt("\nEnter selection ");
		long startMillisec = System.currentTimeMillis();
		if (choice == 9)
			System.out.println("Thanks for playing Population!\n\n\n");
		else if (choice == 1)
		{
			sort.selectionSort(cities);
			System.out.println("\nFifty least populous cities");
		}
		else if (choice == 2)
		{
			sort.mergeSort(cities, 0, cities.size() - 1, choice);
			System.out.println("\nFifty most populous cities");
		}
		else if (choice == 3)
		{
			sort.insertionSort(cities);
			System.out.println("\nFifty cities sorted by name");
		}
		else if (choice == 4)
		{
			sort.mergeSort(cities, 0, cities.size() - 1, choice);
			System.out.println("\nFifty cities sorted by name descending");
		}
		else if (choice == 5)
		{
			boolean b = false;
			String stateName = "";
			do
			{
				stateName = Prompt.getString("Enter state name (ie. Alabama) ");
				for (int i = 0; i < cities.size(); i++)
				{
					if (cities.get(i).getState().equalsIgnoreCase(stateName))
					{
						b = true;
						break;
					}
				}
				if (b == false)
					System.out.println("ERROR: " + stateName + " is not valid");
			}
			while (b == false);
			System.out.println("\nFifty most populous cities in " + stateName);
			ArrayList<City> namedState = new ArrayList<City>();
			for (int i = 0; i < cities.size(); i++)
			{
				if (cities.get(i).getState().equalsIgnoreCase(stateName))
					namedState.add(cities.get(i));
			}
			sort.mergeSort(namedState, 0, namedState.size() - 1, choice);
			System.out.printf("%15s%22s%23s%21s", "State", "City", "Type", "Population");
			System.out.println();
			for (int i = 0; i < 50; i++)
			{
				System.out.printf("%5d:%75s\n", i + 1, namedState.get(i));
			}
		}
		else if (choice == 6)
		{
			boolean b = false;
			String cityName = "";
			do
			{
				cityName = Prompt.getString("Enter city name ");
				for (int i = 0; i < cities.size(); i++)
				{
					if (cities.get(i).getName().equalsIgnoreCase(cityName))
					{
						b = true;
						break;
					}
				}
				if (b == false)
					System.out.println("ERROR: " + cityName + " is not valid");
			}
			while (b == false);
			System.out.println("\nCity " + cityName + " by population");
			ArrayList<City> namedCity = new ArrayList<City>();
			for (int i = 0; i < cities.size(); i++)
			{
				if (cities.get(i).getName().equalsIgnoreCase(cityName))
					namedCity.add(cities.get(i));
			}
			sort.mergeSort(namedCity, 0, namedCity.size() - 1, choice);
			System.out.printf("%15s%22s%23s%21s", "State", "City", "Type", "Population");
			System.out.println();
			for (int i = 0; i < namedCity.size(); i++)
			{
				System.out.printf("%5d:%75s\n", i + 1, namedCity.get(i));
			}
		}
		long endMillisec = System.currentTimeMillis();
		if (choice == 1 || choice == 2 || choice == 3 || choice == 4)
		{
			System.out.printf("%15s%22s%23s%21s", "State", "City", "Type", "Population");
			System.out.println();
			for (int i = 0; i < 50; i++)
			{
				System.out.printf("%5d:%75s\n", i + 1, cities.get(i));
			}
			System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds\n");
		}
		if (choice != 9)
		{
			System.out.println();
			printMenu();
			run();
		}
	}
	public static void main(String[] args)
	{
		Population pop = new Population();
		pop.printIntroduction();
		pop.printMenu();
		pop.readInput();
	}
}
