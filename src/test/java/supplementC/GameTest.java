package test.java.supplementC;

import main.java.supplementC.Game;
import main.java.supplementC.GameLevel;
import main.java.supplementC.GameNumGen;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class GameTest {

    @Test
    void init() {
        GameNumGen genMock = mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.EASY);

        then(genMock).should().generate(GameLevel.EASY);
    }
}
