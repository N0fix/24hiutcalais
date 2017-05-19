import java.util.*;
import java.awt.*;

class cheminMoule {
    public static void main(String[] args) {
	Level level = new Level();
	String str = "5x5/";
	str += "D-D-D-D-D-";
	str += "D-S-S-S-D-";
	str += "D-M-S-S-D-";
	str += "D-S-S-S-D-";
	str += "D-D-D-D-D/";
	str += "1-3,2";
	level.update(str);
	System.out.println(level.toString());
	level.getPlayers().get(0).setPlayerPosition(2, 2);
	System.out.println(level.toString());

	Moule moule = new Moule(5,5,2,3,60,level);
    }
}

class Case {

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

class Level {
    private ArrayList<Case> grid;
    private ArrayList<Player> players;
    private static int width;
    private static int height;
    private int nbMoules;
    private int nbJoueurs;

    public Level() {
	width = 0;
	this.height = 0;
	this.nbMoules = 0;
	this.nbJoueurs = 0;
	this.grid = new ArrayList<Case>();
	this.players = new ArrayList<Player>();
    }

    public void update(String chaine) {
	this.grid.clear();
	String[] infos = chaine.split("/");
	int index = infos[0].indexOf("x");

	width = Integer.parseInt(infos[0].substring(0, index));
	index++;
	this.height = Integer.parseInt(infos[0].substring(index));
	String[] struct = infos[1].split("-");
	for (int i = 0; i < struct.length; i++) {
	    Point pos = this.calculatePosition(i);

	    if (struct[i].equals("D"))
		grid.add(new Case(pos.x, pos.y, Case.CaseType.DUNE));
	    else if (struct[i].equals("S"))
		grid.add(new Case(pos.x, pos.y, Case.CaseType.SAND));
	    else if (struct[i].equals("F"))
		grid.add(new Case(pos.x, pos.y, Case.CaseType.FRIES));
	    else if (struct[i].equals("B"))
		grid.add(new Case(pos.x, pos.y, Case.CaseType.BEER));
	    else {
		grid.add(new Case(pos.x, pos.y, 0));
		this.nbMoules++;
	    }
	}
	
	String[] joueurs = infos[2].split("-");
	this.nbJoueurs = Integer.parseInt(joueurs[0]);
	this.players.clear();
	for (int i = 1; i < joueurs.length; i++) {
	    index = joueurs[i].indexOf(",");
	    int joueurX = Integer.parseInt(joueurs[i].substring(0, index));
	    index++;
	    int joueurY = Integer.parseInt(joueurs[i].substring(index));
	    players.add(new Player(joueurX, joueurY, this));
	}
    }

    public String toString() {
	String str = "";
	for (int i = 0; i < width; i++) {
	    for (int j = 0; j < height; j++) {
		boolean play = false;
		for(int z = 0; z < players.size(); z++){
		    if(i == players.get(z).getPositionX()
		       && j == players.get(z).getPositionY()){
			play = true;
			str += "J";
		    }
		}
		if(play == false)
		    str += grid.get(Level.calculateCase(j, i)).toString();
	    }
	    str += "\n";
	}


	return str;
    }

    public static int calculateCase(int positionX, int positionY) {
	return (width * positionY + positionX);
    }

    private Point calculatePosition(int index) {
	return new Point(index % this.width, index / height);
    }

    public Case.CaseType checkCase(int position) {
	return grid.get(position).getCaseType();
    }

    public ArrayList<Case> getGrid() {
	return grid;
    }

    public void setGrid(ArrayList<Case> grid) {
	this.grid = grid;
    }

    public ArrayList<Player> getPlayers() {
	return players;
    }

    public void setPlayers(ArrayList<Player> players) {
	this.players = players;
    }

    public static int getWidth() {
	return width;
    }

    public static void setWidth(int width) {
	Level.width = width;
    }

    public int getHeight() {
	return height;
    }

    public void setHeight(int height) {
	this.height = height;
    }

    public int getNbMoules() {
	return nbMoules;
    }

    public void setNbMoules(int nbMoules) {
	this.nbMoules = nbMoules;
    }

    public int getNbJoueurs() {
	return nbJoueurs;
    }

    public void setNbJoueurs(int nbJoueurs) {
	this.nbJoueurs = nbJoueurs;
    }

}

//class PlayerIA {
//    
//}

class Moule {
    private int tailleX;
    private int tailleY;
    private int posX;
    private int posY;
    private int valeur;
    private Level level;
    private ArrayList<Integer> cheminMoule;

    Moule(int tailleX, int tailleY, int posX, int posY, int valeur, Level level) {
	this.tailleX = tailleX;
	this.tailleY = tailleY;
	this.posX = posX;
	this.posY = posY;
	this.valeur = valeur;
	this.level = level;
	this.cheminMoule = new ArrayList<Integer>();
    }

    public void genererCheminMoule() {
	int i = 0;
	while (i < this.tailleX*this.tailleY) {
	    if (level.checkCase(i) == Case.CaseType.DUNE)
		this.cheminMoule.add(-1);
	    else if (level.checkCase(i) == Case.CaseType.MOULE)
		this.cheminMoule.add(0);
	    else
		this.cheminMoule.add(-2);
	    i++;
	}

	remplirCase(this.getIDMoule());
    }

    public int getIDMoule() {
	return (this.posX+1)+(this.posY*this.tailleX); 
    }

    public Integer getCaseUp(int idCase) {
	if (idCase-tailleX > 0)
	    return new Integer(idCase-tailleX);
	else
	    return new Integer(-10);
    }

    public Integer getCaseDown(int idCase) {
	if (idCase+tailleX < tailleX*tailleY+1) // verifier ça
	    return new Integer(idCase+tailleX);
	else
	    return new Integer(-10);
    }

    public int getCaseLeft(int idCase) {
	if (idCase == 0)
	    return -10;
	else if (idCase%tailleX != 0)
	    return idCase-1;
	else
	    return -10;
    }

    public int getCaseRight(int idCase) {
	if (idCase == tailleX-1)
	    return -10;
	else if (idCase%tailleX != tailleX-1)
	    return idCase+1;
	else
	    return -10;
    }

    public void remplirCase(int idCase) {
	if (getCaseRight(idCase) == -2) {
	    int caseDroite = getCaseRight(idCase);
	    cheminMoule.get(caseDroite) = cheminMoule.get(idCase)+1;
	    remplirCase(caseDroite);
	}
	if (getCaseLeft(idCase) == -2) {
	    int caseGauche = getCaseLeft(idCase);
            cheminMoule.get(caseGauche) = cheminMoule.get(idCase)+1;
	    remplirCase(caseGauche);
	}
	if (getCaseDown(idCase) == -2) {
            int caseBas = getCaseDown(idCase);
            cheminMoule.get(caseBas) = cheminMoule.get(idCase)+1;
            remplirCase(caseBas);
	}
	if (getCaseUp(idCase) == -2) {
            int caseHaut = getCaseUp(idCase);
            cheminMoule.get(caseHaut) = cheminMoule.get(idCase)+1;
            remplirCase(caseHaut);
	}
    }

    public void afficherCheminMoule() {
	int i = 0;

	while (i < tailleX*tailleY) {
	    System.out.print("" + cheminMoule.get(i));
	    if (i%tailleX == tailleX-1)
		System.out.println("");
	    i++;
	}
    }
}