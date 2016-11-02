import java.applet.*;
import java.awt.*;

public class WaterFlowVisualization extends Applet {

	public static final long serialVersionUID = 1L;

	public int[][] delayTimeGrid;

    public int rows;
    public int columns;

    WaterFlowVisualization(WaterFlow waterFlow) {
        this.delayTimeGrid = waterFlow.getDelayTimeGrid();
        this.rows = waterFlow.getRows();
        this.columns = waterFlow.getColumns();
    }

    public void init() {
        setSize(rows * 10, columns * 10);
        setVisible(true);
    }


    public void paint(Graphics g) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                /* To paint the path */
                
                if (delayTimeGrid[i][j] == 0)
                    g.setColor(Color.black);
                else
                    g.setColor(Color.white);
                g.fillRect(j * 20, i * 20, 20, 20);

            }
        }
    }
}
