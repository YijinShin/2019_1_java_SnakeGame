package Game.src;

public class Collisions {
    // Board에서 만든 객체들 저장할 private 변수
    private Snake snake1;
    private Snake snake2;
    private Food food;
    private Poison poison = new Poison();
    private boolean fstPlayerWin = false;
    private boolean sndPlayerWin = false;

    // Constructor을 만들때 snake1, snake2, food, poison 객체 reference를 전달해준
    public Collisions(Snake snake1, Snake snake2, Food food, Poison poison) {
        this.snake1 = snake1;
        this.snake2 = snake2;
        this.food = food;
        this.poison = poison;
    }

    // if our snake is in the close proximity of the food..
    void checkFoodCollisions() {

        if ((proximity(snake1.getSnakeX(0), food.getFoodX(), 10))
                && (proximity(snake1.getSnakeY(0), food.getFoodY(), 10))) {

            System.out.println("intersection");
            // Add a 'joint' to our snake
            snake1.setJoints(snake1.getJoints() + 1);
            // Create new food
            food.createFood();
        }
        if ((proximity(snake2.getSnakeX(0), food.getFoodX(), 10))
                && (proximity(snake2.getSnakeY(0), food.getFoodY(), 10))) {

            System.out.println("intersection");
            // Add a 'joint' to our snake
            snake2.setJoints(snake2.getJoints() + 1);
            // Create new food
            food.createFood();
        }
    }

    // if our snake is in the close proximity of the poison..
    boolean checkPoisonCollisions() {

        if ((proximity(snake1.getSnakeX(0), poison.getPoisonX(), 10))
                && (proximity(snake1.getSnakeY(0), poison.getPoisonY(), 10))) {

            System.out.println("intersection");
            // Add a 'joint' to our snake
            snake1.setJoints(snake1.getJoints() - 1);
            if (snake1.getJoints() < 1) {
                sndPlayerWin = true;
                return false;
            }
            // Create new food
            poison.createPoison();
        } else if ((proximity(snake2.getSnakeX(0), poison.getPoisonX(), 10))
                && (proximity(snake2.getSnakeY(0), poison.getPoisonY(), 10))) {

            System.out.println("intersection");
            // Add a 'joint' to our snake
            snake2.setJoints(snake2.getJoints() - 1);
            if (snake2.getJoints() < 1) {
                sndPlayerWin = true;
                return false;
            }
            // Create new food
            poison.createPoison();
        }
        return true;
    }

    // Used to check collisions with snake's self and board edges
    boolean checkCollisions(int width, int height) { // -> 여기서 width, height 위치 구별해서 뱀 위치 조정하는데 이 기능 딴데로 가야할 듯 수정 필요함
                                                     // (준현)

        // If the snake hits other snake's joints..
        for (int i = snake2.getJoints(); i > 1; i--) {

            if (proximity(snake1.getSnakeX(0), snake2.getSnakeX(i), 10)
                    && proximity(snake1.getSnakeY(0), snake2.getSnakeY(i), 10)) {
                sndPlayerWin = true;
                //return false; // then the game ends
            }
        }

        for (int i = snake1.getJoints(); i > 1; i--) {

            if (proximity(snake2.getSnakeX(0), snake1.getSnakeX(i), 10)
                    && proximity(snake2.getSnakeY(0), snake1.getSnakeY(i), 10)) {
                fstPlayerWin = true;
                //return false; // then the game ends
            }
        }
        if(sndPlayerWin&&fstPlayerWin) {
        	sndPlayerWin=false;
        	fstPlayerWin=false;
        	return true;
        }
        //둘중 하나만 true일경우 각 뱀의 승패여부는 그대로 유지하고 게임 끝내기 
        else if(sndPlayerWin||fstPlayerWin) return false;

        // If the snake intersects with the board edges..
        if (snake1.getSnakeY(0) >= height) {
            snake1.setSnakeY(0);
            snake1.setSnakeX((int) (Math.random() * width));
        }

        if (snake1.getSnakeY(0) < 0) {
            snake1.setSnakeY(height);
            snake1.setSnakeX((int) (Math.random() * width));
        }

        if (snake1.getSnakeX(0) >= width) {
            snake1.setSnakeX(0);
            snake1.setSnakeY((int) (Math.random() * height));
        }

        if (snake1.getSnakeX(0) < 0) {
            snake1.setSnakeX(width);
            snake1.setSnakeY((int) (Math.random() * height));
        }

        if (snake2.getSnakeY(0) >= height) {
            snake2.setSnakeY(0);
            snake2.setSnakeX((int) (Math.random() * width));
        }

        if (snake2.getSnakeY(0) < 0) {
            snake2.setSnakeY(height);
            snake2.setSnakeX((int) (Math.random() * width));
        }

        if (snake2.getSnakeX(0) >= width) {
            snake2.setSnakeX(0);
            snake2.setSnakeY((int) (Math.random() * height));
        }

        if (snake2.getSnakeX(0) < 0) {
            snake2.setSnakeX(width);
            snake2.setSnakeY((int) (Math.random() * height));
        }

        return true;
    }

    private boolean proximity(int a, int b, int closeness) {
        return Math.abs((long) a - b) <= closeness;
    }

    public boolean getFstPlayerWin() {
        return fstPlayerWin;
    }

    public boolean getSndPlayerWin() {
        return sndPlayerWin;
    }

}
