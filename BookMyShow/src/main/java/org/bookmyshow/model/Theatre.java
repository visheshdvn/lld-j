package org.bookmyshow.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class Theatre {
    private final String id;
    private final String name;
    private final Map<String, Screen> screens = new HashMap<>();

    public void addScreen(Screen screen) {
        screens.put(screen.getId(), screen);
    }

    public Screen getScreen(String screenId) {
        return screens.get(screenId);
    }
}
