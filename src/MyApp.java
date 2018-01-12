import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.cycle.PatonCycleBase;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class MyApp extends Frame implements ActionListener, MouseListener, ComponentListener {

    Menu setGraphMenu;
    Menu setParametersMenu;
    MenuBar mb;
    Graph<Integer, DefaultEdge> graph;
    HashMap<Integer, ArrayList<Integer> > edges;
    HashMap<Integer, Node > nodes;
    int v1, v2; // vertexes - problem parameters

    public MyApp(String title){
        super(title);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we)
            {System.exit(0);}
        });
        this.setResizable(false);
        init();
    }

    public void init(){

        addMouseListener(this);
        addComponentListener(this);
        setLayout(new BorderLayout());

        graph = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);

        edges = new HashMap<>();
        nodes = new HashMap<>();
        mb = new MenuBar();
        this.setMenuBar(mb);

        setGraphMenu = new Menu("Граф");
        mb.add(setGraphMenu);
        MenuItem create = new MenuItem("Создать");
        setGraphMenu.add(create);

        create.addActionListener(e -> {
            System.out.println("test");
            LinkedList<Label> labels = new LinkedList<>(); // сделать список int-ов TODO
            for (int i = 0; i < 9; i ++){
                labels.add(new Label(Integer.toString(i)));
            }

            Dialog d = new Dialog(this);
            d.setVisible(true);
            d.setSize(300,300);
            GridLayout gl = new GridLayout(0,10);
            d.setLayout(gl);

            d.add(new Label("X"));
            for (Label l : labels){
                d.add(l);
            }
//
            HashMap<Integer, ArrayList<TextField>> inputDict = new HashMap<>();
            //inputDict.
            for (int i = 0; i < 9; i++){
                d.add(new Label(labels.get(i).getText()));
                inputDict.put(i, new ArrayList<TextField>());
                for (int j = 0; j <= 8; j++){
                    TextField f = new TextField("0",1);
                    d.add(f);
                    inputDict.get(i).add(f);

                }
            }
            for (int i = 0; i < 9; i++){
                System.out.println(inputDict.get(i).get(0).getText());
            }

            for (int i = 0; i < 4; i++) d.add(new Label("")); //button-align


            Button OK = new Button("Ok");
            OK.addActionListener(e1 -> {
                for (int i = 0; i < 9; i++){
                    ArrayList<Integer> lst = new ArrayList<>();
                    ArrayList<TextField> inp = inputDict.get(i);
                    for (TextField tf : inp){lst.add(Integer.parseInt(tf.getText()));}
                    edges.put(i, lst);
                }
                initGraph();
                drawGraph();
                d.dispose();
            });
            d.add(OK);

            char[][] table = {  {'0', '0', '0', '1', '1', '0', '1', '0', '1'},//0
                                {'0', '0', '0', '0', '1', '1', '0', '1', '1'},//1
                                {'0', '0', '0', '0', '1', '1', '1', '1', '1'},//2
                                {'1', '0', '0', '0', '1', '1', '1', '1', '1'},//3
                                {'1', '0', '1', '1', '0', '1', '1', '1', '1'},//4
                                {'0', '1', '1', '1', '1', '0', '1', '1', '1'},//5
                                {'1', '0', '1', '1', '1', '1', '0', '1', '1'},//6
                                {'0', '1', '1', '1', '1', '1', '1', '0', '1'},//7
                                {'1', '1', '1', '1', '1', '1', '1', '1', '0'}};//8
            Button templateGraph = new Button("Test");
            templateGraph.addActionListener(e1 -> {
                for (int i = 0; i < 9; i++){
                    int j = 0;
                    for(TextField tf : inputDict.get(i)){
                        tf.setText(Character.toString(table[i][j])); j++;
                    }
                }
            });
            d.add(templateGraph);

            Button cancel = new Button("Cancel");
            cancel.addActionListener(e1 -> {d.dispose();});

            d.add(cancel);

        });

        MenuItem clear = new MenuItem("Очистить");
        clear.addActionListener(e -> {
            System.out.println("called");
            this.getGraphics().clearRect(0, 0, getWidth(), getHeight());
            this.init();
        });
        setGraphMenu.add(clear);

        setParametersMenu = new Menu("Параметры");
        mb.add(setParametersMenu);
        MenuItem _set = new MenuItem("Задать параметры задачи");
        setParametersMenu.add(_set);
        _set.addActionListener(e -> {
            Dialog d = new Dialog(this);
            d.setVisible(true);
            d.setSize(300,300);
            GridLayout gl = new GridLayout(3,2);
            d.setLayout(gl);

            TextField tf1 = new TextField();
            TextField tf2 = new TextField();
            d.add(new Label("Вершина 1: "));
            d.add(tf1);
            d.add(new Label("Вершина 2: "));
            d.add(tf2);
            Button OK = new Button("OK");
            OK.addActionListener(e1 -> {
                v1 = Integer.parseInt(tf1.getText());
                v2 = Integer.parseInt(tf2.getText());
                pinpointVertices();
                d.dispose();});
            Button notok = new Button("Cancel");
            notok.addActionListener(e1 -> d.dispose());
            d.add(OK);
            d.add(notok);
        });

    }
    public void actionPerformed(ActionEvent av){repaint();}
    public void componentResized(ComponentEvent e){repaint();}
    public void mouseClicked(MouseEvent me){
        int _x = me.getX();
        int _y = me.getY();
        System.out.println(_x+","+_y);
    }
    public void mouseEntered(MouseEvent arg0) {}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}

    public void componentHidden(ComponentEvent arg0) {}
    public void componentMoved(ComponentEvent arg0) {}
    public void componentShown(ComponentEvent arg0) {}
    private void initGraph(){
        for (int i = 0; i < 9; i++){
            Node n = new Node(i, edges.get(i));
            graph.addVertex(i);
            nodes.put(i,n);
        }
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (edges.get(i).get(j) == 1)
                    graph.addEdge(i,j);
            }
        }
    }
    private void drawGraph(){
        Graphics g = this.getGraphics();

        for (int i = 0; i < 9; i++){
            Node n = nodes.get(i);

            g.setColor(Color.ORANGE);
            Double newX = (n.xy.x - 70 / 2.0);
            Double newY = (n.xy.y - 70 / 2.0);//70 - height/width
            g.drawOval(newX.intValue() , newY.intValue() , 70,70);
            g.fillOval(newX.intValue() , newY.intValue()  , 70,70);
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(i), n.textX , n.textY );

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            for (int j = 0; j < 9; j++){
                if (n.connections.get(j) == 1)
                    g2.drawLine(n.xy.x, n.xy.y, nodes.get(j).xy.x, nodes.get(j).xy.y);
            }
        }
    }
    private void pinpointVertices(){
        try{
        Node n1 = nodes.get(v1);
        Node n2 = nodes.get(v2);

        Graphics g = this.getGraphics();
        g.setColor(Color.RED);
        g.fillOval(n1.ovalX, n1.ovalY, n1.width, n1.height);
        g.fillOval(n2.ovalX, n2.ovalY, n2.width, n2.height);

        GraphPath<Integer, DefaultEdge> path;
        path = DijkstraShortestPath.findPathBetween(graph, v1, v2);
        ArrayList<Integer> vertexList = (ArrayList<Integer>) path.getVertexList();

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        int prev = v1;
        for (Integer i : vertexList){
            g2.setColor(Color.RED);
            g.setColor(Color.RED);
            g2.drawLine(nodes.get(prev).xy.x, nodes.get(prev).xy.y, nodes.get(i).xy.x, nodes.get(i).xy.y);
            graph.removeEdge(prev, i);
            prev = i;
        }
        PatonCycleBase cycleFinder = new PatonCycleBase(graph);
        List<List<Integer>> cycles = cycleFinder.findCycleBase();
        if (cycles.size() == 0){
            Dialog err = new Dialog(this);
            err.setVisible(true);
            err.setSize(250,100);
            GridLayout gl = new GridLayout(2,1);
            err.setLayout(gl);
            err.add(new Label("Циклов не найдено"));
            Button ok = new Button("Ok");
            ok.addActionListener(e -> {err.dispose();});
            err.add(ok);
        }

        List<Integer> cycle1 = cycles.get(0);
        cycle1.add(cycle1.get(0));
        int start = cycle1.get(0);
        g.setColor(Color.GREEN);
        for(int h = 1; h < cycle1.size(); h++){
            g2.drawLine(nodes.get(start).xy.x, nodes.get(start).xy.y,
                       nodes.get(cycle1.get(h)).xy.x, nodes.get(cycle1.get(h)).xy.y);
            start = cycle1.get(h);
        }

//        for (int j = 0; j < cycles.size(); j++){
//            System.out.println(cycles.get(j));
////            for (Integer i : cycles.get(j)){
////
////            }
//        }


    }//try
        catch(Exception ex){
            Dialog cerr = new Dialog(this);
            cerr.setVisible(true);
            cerr.setSize(350,100);
            GridLayout gl = new GridLayout(2,1);
            cerr.setLayout(gl);
            cerr.add(new Label("Ошибка. Убедитесь в правильности входных данных"));
            Button ok = new Button("Ok");
            ok.addActionListener(e -> {cerr.dispose();});
            cerr.add(ok);
        }
    }//pinpointVertices
}
