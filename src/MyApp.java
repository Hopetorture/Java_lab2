//import java.applet.Applet;
//import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import org.jgrapht.Graph;
import sun.awt.WindowClosingListener;

import javax.swing.*;
import javax.xml.soap.Text;


public class MyApp extends Frame implements ActionListener, MouseListener, ComponentListener {

    Menu setGraphMenu;
    MenuBar mb;
    Graph<Node, Integer> graph;

    public MyApp(String title){
        super(title);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we)
            {System.exit(0);}
        });

        init();
    }

    public void init(){

        addMouseListener(this);
        addComponentListener(this);
        setLayout(new BorderLayout());


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
            //d.add((new Label(labels.get(1).getText())));
            //Label fstRow = new Label("Y");
            //d.add(fstRow);
            //fstRow.setText(labels.get(1).getText());
            //d.add(new TextField("0",1));

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

            for (int i = 0; i < 4; i++) d.add(new Label(""));
            Button OK = new Button("Ok");
            OK.addActionListener(e1 -> {
                System.out.println("123123");
            });
            d.add(OK);


            Button cancel = new Button("Cancel");
            cancel.addActionListener(e1 -> {d.dispose();});

            d.add(cancel);

            Button templateGraph = new Button("Test Graph");
            templateGraph.addActionListener(e1 -> {

            });


//            GroupLayout gl = new GroupLayout(d);
//            d.setLayout(gl);
//            gl.setAutoCreateGaps(true);
//            gl.setAutoCreateContainerGaps(true);
//
//            gl.setHorizontalGroup(
//                    gl.createSequentialGroup()
//                    .addComponent(labels.get(0))
//                    //.addComponent(new TextField("0",2))
//                    //.addComponent(new TextField("1", 2))
//
//            );
//            gl.setVerticalGroup(
//                    gl.createSequentialGroup()
//                    .addComponent(labels.get(0))
//                    //.addComponent(new TextField("0", 2))
//                    //.addComponent(new TextField("0", 1))
//            );


            //d.setLayout();
//            JDialog  jd = new JDialog();
//            jd.setVisible(true);
//            jd.setSize(100,100);
//            GroupLayout gl = new GroupLayout(getConte);
           // LayoutManager lm = new LayoutManager();
            //Component line = new C

        });


        MenuItem createFromTemplate = new MenuItem("Создать из шаблона");
        setGraphMenu.add(createFromTemplate);
        createFromTemplate.addActionListener(e -> {
            System.out.println("from template");
        });


        mxGraph grph = new mxGraph();
        Object parent = grph.getDefaultParent();

        grph.getModel().beginUpdate();
        try
        {
            Object v1 = grph.insertVertex(parent, null, "Hello", 20, 20, 80,
                    30, "Rounded");
            Object v2 = grph.insertVertex(parent, null, "World!", 240, 150,
                    80, 30);
            grph.insertEdge(parent, null, "Edge", v1, v2);

            System.out.println(grph.getCellStyle(v1));
        }
        finally
        {
            grph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(grph);
        //this.getContentPane().add(graphComponent);
        //this.getLayout().layoutContainer(graphComponent);
        //this.lay
        //add(new Frame());


        //create.addActionListener(); //todo


    }
    public void actionPerformed(ActionEvent av){repaint();}
    public void componentResized(ComponentEvent e){repaint();}
    public void mouseClicked(MouseEvent me){}
    public void mouseEntered(MouseEvent arg0) {}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}

    public void componentHidden(ComponentEvent arg0) {}
    public void componentMoved(ComponentEvent arg0) {}
    public void componentShown(ComponentEvent arg0) {}

}
