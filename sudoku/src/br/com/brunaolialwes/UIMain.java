package br.com.brunaolialwes;

import br.com.brunaolialwes.ui.custom.screen.MainScreen;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UIMain {

    public static void main(String[] args) {
        final Map<String, String> gameConfig = Stream.of(args)
                .collect(Collectors.toMap(k -> k.split(";")[0], v -> v.split(";")[1]));

        var mainScreen = new MainScreen(gameConfig);
        mainScreen.buildMainScreen();

    }
}
