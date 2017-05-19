
public class Jeu {
	public static void main(String[] args){
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
	}
}
