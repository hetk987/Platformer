package gamestates;

public enum Gamestate{
    PLAYING, MENU, GAMEOVER, GAMEWIN, PAUSE;

    public static Gamestate state = PLAYING;
    
    public String getGamestate(){
        if(state == PLAYING)
            return "playing";
        else if(state == MENU)
            return "menu";
        else
            return null;
    }

}