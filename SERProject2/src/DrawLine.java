
public class DrawLine {
	private int shapeX, shapeY, lineX, lineY;
	private Connections line;
	public int getShapeX() {
		return shapeX;
	}
	public void setShapeX(int shapeX) {
		this.shapeX = shapeX;
	}
	public int getShapeY() {
		return shapeY;
	}
	public void setShapeY(int shapeY) {
		this.shapeY = shapeY;
	}
	public int getLineX() {
		return lineX;
	}
	public void setLineX(int lineX) {
		this.lineX = lineX;
	}
	public int getLineY() {
		return lineY;
	}
	public void setLineY(int lineY) {
		this.lineY = lineY;
	}
	public Connections getLine() {
		return line;
	}
	public void setLine(Connections line) {
		this.line = line;
	}
	@Override
	public String toString() {
		return "DrawLine [shapeX=" + shapeX + ", shapeY=" + shapeY + ", lineX=" + lineX + ", lineY=" + lineY + ", line="
				+ line + "]";
	}
}
