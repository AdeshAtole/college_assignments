import java.util.ArrayList;
import java.util.Random;

public class State {
	int mat[][] = new int[3][3];

	static final int LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4;

	public State() {
		// TODO Auto-generated constructor stub
		int x = 1;
		int i, j = 0;
		for (i = 0; i < 3; i++) {
			mat[j][i] = x;
			x++;
		}
		i--;
		j++;
		for (; j < 3; j++) {
			mat[j][i] = x;
			x++;
		}
		i--;
		j--;
		for (; i >= 0; i--) {
			mat[j][i] = x;
			x++;
		}
		j--;
		i++;
		mat[j][i] = x;

	}

	// public State(int mat[][]) {
	// // TODO Auto-generated constructor stub
	// this.mat = new int[3][3];
	// for (int i = 0; i < 3; i++) {
	// for (int j = 0; j < 3; j++) {
	// this.mat[i][j] = mat[i][j];
	// }
	// }
	// }

	State(State s) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.mat[i][j] = s.mat[i][j];
			}
		}
	}

	private Position getEmptyCell() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (mat[i][j] == 0) {
					return new Position(i, j);
				}
			}
		}
		return null;
	}

	private boolean move(int d) {
		boolean ret = true;
		int row = getEmptyCell().getRow(), column = getEmptyCell().getColumn();
		try {
			switch (d) {
			case UP:
				int temp = mat[row][column];
				mat[row][column] = mat[row - 1][column];
				mat[row - 1][column] = temp;
				break;
			case DOWN:
				temp = mat[row][column];
				mat[row][column] = mat[row + 1][column];
				mat[row + 1][column] = temp;
				break;
			case LEFT:
				temp = mat[row][column];
				mat[row][column] = mat[row][column - 1];
				mat[row][column - 1] = temp;
				break;
			case RIGHT:
				temp = mat[row][column];
				mat[row][column] = mat[row][column + 1];
				mat[row][column + 1] = temp;
				break;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			ret = false;
		}

		return ret;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return mat[0][0];
	}

	ArrayList<State> nextStates() {
		ArrayList<State> list = new ArrayList<State>();

		State s = new State(this);
		if (s.move(UP)) {
			list.add(s);
		}

		s = new State(this);
		if (s.move(DOWN)) {
			list.add(s);
		}

		s = new State(this);
		if (s.move(LEFT)) {
			list.add(s);
		}
		s = new State(this);
		if (s.move(RIGHT)) {
			list.add(s);
		}

		return list;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				builder.append(mat[i][j] + "\t");
			}
			builder.append("\n");
		}

		return builder.toString();
	}

	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.mat[i][j] != ((State) obj).mat[i][j]) {
					return false;
				}
			}
		}

		return true;
	}

	public void scramble() {
		Random random = new Random();
		int moves = 20 + random.nextInt(100);
		while (--moves > 0) {
			this.move(1 + random.nextInt(4));
		}
	}
}
