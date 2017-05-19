
public class Jeu {
	public static void main(String[] args){
		Level level = new Level(0);
		String str = "13x11/";
		str += "D-D-D-D-D-D-D-D-D-D-D-D-D-";
		str += "D-60-S-S-D-S-S-S-D-S-D-S-D-";
		str += "D-S-D-D-D-D-D-S-D-S-D-S-D-";
		str += "D-120-D-D-D-D-D-S-D-S-D-S-D-";
		str += "D-S-D-D-D-D-D-S-D-S-D-S-D-";
		str += "D-S-D-D-D-D-D-S-10-S-D-S-D-";
		str += "D-S-D-D-D-D-D-S-D-S-D-S-D-";
		str += "D-S-D-D-D-D-D-S-D-S-D-S-D-";
		str += "D-S-D-D-D-D-D-S-D-S-D-S-D-";
		str += "D-S-D-D-D-D-D-S-D-S-D-S-D-";
		str += "D-D-D-D-D-D-D-D-D-D-D-D-D/";
		str += "1-1,7";
		level.update(str);
		level.getMoules().get(0).genererCheminMoule();
		level.getMoules().get(0).afficherCheminMoule();
		
		
		
		System.out.println(level.toString());
		System.out.println(level.getCoup());
	}
}
