package de.skijearz.model;
import java.awt.event.KeyEvent;
import java.util.Random;
import org.sikuli.basics.Settings;
import org.sikuli.script.*;

public class bot implements Runnable {

	private static final Pattern actionPopUp = new Pattern (bot.class.getResource("../view/resources/Action_popup_button.png"));
	private static final Pattern succesrate_imp = new Pattern (bot.class.getResource("../view/resources/succes_rate_imp.png"));;
	private static Screen s = new Screen(0);
	private static Region r = new Region(455, 170, 1050, 680);
	Random rand = new Random();

	public boolean botRunning = false;
	public boolean initiated = false;

	public boolean getState() {
		return botRunning;
	}

	public void setState(boolean botState) {
		this.botRunning = botState;
	}

	@Override
	public void run() {
		if (!initiated) {
			r.setAutoWaitTimeout(0.3);

			
			Settings.ObserveScanRate = 10f;
			Settings.WaitScanRate = 10f;
			App.focus("MapleStory2 - A new Beginning");
			initiated = true;
		}

		while (botRunning) {

			if (r.exists(actionPopUp) != null && !(r.exists(succesrate_imp) != null)) {
				s.keyDown(KeyEvent.VK_SPACE);
				s.keyUp(KeyEvent.VK_SPACE);
			}
			
			r.wait(rand.nextDouble());

		}

	}

}
