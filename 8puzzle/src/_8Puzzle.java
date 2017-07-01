import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Stack;

public class _8Puzzle {

	// 8 puzzle - Uninformed search BFS and DFS
	
	private static final int MAX_DFS_DEPTH = 1024;

	public static ArrayList<State> getStepsBFS(State initialState,
			State finalState) {
		ArrayList<State> sol = new ArrayList<State>();
		LinkedHashSet<State> closed = new LinkedHashSet<State>(), open = new LinkedHashSet<State>();
		HashMap<State, State> parents = new HashMap<State, State>();

		open.add(initialState);
		while (!open.isEmpty()) {
			State currentState = open.iterator().next();
			if (currentState.equals(finalState)) {
				Stack<State> stack = new Stack<State>();
				while (true) {
					stack.push(new State(currentState));
					if (currentState.equals(initialState))
						break;
					currentState = parents.get(currentState);
				}

				while (!stack.isEmpty()) {
					// System.out.println(stack.pop());
					sol.add(stack.pop());
				}

				break;
			} else {
				for (State state : currentState.nextStates()) {
					if (!closed.contains(state)) {
						open.add(state);
						parents.put(state, currentState);
					}
				}
				closed.add(currentState);
				open.remove(currentState);
			}

		}
		return sol;
	}

	public static ArrayList<State> getStepsDFS(State initialState,
			State finalState) {
		ArrayList<State> sol = new ArrayList<State>();
		LinkedHashSet<State> closed = new LinkedHashSet<State>();// , open = new
																	// LinkedHashSet<State>();
		Stack<State> open = new Stack<State>();
		HashMap<State, State> parents = new HashMap<State, State>();

		open.push(initialState);
		while (!open.isEmpty()) {
			State currentState = open.pop();
			if (currentState.equals(finalState)) {
				Stack<State> stack = new Stack<State>();
				while (true) {
					stack.push(new State(currentState));
					if (currentState.equals(initialState))
						break;
					currentState = parents.get(currentState);
				}

				while (!stack.isEmpty()) {
					// System.out.println(stack.pop());
					sol.add(stack.pop());
				}

				break;
			} else {
				for (State state : currentState.nextStates()) {
					if (!closed.contains(state) && !open.contains(state)
							&& state.getLevel() < MAX_DFS_DEPTH) {
						open.push(state);
						parents.put(state, currentState);
					}
				}
				closed.add(currentState);
				// open.remove(currentState);
			}

		}
		return sol;
	}

	public static void main(String[] args) {
		State initialState = new State();
		initialState.scramble();

		State finalState = new State();

		System.out.println("Initial State : \n" + initialState);
		System.out.println("Final State : \n" + finalState);
		System.out.println("Steps : ");

		ArrayList<State> steps = getStepsBFS(initialState, finalState);

		for (State step : steps) {
			System.out.println(step);
		}

		System.out.println("Total " + steps.size() + " steps");
	}
}
