import java.awt.Color;


/**
 * Definition of a virus.
 * @author Stefan Jeremic
 *
 */
public class VirusDefinition {
	private int strength;
	private int dexterity;
	private int vitality;
	private Color color;
	/**
	 * @return Strength of a virus
	 */
	public int getStrength() {
		return strength;
	}
	/**
	 * @param strength Strength to be set.
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}
	/**
	 * @return Dexterity of a virus
	 */
	public int getDexterity() {
		return dexterity;
	}
	/**
	 * @param dexterity Dexterity to be set.
	 */
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
	/**
	 * @return Vitality of a virus
	 */
	public int getVitality() {
		return vitality;
	}
	/**
	 * @param vitality Vitality to be set
	 */
	public void setVitality(int vitality) {
		this.vitality = vitality;
	}
	/**
	 * @return Color of a virus
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @param color Color to be set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
}
