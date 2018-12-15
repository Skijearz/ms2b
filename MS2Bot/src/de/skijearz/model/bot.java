package de.skijearz.model;

import java.awt.event.KeyEvent;
import java.util.Random;
import org.sikuli.basics.Settings;
import org.sikuli.script.*;

public class bot implements Runnable {
	private static final double TIME_TO_WAIT = 0.795;
	private static final Pattern actionPopUp = new Pattern(
			Main.class.getResource("/de/skijearz/view/resources/Action_popup_button.png"));
	private static final Pattern succesrate_imp = new Pattern(
			Main.class.getResource("/de/skijearz/view/resources/succes_rate_imp.png"));;
	private static Screen s = new Screen(0);
	private static Region r = new Region(455, 170, 1050, 680);

	private int currentCollum = 1;
	private int maxCollum;
	private int row = 0;
	Random rand = new Random();

	private static boolean initiated = false;
	private boolean botRunning = false;
	private boolean autoWalk = false;

	public void setCollums(int Collums) {
		this.maxCollum = Collums;
	}
	public boolean getState() {
		return botRunning;
	}

	public void setState(boolean botState) {
		this.botRunning = botState;
	}

	public boolean getAutoWalk() {
		return autoWalk;
	}

	public void setAutoWalk(boolean botState) {
		this.autoWalk = botState;
	}

	@Override
	public void run() {
		if (!initiated) {
			r.setAutoWaitTimeout(0.3);

			Settings.ObserveScanRate = 5f;
			Settings.WaitScanRate = 5f;
			App.focus("MapleStory2 - A new Beginning");
			initiated = true;
		}

		while (botRunning) {

			if (r.exists(actionPopUp) != null && !(r.exists(succesrate_imp) != null)) {

				keyPress(KeyEvent.VK_SPACE, 0.09);

			}
			if (r.exists(actionPopUp) != null && (r.exists(succesrate_imp) != null) && autoWalk) {

				if (currentCollum % maxCollum == 0) {
					keyPress(KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD6, TIME_TO_WAIT);
					currentCollum++;
					row++;
					
				} else if (row % 2 == 0) {
					keyPress(KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD6, TIME_TO_WAIT);
					
					currentCollum++;
					
				} else {
					keyPress(KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD8, TIME_TO_WAIT);
					
					currentCollum++;
					
				}

			}

			r.wait(rand.nextDouble());

		}

	}

	public static void keyPress(int key, Double timeToWait) {
		r.keyDown(key);
		try {
			s.wait(timeToWait);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		r.keyUp(key);
	}

	public static void keyPress(int key1, int key2, Double timeToWait) {
		r.keyDown(key1);
		r.keyDown(key2);
		try {
			r.wait(timeToWait);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		r.keyUp(key1);
		r.keyUp(key2);
	}

}
