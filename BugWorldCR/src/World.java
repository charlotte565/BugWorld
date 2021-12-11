import java.util.ArrayList;
import java.util.Collections;

public class World {

	static ArrayList<Bug> bugs = new ArrayList<Bug>();
	static ArrayList<Plant> plants = new ArrayList<Plant>();
	static ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
	ArrayList<NameEnergy> nameEns = new ArrayList<NameEnergy>();
	ArrayList<BugPairs> bugPairs = new ArrayList<BugPairs>();
	ArrayList<BugPlantPairs> bugPlantPairs = new ArrayList<BugPlantPairs>();
	private static final int width = 40;
	private static final int height = 20;
	private static final int imageWidth = 30;

	public World() {

	}

	// Method creates a new bugWorld and deletes old one
	public void addEntities() {
		bugPairs.clear();
		bugs.clear();
		obs.clear();
		plants.clear();
		nameEns.clear();
		bugPairs.clear();

		Ladybug buggie1 = new Ladybug("Lady", 3, 1);
		Grasshopper buggie2 = new Grasshopper("Gary", 1, 2);
		Beatle buggie3 = new Beatle("Freddie", 2, 3);
		Ladybug buggie4 = new Ladybug("Katarina", 5, 4);
		Grasshopper buggie5 = new Grasshopper("Nick", 1, 5);
		Beatle buggie6 = new Beatle("Matej", 4, 6);
		addBugs(buggie1);
		addBugs(buggie2);
		addBugs(buggie3);
		addBugs(buggie4);
		addBugs(buggie5);
		addBugs(buggie6);

		Ladybug buggie7 = new Ladybug("Lady", 6, 1);
		Grasshopper buggie8 = new Grasshopper("Gary", 1, 2);
		Beatle buggie9 = new Beatle("Freddie", 7, 3);
		Ladybug buggie10 = new Ladybug("Katarina", 8, 4);
		Grasshopper buggie11 = new Grasshopper("Nick", 1, 5);
		Beatle buggie12 = new Beatle("Matej", 5, 6);
		addBugs(buggie7);
		addBugs(buggie8);
		addBugs(buggie9);
		addBugs(buggie10);
		addBugs(buggie11);
		addBugs(buggie12);

		orderBugEnergy();

		for (int i = 0; i < 15; i++) {
			Plant plantie1 = new Plant(randomPlant());

			addPlants(plantie1);

		}

		for (int i = 0; i < 4; i++) {
			Obstacle ob = new Obstacle();
			addObs(ob);

		}
		updatePairs();

	}

	// this method draws the textual based bugworld
	public void drawWorld() {
		for (int j = 0; j < height; j++) {
			String linePrint = "";
			for (int i = 0; i < width; i++) {
				Bug bugHold = null;
				for (Bug k : bugs) {
					if (k.getX() == i && k.getY() == j) {
						bugHold = k;
					}
				}
				Plant plantHold = null;
				for (Plant l : plants) {
					if (l.getX() == i && l.getY() == j) {
						plantHold = l;
					}
				}
				Obstacle obHold = null;
				for (Obstacle l : obs) {
					if (l.getX() == i && l.getY() == j) {
						obHold = l;
					}
				}

				if (bugHold != null) {
					linePrint = linePrint + bugHold.getValue();
				} else if (plantHold != null) {
					linePrint = linePrint + "P";
				} else if (obHold != null) {
					linePrint = linePrint + obHold.getValue();
				} else if (j == 0 || j == height - 1) {
					if (i == 0 || i == width - 1) {
						linePrint = linePrint + "|";
					} else
						linePrint = linePrint + "-";
				} else if (i == 0 || i == width - 1) {
					linePrint = linePrint + "|";
				}

				else
					linePrint = linePrint + " ";
			}
			System.out.println(linePrint);

		}

	}

