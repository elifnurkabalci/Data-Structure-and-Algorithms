import javax.swing.*;
import java.awt.*;

/*For visualization of the avltree */
public class AVLTreePanel extends JPanel {
    private AVLTree.Node root;

    public AVLTreePanel(AVLTree.Node root) {
        this.root = root;
        setPreferredSize(new Dimension(800, 600));
    }

    /**
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (root != null) {
            drawNode(g, root, getWidth() / 2, 30, getWidth() / 4);
        }
    }

    private void drawNode(Graphics g, AVLTree.Node node, int x, int y, int xOffset) {
        if (node != null) {
            g.drawOval(x - 15, y - 15, 30, 30);
            g.drawString(node.stock.getSymbol(), x - 10, y);
            if (node.left != null) {
                g.drawLine(x - 10, y + 10, x - xOffset + 10, y + 50 - 10);
                drawNode(g, node.left, x - xOffset, y + 50, xOffset / 2);
            }
            if (node.right != null) {
                g.drawLine(x + 10, y + 10, x + xOffset - 10, y + 50 - 10);
                drawNode(g, node.right, x + xOffset, y + 50, xOffset / 2);
            }
        }
    }
}
