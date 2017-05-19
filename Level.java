import java.util.ArrayList;
import java.awt.Point;

public class Level {
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