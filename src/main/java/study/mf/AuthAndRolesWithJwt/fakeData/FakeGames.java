package study.mf.AuthAndRolesWithJwt.fakeData;

import java.util.ArrayList;
import java.util.List;

public final class FakeGames {
    public static final List<String> games = new ArrayList<>(List.of(
            "Super Mario World",
            "Super Mario Kart",
            "Super Buster Bros",
            "Mickey Mania - Timeless Adventure",
            "Aladdin"
    ));

    private FakeGames() {}
}
