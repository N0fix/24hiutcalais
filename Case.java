

public class Case {
	private int positionX;
	private int positionY;
	private CaseType CaseType;
	public Case(int x, int y){
		CaseType = CaseType.DUNE;
		positionX = x;
		positionY = y;
	}
	
	public void transfortCase(CaseType c){
		CaseType = c;
	}
	
	
	public enum CaseType {
		SAND, DUNE, BEER, PLAYER, FRIES, MOULE;
	}
}

