package Game.src;


public class Collisions {
    // Board에서 만든 객체들 저장할 private 변수
    private Snake snake1;
    private Snake snake2;
    private Food food;
    private Poison poison = new Poison();

    // Constructor을 만들때 snake1, snake2, food, poison 객체 reference를 전달해준
    public Collisions(Snake snake1, Snake snake2,
                        Food food, Poison poison) {
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
    //if our snake is in the close proximity of the poison..
    void checkPoisonCollisions() {

         if ((proximity(snake1.getSnakeX(0), poison.getPoisonX(), 10))
                 && (proximity(snake1.getSnakeY(0), poison.getPoisonY(), 10))) {

             System.out.println("intersection");
             // Add a 'joint' to our snake
             snake1.setJoints(snake1.getJoints() - 1);
             // Create new food
             poison.createPoison();
         }
         else if ((proximity(snake2.getSnakeX(0), poison.getPoisonX(), 10))
                 && (proximity(snake2.getSnakeY(0), poison.getPoisonY(), 10))) {

             System.out.println("intersection");
             // Add a 'joint' to our snake
             snake2.setJoints(snake2.getJoints() - 1);
             // Create new food
             poison.createPoison();
         }
    }

    // Used to check collisions with snake's self and board edges
    boolean checkCollisions(int width, int height) { //-> 여기서 width, height 위치 구별해서 뱀 위치 조정하는데 이 기능 딴데로 가야할 듯 수정 필요함 (준현)

        // If the snake hits other snake's joints..
        for (int i = snake2.getJoints(); i > 0; i--) {


            if ((snake1.getSnakeX(0) == snake2.getSnakeX(i) && (snake1
                            .getSnakeY(0) == snake2.getSnakeY(i))) || (snake1
                            		.getSnakeX(0) == snake2.getSnakeY(i) && (snake1
                                    .getSnakeY(0) == snake2.getSnakeX(i)))) {
                return false; // then the game ends
            }
        }

        for (int i = snake1.getJoints(); i > 0; i--) {


            if ((snake2.getSnakeX(0) == snake1.getSnakeX(i) && (snake2
                            .getSnakeY(0) == snake1.getSnakeY(i))) || (snake2
                            		.getSnakeX(0) == snake1.getSnakeX(i) && (snake2
                                    .getSnakeY(0) == snake1.getSnakeY(i)))) {
                return false; // then the game ends
            }
        }

        // If the snake intersects with the board edges..
        if (snake1.getSnakeY(0) >= height) {
            snake1.setSnakeY(0);
        }

        if (snake1.getSnakeY(0) < 0) {
            snake1.setSnakeY(height);
        }

        if (snake1.getSnakeX(0) >= width) {
            snake1.setSnakeX(0);
        }

        if (snake1.getSnakeX(0) < 0) {
            snake1.setSnakeX(width);
        }

        if (snake2.getSnakeY(0) >= height) {
            snake2.setSnakeY(0);
        }

        if (snake2.getSnakeY(0) < 0) {
            snake2.setSnakeY(height);
        }

        if (snake2.getSnakeX(0) >= width) {
            snake2.setSnakeX(0);
        }

        if (snake2.getSnakeX(0) < 0) {
            snake2.setSnakeX(width);
        }
        
        return true;
    }

    private boolean proximity(int a, int b, int closeness) {
        return Math.abs((long) a - b) <= closeness;
    }
}
