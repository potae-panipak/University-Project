/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameframe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.FileWriter;

import java.util.*;

public class MainApplication extends JFrame {

    private JPanel contentpane;
    private JLabel background, backgroundstatus,backgroundcredit;
    private JButton startbutton, statusbutton, howtoplaybutton, creditbutton;
    private JButton playbutton, OKbutton1, OKbutton2, backbutton, sentbutton, showbutton;
    private JTextField playername1, playername2;
    private MySoundEffect themeSound, startsound;
    private JToggleButton[] playerToggle;
    private JRadioButton[] feedbackradio;
    private ButtonGroup bgroup, bgroup1;
    private JList herolist;
    private JDialog dialoghero,dialogcredit;
    private JSpinner dayspinner,mountspinner,yearspinner ;

    private String name1, name2;
    private String[] myhero = {"       Emperor", "       Speedguy", "       Tank", "       Healer", "       Dragon", "       Warrior"};
    private int frameWidth = 1300, frameHeight = 700, dialogWidth = 600, dialogHeight = 600;
    private int numday=15,numyear= 2000;
    private String nummount="January" ;
    ImageIcon started = new MyImageIcon("main/startbutton.jpg").resize(350, 60);
    ImageIcon status = new MyImageIcon("main/statusbutton.jpg").resize(350, 60);
    ImageIcon howtoplay = new MyImageIcon("main/howtoplaybutton.jpg").resize(350, 60);
    ImageIcon credit = new MyImageIcon("main/creditbutton.jpg").resize(350, 60);
    ImageIcon showw = new MyImageIcon("main/showbutton.jpg").resize(200, 100);
    ImageIcon backk = new MyImageIcon("main/backbutton.jpg").resize(130, 60);
    ImageIcon player1 = new MyImageIcon("main/1playerbutton.jpg").resize(270, 60);
    ImageIcon player2 = new MyImageIcon("main/2playerbutton.jpg").resize(270, 60);
    ImageIcon play = new MyImageIcon("main/playnewbutton.jpg").resize(350, 100);
    ImageIcon sent = new MyImageIcon("main/sentbutton.jpg").resize(200, 100);
    ImageIcon ok = new MyImageIcon("main/oknewbutton.png").resize(150, 50);

    private boolean check1 = false, check2 = false;

    public MainApplication() {
        setTitle("MENU");
        setBounds(250, 120, frameWidth, frameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(new BorderLayout());
        background = new JLabel();
        background.setIcon(new MyImageIcon("main/bgr.jpg").resize(frameWidth, frameHeight));
        background.setLayout(null);
        backgroundstatus = new JLabel();
        backgroundcredit = new JLabel();
        background.setLayout(null);
        themeSound = new MySoundEffect("main/Song.wav");
        themeSound.playLoop();

        AddComponents();
    }

    public void AddComponents() {
  
        startbutton = new JButton(started);
        startbutton.addMouseListener(new MouseListener() {

            public void mouseEntered(MouseEvent e) {
                themeSound.stop();
                startsound = new MySoundEffect("main/sound.wav");
                startsound.playOnce();
                setCursor(Cursor.HAND_CURSOR);
            }

            public void mouseClicked(MouseEvent e) {
                clearScreen1();
                playbutton.setVisible(true);
                OKbutton1.setVisible(true);
                backbutton.setVisible(true);
                playerToggle[0].setVisible(true);
                playerToggle[1].setVisible(true);
                playername1.setVisible(true);

            }

            public void mouseExited(MouseEvent e) {
                themeSound.playLoop();
                setCursor(null);
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseMoved(MouseEvent e) {
            }

        });

        statusbutton = new JButton(status);
        statusbutton.addMouseListener(new MouseListener() {

            public void mouseEntered(MouseEvent e) {
                themeSound.stop();
                startsound = new MySoundEffect("main/sound.wav");
                startsound.playOnce();
                setCursor(Cursor.HAND_CURSOR);
            }

            public void mouseClicked(MouseEvent e) {
                clearScreen1();
                herolist.setVisible(true);
                backbutton.setVisible(true);
                showbutton.setVisible(true);
            }

            public void mouseExited(MouseEvent e) {
                themeSound.playLoop();
                setCursor(null);
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseMoved(MouseEvent e) {
            }

        });

        howtoplaybutton = new JButton(howtoplay);
        howtoplaybutton.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                themeSound.stop();
                startsound = new MySoundEffect("main/sound.wav");
                startsound.playOnce();
                setCursor(Cursor.HAND_CURSOR);
            }

            public void mouseClicked(MouseEvent e) {
                dialogcredit.setTitle("HOW TO PLAY");
                backgroundcredit.setIcon(new MyImageIcon("main/howtoplay.jpg").resize(1200,700));
                dialogcredit.setVisible(true);

            }

            public void mouseExited(MouseEvent e) {
                themeSound.playLoop();
                setCursor(null);
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseMoved(MouseEvent e) {
            }
        });
        
        creditbutton = new JButton(credit);
        creditbutton.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                themeSound.stop();
                startsound = new MySoundEffect("main/sound.wav");
                startsound.playOnce();
                setCursor(Cursor.HAND_CURSOR);
            }

            public void mouseClicked(MouseEvent e) {
                dialogcredit.setTitle("CREDIT");
                backgroundcredit.setIcon(new MyImageIcon("main/endcredit.jpg").resize(1200,700));
                dialogcredit.setVisible(true);
            }

            public void mouseExited(MouseEvent e) {
                themeSound.playLoop();
                setCursor(null);
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseMoved(MouseEvent e) {
            }
        });

