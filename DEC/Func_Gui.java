import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


class Func_Gui{
  private JFrame frame; // Everything contained here
  private Painter p;
  private JPanel top;
  private JPanel bottom;
  public static Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
  public static final int SCALE = screenDimension.height; // This for me is 2610

  private JButton function;
  private Boolean flag_f = true;
  private JButton total;
  private Boolean flag_t = true;
  private JButton surjection;
  private Boolean flag_s = true;
  private JButton injection;
  private Boolean flag_i = true;
  private JButton bijection;
  private Boolean flag_b = true;

  private JLabel prompt;
  private JButton submit;

  private HashSet<String> ans;

  private Boolean restart = false;

  // Constructor
  public Func_Gui(){
    ans = new HashSet<String>();
    frame = makeBasicInterface();
    frame.setVisible(true);
    p.repaint();
  }

  // Makes the basic interface
  public JFrame makeBasicInterface(){
    JFrame frame = new JFrame();
    //*********************RESCALING******************************
    GuiFix fixer = new GuiFix();
    fixer.fix(frame);
    //************************************************************
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(Gui.SCALE*4/5,Gui.SCALE*1/2);

    p = new Painter();
    top = new JPanel();
    top.add(p);
    bottom = new JPanel();
    bottom.setLayout(new GridLayout(3,1));

    prompt = new JLabel("What kind of relation is this?");
    JPanel c = new JPanel();
    c.add(prompt, BorderLayout.CENTER);

    function = new JButton("Function");
    function.addActionListener(new Function());
    total = new JButton("Total");
    total.addActionListener(new Total());
    surjection = new JButton("Surjection");
    surjection.addActionListener(new Surjection());
    injection = new JButton("Injection");
    injection.addActionListener(new Injection());
    bijection = new JButton("Bijection");
    bijection.addActionListener(new Bijection());

    JPanel bb = new JPanel();
    bb.setLayout(new GridLayout(1,5));
    bb.add(function);
    bb.add(total);
    bb.add(surjection);
    bb.add(injection);
    bb.add(bijection);

    submit = new JButton("submit");
    submit.addActionListener(new Submit());

    bottom.add(c);
    bottom.add(bb);
    bottom.add(submit);


    frame.add(p,BorderLayout.CENTER);
    frame.add(bottom,BorderLayout.SOUTH);
    return frame;
  }

  public static void main(String[] args) {
    new Func_Gui();
  }

  public class Submit implements ActionListener{
    boolean flag = true;
    public void actionPerformed(ActionEvent e){
      System.out.println(p.getClassification());
      if(ans.equals(p.getClassification()) && flag){
        flag = false;
        prompt.setText("Correct!");
        submit.setBackground(Color.GREEN);
        submit.setText("Click to continue");

        function.setEnabled(false);
        total.setEnabled(false);
        surjection.setEnabled(false);
        injection.setEnabled(false);
        bijection.setEnabled(false);
      }else if(!flag){
        flag = true;
        prompt.setText("What kind of relation is this?");
        submit.setBackground(null);
        submit.setText("Submit");

        function.setEnabled(true);
        total.setEnabled(true);
        surjection.setEnabled(true);
        injection.setEnabled(true);
        bijection.setEnabled(true);

        flag_f = true;
        flag_t = true;
        flag_s = true;
        flag_i = true;
        flag_b = true;

        function.setBackground(null);
        total.setBackground(null);
        surjection.setBackground(null);
        injection.setBackground(null);
        bijection.setBackground(null);
        restart = true;
        ans.clear();
        p.repaint();
      }else{
        prompt.setText("Nope! Try again!");
      }
    }
  }

  public class Function implements ActionListener{
    public void actionPerformed(ActionEvent e){
      if(flag_f){
        flag_f = false;
        ans.add("Function");
        function.setBackground(new Color(137, 137, 137));
        prompt.setText(ans.toString());
      }else{
        flag_f = true;
        ans.remove("Function");
        function.setBackground(null);
        prompt.setText(ans.toString());
      }
    }
  }

  public class Total implements ActionListener{
    public void actionPerformed(ActionEvent e){
      if(flag_t){
        flag_t = false;
        ans.add("Total");
        total.setBackground(new Color(137, 137, 137));
        prompt.setText(ans.toString());
      }else{
        flag_t = true;
        ans.remove("Total");
        total.setBackground(null);
        prompt.setText(ans.toString());
      }
    }
  }

  public class Surjection implements ActionListener{
    public void actionPerformed(ActionEvent e){
      if(flag_s){
        flag_s = false;
        ans.add("Surjection");
        surjection.setBackground(new Color(137, 137, 137));
        prompt.setText(ans.toString());
      }else{
        flag_s = true;
        ans.remove("Surjection");
        surjection.setBackground(null);
        prompt.setText(ans.toString());
      }
    }
  }

  public class Injection implements ActionListener{
    public void actionPerformed(ActionEvent e){
      if(flag_i){
        flag_i = false;
        ans.add("Injection");
        injection.setBackground(new Color(137, 137, 137));
        prompt.setText(ans.toString());
      }else{
        flag_i = true;
        ans.remove("Injection");
        injection.setBackground(null);
        prompt.setText(ans.toString());
      }
    }
  }

  public class Bijection implements ActionListener{
    public void actionPerformed(ActionEvent e){
      if(flag_b){
        flag_b = false;
        ans.add("Bijection");
        bijection.setBackground(new Color(137, 137, 137));
        prompt.setText(ans.toString());
      }else{
        flag_b = true;
        ans.remove("Bijection");
        bijection.setBackground(null);
        prompt.setText(ans.toString());
      }
    }
  }

}

//------------------------------------------------------------------------------

class Painter extends JPanel{

  private HashMap<String,String> dict;
  private ArrayList<String> names;
  private int i;
  private File f;

  public Painter(){
    i = 0;
    dict = new HashMap<String,String>();
    names = new ArrayList<String>();
    addFiles();
    Collections.shuffle(names);
  }

  public void addFile(String name, String classification){
    names.add("function_pics/" + name);
    dict.put(name,classification);
  }

  public HashSet<String> getClassification(){
    HashSet<String> out = new HashSet<String>();
    String s = dict.get(f.getName());
    for(String item : s.split(",")){
      out.add(item);
    }
    return out;
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    try{
      f = new File(names.get(i));
      BufferedImage img = ImageIO.read(f);
      g2.drawImage(img,Gui.SCALE*4/20,0,null); // x,y,width,height
      i++;
    }catch(IOException e){
      e.printStackTrace();
    }catch(IndexOutOfBoundsException e){
      System.out.println("restarting");
      i = 0;
      this.repaint();
    }

    }

    public void addFiles(){
      // 4 Choose 1
      addFile("1.png","Function");
      addFile("2.png","Total");
      addFile("3.png","Surjection");
      addFile("4.png","Injection");

      // 4 Choose 2
      addFile("5.png","Total,Function");
      addFile("6.png","Surjection,Injection");
      addFile("7.png","Function,Injection");
      addFile("10.png","Function,Surjection");
      addFile("11.png","Total,Injection");
      addFile("9.png","Total,Surjection");

      // 4 Choose 3
      addFile("8.png","Function,Total,Surjection");
      addFile("12.png","Function,Total,Injection");
      addFile("13.png","Surjection,Injection,Function");
      addFile("14.png","Surjection,Injection,Total");

      // 4 Choose 4
      addFile("bijection.png","Bijection");



    }
  }
