import java.awt.Point;
import java.util.ArrayList;

public class Level {
	private ArrayList<Case> grid;
	private Player player;
	private ArrayList<Moule> moules;
	private static int width;
	private static int height;
	private int nbMoules;
	private int nbJoueurs;
	private int numeroJoueur;
	
	public Level(int numeroJoueur) {
		width = 0;
		height = 0;
		this.numeroJoueur = numeroJoueur;
		this.nbMoules = 0;
		this.nbJoueurs = 0;
		this.grid = new ArrayList<Case>();
		this.moules = new ArrayList<Moule>();
	}

	public void update(String chaine) {
		this.grid.clear();
		String[] infos = chaine.split("/");
		int index = infos[0].indexOf("x");

		width = Integer.parseInt(infos[0].substring(0, index));
		index++;
		height = Integer.parseInt(infos[0].substring(index));
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
				grid.add(new Case(pos.x, pos.y, Integer.parseInt(struct[i])));
				this.moules.add(new Moule(width, height, pos.x, pos.y, Integer.parseInt(struct[i]), this));
				this.nbMoules++;
			}
		}
		String[] joueurs = infos[2].split("-");
		this.nbJoueurs = Integer.parseInt(joueurs[0]);
		
		for (int i = 1; i < joueurs.length; i++) {
			index = joueurs[i].indexOf(",");
			int joueurX = Integer.parseInt(joueurs[i].substring(0, index));
			index++;
			int joueurY = Integer.parseInt(joueurs[i].substring(index));
			if(i-1 == numeroJoueur){
				player = new Player(joueurX, joueurY, this);
			}
				
		}
	}

	public String getCoup() {
		int size = moules.size();
		int direction = 0;
		int value = 2000;
		int indexPlayer = calculateCase(player.getPositionX(), player.getPositionY());
		int indexN = indexPlayer - width;
		int indexS = indexPlayer + width;
		int indexW = indexPlayer - 1;
		int indexE = indexPlayer + 1;
		
		for(int i = 0; i < size; i++){
			
			moules.get(i).genererCheminMoule();
			if(moules.get(i).getCheminMoule().get(indexN) < value && moules.get(i).getCheminMoule().get(indexN) != -1){
				value = moules.get(i).getCheminMoule().get(indexN);
				direction = 1;
				System.out.println("test");
			} else if (moules.get(i).getCheminMoule().get(indexE) < value && moules.get(i).getCheminMoule().get(indexE) != -1){
				value = moules.get(i).getCheminMoule().get(indexE);
				direction = 2;
				System.out.println("test");
			} else if (moules.get(i).getCheminMoule().get(indexS) < value && moules.get(i).getCheminMoule().get(indexS) != -1){
				value = moules.get(i).getCheminMoule().get(indexS);
				direction = 3;
				System.out.println("test");
			} else if (moules.get(i).getCheminMoule().get(indexW) < value && moules.get(i).getCheminMoule().get(indexW) != -1){
				value = moules.get(i).getCheminMoule().get(indexW);
				direction = 4;
				System.out.println("test");
			}
		}
		System.out.println(direction);
		if (direction == 1){
			return "N";
		} else if (direction == 2){
			return "E";
		} else if (direction == 3){
			return "S";
		} else if (direction == 4)
			return "W";
		else return "C";
	}

	public ArrayList<Moule> getMoules() {
		return moules;
	}

	public void setMoules(ArrayList<Moule> moules) {
		this.moules = moules;
	}

	public static void setHeight(int height) {
		Level.height = height;
	}

	public String toString() {
		String str = "";
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				boolean play = false;
					if (i == player.getPositionX() && j == player.getPositionY()) {
						play = true;
						str += "J ";
					}
				if (play == false)
					str += grid.get(Level.calculateCase(i, j)).toString() + " ";
			}
			str += "\n";
		}
		return str;
	}

	public static int calculateCase(int positionX, int positionY) {
		return (width * positionY + positionX);
	}

	private Point calculatePosition(int index) {
		return new Point(index % width, index / height);
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



	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getNumeroJoueur() {
		return numeroJoueur;
	}

	public void setNumeroJoueur(int numeroJoueur) {
		this.numeroJoueur = numeroJoueur;
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