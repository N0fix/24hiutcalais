import java.awt.Point;
import java.util.ArrayList;

//Moule moule = new Moule(5,5,2,3,60,level);

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
		this.cheminMoule.add(new Integer(-1));
	    else if (level.checkCase(i) == Case.CaseType.MOULE)
		this.cheminMoule.add(new Integer(0));
	    else
		this.cheminMoule.add(new Integer(-2));
	    i++;
	}

	remplirCase(this.getIDMoule());
    }

    public int getIDMoule() {
	int calcul = (this.posX+1)+(this.posY*this.tailleX);
	System.out.println("IDMoule: " + calcul);
	return (this.posX+1)+(this.posY*this.tailleX)-1;
    }

    public int getCaseUp(int idCase) {
	if (idCase-tailleX > 0)
	    return idCase-tailleX;
	else
	    return -10;
    }

    public int getCaseDown(int idCase) {
	if (idCase+tailleX < tailleX*tailleY+1) // verifier ça
	    return idCase+tailleX;
	else
	    return -10;
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
	Integer casePlus = new Integer(cheminMoule.get(idCase)+1);
	/*System.out.println("1er " + idCase);
	System.out.println("Case en dessous: " + getCaseDown(idCase));
	System.out.println("Case a gauche: " + getCaseLeft(idCase));
	System.out.println("Case a droite: " + getCaseRight(idCase));
	System.out.println("Case en haut: " + getCaseUp(idCase));
	System.out.println("2eme " + cheminMoule.get(getCaseDown(idCase)));
	*/
	
	if (cheminMoule.get(getCaseRight(idCase)) != -10 
	    && cheminMoule.get(getCaseUp(idCase)) != -10 
	    && cheminMoule.get(getCaseDown(idCase)) != -10 
	    && cheminMoule.get(getCaseLeft(idCase)) != -10) {
	    
	    if (cheminMoule.get(getCaseRight(idCase)) == -2) {
		int caseDroite = getCaseRight(idCase);
		cheminMoule.set(caseDroite, casePlus);
		remplirCase(caseDroite);
	    }
	    if (cheminMoule.get(getCaseLeft(idCase)) == -2) {
		int caseGauche = getCaseLeft(idCase);
		cheminMoule.set(caseGauche, casePlus);
	    remplirCase(caseGauche);
	    }
	    if (cheminMoule.get(getCaseDown(idCase)) == -2) {
		int caseBas = getCaseDown(idCase);
		cheminMoule.set(caseBas, casePlus);
		remplirCase(caseBas);
	    }
	    if (cheminMoule.get(getCaseUp(idCase)) == -2) {
		int caseHaut = getCaseUp(idCase);
		cheminMoule.set(caseHaut, casePlus);
		remplirCase(caseHaut);
	    }
	}
    }

    public void afficherCheminMoule() {
	int i = 0;
	
	while (i < tailleX*tailleY) {
	    System.out.print(" " + cheminMoule.get(i));
	    if (i%tailleX == tailleX-1)
		System.out.println("");
	    i++;
	}
    }
}