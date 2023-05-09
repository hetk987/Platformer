package main;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

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
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

		int frames = 0;
        int updates = 0;

        double deltaU = 0;
        double deltaF = 0;
		long lastCheck = System.currentTimeMillis();

		while (true) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;

            previousTime = currentTime;

            if (deltaU>1)
            {
                update();
                updates++;
                deltaU--;
            }
            if (deltaF>1)
            {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + "UPS:" + updates);
				frames = 0;
                updates = 0;   
			}
        }
    }


    private void update() {
        gamePanel.updateGame();
    }

    private void StartGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
}
