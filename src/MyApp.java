//import java.applet.Applet;
//import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import sun.awt.WindowClosingListener;

import javax.swing.*;


public class MyApp extends JFrame {
    public MyApp(){
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we)
            {System.exit(0);}
        });

//        addMouseListener(new MouseAdapter(){
//            public void mouseClicked(MouseEvent me){
//                int []mx = new int [4];
//                int []my = new int [4];
//
//                int x = me.getX();
//                int y = me.getY();
//
//                mx[0] = x-10; my[0] = y;
//                mx[1] = x;    my[1] = y-15;
//                mx[2] = x +10;my[2] = y;
//                mx[3] = x;    my[3] = y+15;
//
//                Graphics g = getGraphics();
//                g.setColor(new Color(100,120,0));
//                g.fillPolygon(mx,my,4);

//            }
//        });
        init();
        //Graphics g = this.getGraphics();
        //g.drawOval(5, 10, 5 , 10);
    }

    public void init(){
        //setLayout(new BorderLayout());
        MenuBar mb = new MenuBar();
        this.setMenuBar(mb);
        Menu graph = new Menu("Граф");
        mb.add(graph);
        MenuItem create = new MenuItem("Создать");
        graph.add(create);
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


//            d.add(new Label("1"));
//            d.add(new Label("2"));
//            d.add(new Label("3"));
//            d.add(new Label("4"));
//            d.add(new Label("5"));
//            d.add(new Label("6"));
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
            for (int i = 0; i < 9; i++){
                d.add(new Label(labels.get(i).getText()));
                for (int j = 0; j <= 8; j++){
                    d.add(new TextField("0",1));
                }
            }

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
        graph.add(createFromTemplate);
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
        this.getContentPane().add(graphComponent);
        //this.getLayout().layoutContainer(graphComponent);
        //this.lay
        //add(new Frame());


        //create.addActionListener(); //todo


    }
}
