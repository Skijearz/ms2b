import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javafx.fxml.FXML;

public class botMenuController extends Thread {
	private static boolean initiated = false;
	public Main main;
	bot b = new bot();

	public void setMain(Main main) {
		this.main = main;
	}

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
}
