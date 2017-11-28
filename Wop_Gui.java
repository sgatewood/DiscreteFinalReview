import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;

class Wop_Gui{
  private JFrame frame; // Everything contained here
  private static Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
  public static final int SCALE = screenDimension.height; // This for me is 2610

  private ArrayList<String[]> prompts;
  private int current = -1; // current question

  private JPanel grid;
  private JPanel set;
  private JPanel choice1;
  private JPanel comp;
  private JPanel choice2;

  private JButton b1;
  private JButton b2;
  private JButton _b1;
  private JButton _b2;

  private JLabel set_prompt;

  private String sAns = ""; // set answer
  private String cAns = ""; // comparator answer

  private JButton submit;
  private JLabel correct;
  private boolean pause = true;

  // Constructor
  public Wop_Gui(){
    frame = makeBasicInterface();
    frame.setVisible(true);
    prompts = new ArrayList<String[]>();
  }

  // Makes the basic interface
  public JFrame makeBasicInterface(){
    JFrame frame = new JFrame();
    //*********************RESCALING******************************
    GuiFix fixer = new GuiFix();
    fixer.fix(frame);
    //************************************************************
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("2102.ico")));
    frame.setSize(Gui.SCALE*4/5,Gui.SCALE*2/5);

    grid = new JPanel();
    grid.setLayout(new GridLayout(5,1));

    set = new JPanel();
    set_prompt = new JLabel("placeholder");
    set.add(set_prompt, BorderLayout.NORTH);

    choice1 = new JPanel();
    //g1 = new ButtonGroup();
    b1 = new JButton("Well-Ordered");
    b1.addActionListener(new B1());
    b2 = new JButton("Not Well-Ordered");
    b2.addActionListener(new B2());
    //g1.add(b1);
    //g1.add(b2);
    choice1.add(b1);
    choice1.add(b2);
    //b1.setRolloverIcon(new ImageIcon("test.ico"));
    //b2.setPressedIcon(new ImageIcon("test2.ico"));

    comp = new JPanel();
    comp.add(new JLabel("Comparator:"));

    choice2 = new JPanel();
    //g2 = new ButtonGroup();
    _b1 = new JButton("<");
    _b1.addActionListener(new _B1());
    _b2 = new JButton(">");
    _b2.addActionListener(new _B2());
    //g2.add(_b1);
    //g2.add(_b2);
    choice2.add(_b1);
    choice2.add(_b2);

    submit = new JButton("Submit");
    submit.addActionListener(new Submit());

    correct = new JLabel("");

    grid.add(set);
    grid.add(choice1);
    grid.add(comp);
    grid.add(choice2);
    grid.add(correct);
    frame.add(grid,BorderLayout.NORTH);
    frame.add(submit,BorderLayout.SOUTH);

    return frame;
  }



  public class Submit implements ActionListener{
    public void actionPerformed(ActionEvent e){
      if(isCorrect()){
        correct.setText("Correct!");
        submit.setBackground(Color.GREEN);
        submit.setText("Next Question");
        if(pause){
          pause = false;
          b1.setEnabled(false);
          b2.setEnabled(false);
          _b1.setEnabled(false);
          _b2.setEnabled(false);
        }else if (current+1 < prompts.size()){
          pause = true;
          b1.setEnabled(true);
          b2.setEnabled(true);
          _b1.setEnabled(true);
          _b2.setEnabled(true);
          submit.setBackground(null);
          b1.setBackground(null);
          b2.setBackground(null);
          _b1.setBackground(null);
          _b2.setBackground(null);
          correct.setText("");
          next();
        }else{
          correct.setText("");
          submit.setEnabled(false);
          submit.setBackground(null);
          set_prompt.setText("Game over. You win! :-)");
        }
      }else{
        correct.setText("Nope!");
      }
    }
  }

  public class B1 implements ActionListener{
    public void actionPerformed(ActionEvent e){
      b1.setBackground(new Color(137, 137, 137));
      b2.setBackground(new Color(224, 224, 224));
      sAns = "Well-Ordered";
      _b1.setEnabled(true);
      _b2.setEnabled(true);
    }
  }

  public class B2 implements ActionListener{
    public void actionPerformed(ActionEvent e){
      b1.setBackground(new Color(224, 224, 224));
      b2.setBackground(new Color(137, 137, 137));
      sAns = "Not Well-Ordered";
      _b1.setEnabled(false);
      _b2.setEnabled(false);
      _b1.setBackground(null);
      _b2.setBackground(null);
      cAns = "";
    }
  }

  public class _B1 implements ActionListener{
    public void actionPerformed(ActionEvent e){
      _b1.setBackground(new Color(137, 137, 137));
      _b2.setBackground(new Color(224, 224, 224));
      cAns = "<";
    }
  }

  public class _B2 implements ActionListener{
    public void actionPerformed(ActionEvent e){
      _b1.setBackground(new Color(224, 224, 224));
      _b2.setBackground(new Color(137, 137, 137));
      cAns = ">";
    }
  }

  public void next(){
    current += 1;
    cAns = "";
    sAns = "";
    set_prompt.setText(prompts.get(current)[0]);
  }

  public boolean isCorrect(){
    return sAns.equals(prompts.get(current)[1]) && cAns.equals(prompts.get(current)[2]);
  }

  public void add(String s, String ans1, String ans2){
    String[] x = {s,ans1,ans2};
    prompts.add(x);
  }

  public void play(){
    add("The set of all natural numbers","Well-Ordered","<");
    add("The set of all real numbers","Not Well-Ordered","");
    add("The set of all nonnegative integers","Well-Ordered","<");
    add("The set of all integers less than 5", "Well-Ordered",">");
    add("The set of all integers", "Not Well-Ordered","");
    add("The empty set","Well-Ordered","");
    add("[5]","Well-Ordered","");
    add("The set of all negative integers","Well-Ordered",">");
    add("The set of all real numbers greater than -1","Not Well-Ordered","");
    add("The set of all rational numbers","Well-Ordered","");
    add("The set of all infinite binary strings","Not Well-Ordered","");

    Collections.shuffle(prompts);
    next();
  }

  public static void main(String[] args) {
    Wop_Gui a = new Wop_Gui();
    a.play();
  }
}
