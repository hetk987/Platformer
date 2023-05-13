package utilz;

public class Constants{
    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
        public static final int IDLE = -1;
    }
    public static class PlayerConstants{
        public static final int IDLE_RIGHT = 0;
        public static final int IDLE_LEFT = 1;
        public static final int RUNNING_RIGHT = 2;
        public static final int RUNNING_LEFT = 3;
        public static final int JUMPING_RIGHT = 4;
        public static final int JUMPING_LEFT = 5;

        public static int GetSpriteAmount(int playerAction){
            switch (playerAction) {
                case IDLE_LEFT: case IDLE_RIGHT:
                    return 7;
                case RUNNING_LEFT: case RUNNING_RIGHT:
                    return 4;
                case JUMPING_LEFT: case JUMPING_RIGHT:
                    return 1;
                default:
                    return 1;
            }
        }


    }

}