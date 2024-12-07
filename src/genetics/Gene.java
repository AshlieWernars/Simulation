package genetics;

public class Gene {

	private String name;
	private int expression; // Expression of the gene (0-10)

	// Constructor to initialize gene with name and expression (0-10)
	public Gene(String name, int expression) {
		this.name = name;
		this.expression = Math.max(0, Math.min(expression, 10)); // Ensure it's between 0 and 10
	}

	// Get the expression value of the gene (0-10)
	public int getExpression() {
		return expression;
	}

	public String getName() {
		return name;
	}

	public void setExpression(int expression) {
		this.expression = Math.max(0, Math.min(expression, 10)); // Ensure it's between 0 and 10
	}

	public void setName(String name) {
		this.name = name;
	}
}