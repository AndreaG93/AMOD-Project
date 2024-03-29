package gui.javafx.LoadingView;

import java.util.concurrent.ThreadLocalRandom;

import gui.javafx.UserInterfaceJavaFX;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;

public class LoadingScreen extends UserInterfaceJavaFX {

	@FXML
	private ProgressBar progressBar_loading;
	@FXML
	private Label label_message;

	private boolean updateProgressBarColor = true;

	/**
	 * Constructs a newly allocated {@code View} object.
	 */
	public LoadingScreen() throws Exception {
	}

	private void startProgressBarColorAnimation()  {

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					while (updateProgressBarColor) {
						Thread.sleep(2000);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {

								ColorAdjust colorAdjust = new ColorAdjust();
								colorAdjust.setHue(ThreadLocalRandom.current().nextDouble(-1, 1));
								progressBar_loading.setEffect(colorAdjust);
							}
						});
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		}).start();
	}

	public void close() {
		this.updateProgressBarColor = false;
		this.stage.close();
	}

	@Override
	public void updateUserInterface() {
		this.startProgressBarColorAnimation();
	}
}
