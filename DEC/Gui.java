import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.io.*;

class Gui{
  private JFrame frame; // Everything contained here
  private static Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
  public static final int SCALE = screenDimension.height; // This for me is 2610

  private JPanel top;
  private JPanel bottom;

  private JButton wop;
  private JButton logical_formulas;
  private JButton sets;
  private JButton functions_relations;
  private JButton state_machines;
  private JButton recursive;
  private JButton infinite;
  private JButton number_theory;

  // Constructor
  public Gui(){
    frame = makeBasicInterface();
    frame.setVisible(true);
  }

  // Makes the basic interface
  public JFrame makeBasicInterface(){
    JFrame frame = new JFrame();
    //*********************RESCALING******************************
    GuiFix fixer = new GuiFix();
    fixer.fix(frame);
    //************************************************************
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(Gui.SCALE*4/5,Gui.SCALE*2/5);
    top = new JPanel();
    top.add(new JLabel("Discrete Math Final Exam Review"));
    bottom = new JPanel();
    bottom.setLayout(new GridLayout(4,2));

    wop = new JButton("Well Ordering and Induction");
    wop.addActionListener(new Wop());
    logical_formulas = new JButton("Logical Formulas, Predicate Logic");
    logical_formulas.addActionListener(new Logical_formulas());
    sets = new JButton("Sets");
    sets.addActionListener(new Sets());
    functions_relations = new JButton("Functions and Relations");
    functions_relations.addActionListener(new Functions_relations());
    state_machines = new JButton("State Machines");
    state_machines.addActionListener(new State_machines());
    recursive = new JButton("Recursive Data Types");
    recursive.addActionListener(new Recursive());
    infinite = new JButton("Infinite Sets");
    infinite.addActionListener(new Infinite());
    number_theory = new JButton("Number Theory");
    number_theory.addActionListener(new Number_theory());

    bottom.add(wop);
    bottom.add(logical_formulas);
    bottom.add(sets);
    bottom.add(functions_relations);
    bottom.add(state_machines);
    bottom.add(recursive);
    bottom.add(infinite);
    bottom.add(number_theory);

    frame.add(top,BorderLayout.NORTH);
    frame.add(bottom);

    return frame;
  }

  public void runCommand(String command){
    frame.setVisible(false);
    try {
    // Execute command
    Process p = Runtime.getRuntime().exec(command);
    p.waitFor();
  } catch (Exception t) {
      t.printStackTrace();
    }finally{
      frame.setVisible(true);
    }
  }

  public class Wop implements ActionListener{
    public void actionPerformed(ActionEvent e){
      System.out.println("WOP not implemented yet. Enjoy this python game instead");
      runCommand("py -3 flappybird.py");
    }
  }

  public class Logical_formulas implements ActionListener{
    public void actionPerformed(ActionEvent e){
      System.out.println("Logical_formulas not implemented yet");
    }
  }

  public class Sets implements ActionListener{
    public void actionPerformed(ActionEvent e){
      System.out.println("Sets not implemented yet");
    }
  }

  public class Functions_relations implements ActionListener{
    public void actionPerformed(ActionEvent e){
      System.out.println("Functions_relations not implemented yet");
    }
  }

  public class State_machines implements ActionListener{
    public void actionPerformed(ActionEvent e){
      System.out.println("State_machines not implemented yet");
    }
  }

  public class Recursive implements ActionListener{
    public void actionPerformed(ActionEvent e){
      System.out.println("Recursive not implemented yet");
    }
  }

  public class Infinite implements ActionListener{
    public void actionPerformed(ActionEvent e){
      System.out.println("Infinite not implemented yet");
    }
  }

  public class Number_theory implements ActionListener{
    public void actionPerformed(ActionEvent e){
      System.out.println("Number_theory not implemented yet");
    }
  }

  public static void main(String[] args) {
    new Gui();
  }
}
