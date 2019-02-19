package menu.view;

import java.io.IOException;

import javafx.fxml.FXML;
import menu.StartMenu;

public class StartMenuController {
	
	private StartMenu main;
	
	@FXML
	private void goStart() throws IOException {
		main.showStart();
		
	}

}
