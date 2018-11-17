import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		botWindow();
	}

	public void botWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("botMenu.fxml"));
			Pane p = loader.load();
			botMenuController bmc = loader.getController();
			bmc.setMain(this);
			GlobalKeyListener.getControllerInstance(bmc);

			Scene s = new Scene(p);
			primaryStage.setScene(s);
			primaryStage.setResizable(false);
			primaryStage.setTitle("MS2BOT");
			primaryStage.setOnCloseRequest(evt -> {
				System.exit(1);
			});
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
