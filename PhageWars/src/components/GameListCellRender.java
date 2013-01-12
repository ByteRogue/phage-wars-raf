package components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * @author Stefan Jeremic
 *
 */
public class GameListCellRender extends JLabel implements ListCellRenderer {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 9170788347510328295L;
	/**
	 * Main constructor, sets opaque true.
	 */
	public GameListCellRender(){
		setOpaque(true);
	}
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
        setText(value.toString());

        Color background;
        Color foreground;

        if (isSelected) {
            background = Color.green;
            foreground = Color.white;

        // unselected, and not the DnD drop location
        } else {
            background = new Color(0xaaffaa);
            foreground = new Color(0x222222);
        };

        setBackground(background);
        setForeground(foreground);
		return this;
	}

}
