import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;

public class QuadTree {
    private int MAX_BALLS= 5;
    private int MAX_LEVELS = 4;
    private int level;
    private List BallCoordinates;
    private Rectangle bounds;
    private QuadTree[] nodes;

    /*
     * Constructor
     */
    public QuadTree(int pLevel, Rectangle pBounds) {
        level = pLevel;
        BallCoordinates = new ArrayList<BallCoordinate>();
        bounds = pBounds;
        nodes = new QuadTree[4];
    }
    /*
     * Clears the quadtree
     */
    public void Clear() {
        BallCoordinates.clear();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].Clear();
                nodes[i] = null;
            }
        }
    }
    /*
     * Splits the node into 4 subnodes
     */
    private void Split() {
        int subWidth = (int)(bounds.getWidth() / 2);
        int subHeight = (int)(bounds.getHeight() / 2);
        int x = (int)bounds.getX();
        int y = (int)bounds.getY();

        nodes[0] = new QuadTree(level+1, new Rectangle(x + subWidth, y, subWidth, subHeight));
        nodes[1] = new QuadTree(level+1, new Rectangle(x, y, subWidth, subHeight));
        nodes[2] = new QuadTree(level+1, new Rectangle(x, y + subHeight, subWidth, subHeight));
        nodes[3] = new QuadTree(level+1, new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight));
    }
    /*
     * Determine which node the object belongs to. -1 means
     * object cannot completely fit within a child node and is part
     * of the parent node
     */
    private int getIndex(Rectangle pRect) {
        int index = -1;
        double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
        double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);
        boolean topQuadrant = (pRect.getY() < horizontalMidpoint && pRect.getY() + pRect.getHeight() < horizontalMidpoint);
        boolean bottomQuadrant = (pRect.getY() > horizontalMidpoint);

        if (pRect.getX() < verticalMidpoint && pRect.getX() + pRect.getWidth() < verticalMidpoint) {
            if (topQuadrant) {
                index = 1;
            }
            else if (bottomQuadrant) {
                index = 2;
            }
        }

        else if (pRect.getX() > verticalMidpoint) {
            if (topQuadrant) {
                index = 0;
            }
            else if (bottomQuadrant) {
                index = 3;
            }
        }

        return index;
    }

    /*
     * Insert the object into the quadtree. If the node
     * exceeds the capacity, it will split and add all
     * objects to their corresponding nodes.
     */
    public void insert(Rectangle pRect) {
        if (nodes[0] != null) {
            int index = getIndex(pRect);

            if (index != -1) {
                nodes[index].insert(pRect);

                return;
            }
        }

        BallCoordinates.add(pRect);

        if (BallCoordinates.size() > MAX_BALLS && level < MAX_LEVELS) {
            if (nodes[0] == null) {
                Split();
            }

            int i = 0;
//            while (i < BallCoordinates.size()) {
//                int index = getIndex(bounds.get(i));
//                if (index != -1) {
//                    nodes[index].insert(bounds.remove(i));
//                }
//                else {
//                    i++;
//                }
//            }
        }
    }
    /*
     * Return all objects that could collide with the given object
     */
    public List retrieve(List returnObjects, Rectangle pRect) {
        int index = getIndex(pRect);
        if (index != -1 && nodes[0] != null) {
            nodes[index].retrieve(returnObjects, pRect);
        }

        returnObjects.addAll(BallCoordinates);

        return returnObjects;
    }

}