        backbutton = new JButton(backk);
        backbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearScreen2();
                herolist.setVisible(false);
                showbutton.setVisible(false);
                playerToggle[1].setEnabled(true);
                playerToggle[0].setEnabled(true);
                playerToggle[0].setSelected(true);
                dayspinner.setVisible(false);
                mountspinner.setVisible(false);
                yearspinner.setVisible(false);
                playername1.setEditable(true);
                playername2.setEditable(true);
                sentbutton.setEnabled(true);
                feedbackradio[0].setSelected(true);
                dayspinner.setEnabled(true);
                mountspinner.setEnabled(true);
                yearspinner.setEnabled(true);
                check1 = false ;
                check2 = false ;
                themeSound.playLoop();
            }
        });
        playbutton = new JButton(play);
        playbutton.addMouseListener(new MouseListener() {

            public void mouseEntered(MouseEvent e) {
                themeSound.stop();
                startsound = new MySoundEffect("main/sound.wav");
                startsound.playOnce();
            }

            public void mouseClicked(MouseEvent e) {
                if(playerToggle[0].isSelected()){
                if (check1 == true) {
                    clearScreen2();
                    clearScreen1();
                    feedbackradio[0].setVisible(true);
                    feedbackradio[1].setVisible(true);
                    feedbackradio[2].setVisible(true);
                    feedbackradio[3].setVisible(true);
                    feedbackradio[4].setVisible(true);
                    dayspinner.setVisible(true);
                    mountspinner.setVisible(true);
                    yearspinner.setVisible(true);
                    sentbutton.setVisible(true);
                    backbutton.setVisible(true);
                    GameFrame game = new GameFrame();
                    game.setNameAndBot(true,name1,name2);
                    
                } 
                else QuickDialog.show("ENTER PLAYER 1 NAME"); 
                }
                    if(playerToggle[1].isSelected()){
                    if (check1 == true && check2 == true) {
                    clearScreen2();
                    clearScreen1();
                    feedbackradio[0].setVisible(true);
                    feedbackradio[1].setVisible(true);
                    feedbackradio[2].setVisible(true);
                    feedbackradio[3].setVisible(true);
                    feedbackradio[4].setVisible(true);
                    dayspinner.setVisible(true);
                    mountspinner.setVisible(true);
                    yearspinner.setVisible(true);
                    sentbutton.setVisible(true);
                    backbutton.setVisible(true);
                    GameFrame game = new GameFrame();
                    game.setNameAndBot(false,name1,name2);
                } 
                    else if(check1 == true && check2 == false)
                    {
                        QuickDialog.show("ENTER PLAYER 2 NAME");
                    }
                    else if(check1 == false && check2 == true)
                    {
                        QuickDialog.show("ENTER PLAYER 1 NAME");
                    }
                    else QuickDialog.show("ENTER PLAYER 1 AND PLAYER 2 NAME"); 
                    }
                   
                    

            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseMoved(MouseEvent e) {
            }

        });

        OKbutton1 = new JButton(ok);
        OKbutton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (playername1.getText().equals("")) {
                    QuickDialog.show("PLEASE ENTER YOUR NAME");
                } else {
                    playerToggle[1].setEnabled(false);
                    playerToggle[0].setEnabled(false);
                    playername1.setEditable(false);
                    OKbutton1.setVisible(false);
                    name1 = playername1.getText();
                    check1 = true;
                }
            }
        });
        OKbutton2 = new JButton(ok);
        OKbutton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (playername2.getText().equals("")) {
                    QuickDialog.show("PLEASE ENTER YOUR NAME");
                } else {
                    playerToggle[0].setEnabled(false);
                    OKbutton2.setVisible(false);
                    playername2.setEditable(false);
                    name2 = playername2.getText();
                    check2 = true;
                }
            }
        });

        sentbutton = new JButton(sent);
        sentbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 5; i++) {
                    if (feedbackradio[i].isSelected() == true) {
                        String scoree = feedbackradio[i].getText();
                        try {
                            FileWriter fw = new FileWriter("score.txt", true);
                            fw.write(numday+" "+nummount+" "+numyear+" ");
                            fw.write(name1+" give ");
                            fw.write(scoree);
                            fw.write(String.format("%n"));
                            fw.close();
                            QuickDialog.show("THANK YOU");
                        } catch (Exception E) {
                            System.out.println(E);
                        }
                    }

                }
                sentbutton.setEnabled(false);
                dayspinner.setEnabled(false);
                mountspinner.setEnabled(false);
                yearspinner.setEnabled(false);
            }
        });

        playername1 = new JTextField();
        playername2 = new JTextField();

        playerToggle = new JToggleButton[3];
        bgroup = new ButtonGroup();
        playerToggle[0] = new JRadioButton(player1);
        playerToggle[0].addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (playerToggle[0].isSelected()) {
                    playername2.setVisible(false);
                    OKbutton2.setVisible(false);
                }
            }
        });

        playerToggle[1] = new JRadioButton(player2);
        playerToggle[1].addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (playerToggle[1].isSelected()) {
                    playername2.setVisible(true);
                    OKbutton2.setVisible(true);
                }
            }
        });
        for (int i = 0; i < 3; i++) {
            bgroup.add(playerToggle[i]);
        }

        feedbackradio = new JRadioButton[5];
        bgroup1 = new ButtonGroup();
        feedbackradio[0] = new JRadioButton("    5 (VERY GOOD)");
        feedbackradio[1] = new JRadioButton("    4 (GOOD)    ");
        feedbackradio[2] = new JRadioButton("    3 (MEDIUM)");
        feedbackradio[3] = new JRadioButton("    2 (BAD)");
        feedbackradio[4] = new JRadioButton("    1 (VERY BAD)");
        for (int i = 0; i < 5; i++) {
            bgroup1.add(feedbackradio[i]);
        }

        herolist = new JList(myhero);
        herolist.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                }
            }
        });
        
     
        
        String months[] = { "January", "February", "March", "April", "May",
                            "June", "July", "August", "September", "October", "November","December" };
     
        SpinnerModel day = new  SpinnerNumberModel(15, 1, 31, 1);
        SpinnerModel mount = new  SpinnerListModel(months);
        SpinnerModel year = new  SpinnerNumberModel(2000,1930,2019,1);
        
        dayspinner = new JSpinner(day);   
        dayspinner.addChangeListener(new ChangeListener() {
             public void stateChanged(ChangeEvent e) {
                numday = (int)dayspinner.getValue() ;
           
            }
        });
        
        mountspinner = new JSpinner(mount);
        mountspinner.addChangeListener(new ChangeListener() {
             public void stateChanged(ChangeEvent e) {
                nummount = (String)mountspinner.getValue() ;
              
            }
        });
        
        yearspinner = new JSpinner(year);   
        yearspinner.addChangeListener(new ChangeListener() {
             public void stateChanged(ChangeEvent e) {
                numyear = (int)yearspinner.getValue() ;
                
            }
        });
        

        dialoghero = new JDialog();
        dialogcredit = new JDialog();

        showbutton = new JButton(showw);
        showbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (herolist.getSelectedValue().toString().equals("       Emperor")) {
                    dialoghero.setTitle("HERO STATUS");
                    backgroundstatus.setIcon(new MyImageIcon("main/emperor.jpg").resize(dialogWidth, dialogHeight));
                    dialoghero.setVisible(true);
                } else if (herolist.getSelectedValue().toString().equals("       Speedguy")) {
                    dialoghero.setTitle("HERO STATUS");
                    backgroundstatus.setIcon(new MyImageIcon("main/Speedguy.jpg").resize(dialogWidth, dialogHeight));
                    dialoghero.setVisible(true);
                } else if (herolist.getSelectedValue().toString().equals("       Tank")) {
                    dialoghero.setTitle("HERO STATUS");
                    backgroundstatus.setIcon(new MyImageIcon("main/Tank.jpg").resize(dialogWidth, dialogHeight));
                    dialoghero.setVisible(true);
                } else if (herolist.getSelectedValue().toString().equals("       Healer")) {
                    dialoghero.setTitle("HERO STATUS");
                    backgroundstatus.setIcon(new MyImageIcon("main/Healer.jpg").resize(dialogWidth, dialogHeight));
                    dialoghero.setVisible(true);
                } else if (herolist.getSelectedValue().toString().equals("       Dragon")) {
                    dialoghero.setTitle("HERO STATUS");
                    backgroundstatus.setIcon(new MyImageIcon("main/Dragon.jpg").resize(dialogWidth, dialogHeight));
                    dialoghero.setVisible(true);
                } else if (herolist.getSelectedValue().toString().equals("       Warrior")) {
                    dialoghero.setTitle("HERO STATUS");
                    backgroundstatus.setIcon(new MyImageIcon("main/warrior.jpg").resize(dialogWidth, dialogHeight));
                    dialoghero.setVisible(true);
                }
            }
        });
        dialoghero.setResizable(false);
        dialoghero.add(backgroundstatus);
        dialoghero.setBounds(500, 200, dialogWidth, dialogHeight);
        dialoghero.setVisible(false);
        dialoghero.setModal(true);
        dialogcredit.setResizable(false);
        dialogcredit.add(backgroundcredit);
        dialogcredit.setBounds(500, 200, 1200, 700);
        dialogcredit.setVisible(false);
        dialogcredit.setModal(true);

        showbutton.setVisible(false);
        herolist.setVisible(false);
        playbutton.setVisible(false);
        OKbutton1.setVisible(false);
        OKbutton2.setVisible(false);
        backbutton.setVisible(false);
        playerToggle[0].setSelected(true);
        playerToggle[0].setVisible(false);
        playerToggle[1].setVisible(false);
        playername1.setVisible(false);
        playername2.setVisible(false);
        sentbutton.setVisible(false);
        feedbackradio[0].setVisible(false);
        feedbackradio[1].setVisible(false);
        feedbackradio[2].setVisible(false);
        feedbackradio[3].setVisible(false);
        feedbackradio[4].setVisible(false);
        feedbackradio[0].setSelected(true);
        dayspinner.setVisible(false);
        mountspinner.setVisible(false);
        yearspinner.setVisible(false);
        dayspinner.setEditor(new JSpinner.DefaultEditor(dayspinner));
        mountspinner.setEditor(new JSpinner.DefaultEditor(mountspinner));
        yearspinner.setEditor(new JSpinner.DefaultEditor(yearspinner));
      
        dayspinner.setBounds(100, 150, 150, 40);
        dayspinner.setFont(new Font("serif", Font.BOLD, 24));
        mountspinner.setBounds(300, 150, 150, 40);
        mountspinner.setFont(new Font("serif", Font.BOLD, 24));
        yearspinner.setBounds(500, 150, 150, 40);
        yearspinner.setFont(new Font("serif", Font.BOLD, 24));
        showbutton.setBounds(700, 330, 200, 100);
        herolist.setBounds(250, 250, 300, 300);
        herolist.setFont(new Font("serif", Font.BOLD, 35));
        herolist.setForeground(Color.WHITE);
        herolist.setBackground(Color.BLACK);
        backbutton.setBounds(50, 570, 130, 60);
        sentbutton.setBounds(700, 350, 200, 100);
        playername1.setBounds(250, 400, 270, 50);
        playername1.setFont(new Font("serif", Font.BOLD, 35));
        OKbutton1.setBounds(550, 400, 70, 50);
        playername2.setBounds(700, 400, 270, 50);
        playername2.setFont(new Font("serif", Font.BOLD, 35));
        OKbutton2.setBounds(1000, 400, 70, 50);
        playbutton.setSize(350, 100);
        playbutton.setLocation(460, 500);
        playerToggle[0].setSize(270, 60);
        playerToggle[0].setLocation(250, 330);
        playerToggle[1].setSize(270, 60);
        playerToggle[1].setLocation(700, 330);    
        feedbackradio[0].setBounds(200, 200, 350, 50);
        feedbackradio[0].setBackground(Color.BLACK);
        feedbackradio[0].setForeground(Color.WHITE);
        feedbackradio[0].setFont(new Font("serif", Font.BOLD, 35));
        feedbackradio[1].setBounds(200, 300, 350, 50);
        feedbackradio[1].setBackground(Color.BLACK);
        feedbackradio[1].setForeground(Color.WHITE);
        feedbackradio[1].setFont(new Font("serif", Font.BOLD, 35));
        feedbackradio[2].setBounds(200, 400, 350, 50);
        feedbackradio[2].setBackground(Color.BLACK);
        feedbackradio[2].setForeground(Color.WHITE);
        feedbackradio[2].setFont(new Font("serif", Font.BOLD, 35));
        feedbackradio[3].setBounds(200, 500, 350, 50);
        feedbackradio[3].setBackground(Color.BLACK);
        feedbackradio[3].setForeground(Color.WHITE);
        feedbackradio[3].setFont(new Font("serif", Font.BOLD, 35));
        feedbackradio[4].setBounds(200, 600, 350, 50);
        feedbackradio[4].setBackground(Color.BLACK);
        feedbackradio[4].setForeground(Color.WHITE);
        feedbackradio[4].setFont(new Font("serif", Font.BOLD, 35));

        startbutton.setSize(350, 60);
        startbutton.setLocation(200, 280);
        statusbutton.setSize(350, 60);
        statusbutton.setLocation(200, 370);
        howtoplaybutton.setSize(350, 60);
        howtoplaybutton.setLocation(200, 460);
        creditbutton.setSize(350, 60);
        creditbutton.setLocation(200, 550);
        
        contentpane.add(dayspinner);
        contentpane.add(mountspinner);
        contentpane.add(yearspinner);
        contentpane.add(showbutton);
        contentpane.add(herolist);
        contentpane.add(sentbutton);
        contentpane.add(feedbackradio[0]);
        contentpane.add(feedbackradio[1]);
        contentpane.add(feedbackradio[2]);
        contentpane.add(feedbackradio[3]);
        contentpane.add(feedbackradio[4]);
        contentpane.add(backbutton);
        contentpane.add(playername1);
        contentpane.add(OKbutton1);
        contentpane.add(playername2);
        contentpane.add(OKbutton2);
        contentpane.add(playbutton);
        contentpane.add(playerToggle[0]);
        contentpane.add(playerToggle[1]);
        contentpane.add(statusbutton);
        contentpane.add(creditbutton);
        contentpane.add(startbutton);
        contentpane.add(howtoplaybutton);
        contentpane.add(background, BorderLayout.CENTER);
        validate();
        // repaint();
    }

    public void choseplayer() {

    }

    public void clearScreen1() {
        startbutton.setVisible(false);
        howtoplaybutton.setVisible(false);
        creditbutton.setVisible(false);
        statusbutton.setVisible(false);
    }

    public void clearScreen2() {
        feedbackradio[0].setVisible(false);
        feedbackradio[1].setVisible(false);
        feedbackradio[2].setVisible(false);
        feedbackradio[3].setVisible(false);
        feedbackradio[4].setVisible(false);
        sentbutton.setVisible(false);
        playbutton.setVisible(false);
        OKbutton1.setVisible(false);
        OKbutton2.setVisible(false);
        backbutton.setVisible(false);
        playerToggle[0].setVisible(false);
        playerToggle[1].setVisible(false);
        playername1.setVisible(false);
        playername2.setVisible(false);
        startbutton.setVisible(true);
        howtoplaybutton.setVisible(true);
        creditbutton.setVisible(true);
        statusbutton.setVisible(true);
        check1 = false;
        check2 = false;
    }

    public static void main(String[] args) {
        new MainApplication();
    }

}

class MyImageIcon extends ImageIcon {

    public MyImageIcon(String fname) {
        super(fname);
    }

    public MyImageIcon(Image image) {
        super(image);
    }

    public MyImageIcon resize(int width, int height) {
        Image oldimg = this.getImage();
        Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
};

class MySoundEffect {

    private java.applet.AudioClip audio;

    public MySoundEffect(String filename) {
        try {
            java.io.File file = new java.io.File(filename);
            audio = java.applet.Applet.newAudioClip(file.toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playOnce() {
        audio.play();
    }

    public void playLoop() {
        audio.loop();
    }

    public void stop() {
        audio.stop();
    }
}

class QuickDialog {

    public static void show(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, "ERROR",
                JOptionPane.INFORMATION_MESSAGE);
    }
};
