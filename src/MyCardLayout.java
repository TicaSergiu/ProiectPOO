import java.awt.*;

public class MyCardLayout extends CardLayout {

	// In loc sa fie setata marimea fereastrei de panel-ul cel mai mare, marimea va fi setata de marimea panel-ulului
	// care este afisat in fereastra
	@Override
	public Dimension preferredLayoutSize(Container parent) {
		Component current = findCurrentComponent(parent);
		if (current != null) {
			Insets insets = parent.getInsets();
			Dimension pref = current.getPreferredSize();
			pref.width += insets.left + insets.right;
			pref.height += insets.top + insets.bottom;

			return pref;
		}
		return super.preferredLayoutSize(parent);
	}

	public Component findCurrentComponent(Container parent) {
		for (Component comp : parent.getComponents()) {
			if (comp.isVisible()) {
				return comp;
			}
		}
		return null;
	}

}