	// this method checks that the coordinates given are not already used by another
	// entity
	public static boolean checkCoordinates(int x, int y) {
		if (x < width - 1 && x > 0 && y < height - 1 && y > 0) {
			for (Bug b : bugs) {
				if (b.getX() == x && b.getY() == y) {
					return false;
				}
			}
			for (Obstacle o : obs) {
				if (o.getX() == x && o.getY() == y) {
					return false;
				}
			}
			for (Plant p : plants) {
				if (p.getX() == x && p.getY() == y) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	// this method generates coordinates for an entity that are within the
	// boundaries
	// it uses recusrion to ensure that some coordinates are eventually found that
	// are
	// not already being used
	public static void generatedCoordinates(Entity entity) {
		int ranX = (int) (1 + (Math.random() * (width - 2)));

		int ranY = (int) (1 + (Math.random() * (height - 2)));
		if (checkCoordinates(ranX, ranY)) {
			entity.setX(ranX);
			entity.setY(ranY);
		} else
			generatedCoordinates(entity);
	}

	// This method updates the bugWorld by going through all of the bugs and moving
	// them
	// depending on their behaviour and position
	// they will move towards something if they smell it or move randomly
	public void updateWorld(ArrayList<Bug> bugs) {

		for (int i = bugs.size() - 1; i >= 0; i--) {
			Bug bug = bugs.get(i);
			if (!updateSmell(bug, i)) {
				randomOneBug(bug, i);
			}
		}
		updatePairs();
	}

	public boolean updateSmell(Bug bug, int i) {

		if (smellFoodNew(bug)) {
			return true;
		}

		return false;

	}

	// this method updates a plant for one frame
	public static void plantGrow(ArrayList<Plant> plants) {
		for (int i = plants.size() - 1; i >= 0; i--) {
			Plant p = plants.get(i);
			p.growPlant();
		}
	}

	// This method generates a random direction for the bug to move
	public static void randomOneBug(Bug bug, int index) {
		Bug bugMove = bug;
		int ranD = (int) (1 + (Math.random() * 8));

		moveBugList(bugMove, ranD, index);
	}

	// This method takes a direction and a bug and will move it in one direction
	public static void moveBugList(Bug bugmove, int direction, int index) {
		int x = bugmove.getX();
		int y = bugmove.getY();
		if (direction == 1) {
			y = y - 1;
		} else if (direction == 2) {
			y = y + 1;
		} else if (direction == 3) {
			x = x - 1;
		} else if (direction == 4) {
			x = x + 1;
		} else if (direction == 5) {
			y = y + 1;
			x = x - 1;
		} else if (direction == 6) {
			x = x + 1;
			y = y - 1;
		} else if (direction == 7) {
			x = x + 1;
			y = y + 1;
		} else {
			y = y - 1;
			x = x - 1;
		}
		if (validMove(x, y, bugmove)) {

			bugmove.setX(x);
			bugmove.setY(y);

			bugmove.getView().setTranslateX(x * World.getImagewidth());
			bugmove.getView().setTranslateY(y * World.getImagewidth());

		}
	}

	// This method checks if a move is valid for a bug
	// It checks first if it is within boundaries
	// then checks if the new coordinates 'Hit' another bug
	// or obstavle or plant
	public static boolean validMove(int x, int y, Bug bug) {
		boolean valid = false;

		if (x < width - 1 && x > 0 && y < height - 1 && y > 0) {
			valid = true;

			for (Bug i : bugs) {

				if (i.getX() == x && i.getY() == y) {
					// if the bugs energy is greater than another bug it can move onto it
					if (bug.getEnergy() > i.getEnergy()) {
						valid = true;

						break;
					}

					valid = false;
				}
			}
			// if it's an obstacle then the bug cannot move onto it
			for (Obstacle i : obs) {
				if (i.getX() == x && i.getY() == y) {
					valid = false;
				}
			}
			// if its a plant and the bug is a plant eater then it can move onto it
			// and the plant gets removed
			for (Plant i : plants) {
				if (i.getX() == x && i.getY() == y) {
					if (bug.foodP == 2 || bug.foodP == 0) {
						plants.remove(i);
						Main.removePlantRoot(i);
						valid = true;
					} else
						valid = false;

					break;
				}
			}
		}
		return valid;

	}

	// this checks if a move made will hit a bug, if it will and the bug
	// energy is greater than the other then the bug will be returned to be eaten
	public static Bug validBugEat(int x, int y, Bug bug) {
		Bug bug2 = null;
		if (x < width - 1 && x > 0 && y < height - 1 && y > 0) {

			for (Bug i : bugs) {

				if (i.getX() == x && i.getY() == y) {

					if (bug.getEnergy() > i.getEnergy()) {
						bug2 = i;

						break;
					}

				}
			}

		}
		return bug2;

	}

	// this method makes bugs move towards their food
	public boolean smellFood(Bug bug, int index) {
		// smell will be returned true if the bug finds something to smell
		// and follow
		boolean smell = false;
		// FoodP 0 plants, 1 bugs, 2 bugs and plants
		if (bug.getFoodP() == 0 || bug.getFoodP() == 2) {
			// loops through all plants
			for (Plant plant : plants) {

				int bugX = bug.getX();
				int bugY = bug.getY();
				int bugSmell = bug.getSmell();

				int plantX = plant.getX();
				int plantY = plant.getY();

				int disX = Math.abs((int) (bug.getX() - plant.getX()));
				int disY = Math.abs((int) (bug.getY() - plant.getY()));
				int x = bugX;
				int y = bugY;
				// checks if plant is within the bugs smelling range
				if (disX < bugSmell && disY < bugSmell) {
					// if it is then the bug is moved towards it
					if (bugX > plantX) {
						x = x - 1;
						if (bugY > plantY)
							y = y - 1;
						else if (bugY < plantY)
							y = y + 1;
						if (validMove(x, y, bug)) {

							setBug(bug, x, y);

							smell = true;
							break;
						}
					} else if (bugX < plantX) {
						x = x + 1;
						if (bugY > plantY)
							y = y - 1;
						else if (bugY < plantY)
							y = y + 1;
						if (validMove(x, y, bug)) {
							setBug(bug, x, y);

							smell = true;
							break;
						}
					} else {
						if (bugY > plantY)
							y = y - 1;
						else if (bugY < plantY)
							y = y + 1;
						if (validMove(x, y, bug)) {
							setBug(bug, x, y);

							smell = true;
							break;
						}
					}
				}
			}
		}
		if (smell) {
			return smell;
		} else {

			if (bug.getFoodP() == 2 || bug.getFoodP() == 1) {
				// this loops through all the bugs
				for (int j = 0; j < bugs.size(); j++) {

					Bug bug2 = bugs.get(j);
					if (index != j) {
						int bugX = bug.getX();
						int bugY = bug.getY();
						int bugSmell = bug.getSmell();

						int bug2X = bug2.getX();
						int bug2Y = bug2.getY();

						int disX = Math.abs((int) (bug.getX() - bug2.getX()));
						int disY = Math.abs((int) (bug.getY() - bug2.getY()));
						int x = bugX;
						int y = bugY;
						// if the plant is within the smelling distance of the bug then the
						// bug will move towards it
						if (disX < bugSmell && disY < bugSmell) {

							if (bugX > bug2X) {
								x = x - 1;
								if (bugY > bug2Y)
									y = y - 1;
								else if (bugY < bug2Y)
									y = y + 1;
								if (validMove(x, y, bug)) {
									setBug(bug, x, y);
									bug2 = validBugEat(x, y, bug);
									if (bug2 != null) {
										bugs.remove(bug2);
										Main.removeBugRoot(bug2);

									}
									smell = true;
									break;
								}
							} else if (bugX < bug2X) {
								x = x + 1;
								if (bugY > bug2Y)
									y = y - 1;
								else if (bugY < bug2Y)
									y = y + 1;
								if (validMove(x, y, bug)) {
									setBug(bug, x, y);
									bug2 = validBugEat(x, y, bug);
									if (bug2 != null) {
										bugs.remove(bug2);
										Main.removeBugRoot(bug2);
									}
									smell = true;
									break;
								}
							} else {
								if (bugY > bug2Y)
									y = y - 1;
								else if (bugY < bug2Y)
									y = y + 1;
								if (validMove(x, y, bug)) {
									setBug(bug, x, y);
									bug2 = validBugEat(x, y, bug);
									if (bug2 != null) {
										bugs.remove(bug2);
										Main.removeBugRoot(bug2);
									}
									smell = true;
									break;
								}
							}
						}
					}
				}
			}
		}

		return smell;
	}

	// this method makes bugs move towards their food
	public boolean smellFoodNew(Bug bug) {
		// smell will be returned true if the bug finds something to smell
		// and follow
		boolean smell = false;
		// FoodP 0 plants, 1 bugs, 2 bugs and plants
		if (bug.getFoodP() == 0 || bug.getFoodP() == 2) {
			// loops through all bug and plant pairs to find the nearest to that bug
			for (BugPlantPairs bP : bugPlantPairs) {
				if (bP.getBug() == bug) {
					Plant plant = bP.getPlant();
					int bugX = bug.getX();
					int bugY = bug.getY();
					int bugSmell = bug.getSmell();

					int plantX = plant.getX();
					int plantY = plant.getY();

					int disX = Math.abs((int) (bug.getX() - plant.getX()));
					int disY = Math.abs((int) (bug.getY() - plant.getY()));
					int x = bugX;
					int y = bugY;
					// checks if plant is within the bugs smelling range
					if (disX < bugSmell && disY < bugSmell) {
						// if it is then the bug is moved towards it
						if (bugX > plantX) {
							x = x - 1;
							if (bugY > plantY)
								y = y - 1;
							else if (bugY < plantY)
								y = y + 1;
							if (validMove(x, y, bug)) {

								setBug(bug, x, y);

								smell = true;
								break;
							}
						} else if (bugX < plantX) {
							x = x + 1;
							if (bugY > plantY)
								y = y - 1;
							else if (bugY < plantY)
								y = y + 1;
							if (validMove(x, y, bug)) {
								setBug(bug, x, y);

								smell = true;
								break;
							}
						} else {
							if (bugY > plantY)
								y = y - 1;
							else if (bugY < plantY)
								y = y + 1;
							if (validMove(x, y, bug)) {
								setBug(bug, x, y);

								smell = true;
								break;
							}
						}
					}
				}
			}
		}
		if (smell) {
			return smell;
		} else {

			if (bug.getFoodP() == 2 || bug.getFoodP() == 1) {
				// this loops through all the bug pairs to find the nearest bug to it
				for (BugPairs bP : bugPairs) {
					if (bP.getBug1() == bug || bP.getBug2() == bug) {
						Bug bug2 = null;
					if(bP.getBug1() == bug) {
						bug2 = bP.getBug2();
					}
					else if(bP.getBug2() == bug) {
						bug2 = bP.getBug1();
					}
						if (bug2 != bug) {
							int bugX = bug.getX();
							int bugY = bug.getY();
							int bugSmell = bug.getSmell();

							int bug2X = bug2.getX();
							int bug2Y = bug2.getY();

							int disX = Math.abs((int) (bug.getX() - bug2.getX()));
							int disY = Math.abs((int) (bug.getY() - bug2.getY()));
							int x = bugX;
							int y = bugY;
							// if the bug is within the smelling distance of the bug then the
							// bug will move towards it
							if (disX < bugSmell && disY < bugSmell) {

								if (bugX > bug2X) {
									x = x - 1;
									if (bugY > bug2Y)
										y = y - 1;
									else if (bugY < bug2Y)
										y = y + 1;
									if (validMove(x, y, bug)) {
										setBug(bug, x, y);
										//this valid bug eat method checks if the bug is about
										//to walk/eat into a bug 
										bug2 = validBugEat(x, y, bug);
										if (bug2 != null) {
											bugs.remove(bug2);
											Main.removeBugRoot(bug2);

										}
										smell = true;
										break;
									}
								} else if (bugX < bug2X) {
									x = x + 1;
									if (bugY > bug2Y)
										y = y - 1;
									else if (bugY < bug2Y)
										y = y + 1;
									if (validMove(x, y, bug)) {
										setBug(bug, x, y);
										bug2 = validBugEat(x, y, bug);
										if (bug2 != null) {
											bugs.remove(bug2);
											Main.removeBugRoot(bug2);
										}
										smell = true;
										break;
									}
								} else {
									if (bugY > bug2Y)
										y = y - 1;
									else if (bugY < bug2Y)
										y = y + 1;
									if (validMove(x, y, bug)) {
										setBug(bug, x, y);
										bug2 = validBugEat(x, y, bug);
										if (bug2 != null) {
											bugs.remove(bug2);
											Main.removeBugRoot(bug2);
										}
										smell = true;
										break;
									}
								}
							}
						}
					}
				}
			}
		}

		return smell;
	}

	public void setBug(Bug bug, int x, int y) {
		bug.setX(x);
		bug.setY(y);
		bug.getView().setTranslateX(x * World.getImagewidth());
		bug.getView().setTranslateY(y * World.getImagewidth());
	}

	public ArrayList<Bug> getBugs() {
		return bugs;
	}

	public void setBugs(ArrayList<Bug> bugs) {
		this.bugs = bugs;
	}

	public void addBugs(Bug bug) {
		bugs.add(bug);

		NameEnergy nameE = new NameEnergy(bug.name, bug.energy);
		nameEns.add(nameE);

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ArrayList<Plant> getPlants() {
		return plants;
	}

	public void setPlants(ArrayList<Plant> plants) {
		this.plants = plants;
	}

	public void addPlants(Plant plant) {
		plants.add(plant);
	}

	public ArrayList<Obstacle> getObs() {
		return obs;
	}

	public void setObs(ArrayList<Obstacle> obs) {
		this.obs = obs;
	}

	public void addObs(Obstacle ob) {
		obs.add(ob);
	}

	public ArrayList<NameEnergy> getNameEns() {
		return nameEns;
	}

	public void setNameEns(ArrayList<NameEnergy> nameEns) {
		this.nameEns = nameEns;
	}

	public void addNameEns(NameEnergy nameE) {
		nameEns.add(nameE);
	}

	public ArrayList<String> getNames(ArrayList<NameEnergy> nameEns) {
		ArrayList<String> names = new ArrayList<String>();
		for (NameEnergy nE : nameEns) {
			names.add(nE.getName());
		}
		return names;
	}

	public ArrayList<Integer> getEnergies(ArrayList<NameEnergy> nameEns) {
		ArrayList<Integer> energies = new ArrayList<Integer>();
		for (NameEnergy nE : nameEns) {
			energies.add(nE.getEnergy());
		}
		return energies;
	}

	public ArrayList<BugPairs> getBugPairs() {
		return bugPairs;
	}

	public void setBugPairs(ArrayList<BugPairs> bugPairs) {
		this.bugPairs = bugPairs;
	}

	public void pairTheBugs(ArrayList<Bug> bugs) {
		for (int i = 0; i < bugs.size() - 1; i++) {
			for (int j = i + 1; j < bugs.size(); j++) {
				BugPairs bugPair = new BugPairs(bugs.get(i), bugs.get(j));
				bugPairs.add(bugPair);
			}
		}
	}

	public ArrayList<BugPlantPairs> getBugPlantPairs() {
		return bugPlantPairs;
	}

	public void setBugPlantPairs(ArrayList<BugPlantPairs> bugPlantPairs) {
		this.bugPlantPairs = bugPlantPairs;
	}

	public void pairTheBugPlants() {
		for (int i = 0; i < bugs.size(); i++) {
			for (int j = 0; j < plants.size(); j++) {
				BugPlantPairs bugPair = new BugPlantPairs(bugs.get(i), plants.get(j));
				bugPlantPairs.add(bugPair);
			}
		}
	}

	public void updatePairs() {
		bugPairs.clear();
		pairTheBugs(bugs);
		BugDistanceComparator bugDistanceComparator = new BugDistanceComparator();
		Collections.sort(bugPairs, bugDistanceComparator);

		bugPlantPairs.clear();
		pairTheBugPlants();
		bugPlantDistanceComparator bugPlantDistanceComparator = new bugPlantDistanceComparator();
		Collections.sort(bugPlantPairs, bugPlantDistanceComparator);
	}

	public void orderBugEnergy() {
		BugEnergyComparator bugEnergyComparator = new BugEnergyComparator();
		Collections.sort(bugs, bugEnergyComparator);
	}

	public static void orderBugID() {
		Collections.sort(bugs);
	}

	public static void orderBugSpecies() {
		BugSpeciesComparator bugSpeciesComparator = new BugSpeciesComparator();
		Collections.sort(bugs, bugSpeciesComparator);
	}

	public static void orderPlantValue() {
		PlantSizeComparator plantComparator = new PlantSizeComparator();
		Collections.sort(plants, plantComparator);
	}

	public static int randomPlant() {
		int ranP = (int) (0 + (Math.random() * (9)));
		return ranP;
	}

	public static int getImagewidth() {
		return imageWidth;
	}

}
