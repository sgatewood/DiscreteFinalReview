/* COPY IN:
//*********************RESCALING******************************
GuiFix fixer = new GuiFix();
fixer.fix(frame);
//************************************************************
*/

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.lang.Math;

class GuiFix{ // Used to standardize a GUI for all screen resolutions (including my annoying laptop)
  // e.g. fix(frame);
  //http://thebadprogrammer.com/swing-uimanager-keys/
  //https://gist.github.com/itzg/5938035
  public void fix(JFrame frame){
    Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
    int fontSize = screenDimension.height/30;
    int jFrameWidth = (int) Math.round(screenDimension.width * 0.5);
    int jFrameHeight = (int) Math.round(screenDimension.height * 0.92);

    UIManager.put("Label.font", new FontUIResource(new Font("Dialog", Font.PLAIN, fontSize)));
    UIManager.put("Button.font", new FontUIResource(new Font("Dialog", Font.BOLD, fontSize*2/3)));
    UIManager.put("TextField.font", new FontUIResource(new Font("Dialog", Font.PLAIN, fontSize)));
    UIManager.put("List.font", new FontUIResource(new Font("Dialog", Font.PLAIN, fontSize)));
    UIManager.put("RadioButton.font", new FontUIResource(new Font("Dialog", Font.PLAIN, fontSize)));
    UIManager.put("ComboBox.font", new FontUIResource(new Font("Dialog", Font.PLAIN, fontSize)));
    UIManager.put("Spinner.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 120)));
    UIManager.put("Slider.font", new FontUIResource(new Font("Dialog", Font.PLAIN, fontSize)));
    UIManager.put("Slider.horizontalSize", new Dimension(jFrameWidth/3,jFrameWidth/3));
    UIManager.put("JRadioButton.icon",90);

    frame.setBounds(screenDimension.width/4, screenDimension.height/10, jFrameWidth,jFrameHeight);
  }

  public int getFontSize(){
    Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
    int fontSize = screenDimension.height/90;
    return fontSize;
  }


}
