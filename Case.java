
public class Case {

	private int positionX;
	private int positionY;
	private CaseType caseType;
	private int score;

	public Case(int x, int y, CaseType c) {
		caseType = c;
		positionX = x;
		positionY = y;
	}

	public Case(int x, int y, int score) {
		System.out.println("MON SCORE = " + score);
		this.score = score;
		caseType = CaseType.MOULE;
		positionX = x;
		positionY = y;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

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

	public CaseType getCaseType() {
		return caseType;
	}

	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}

	public static enum CaseType {
		SAND, DUNE, BEER, PLAYER, FRIES, MOULE;
	}

	@Override
	public String toString() {
		String str = "M";
		if (caseType == CaseType.SAND)
			return "S";
		else if (caseType == CaseType.DUNE)
			return "D";
		else if (caseType == CaseType.BEER)
			return "B";
		else if (caseType == CaseType.FRIES)
			return "F";
		return str;
	}
}
