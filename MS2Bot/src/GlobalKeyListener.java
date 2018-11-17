import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.sikuli.script.App;

public class GlobalKeyListener implements NativeKeyListener { 
	private static bot b = null;
	private static botMenuController bmc = null;

	public static void getBot(bot botInstace) {
		b = botInstace;
	}

	public static void getControllerInstance(botMenuController bmcInstance) {
		bmc = bmcInstance;
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		if (e.getKeyCode() == NativeKeyEvent.VC_F6) {
			if (!b.getState()) {
				bmc.startBot();
			} else {
				bmc.stopBot();
			}

		}
		if(e.getKeyCode() == NativeKeyEvent.VC_F7) {
			if(App.focusedWindow().getW() < 1919) {
				App.focus("MapleStory2 - A new Beginning");
			}else {
				App.focus("MS2BOT");
			}
			
		}

	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {

	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {

	}

}
