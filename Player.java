
public class Player {
	private int positionX;
	private int positionY;
	private int numberBeer;
	private int numberFries;
	private int score;
	private Level level;

	public Player(int x, int y, Level level) {
		positionX = x;
		positionY = y;
		score = 0;
		numberBeer = 0;
		numberFries = 0;
		this.level = level;
	}

	public void setPlayerPosition(int newPositionX, int newPositionY) {
		int calcul = Level.calculateCase(newPositionX, newPositionY);
		if (level.getGrid().get(calcul).getCaseType() == Case.CaseType.DUNE) {
			System.out.println("Can't move there");
			return;
		}
		positionX = newPositionX;
		positionY = newPositionY;

		if (level.checkCase(calcul) == Case.CaseType.BEER) {
			numberBeer++;
		} else if (level.checkCase(calcul) == Case.CaseType.FRIES) {
			numberFries++;
		} else if (level.checkCase(calcul) == Case.CaseType.MOULE) {

		}

		level.getGrid().get(calcul).setCaseType(Case.CaseType.SAND);

	}

	public void setPlayerPosition(String str) {
		int newPositionX = 0;
		int newPositionY = 0;
		if (str == "SOUTH") {
			newPositionX = positionX;
			newPositionY = positionY + 1;
		} else if (str == "NORTH") {
			newPositionX = positionX;
			newPositionY = positionY - 1;
		} else if (str == "WEST") {
			newPositionX = positionX - 1;
			newPositionY = positionY;
		} else if (str == "EAST") {
			newPositionX = positionX + 1;
			newPositionY = positionY;
		}
		int calcul = Level.calculateCase(newPositionX, newPositionY);
		if (level.getGrid().get(calcul).getCaseType() == Case.CaseType.DUNE) {
			System.out.println("Can't move there");
			return;
		}
		positionX = newPositionX;
		positionY = newPositionY;

		if (level.checkCase(calcul) == Case.CaseType.BEER) {
			numberBeer++;
		} else if (level.checkCase(calcul) == Case.CaseType.FRIES) {
			numberFries++;
		} else if (level.checkCase(calcul) == Case.CaseType.MOULE) {
			score += level.getGrid().get(calcul).getScore();
			int size = level.getMoules().size();
			int indexToRemove = -1;
			for(int i = 0; i < size; i++){
				int x = level.getMoules().get(i).getPosX();
				int y = level.getMoules().get(i).getPosY();
				if(x == positionX && y == positionY){
					indexToRemove = i;
				}
			}
			if(indexToRemove != -1) level.getMoules().remove(indexToRemove);
			
		}

		level.getGrid().get(calcul).setCaseType(Case.CaseType.SAND);

	}

	////

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getNumberBeer() {
		return numberBeer;
	}

	public void setNumberBeer(int numberBeer) {
		this.numberBeer = numberBeer;
	}

	public int getNumberFries() {
		return numberFries;
	}

	public void setNumberFries(int numberFries) {
		this.numberFries = numberFries;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
