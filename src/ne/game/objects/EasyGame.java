package ne.game.objects;
import org.newdawn.slick.*;
import org.newdawn.slick.tests.SoundTest;

import java.util.ArrayList;

public class EasyGame extends BasicGame {

    private ArrayList<MeinUfo> mUfoList;

    private Image background;

    private Crusher crusher;

    public EasyGame() {
        super("EasyGame");
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer container = new AppGameContainer(new EasyGame());
        container.setDisplayMode(1024, 768, false);
        //container.setClearEachFrame(false);
        container.setMinimumLogicUpdateInterval(25);
        container.setTargetFrameRate(144);
        container.setShowFPS(true);
        container.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        background = new Image("assets/pics/background.png");
        mUfoList = new ArrayList<MeinUfo>();
        for (int i = 1; i <= 10; i++) {
            mUfoList.add(new MeinUfo(100, 100, new Image("assets/pics/meinufo.png")));
        }
        crusher = new Crusher(512,700,new Image("assets/pics/crusher.png"),container.getInput());
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            container.exit();
        }
        for (MeinUfo u : mUfoList) {
            if (crusher.intersects(u.getShape())) {
                System.out.println("collide");
                u.setRandomPosition();
            }
            u.update(delta);
        }
        crusher.update(delta);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        background.draw();
        for (MeinUfo u : mUfoList) {
            u.draw(g);
        }
        crusher.draw(g);
    }
}


