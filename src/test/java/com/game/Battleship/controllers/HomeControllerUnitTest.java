package com.game.Battleship.controllers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HomeControllerUnitTest {

    @Test
    public void welcome() {
        HomeController controller = new HomeController();
        String result = controller.welcome();
        assertEquals ("Welcome to Battleship game!", result);
    }
}
