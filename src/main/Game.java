package main;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus(true);
        StartGameLoop();
        
    }

    @Override
    public void run() {
        //time per frame in nanoseconds
        double timePerFrame = 1000000000.0 / FPS_SET;
		long lastFrame = System.nanoTime();
		long now = System.nanoTime();

		int frames = 0;
		long lastCheck = System.currentTimeMillis();

		while (true) {

			now = System.nanoTime();
			if (now - lastFrame >= timePerFrame) {
				gamePanel.repaint();
				lastFrame = now;
				frames++;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
			}
        }
    }


    private void StartGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
}
