package miniGame.shooting;


import org.newdawn.slick.Graphics;
import miniGame.shooting.Bullet;



public class Target {

	public static final int BAL_ROWS = 5;
	public static final int COLUMNS = 20;
	public static final int DUCK_ROWS = 3;
	public static final int DUCK_COLUMNS = 10;
	private static final int MOVE_COUNT = 75;

	private static Baloon[][] baloons = null;
	private static Duck[][] ducks;
	private int counter;
	private int baloonCount;
	private int duckCount;

	private static int SPEED = 5;
	private static int xShift;

	public Target()
	{
		ducks = new Duck[DUCK_ROWS][DUCK_COLUMNS];
		for(int i = 0; i < DUCK_ROWS; i++)
		{
			for(int j = 0; j < DUCK_COLUMNS; j++)
			{
				if (Math.random() <= 0.5) {
					ducks[i][j] = new Duck(i,j, SPEED);
					duckCount++;
				}
			}
		}

	}

	public void render(Graphics g)
	{

		for(int i = 0; i < DUCK_ROWS; i++)
		{
			for(int j = 0; j < DUCK_COLUMNS; j++)
			{
				if (ducks[i][j]!= null) {
					ducks[i][j].render(g);
				}
			}
		}
	}

	public void update(int delta)
	{
		/*counter++;
		if (counter == MOVE_COUNT) {
			boolean right = false;
			if (Math.random() < 0.5) {
				right = true;
			}
			for (int i = 0; i < baloons.length; i++) {
				if (right) {
					shiftRight(baloons[i], i);
				} else {
					shiftLeft(baloons[i], i);
				}
			}
			counter = 0;
		}*/
		counter++;
		if (counter == MOVE_COUNT) {
			boolean right = false;
			if (Math.random() < 0.5) {
				right = true;
			}
			for (int i = 0; i < ducks.length; i++) {
				if (right) {
					shiftRight(ducks[i], i);
				} else {
					shiftLeft(ducks[i], i);
				}
			}
			counter = 0;
		}

	}

	public boolean check(miniGame.shooting.Bullet bullet) {


		for(int i = 0; i < DUCK_ROWS; i++)
		{
			for(int j = 0; j < DUCK_COLUMNS; j++)
			{
				if (ducks[i][j] != null) {
					Duck b = ducks[i][j];
					if (b.contains(bullet.getX(), bullet.getY()) ||
							b.contains(bullet.getX() + bullet.getWidth(), bullet.getY()) ||
							b.contains(bullet.getX(), bullet.getY() + bullet.getHeight()) ||
							b.contains(bullet.getX() + bullet.getWidth(), bullet.getY() + bullet.getHeight())
					) {
						duckCount--;
						ducks[i][j] = null;
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isComplete() {
		return duckCount == 0;
	}

	public void clear()
	{
		for(int i = 0; i < DUCK_ROWS; i++) {
			for (int j = 0; j < DUCK_COLUMNS; j++) {
				ducks[i][j] = null;
			}
		}

	}

	private static void shiftLeft(Duck[] array, int j) {

		xShift -= SPEED;
		Duck temp = array[0];
		for (int i = array.length - 1; i >= 0; i--) {
			Duck copy = array[i];
			array[i] = temp;
			if (array[i] != null) {
				array[i].setX(i);
				array[i].setY(j);
			}
			temp = copy;
		}
	}

	private static void shiftRight(Baloon[] array, int j) {
		Baloon temp = array[array.length-1];
		for (int i = 0; i < array.length; i++) {
			Baloon copy = array[i];
			array[i] = temp;
			if (array[i] != null) {
				array[i].setX(i);
				array[i].setY(j);
			}
			temp = copy;
		}
	}
	private static void shiftRight(Duck[] array, int j) {

		xShift += SPEED;

		Duck temp = array[array.length-1];
		for (int i = 0; i < array.length; i++) {
			Duck copy = array[i];
			array[i] = temp;
			if (array[i] != null) {
				array[i].setX(i);
				array[i].setY(j);
			}
			temp = copy;
		}
	}
}
