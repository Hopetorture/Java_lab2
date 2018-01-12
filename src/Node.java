import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    Node(int i, ArrayList<Integer> cons){
        initLocationTable();
        id = i;
        this.xy = locationTable.get(id);
        this.connections = cons;
        this.textX = textTable.get(id).x;
        this.textY = textTable.get(id).y;

        Double newX = (xy.x - 70 / 2.0);
        Double newY = (this.xy.y - 70 / 2.0);//70 - height/width
        ovalX = newX.intValue();
        ovalY = newY.intValue();

    }
    int id;
    int textX, textY;
    int ovalX, ovalY;
    int height = 70;
    int width = 70;
    Coords xy;
    ArrayList<Integer> connections;
    static HashMap<Integer, Coords > locationTable;
    static HashMap<Integer, Coords > textTable;

    private void initLocationTable(){
        locationTable = new HashMap<>();
        locationTable.put(0, new Coords(212,106));
        locationTable.put(1, new Coords(90,265));
        locationTable.put(2, new Coords(107,392));
        locationTable.put(3, new Coords(149,560));
        locationTable.put(4, new Coords(352,611));
        locationTable.put(5, new Coords(546,562));
        locationTable.put(6, new Coords(627,453));
        locationTable.put(7, new Coords(638,287));
        locationTable.put(8, new Coords(501,100));

        textTable = new HashMap<>();
        textTable.put(0, new Coords(179,65));
        textTable.put(1, new Coords(54,226));
        textTable.put(2, new Coords(65,352));
        textTable.put(3, new Coords(103,526));
        textTable.put(4, new Coords(354,657));
        textTable.put(5, new Coords(590,547));
        textTable.put(6, new Coords(669,423));
        textTable.put(7, new Coords(671,242));
        textTable.put(8, new Coords(532,65));
    }
}
class Coords{
    Coords(Integer x, Integer y){this.x=x;this.y=y;}
    public int x,y;
}
