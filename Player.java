
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

		if (str == "S") {
			positionY += 1;
		} else if (str == "N") {
			positionY -= positionY - 1;
		} else if (str == "W") {
			positionX = positionX - 1;
		} else if (str == "E") {
			positionX = positionX + 1;
		} else {
			
		}
	
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
