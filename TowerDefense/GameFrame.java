/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameframe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class GameFrame extends JFrame {

    JPanel contentpane, selection;
    JLabel gamepane;
    JLabel p1Base, p2Base;
    JLabel disaster;

    JTextField p1Box, p2Box, deathBox, p1NameField, p2NameField, p1LevelField, p2LevelField;

    JProgressBar p1BaseHealthBar, p2BaseHealthBar;
    int barWidth = 600;
    int barHeight = 20;

    JButton p1Creep, p2Creep, p1Spd, p1Tank, p1Atk, p2Spd, p2Tank, p2Atk, p1Flyer, p2Flyer, p1Healer, p2Healer,p1UpLevel,p2UpLevel;

    String[] creepP1Pic = {"resource/creep1P1.png", "resource/creep2P1.png", "resource/creep3P1.png", "resource/creepP1_atk1.png", "resource/creepP1_atk2.png", "resource/creepP1_atk3.png"};
    String[] attackerP1Pic = {"resource/attacker1P1.png", "resource/attacker2P1.png", "resource/attacker3P1.png", "resource/attackerP1_atk1.png", "resource/attackerP1_atk2.png", "resource/attackerP1_atk3.png"};
    String[] tankP1Pic = {"resource/tank1P1.png", "resource/tank2P1.png", "resource/tank3P1.png", "resource/tankP1_atk1.png", "resource/tankP1_atk2.png", "resource/tankP1_atk3.png"};
    String[] speedyP1Pic = {"resource/speedy1P1.png", "resource/speedy2P1.png", "resource/speedy3P1.png", "resource/speedyP1_atk1.png", "resource/speedyP1_atk2.png", "resource/speedyP1_atk3.png"};
    String[] flyerP1Pic = {"resource/flyer1P1.png", "resource/flyer2P1.png", "resource/flyer3P1.png", "resource/flyerP1_atk1.png", "resource/flyerP1_atk2.png", "resource/flyerP1_atk3.png"};
    String[] healerP1Pic = {"resource/healer1.png", "resource/healer2.png", "resource/healer3.png", "resource/healer_atk1.png", "resource/healer_atk2.png", "resource/healer_atk2.png"};

    String[] creepP2Pic = {"resource/creep1P2.png", "resource/creep2P2.png", "resource/creep3P2.png", "resource/creepP2_atk1.png", "resource/creepP2_atk2.png", "resource/creepP2_atk3.png"};
    String[] attackerP2Pic = {"resource/attacker1P2.png", "resource/attacker2P2.png", "resource/attacker3P2.png", "resource/attackerP2_atk1.png", "resource/attackerP2_atk2.png", "resource/attackerP2_atk3.png"};
    String[] tankP2Pic = {"resource/tank1P2.png", "resource/tank2P2.png", "resource/tank3P2.png", "resource/tankP2_atk1.png", "resource/tankP2_atk2.png", "resource/tankP2_atk3.png"};
    String[] speedyP2Pic = {"resource/speedy1P2.png", "resource/speedy2P2.png", "resource/speedy3P2.png", "resource/speedyP2_atk1.png", "resource/speedyP2_atk2.png", "resource/speedyP2_atk3.png"};
    String[] flyerP2Pic = {"resource/flyer1P2.png", "resource/flyer2P2.png", "resource/flyer3P2.png", "resource/flyerP2_atk1.png", "resource/flyerP2_atk2.png", "resource/flyerP2_atk3.png"};
    String[] healerP2Pic = {"resource/healer1P2.png", "resource/healer2P2.png", "resource/healer3P2.png", "resource/healerP2_atk1.png", "resource/healerP2_atk2.png", "resource/healerP2_atk2.png"};

    String[] creepBarPic = {"resource/creepLogo.jpg", "resource/creepLogo_dark.jpg", "resource/creepLogo2.jpg", "resource/creepLogo_dark2.jpg"};
    String[] tankBarPic = {"resource/tankLogo.jpg", "resource/tankLogo_dark.jpg", "resource/tankLogo2.jpg", "resource/tankLogo_dark2.jpg"};
    String[] speedyBarPic = {"resource/speedyLogo.jpg", "resource/speedyLogo_dark.jpg", "resource/speedyLogo2.jpg", "resource/speedyLogo_dark2.jpg"};
    String[] flyerBarPic = {"resource/flyerLogo.jpg", "resource/flyerLogo_dark.jpg", "resource/flyerLogo2.jpg", "resource/flyerLogo_dark2.jpg"};
    String[] healerBarPic = {"resource/healerLogo.jpg", "resource/healerLogo_dark.jpg", "resource/healerLogo2.jpg", "resource/healerLogo2_dark.jpg"};
    String[] attackerBarPic = {"resource/attackerLogo.jpg", "resource/attackerLogo_dark.jpg", "resource/attackerLogo2.jpg", "resource/attackerLogo_dark2.jpg"};

    MyImageIcon[] creepP1Icon, attackerP1Icon, tankP1Icon, speedyP1Icon, flyerP1Icon, healerP1Icon;
    MyImageIcon[] creepP2Icon, attackerP2Icon, tankP2Icon, speedyP2Icon, flyerP2Icon, healerP2Icon;
    MyImageIcon[] creepLogo, attackerLogo, speedyLogo, tankLogo, healerLogo, flyerLogo;
    MyImageIcon lvlUpIcon,lvlUpIcon_Dull;

    Thread gameUpdater, disasterThread;

    int frameWidth = 1300, frameHeight = 700;
    int baseWidth = 200;
    int baseHeight = 300;
    int base1Hp = 10000;
    int base1Xpos = 0;
    int base1Ypos = frameHeight - baseHeight - 100;
    int base2Hp = 10000;
    int base2Xpos = frameWidth - baseWidth;
    int base2Ypos = frameHeight - baseHeight - 100;
    int p1Money = 20;
    int p2Money = 20;
    int attackerPrice = 35;
    int creepPrice = 10;
    int tankPrice = 25;
    int speedyPrice = 15;
    int flyerPrice = 20;
    int healerPrice = 20;
    int creepHp = 150;
    int creepAtk = 30;
    int creepSpeed = 100;
    int creepWidth = 100;
    int creepHeight = 110;
    int tankHp = 5000;
    int tankAtk = 30;
    int tankSpeed = 120;
    int tankWidth = 200;
    int tankHeight = 200;
    int speedyHp = 500;
    int speedyAtk = 25;
    int speedySpeed = 25;
    int speedyWidth = 100;
    int speedyHeight = 110;
    int attackerHp = 1500;
    int attackerAtk = 90;
    int attackerSpeed = 90;
    int attackerWidth = 120;
    int attackHeight = 120;
    int flyerHp = 500;
    int flyerAtk = 20;
    int flyerSpeed = 30;
    int flyerWidth = 150;
    int flyerHeight = 100;
    int healerHp = 100;
    int healerAtk = 30;
    int healerSpeed = 110;
    int healerWidth = 100;
    int healerHeight = 100;

    int deathCount = 0;
    int maxMoney[] = {10, 20, 40, 60, 80};

    int p1Level;
    int p2Level;

    boolean gameRunning;
    boolean disasterOccur;
    boolean summonedP1, summonedP2;
    boolean enableBot;

    ArrayList<player1Minion> p1Minion;
    ArrayList<player2Minion> p2Minion;

    MySoundEffect themeSound = new MySoundEffect("resource/playingTheme.wav");
    MySoundEffect dieSound = new MySoundEffect("resource/painSound.wav");

    public GameFrame() {
        setTitle("GameStarted");
        setBounds(50, 50, frameWidth, frameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        gameRunning = true;
        disasterOccur = false;
        summonedP1 = false;
        summonedP2 = false;
        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(new BorderLayout());
        selection = new JPanel();
        selection.setBounds(0, 0, 1000, 50);
        selection.setBackground(Color.darkGray);
        gamepane = new JLabel();
        gamepane.setIcon(new MyImageIcon("resource/BackGround.jpg").resize(frameWidth, frameHeight));
        gamepane.setLayout(null);
        p1Minion = new ArrayList<player1Minion>();
        p2Minion = new ArrayList<player2Minion>();
        p1Base = new JLabel(new MyImageIcon("resource/castle.png").resize(baseWidth, baseHeight));
        p2Base = new JLabel(new MyImageIcon("resource/castle.png").resize(baseWidth, baseHeight));
        p1Base.setBounds(base1Xpos, base1Ypos, baseWidth, baseHeight);
        p2Base.setBounds(base2Xpos, base2Ypos, baseWidth, baseHeight);

        p1BaseHealthBar = new JProgressBar(0, base1Hp);
        p2BaseHealthBar = new JProgressBar(0, base2Hp);
        p1BaseHealthBar.setBounds(20, 20, barWidth, barHeight);
        p2BaseHealthBar.setBounds(frameWidth - barWidth - 20, 20, barWidth, barHeight);
        p1BaseHealthBar.setValue(base1Hp);
        p2BaseHealthBar.setValue(base2Hp);
        p1BaseHealthBar.setStringPainted(true);
        p1BaseHealthBar.setString(base1Hp + " / 10000");
        p2BaseHealthBar.setStringPainted(true);
        p2BaseHealthBar.setString(base2Hp + " / 10000");
        p1BaseHealthBar.setForeground(Color.red);
        p2BaseHealthBar.setForeground(Color.red);

        p1Level = 0;
        p2Level = 0;
        themeSound.playLoop();

        setAllIcon();
        addComponent();

        setGameThread();

    }

    public void addComponent() {
        p1Creep = new JButton();
        p1Creep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                summonCreep("p1");
            }
        });
        p1Creep.setFocusable(false);
        p1Creep.setMargin(new Insets(0, 0, 0, 0));

        p2Creep = new JButton();
        p2Creep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                summonCreep("p2");
            }
        });
        p2Creep.setFocusable(false);
        p2Creep.setMargin(new Insets(0, 0, 0, 0));

        p1Spd = new JButton();
        p1Spd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                summonSpeedy("p1");
            }
        });
        p1Spd.setFocusable(false);
        p1Spd.setMargin(new Insets(0, 0, 0, 0));

        p2Spd = new JButton();
        p2Spd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                summonSpeedy("p2");
            }
        });
        p2Spd.setFocusable(false);
        p2Spd.setMargin(new Insets(0, 0, 0, 0));

        p1Tank = new JButton();
        p1Tank.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                summonTank("p1");
            }
        });
        p1Tank.setFocusable(false);
        p1Tank.setMargin(new Insets(0, 0, 0, 0));

        p2Tank = new JButton();
        p2Tank.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                summonTank("p2");
            }
        });
        p2Tank.setFocusable(false);
        p2Tank.setMargin(new Insets(0, 0, 0, 0));

        p1Atk = new JButton();
        p1Atk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                summonAttacker("p1");
            }
        });
        p1Atk.setFocusable(false);
        p1Atk.setMargin(new Insets(0, 0, 0, 0));

        p2Atk = new JButton();
        p2Atk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                summonAttacker("p2");
            }
        });
        p2Atk.setFocusable(false);
        p2Atk.setMargin(new Insets(0, 0, 0, 0));

        p1Flyer = new JButton();
        p1Flyer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                summonFlyer("p1");
            }
        });
        p1Flyer.setFocusable(false);
        p1Flyer.setMargin(new Insets(0, 0, 0, 0));

        p2Flyer = new JButton();
        p2Flyer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                summonFlyer("p2");
            }
        });
        p2Flyer.setFocusable(false);
        p2Flyer.setMargin(new Insets(0, 0, 0, 0));

        p1Healer = new JButton();
        p1Healer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                summonHealer("p1");
            }
        });
        p1Healer.setFocusable(false);
        p1Healer.setMargin(new Insets(0, 0, 0, 0));

        p2Healer = new JButton();
        p2Healer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                summonHealer("p2");
            }
        });
        p2Healer.setFocusable(false);
        p2Healer.setMargin(new Insets(0, 0, 0, 0));
        
        p1UpLevel = new JButton();
        p1UpLevel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                if(p1Level<4)levelUp("p1");
            }
        });
        p1UpLevel.setFocusable(false);
        p1UpLevel.setContentAreaFilled(false);
        p1UpLevel.setBorder(null);
        
        p2UpLevel = new JButton();
        p2UpLevel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                if(p2Level<4)levelUp("p2");
            }
        });
        
        p2UpLevel.setFocusable(false);
        p2UpLevel.setContentAreaFilled(false);
        p2UpLevel.setBorder(null);

        p1Box = new JTextField("");
        p1Box.setEditable(false);
        p1Box.setForeground(Color.YELLOW);
        p1Box.setFocusable(false);
        p1Box.setBorder(null);
        p1Box.setFont(new Font("AngsanaNew", Font.PLAIN, 20));
        p1Box.setOpaque(false);

        p2Box = new JTextField();
        p2Box.setEditable(false);
        p2Box.setForeground(Color.YELLOW);
        p2Box.setFocusable(false);
        p2Box.setBorder(null);
        p2Box.setFont(new Font("AngsanaNew", Font.PLAIN, 20));
        p2Box.setOpaque(false);
        
        deathBox = new JTextField("Death Count = 0");
        deathBox.setEditable(false);
        deathBox.setOpaque(false);
        deathBox.setBounds(frameWidth / 2 - 80, 50, 200, 70);
        deathBox.setFont(new Font("AngsanaNew", Font.BOLD, 20));
        deathBox.setBorder(null);
        deathBox.setFocusable(false);

        p1NameField = new JTextField();
        p1NameField.setForeground(Color.BLACK);
        p1NameField.setFont(new Font("AngsanaNew", Font.ITALIC, 30));
        p1NameField.setOpaque(false);
        p1NameField.setEditable(false);
        p1NameField.setBorder(null);
        p1NameField.setFocusable(false);
        p1NameField.setBounds(30, 40,200,50);

        p2NameField = new JTextField();
        p2NameField.setForeground(Color.BLACK);
        p2NameField.setFont(new Font("AngsanaNew", Font.ITALIC, 30));
        p2NameField.setOpaque(false);
        p2NameField.setEditable(false);
        p2NameField.setBorder(null);
        p2NameField.setFocusable(false);
        p2NameField.setBounds(frameWidth-230, 40,200,50);
        p2NameField.setHorizontalAlignment(SwingConstants.RIGHT);
        
        p1LevelField = new JTextField("Level 1 - MaxCoin 10");
        p1LevelField.setForeground(Color.BLACK);
        p1LevelField.setFont(new Font("AngsanaNew", Font.PLAIN, 20));
        p1LevelField.setOpaque(false);
        p1LevelField.setEditable(false);
        p1LevelField.setBorder(null);
        p1LevelField.setFocusable(false);
        p1LevelField.setBounds(30, 75,200,50);
        
        p2LevelField = new JTextField("Level 1 - MaxCoin 10",SwingConstants.RIGHT);
        p2LevelField.setForeground(Color.BLACK);
        p2LevelField.setFont(new Font("AngsanaNew", Font.PLAIN, 20));
        p2LevelField.setOpaque(false);
        p2LevelField.setEditable(false);
        p2LevelField.setBorder(null);
        p2LevelField.setFocusable(false);
        p2LevelField.setHorizontalAlignment(SwingConstants.RIGHT);
        p2LevelField.setBounds(frameWidth-230, 75,200,50);

        selection.add(p1UpLevel);
        selection.add(new JLabel("   "));
        selection.add(p1Box);
        selection.add(new JLabel(" "));
        selection.add(p1Creep);
        selection.add(p1Spd);
        selection.add(p1Tank);
        selection.add(p1Atk);
        selection.add(p1Flyer);
        selection.add(p1Healer);
        selection.add(new JLabel("               "));
        selection.add(p2Creep);
        selection.add(p2Spd);
        selection.add(p2Tank);
        selection.add(p2Atk);
        selection.add(p2Flyer);
        selection.add(p2Healer);
        selection.add(new JLabel(" "));
        selection.add(p2Box);
        selection.add(new JLabel("   "));
        selection.add(p2UpLevel);

        disaster = new JLabel();

        addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    summonCreep("p1");
                } else if (e.getKeyCode() == KeyEvent.VK_R) {
                    summonAttacker("p1");
                } else if (e.getKeyCode() == KeyEvent.VK_E) {
                    summonTank("p1");
                } else if (e.getKeyCode() == KeyEvent.VK_W) {
                    summonSpeedy("p1");
                } else if (e.getKeyCode() == KeyEvent.VK_T) {
                    summonFlyer("p1");
                } else if (e.getKeyCode() == KeyEvent.VK_Y) {
                    summonHealer("p1");
                } else if (e.getKeyCode() == KeyEvent.VK_V) {
                    if(p1Level<4)levelUp("p1");
                }

                if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                    if (!enableBot) {
                        summonCreep("p2");
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                    if (!enableBot) {
                        summonAttacker("p2");
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
                    if (!enableBot) {
                        summonTank("p2");
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
                    if (!enableBot) {
                        summonSpeedy("p2");
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                    if (!enableBot) {
                        summonFlyer("p2");
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                    if (!enableBot) {
                        summonHealer("p2");
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                    if (!enableBot) {
                        if(p2Level<4)levelUp("p2");
                    }
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                removeAllMinion();
                gameRunning = false;
            }
        });

        gamepane.add(disaster); // disasterจะได้ทับทุกอย่าง
        gamepane.add(p1NameField);
        gamepane.add(p1LevelField);
        gamepane.add(p2NameField);
        gamepane.add(p2LevelField);
        gamepane.add(deathBox);
        gamepane.add(p1Base);
        gamepane.add(p2Base);

        gamepane.add(p1BaseHealthBar);
        gamepane.add(p2BaseHealthBar);

        contentpane.add(selection, BorderLayout.NORTH);
        contentpane.add(gamepane, BorderLayout.CENTER);

        validate();
    }

    public void setNameAndBot(boolean bot, String nameP1, String nameP2) {
        if (bot) {
            enableBot = bot;
            p2Creep.setVisible(false);
            p2Spd.setVisible(false);
            p2Tank.setVisible(false);
            p2Atk.setVisible(false);
            p2Flyer.setVisible(false);
            p2Healer.setVisible(false);
            p2Box.setVisible(false);
            p2UpLevel.setVisible(false);
            p2LevelField.setVisible(false);
            p2Money = 10000;
            p2Level = 2;
        }
        p1NameField.setText(nameP1);
        if(!bot) p2NameField.setText(nameP2);
        else p2NameField.setText("BOT Kung");
    }

    public void generateBotSummon() {
        int ran = (int) Math.floor((Math.random() * 100) + 1);
        switch (ran % 6) {
            case 1:
                summonAttacker("p2");
                break;
            case 2:
                summonCreep("p2");
                break;
            case 3:
                summonFlyer("p2");
                break;
            case 4:
                summonHealer("p2");
                break;
            case 5:
                summonSpeedy("p2");
                break;
            case 6:
                summonTank("p2");
                break;
        }

    }

    public void setAllIcon() {
        creepP1Icon = new MyImageIcon[creepP1Pic.length];
        attackerP1Icon = new MyImageIcon[attackerP1Pic.length];
        tankP1Icon = new MyImageIcon[tankP1Pic.length];
        speedyP1Icon = new MyImageIcon[speedyP1Pic.length];
        flyerP1Icon = new MyImageIcon[flyerP1Pic.length];
        creepP2Icon = new MyImageIcon[creepP2Pic.length];
        attackerP2Icon = new MyImageIcon[attackerP2Pic.length];
        tankP2Icon = new MyImageIcon[tankP2Pic.length];
        speedyP2Icon = new MyImageIcon[speedyP2Pic.length];
        flyerP2Icon = new MyImageIcon[flyerP2Pic.length];
        healerP1Icon = new MyImageIcon[healerP1Pic.length];
        healerP2Icon = new MyImageIcon[healerP2Pic.length];
        creepLogo = new MyImageIcon[creepBarPic.length];
        flyerLogo = new MyImageIcon[flyerBarPic.length];
        healerLogo = new MyImageIcon[healerBarPic.length];
        tankLogo = new MyImageIcon[tankBarPic.length];
        attackerLogo = new MyImageIcon[attackerBarPic.length];
        speedyLogo = new MyImageIcon[speedyBarPic.length];
        for (int i = 0; i < 6; i++) {
            creepP1Icon[i] = new MyImageIcon(creepP1Pic[i]).resize(creepWidth, creepHeight);
            attackerP1Icon[i] = new MyImageIcon(attackerP1Pic[i]).resize(attackerWidth, attackHeight);
            tankP1Icon[i] = new MyImageIcon(tankP1Pic[i]).resize(tankWidth, tankHeight);
            speedyP1Icon[i] = new MyImageIcon(speedyP1Pic[i]).resize(speedyWidth, speedyHeight);
            flyerP1Icon[i] = new MyImageIcon(flyerP1Pic[i]).resize(flyerWidth, flyerHeight);
            creepP2Icon[i] = new MyImageIcon(creepP2Pic[i]).resize(creepWidth, creepHeight);
            attackerP2Icon[i] = new MyImageIcon(attackerP2Pic[i]).resize(attackerWidth, attackHeight);
            tankP2Icon[i] = new MyImageIcon(tankP2Pic[i]).resize(tankWidth, tankHeight);
            speedyP2Icon[i] = new MyImageIcon(speedyP2Pic[i]).resize(speedyWidth, speedyHeight);
            flyerP2Icon[i] = new MyImageIcon(flyerP2Pic[i]).resize(flyerWidth, flyerHeight);
            healerP1Icon[i] = new MyImageIcon(healerP1Pic[i]).resize(healerWidth, healerHeight);
            healerP2Icon[i] = new MyImageIcon(healerP2Pic[i]).resize(healerWidth, healerHeight);
        }
        for (int i = 0; i < 4; i++) {
            creepLogo[i] = new MyImageIcon(creepBarPic[i]).resize(50, 50);
            tankLogo[i] = new MyImageIcon(tankBarPic[i]).resize(50, 50);
            speedyLogo[i] = new MyImageIcon(speedyBarPic[i]).resize(50, 50);
            attackerLogo[i] = new MyImageIcon(attackerBarPic[i]).resize(50, 50);
            healerLogo[i] = new MyImageIcon(healerBarPic[i]).resize(50, 50);
            flyerLogo[i] = new MyImageIcon(flyerBarPic[i]).resize(50, 50);
        }
        
        lvlUpIcon = new MyImageIcon("resource/levelUp.jpg").resize(100, 50);
        lvlUpIcon_Dull = new MyImageIcon("resource/levelUp_dull.jpg").resize(100, 50);
    }

    public void setGameThread() {
        gameUpdater = new Thread() {
            public void run() {
                while (gameRunning) {
                    addP1Money(2);
                    addP2Money(2);
                    checkBuyable();
                    if (deathCount % 30 == 0 && deathCount != 0 && !disasterOccur) {
                        if (p1Minion.size() != 0 || p2Minion.size() != 0) { //จะเกิดdisasterตอนมีตัวอยู่ในสนามเท่านั้น
                            createDisaster();
                        }
                    }
                    summonedP1 = false;
                    summonedP2 = false;
                    if (enableBot) {
                        generateBotSummon();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    checkWinCondition();
                }
                themeSound.stop();
                dispose();
            }
        };
        gameUpdater.start();
    }

    public void createDisaster() { // เกิดtsunamiที่จะล้างสนามทำให้ทุกตัวตาย
        disasterThread = new Thread() {
            public void run() {
                disasterOccur = true;
                boolean disasterPeak = false;
                int disasterYpos = frameHeight;
                disaster.setBounds(0, frameHeight, frameWidth, frameHeight);
                disaster.setIcon(new MyImageIcon("resource/tsunamiBg.png").resize(frameWidth, frameHeight));
                while (disasterOccur) {
                    if (!disasterPeak) {
                        disasterYpos -= 10;
                    } else {
                        disasterYpos += 7;
                        if (disasterYpos >= frameHeight) {
                            disasterOccur = false;
                        }
                    }
                    if (disasterYpos < frameHeight / 5) {
                        removeAllMinion();
                        disasterPeak = true;
                    }
                    disaster.setLocation(0, disasterYpos);
                    try {
                        Thread.sleep(20);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    repaint();
                }
                disaster.setIcon(null);
            }
        };
        disasterThread.start();
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -> SUMMONต่างๆ

    public void summonCreep(String s) {
        if (s == "p1") {
            if (p1Money >= creepPrice && !summonedP1) {
                useP1Money(creepPrice);
                player1Minion tempMinion = new player1Minion(creepWidth, creepHeight, creepHp, creepAtk, creepSpeed, creepP1Icon);
                p1Minion.add(tempMinion);
                tempMinion.start();
                summonedP1 = true;
            }
        } else if (s == "p2") {
            if (p2Money >= creepPrice && !summonedP2) {
                useP2Money(creepPrice);
                player2Minion tempMinion = new player2Minion(creepWidth, creepHeight, creepHp, creepAtk, creepSpeed, creepP2Icon);
                p2Minion.add(tempMinion);
                tempMinion.start();
                summonedP2 = true;
            }
        }
    }

    public void summonSpeedy(String s) {
        if (s == "p1") {
            if (p1Money >= speedyPrice && !summonedP1) {
                useP1Money(speedyPrice);
                player1Minion tempMinion = new player1Minion(speedyWidth, speedyHeight, speedyHp, speedyAtk, speedySpeed, speedyP1Icon);
                p1Minion.add(tempMinion);
                tempMinion.start();
                summonedP1 = true;
            }
        } else if (s == "p2") {
            if (p2Money >= speedyPrice && !summonedP2) {
                useP2Money(speedyPrice);
                player2Minion tempMinion = new player2Minion(speedyWidth, speedyHeight, speedyHp, speedyAtk, speedySpeed, speedyP2Icon);
                p2Minion.add(tempMinion);
                tempMinion.start();
                summonedP2 = true;
            }
        }
    }

    public void summonTank(String s) {
        if (s == "p1") {
            if (p1Money >= tankPrice && !summonedP1) {
                useP1Money(tankPrice);
                player1Minion tempMinion = new player1Minion(tankWidth, tankHeight, tankHp, tankAtk, tankSpeed, tankP1Icon);
                p1Minion.add(tempMinion);
                tempMinion.start();
                summonedP1 = true;
            }
        } else if (s == "p2") {
            if (p2Money >= tankPrice && !summonedP2) {
                useP2Money(tankPrice);
                player2Minion tempMinion = new player2Minion(tankWidth, tankHeight, tankHp, tankAtk, tankSpeed, tankP2Icon);
                p2Minion.add(tempMinion);
                tempMinion.start();
                summonedP2 = true;
            }
        }
    }

    public void summonAttacker(String s) {
        if (s == "p1") {
            if (p1Money >= attackerPrice && !summonedP1) {
                useP1Money(attackerPrice);
                player1Minion tempMinion = new player1Minion(attackerWidth, attackHeight, attackerHp, attackerAtk, attackerSpeed, attackerP1Icon);
                p1Minion.add(tempMinion);
                tempMinion.start();
                summonedP1 = true;
            }
        } else if (s == "p2") {
            if (p2Money >= attackerPrice && !summonedP2) {
                useP2Money(attackerPrice);
                player2Minion tempMinion = new player2Minion(attackerWidth, attackHeight, attackerHp, attackerAtk, attackerSpeed, attackerP2Icon);
                p2Minion.add(tempMinion);
                tempMinion.start();
                summonedP2 = true;
            }
        }
    }

    public void summonFlyer(String s) {
        if (s == "p1") {
            if (p1Money >= flyerPrice && !summonedP1) {
                useP1Money(flyerPrice);
                player1Minion tempMinion = new player1Minion(flyerWidth, flyerHeight, flyerHp, flyerAtk, flyerSpeed, flyerP1Icon);
                p1Minion.add(tempMinion);
                tempMinion.start();
                summonedP1 = true;
            }
        } else if (s == "p2") {
            if (p2Money >= flyerPrice && !summonedP2) {
                useP2Money(flyerPrice);
                player2Minion tempMinion = new player2Minion(flyerWidth, flyerHeight, flyerHp, flyerAtk, flyerSpeed, flyerP2Icon);
                p2Minion.add(tempMinion);
                tempMinion.start();
                summonedP2 = true;
            }
        }
    }

    public void summonHealer(String s) {
        if (s == "p1") {
            if (p1Money >= healerPrice && !summonedP1) {
                useP1Money(healerPrice);
                player1Minion tempMinion = new player1Minion(healerWidth, healerHeight, healerHp, healerAtk, healerSpeed, healerP1Icon);
                p1Minion.add(tempMinion);
                tempMinion.start();
                summonedP1 = true;
            }
        } else if (s == "p2") {
            if (p2Money >= healerPrice && !summonedP2) {
                useP2Money(healerPrice);
                player2Minion tempMinion = new player2Minion(healerWidth, healerHeight, healerHp, healerAtk, healerSpeed, healerP2Icon);
                p2Minion.add(tempMinion);
                tempMinion.start();
                summonedP2 = true;
            }
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void levelUp(String name){
        if(name == "p1"){
            if(p1Money >= maxMoney[p1Level]/2){
                useP1Money(maxMoney[p1Level]/2);
                p1Level++;
                int temp = p1Level +1;
                p1LevelField.setText("Level "+ temp+ " - MaxCoin "+maxMoney[p1Level]);
            }
            
        }
        if(name == "p2"){
            if(p2Money >= maxMoney[p2Level]/2){
                useP2Money(maxMoney[p2Level]/2);
                p2Level++;
                int temp = p2Level +1;
                p2LevelField.setText("Level "+ temp+ " - MaxCoin "+maxMoney[p2Level]);
            }
        }
    }
    synchronized public void addP1Money(int amount) { //กันเพื่อแย่งกันอัพเดทค่า money
        if (amount + p1Money < maxMoney[p1Level]) {
            p1Money = p1Money + amount;
        } else {
            p1Money = maxMoney[p1Level];
        }
        String temp = Integer.toString(p1Money);
        p1Box.setText("Coin's " + temp);
    }

    synchronized public void addP2Money(int amount) {
        if (amount + p2Money < maxMoney[p2Level]) {
            p2Money = p2Money + amount;
        } else {
            p2Money = maxMoney[p2Level];
        }
        String temp = Integer.toString(p2Money);
        p2Box.setText("Coin's " + temp);
    }

    synchronized public void useP1Money(int amount) {
        p1Money = p1Money - amount;
        String temp = Integer.toString(p1Money);
        p1Box.setText("Coin's " + temp);
    }

    synchronized public void useP2Money(int amount) {
        p2Money = p2Money - amount;
        String temp = Integer.toString(p2Money);
        p2Box.setText("Coin's " + temp);
    }

    synchronized void p1BaseGetAtk(int dmg) {
        base1Hp = base1Hp - dmg;
    }

    synchronized void p2BaseGetAtk(int dmg) {
        base2Hp = base2Hp - dmg;
    }

    public void addDeathCount() {
        deathCount++;
        deathBox.setText("Death Count = " + deathCount);
    }

    public void checkWinCondition() {
        if (base1Hp <= 0) {
            gameOver(p2NameField.getText() + " Win ");
        } else if (base2Hp <= 0) {
            gameOver(p1NameField.getText() + " Win ");
        }
    }
    
    public void gameOver(String s) {

        removeAllMinion();
        JOptionPane.showMessageDialog(new JFrame(), s, "GameOver",
                JOptionPane.INFORMATION_MESSAGE);
        gameRunning = false;
    }

    public void checkBuyable() {
        if (p1Money < creepPrice) {
            p1Creep.setIcon(creepLogo[1]);
        } else {
            p1Creep.setIcon(creepLogo[0]);
        }

        if (p1Money < attackerPrice) {
            p1Atk.setIcon(attackerLogo[1]);
        } else {
            p1Atk.setIcon(attackerLogo[0]);
        }

        if (p1Money < tankPrice) {
            p1Tank.setIcon(tankLogo[1]);
        } else {
            p1Tank.setIcon(tankLogo[0]);
        }

        if (p1Money < speedyPrice) {
            p1Spd.setIcon(speedyLogo[1]);
        } else {
            p1Spd.setIcon(speedyLogo[0]);
        }

        if (p1Money < flyerPrice) {
            p1Flyer.setIcon(flyerLogo[1]);
        } else {
            p1Flyer.setIcon(flyerLogo[0]);
        }

        if (p2Money < creepPrice) {
            p2Creep.setIcon(creepLogo[3]);
        } else {
            p2Creep.setIcon(creepLogo[2]);
        }

        if (p2Money < attackerPrice) {
            p2Atk.setIcon(attackerLogo[3]);
        } else {
            p2Atk.setIcon(attackerLogo[2]);
        }

        if (p2Money < tankPrice) {
            p2Tank.setIcon(tankLogo[3]);
        } else {
            p2Tank.setIcon(tankLogo[2]);
        }

        if (p2Money < speedyPrice) {
            p2Spd.setIcon(speedyLogo[3]);
        } else {
            p2Spd.setIcon(speedyLogo[2]);
        }

        if (p2Money < flyerPrice) {
            p2Flyer.setIcon(flyerLogo[3]);
        } else {
            p2Flyer.setIcon(flyerLogo[2]);
        }

        if (p1Money < healerPrice) {
            p1Healer.setIcon(healerLogo[1]);
        } else {
            p1Healer.setIcon(healerLogo[0]);
        }
        if (p2Money < healerPrice) {
            p2Healer.setIcon(healerLogo[3]);
        } else {
            p2Healer.setIcon(healerLogo[2]);
        }
        
        if(p1Money < maxMoney[p1Level]/2){
            p1UpLevel.setIcon(lvlUpIcon_Dull);
        } else p1UpLevel.setIcon(lvlUpIcon);
        
        if(p2Money < maxMoney[p2Level]/2){
            p2UpLevel.setIcon(lvlUpIcon_Dull);
        } else p2UpLevel.setIcon(lvlUpIcon);
    }

    synchronized void removeP1Minion(player1Minion e) { //ใส่SynchronizeเพราะมีการUpdateค่า arraylist
        for (int i = 0; i < p1Minion.size(); i++) {
            if (p1Minion.get(i) == e) {
                p1Minion.remove(i);
            }
        }
    }

    synchronized void removeP2Minion(player2Minion e) {
        for (int i = 0; i < p2Minion.size(); i++) {
            if (p2Minion.get(i) == e) {
                p2Minion.remove(i);
            }
        }
    }

    boolean healP1Minion(player1Minion e) {
        for (int i = 0; i < p1Minion.size(); i++) {
            if (e.getLabel().getBounds().intersects(p1Minion.get(i).getLabel().getBounds()) && e.getFirstIcon() != p1Minion.get(i).getFirstIcon()) {
                if (p1Minion.get(i).getMaxHp() > p1Minion.get(i).getCurrentHp() + e.getAtkAmount()) {
                    p1Minion.get(i).getHealed(e.getAtkAmount());
                } else {
                    p1Minion.get(i).setMaxHp();
                }
                return true;
            }
        }
        return false;
    }

    boolean healP2Minion(player2Minion e) {
        for (int i = 0; i < p2Minion.size(); i++) {
            if (e.getLabel().getBounds().intersects(p2Minion.get(i).getLabel().getBounds()) && e.getFirstIcon() != p2Minion.get(i).getFirstIcon()) {
                if (p2Minion.get(i).getMaxHp() > p2Minion.get(i).getCurrentHp() + e.getAtkAmount()) {
                    p2Minion.get(i).getHealed(e.getAtkAmount());
                } else {
                    p2Minion.get(i).setMaxHp();
                }
                return true;
            }
        }
        return false;
    }

    synchronized void updateBaseHealth() { //กันbaseUpdateค่าhealthเร็วไปเมื่อมีหลายๆตัวรุมป้อม
        if (base1Hp > 0) {
            p1BaseHealthBar.setValue(base1Hp);
        } else {
            base1Hp = 0;
        }
        if (base2Hp > 0) {
            p2BaseHealthBar.setValue(base2Hp);
        } else {
            base2Hp = 0;
        }
        p1BaseHealthBar.setString(base1Hp + " / 10000");
        p2BaseHealthBar.setString(base2Hp + " / 10000");
    } 

    public void removeAllMinion() {
        while (p1Minion.size() > 0) {
            p1Minion.get(0).destroyThread();
            p1Minion.remove(0);
        }
        while (p2Minion.size() > 0) {
            p2Minion.get(0).destroyThread();
            p2Minion.remove(0);
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public class player1Minion extends Thread {

        JLabel myLabel;
        int xPos, yPos, hp, atk, width, height, speed, maxHp;
        boolean killed;
        MyImageIcon[] icon;
        JProgressBar health;
        int actionProgress;

        public player1Minion(int w, int h, int tempHp, int tempAtk, int tempSpeed, MyImageIcon[] pic) {
            killed = false;
            maxHp = tempHp;
            xPos = base1Xpos + (baseWidth / 2);
            if (pic[0] == flyerP1Icon[0]) {
                yPos = base1Ypos - h / 2;
            } else {
                yPos = base1Ypos + (baseHeight - h);
            }
            hp = tempHp;
            atk = tempAtk;
            speed = tempSpeed;
            width = w;
            height = h;
            icon = pic;
            myLabel = new JLabel(icon[0]);
            myLabel.setBounds(xPos, yPos, width, height);
            gamepane.add(myLabel);

            actionProgress = 1;

            health = new JProgressBar(0, hp);
            health.setValue(hp);
            health.setBounds(xPos, yPos - 10, width, 10);
            health.setForeground(Color.red);
            health.setBackground(Color.black);
            gamepane.add(health);

        }

        public void run() {
            while (!killed) {
                if (hp <= 0) {
                    killed = true;
                    addP2Money(5);
                } else if (minionCollision()); else if (terrainCollision()); else {
                    xPos += 2;
                    myLabel.setLocation(xPos, yPos);

                    health.setLocation(xPos, yPos - 10);

                    switch (actionProgress) {
                        case 1:
                            myLabel.setIcon(icon[0]);
                            break;
                        case 2:
                            myLabel.setIcon(icon[1]);
                            break;
                        case 3:
                            myLabel.setIcon(icon[2]);
                            break;
                        case 4:
                            myLabel.setIcon(icon[1]);
                            actionProgress = 0;
                            break;
                    }
                    actionProgress++;
                }
                if (!disasterOccur) {
                    health.setValue(hp); //แก้การรูปกระตุก
                }
                checkBuyable();

                try {
                    Thread.sleep(speed);
                } catch (Exception e) {
                    System.err.println(e);
                }
                if (!disasterOccur) {
                    repaint(); //แก้รูปกระตุก
                }
            }
            dieSound.playOnce();
            gamepane.remove(myLabel);
            gamepane.remove(health);
            addDeathCount();
            removeP1Minion(this); //เพื่อไม่ไห้เรียก arraylist class เดียวกันใน 1 object (OOP Style)

        }

        public boolean minionCollision() {
            if (icon[0] == healerP1Icon[0]) {
                if (healP1Minion(this)) {
                    myLabel.setIcon(icon[3]);
                    try {
                        Thread.sleep(speed * 8);
                        myLabel.setIcon(icon[4]);
                        Thread.sleep(speed);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    return true;
                } else {
                    return false;
                }
            }
            for (int i = 0; i < p2Minion.size(); i++) {
                if (myLabel.getBounds().intersects(p2Minion.get(i).getLabel().getBounds())) {
                    myLabel.setIcon(icon[3]);
                    double ran = Math.random();
                    try {
                        Thread.sleep(speed * 8);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    myLabel.setIcon(icon[4]);
                    int tempAtk = atk;
                    if (ran <= 0.1) {
                        tempAtk *= 2;
                    }
                    try {
                        Thread.sleep(speed);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    if (p2Minion.size() > i) { /// กัน Array Index Out of Bound ตอน่ที่thread ตายแล้ว
                        if (myLabel.getBounds().intersects(p2Minion.get(i).getLabel().getBounds())) { //กันตอนthread ตัวที่ i ไม่ได้ซ้อนกับ thread นี้ จากการตายของ thread ก่อนๆ
                            p2Minion.get(i).getAttacked(tempAtk);
                        }
                    }
                    myLabel.setIcon(icon[5]);
                    return true;
                }

            }
            return false;
        }

        public boolean terrainCollision() {
            if (myLabel.getBounds().intersects(p2Base.getBounds())) {
                myLabel.setIcon(icon[3]);
                try {
                    Thread.sleep(speed * 8);
                } catch (Exception e) {
                    System.out.println(e);
                }
                myLabel.setIcon(icon[4]);
                try {
                    Thread.sleep(speed);
                } catch (Exception e) {
                    System.out.println(e);
                }
                p2BaseGetAtk(atk);
                updateBaseHealth();
                myLabel.setIcon(icon[5]);
                return true;
            } else {
                return false;
            }
        }

        public JLabel getLabel() {
            return myLabel;
        }

        public int getAtkAmount() {
            return atk;
        }

        public int getMaxHp() {
            return maxHp;
        }

        public int getCurrentHp() {
            return hp;
        }

        public void setMaxHp() {
            hp = maxHp;
        }

        public MyImageIcon getFirstIcon() {
            return icon[0];
        }

        synchronized public void getAttacked(int dmg) {
            hp = hp - dmg;
        }

        synchronized public void getHealed(int dmg) {
            hp = hp + dmg;
        }

        public void destroyThread() {
            killed = true;
        }

    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public class player2Minion extends Thread {

        JLabel myLabel;
        int xPos, yPos, hp, atk, width, height, speed, maxHp;
        boolean killed;
        MyImageIcon[] icon;

        int actionProgress;

        JProgressBar health;

        public player2Minion(int w, int h, int tempHp, int tempAtk, int tempSpeed, MyImageIcon[] pic) {
            killed = false;
            maxHp = tempHp;
            xPos = base2Xpos;
            if (pic[0] == flyerP2Icon[0]) {
                yPos = base2Ypos - h / 2;
            } else {
                yPos = base2Ypos + (baseHeight - h);
            }
            hp = tempHp;
            atk = tempAtk;
            speed = tempSpeed;
            width = w;
            height = h;
            icon = pic;
            myLabel = new JLabel(icon[0]);
            myLabel.setBounds(xPos, yPos, width, height);
            gamepane.add(myLabel);

            actionProgress = 1;

            health = new JProgressBar(0, hp);
            health.setValue(hp);
            health.setBounds(xPos, yPos - 10, width, 10);
            health.setForeground(Color.red);
            health.setBackground(Color.black);
            gamepane.add(health);
        }

        public void run() {

            while (!killed) {
                if (hp <= 0) {
                    killed = true;
                    addP1Money(5);
                } else if (minionCollision()); else if (terrainCollision()); else {
                    xPos -= 2;
                    myLabel.setLocation(xPos, yPos);
                    health.setLocation(xPos, yPos - 10);

                    switch (actionProgress) {
                        case 1:
                            myLabel.setIcon(icon[0]);
                            break;
                        case 2:
                            myLabel.setIcon(icon[1]);
                            break;
                        case 3:
                            myLabel.setIcon(icon[2]);
                            break;
                        case 4:
                            myLabel.setIcon(icon[1]);
                            actionProgress = 0;
                            break;
                    }
                    actionProgress++;
                }

                checkBuyable();
                if (!disasterOccur) {
                    health.setValue(hp); //แก้รูปกระตุก
                }
                try {
                    Thread.sleep(speed);
                } catch (Exception e) {
                    System.err.println(e);
                }
                if (!disasterOccur) {
                    repaint(); //แก้รูปกระตุก
                }
            }
            dieSound.playOnce();
            gamepane.remove(myLabel);
            gamepane.remove(health);
            addDeathCount();
            removeP2Minion(this); //เพื่อไม่ไห้เรียก arraylist class เดียวกันใน 1 object (OOP Style)

        }

        public boolean minionCollision() {
            if (icon[0] == healerP2Icon[0]) {
                if (healP2Minion(this)) {
                    myLabel.setIcon(icon[3]);
                    try {
                        Thread.sleep(speed * 8);
                        myLabel.setIcon(icon[4]);
                        Thread.sleep(speed);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    myLabel.setIcon(icon[4]);
                    return true;
                } else {
                    return false;
                }
            }
            for (int i = 0; i < p1Minion.size(); i++) {
                if (myLabel.getBounds().intersects(p1Minion.get(i).getLabel().getBounds())) {
                    myLabel.setIcon(icon[3]);
                    try {
                        Thread.sleep(speed * 8);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    myLabel.setIcon(icon[4]);
                    double ran = Math.random();
                    int tempAtk = atk;
                    if (ran <= 0.1) {
                        tempAtk *= 2;
                    }
                    try {
                        Thread.sleep(speed);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    if (p1Minion.size() > i) {
                        if (myLabel.getBounds().intersects(p1Minion.get(i).getLabel().getBounds())) { //กันตอนthread ตัวที่ i ไม่ได้ซ้อนกับ thread นี้ จากการตายของ thread ก่อนๆ
                            p1Minion.get(i).getAttacked(tempAtk); /// กัน Array Index Out of Bound
                        }
                    }
                    myLabel.setIcon(icon[5]);
                    return true;
                }
            }
            return false;
        }

        public boolean terrainCollision() {
            if (myLabel.getBounds().intersects(p1Base.getBounds())) {
                myLabel.setIcon(icon[3]);
                try {
                    Thread.sleep(speed * 8);
                } catch (Exception e) {
                    System.out.println(e);
                }
                myLabel.setIcon(icon[4]);
                try {
                    Thread.sleep(speed);
                } catch (Exception e) {
                    System.out.println(e);
                }
                p1BaseGetAtk(atk);
                updateBaseHealth();
                myLabel.setIcon(icon[5]);
                return true;
            } else {
                return false;
            }
        }

        public JLabel getLabel() {
            return myLabel;
        }

        public int getAtkAmount() {
            return atk;
        }

        public MyImageIcon getFirstIcon() {
            return icon[0];
        }

        public int getMaxHp() {
            return maxHp;
        }

        public int getCurrentHp() {
            return hp;
        }

        public void setMaxHp() {
            hp = maxHp;
        }

        synchronized public void getAttacked(int dmg) {
            hp = hp - dmg;
        }

        synchronized public void getHealed(int dmg) {
            hp = hp + dmg;
        }

        public void destroyThread() {
            killed = true;
        }
    }
    public static void main(String[] args) {
    }

}
