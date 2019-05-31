package Game.src;

import java.util.ArrayList;

public class Collisions {
    // Board�뿉�꽌 留뚮뱺 媛앹껜�뱾 ���옣�븷 private 蹂��닔
    private Snake snake1;
    private Snake snake2;
    private Food food;
    private ArrayList<Poison> poison = new ArrayList<Poison>();
    private boolean fstPlayerWin = false;
    private boolean sndPlayerWin = false;

    // Constructor�쓣 留뚮뱾�븣 snake1, snake2, food, poison 媛앹껜 reference瑜� �쟾�떖�빐以�
    public Collisions(Snake snake1, Snake snake2, Food food, ArrayList<Poison> poison) {
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
    int checkPoisonCollisions() {
        for (int i = 0; i < poison.size(); i++) {
            if ((proximity(snake1.getSnakeX(0), poison.get(i).getPoisonX(), 10))
                    && (proximity(snake1.getSnakeY(0), poison.get(i).getPoisonY(), 10))) {

                System.out.println("intersection");
                // Add a 'joint' to our snake
                snake1.setJoints(snake1.getJoints() - 1);
                if (snake1.getJoints() < 1) {
                    sndPlayerWin = true;
                    return 0;
                }
                // Create new food
                relocation(poison, i);
            } else if ((proximity(snake2.getSnakeX(0), poison.get(i).getPoisonX(), 10))
                    && (proximity(snake2.getSnakeY(0), poison.get(i).getPoisonY(), 10))) {

                System.out.println("intersection");
                // Add a 'joint' to our snake
                snake2.setJoints(snake2.getJoints() - 1);
                if (snake2.getJoints() < 1) {
                    sndPlayerWin = true;
                    return 0;
                }
                // Create new food
                relocation(poison, i);
            }
        }
        return 1;
    }

    // Used to check collisions with snake's self and board edges
    int checkCollisions(int width, int height) { // -> �뿬湲곗꽌 width, height �쐞移� 援щ퀎�빐�꽌 諭� �쐞移� 議곗젙�븯�뒗�뜲 �씠 湲곕뒫 �뵶�뜲濡�
                                                 // 媛��빞�븷 �벏 �닔�젙 �븘�슂�븿
                                                 // (以��쁽)

        // If the snake hits other snake's joints..
        for (int i = snake2.getJoints(); i > 0; i--) {

            if (proximity(snake1.getSnakeX(0), snake2.getSnakeX(i), 5)
                    && proximity(snake1.getSnakeY(0), snake2.getSnakeY(i), 5)) {
                System.out.println("asfdjnaovjefakjfsanvkfj");
                sndPlayerWin = true;
                return 2;
            }
        }

        for (int i = snake1.getJoints(); i > 0; i--) {

            if (proximity(snake2.getSnakeX(0), snake1.getSnakeX(i), 5)
                    && proximity(snake2.getSnakeY(0), snake1.getSnakeY(i), 5)) {
                fstPlayerWin = true;
                return 2; // then the game ends
            }
        }

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

        return 1;
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

    public void relocation(ArrayList<Poison> poison, int collidePoisonNum) {
        Poison addpoison = new Poison();
        poison.add(addpoison);
        poison.get(collidePoisonNum).createPoison();
        for (int i = collidePoisonNum; i < poison.size(); i++) {
            poison.get(i).createPoison();
        }
    }

}
