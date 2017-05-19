
public class Jeu {
	public static void main(String[] args){
		Level level = new Level();
		String str = "13x11/";
		str += "D-D-D-D-D-D-D-D-D-D-D-D-D-";
		str += "D-60-S-S-D-S-S-S-D-S-D-S-D-";
		str += "D-S-D-D-D-D-D-S-D-S-D-S-D-";
		str += "D-120-D-D-D-D-D-S-D-S-D-S-D-";
		str += "D-S-D-D-D-D-D-S-D-S-D-S-D-";
		str += "D-S-D-D-D-D-D-S-D-S-D-S-D-";
		str += "D-S-D-D-D-D-D-S-D-S-D-S-D-";
		str += "D-S-D-D-D-D-D-S-D-S-D-S-D-";
		str += "D-S-D-D-D-D-D-S-D-S-D-S-D-";
		str += "D-S-D-D-D-D-D-S-D-S-D-S-D-";
		str += "D-D-D-D-D-D-D-D-D-D-D-D-D/";
		str += "1-3,1";
		level.update(str);
		System.out.println(level.toString());
		level.getPlayers().get(0).setPlayerPosition("WEST");
		System.out.println(level.toString());
		
		Moule moule = new Moule(13,11,1,1,60,level);
		moule.genererCheminMoule();
		
		level.getPlayers().get(0).setPlayerPosition("WEST");
		System.out.println(level.toString());
		level.getPlayers().get(0).setPlayerPosition("SOUTH");
		System.out.println(level.toString());
		System.out.println("SCORE JOUEUR 1 = "+ level.getPlayers().get(0).getScore());
	}
}
