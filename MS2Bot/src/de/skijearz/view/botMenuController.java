package de.skijearz.view;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import de.skijearz.model.GlobalKeyListener;
import de.skijearz.model.Main;
import de.skijearz.model.bot;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class botMenuController extends Thread {
	private static boolean initiated = false;
	public Main main;
	bot b = new bot();
	
	public void setMain(Main main) {
		this.main = main;
	}

	@FXML private TextField numberOfCollums;
	@FXML private CheckBox autoWalk;
	
	@FXML
	public void startBot() {
		if (!initiated) {
			try {
				GlobalScreen.registerNativeHook();

			} catch (NativeHookException e) {
				e.printStackTrace();
			}
			GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
			Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
			logger.setLevel(Level.WARNING);
			logger.setUseParentHandlers(false);
			GlobalKeyListener.getBot(b);
			initiated = true;
		}
		if (!b.getState()) {
			b.setState(true);
			Thread bot = new Thread(b);
			bot.start();
		}
	}

	@FXML
	public void stopBot() {

		if (b.getState()) {
			b.setState(false);

		}
	}
	@FXML
	public void setAutoWalk() {
		try {
		b.setCollums(Integer.parseInt(numberOfCollums.getText()));
		b.setAutoWalk(!b.getAutoWalk());
		}catch(NumberFormatException e) {
			autoWalk.setSelected(false);
			System.out.println("Eingabe keine Zahl");
		}
	}
}
