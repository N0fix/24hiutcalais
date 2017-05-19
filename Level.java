import java.util.ArrayList<E>;

public class Level {
	private ArrayList<Case> grid;
	private int width;
	private int height;
	private int nbMoules;
	private int nbJoueurs;

	public Level(String chaine){
		update(info);
	}

	public update(String chaine){
		String[] infos = chaine.split("/");
		int index = infos[0].indexOf("x");
		
		this.width = Integer.parseInt(infos[0].substring(0, index));
		this.height = Integer.parseInt(infos[0].substring(index++));

		String[] struct = infos.split("-");
		for (int i = 0; i < struct.length(); i++){
			int x = i%this.width;
			int y = i%this.height;

			if (struct[i].equals("D"))
				grid.add = new Case(x, y, CaseType.DUNE);
			else if (struct[i].equals("S"))
				grid.add = new Case(x, y, CaseType.SAND);
			else if (struct[i].equals("F"))
				grid.add = new Case(x, y, CaseType.FRIES);
			else if (struct[i].equals("B"))
				grid.add = new Case(x, y, CaseType.BEER);
			else
				grid.add = new Case(x, y, CaseType.MOULE);
		}
	}


}