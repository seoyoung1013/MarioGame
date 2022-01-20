import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;



public class MarioGame extends JFrame {
	DrawPanel dp;

	static String player_name; //�÷��̾� �̸�

	static int PANEL_WIDTH = 1500; //�г� ����ũ��
	static int PANEL_HEIGHT = 500; //�г� ����ũ��

	static int FRAME_WIDTH = PANEL_WIDTH + 16; //������ ����ũ��
	static int FRAME_HEIGHT = PANEL_HEIGHT + 39; //������ ����ũ��

	static int MARIO_WIDTH = 20; //������ ����ũ��
	static int MARIO_HEIGHT = 35; //������ ����ũ��
	
	static File path = new File(".");
	static String root = path.getAbsolutePath();
	
	//���ӻ���
	ImageIcon startImage = new ImageIcon(getClass().getClassLoader().getResource("���ӽ���ȭ��.gif")); 
	ImageIcon gameImage = new ImageIcon(getClass().getClassLoader().getResource("���ӹ��ȭ��.png"));
	ImageIcon explainImage = new ImageIcon(getClass().getClassLoader().getResource("���Ӽ���ȭ��.png"));
	ImageIcon endImage = new ImageIcon(getClass().getClassLoader().getResource("��������ȭ��.png"));
	
	ImageIcon m_right = new ImageIcon(getClass().getClassLoader().getResource("�����ʼ��ֱ�.png"));
	ImageIcon m_left = new ImageIcon(getClass().getClassLoader().getResource("���ʼ��ֱ�.png"));
	ImageIcon m_rightRun = new ImageIcon(getClass().getClassLoader().getResource("�����ʰȱ�.gif"));
	ImageIcon m_leftRun = new ImageIcon(getClass().getClassLoader().getResource("���ʰȱ�.gif"));
	ImageIcon m_rightJump = new ImageIcon(getClass().getClassLoader().getResource("����������.png"));
	
	Image mushroom = new ImageIcon(getClass().getClassLoader().getResource("����.gif")).getImage();
	Image coin = new ImageIcon(getClass().getClassLoader().getResource("����.gif")).getImage();
	Image monster_m = new ImageIcon(getClass().getClassLoader().getResource("���͹���.gif")).getImage();
	ImageIcon diemonster_m = new ImageIcon(getClass().getClassLoader().getResource("��������.png"));
	Image plant = new ImageIcon(getClass().getClassLoader().getResource("�Ĺ�.gif")).getImage();
	
	Image r_turtle = new ImageIcon(getClass().getClassLoader().getResource("�������ʷϰź���.gif")).getImage();
	Image l_turtle = new ImageIcon(getClass().getClassLoader().getResource("�����ʷϰź���.gif")).getImage();
	Image r_Yturtle = new ImageIcon(getClass().getClassLoader().getResource("�����ʳ���ź���.gif")).getImage();
	Image l_Yturtle = new ImageIcon(getClass().getClassLoader().getResource("���ʳ���ź���.gif")).getImage();
	
	Image heart = new ImageIcon(getClass().getClassLoader().getResource("���.png")).getImage();
	Image attack_m = new ImageIcon(getClass().getClassLoader().getResource("���ݸ�����.png")).getImage();
	
	Image r_king = new ImageIcon(getClass().getClassLoader().getResource("�����ʰź�ŷ.gif")).getImage();
	Image l_king = new ImageIcon(getClass().getClassLoader().getResource("���ʰź�ŷ.gif")).getImage();
	Image fire = new ImageIcon(getClass().getClassLoader().getResource("�Ҳ�.png")).getImage();
	Image attack_t = new ImageIcon(getClass().getClassLoader().getResource("����ź��̰���.gif")).getImage();
	Image star = new ImageIcon(getClass().getClassLoader().getResource("��.png")).getImage();

	Image backImg = gameImage.getImage();
	Image img = m_rightJump.getImage();

	ArrayList<Mushroom> listM = new ArrayList<Mushroom>();
	ArrayList<Coin> listC = new ArrayList<Coin>();
	ArrayList<Monster_m> listMO = new ArrayList<Monster_m>();
	ArrayList<Turtle> listT = new ArrayList<Turtle>();
	ArrayList<YTurtle> listTY = new ArrayList<YTurtle>();
	ArrayList<players> listN = new ArrayList<players>();
	ArrayList<Heart> listH = new ArrayList<Heart>();
	ArrayList<Attack_m> listAM = new ArrayList<Attack_m>();
	ArrayList<Plant> listP = new ArrayList<Plant>();
	ArrayList<King> listK = new ArrayList<King>();
	ArrayList<Fire> listF = new ArrayList<Fire>();
	ArrayList<Star> listS = new ArrayList<Star>();
	ArrayList<Attack_t> listAT = new ArrayList<Attack_t>();

	ArrayList<Mushroom> MRemove = new ArrayList<>();
	ArrayList<Coin> CRemove = new ArrayList<>();
	ArrayList<Monster_m> MORemove = new ArrayList<>();
	ArrayList<Turtle> TRemove = new ArrayList<>();
	ArrayList<YTurtle> TYRemove = new ArrayList<>();
	ArrayList<Heart> HRemove = new ArrayList<>();
	ArrayList<Attack_m> AMRemove = new ArrayList<>();
	ArrayList<Attack_t> ATRemove = new ArrayList<>();
	ArrayList<Plant> PRemove = new ArrayList<>();
	ArrayList<King> KRemove = new ArrayList<>();
	ArrayList<Fire> FRemove = new ArrayList<>();
	ArrayList<Star> SRemove = new ArrayList<>();

	//int backX = 0;
	int field = 450;
	int imgY = 398;
	int score = 0;
	int level = 1;
	int INDEX = 5;
	int moveXM = 1;
	int count = 0;
	int count2 = 0;
	int count3 = 0;

	int dieX;
	int dieY;

	JLabel start = new JLabel();
	JLabel game = new JLabel();
	JLabel nameLabel;
	JLabel scoreLabel;
	JLabel levelLabel;
	JLabel lifeLabel;
	JLabel endLabel;

	Mario mario;
	Die die;

	Timer t;
	Timer t2;

	JButton howButton, startButton;

	boolean up,right,left,at = false;
	boolean up_ma, right_ma, left_ma, right_runma, left_runma = false;
	boolean move_screen;
	boolean fall;
	boolean jump;
	boolean isMainScreen, isExplainScreen, isGameScreen, isFinishScreen;
	boolean mushR = true;
	boolean mushL;
	boolean monster_mR = true;
	boolean monster_mL;
	boolean tR,ytR = true;
	boolean tL,ytL;
	boolean kR = true;
	boolean kL;
	boolean fr,fl,atr,atl;
	boolean howButtonb = false;
	boolean end;
	boolean ty = false;

	//�������
	public static void bgm() {
		try {
			File bgm = new File(root + "/res/�������.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(bgm));
			clip.loop(10);
			clip.start();
		}catch(Exception e) {
			System.out.println("Sound Error bgm");
		}
	}

	//����ȿ����
	public static void coin() {
		try {
			File bgm = new File(root + "/res/���μҸ�.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(bgm));
			clip.loop(0);
			clip.start();
		}catch(Exception e) {
			System.out.println("Sound Error bgm");
		}
	}

	//����ȿ����
	public static void jump() {
		try {
			File bgm = new File(root + "/res/������ ���� ȿ���� .wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(bgm));
			clip.loop(0);
			clip.start();
		}catch(Exception e) {
			System.out.println("Sound Error bgm");
		}
	}


	MarioGame(){
		bgm();
		nameInput();

		dp = new DrawPanel();
		mario = new Mario(15,400);
		dp.add(mario);

		t = new Timer(10, new Draw());
		t2 = new Timer(300, new MakeA());
		dp.addKeyListener(dp);
		dp.setLayout(null);

		//�̹��� ��ư�� ����ϱ� ���� ����
		startButton = new JButton(new ImageIcon("res/���ӽ��۹�ư.png"));
		startButton.setBorderPainted(false);
		startButton.setFocusPainted(false);
		startButton.setContentAreaFilled(false);

		howButton = new JButton(new ImageIcon("res/���Ӽ����ư.png"));
		howButton.setBorderPainted(false);
		howButton.setFocusPainted(false);
		howButton.setContentAreaFilled(false);

		//��ư ��ġ�� ������
		startButton.setBounds(165,250,150,38);
		howButton.setBounds(155,290,170,38);

		//��ư�� ������ �߰�
		ButtonListener bl = new ButtonListener();
		startButton.addActionListener(bl);
		howButton.addActionListener(bl);

		dp.add(startButton);
		dp.add(howButton);

		start.setIcon(startImage);
		dp.add(start);
		start.setBounds(0,0,PANEL_WIDTH,PANEL_HEIGHT);

		nameLabel = new JLabel("NAME: " + player_name);
		scoreLabel = new JLabel("SCORE: " + score);
		levelLabel = new JLabel("LEVEL: " + level);
		lifeLabel = new JLabel("LIFE: ");

		//�� �۾�ü
		nameLabel.setFont(new Font("FTLAB Hoony", Font.BOLD , 15));
		scoreLabel.setFont(new Font("FTLAB Hoony", Font.BOLD , 15));
		levelLabel.setFont(new Font("FTLAB Hoony", Font.BOLD , 15));
		lifeLabel.setFont(new Font("FTLAB Hoony", Font.BOLD , 15));

		//��ġ ����//setBounds(x,y,����,����) ������ġ
		nameLabel.setBounds(450, 0, 100, 30);
		scoreLabel.setBounds(600, 0, 100, 30); 
		levelLabel.setBounds(750, 0, 100, 30);
		lifeLabel.setBounds(900, 0, 100, 30);

		//��� ����
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		levelLabel.setHorizontalAlignment(JLabel.CENTER);
		lifeLabel.setHorizontalAlignment(JLabel.CENTER);

		//�� ����
		nameLabel.setForeground(Color.white);
		scoreLabel.setForeground(Color.white);
		levelLabel.setForeground(Color.white);
		lifeLabel.setForeground(Color.white);

		dp.add(nameLabel);
		dp.add(scoreLabel);
		dp.add(levelLabel);
		dp.add(lifeLabel);

		endLabel = new JLabel();
		endLabel.setBounds(0,0,1500,500);
		endLabel.setVisible(false);
		endLabel.setIcon(endImage);
		dp.add(endLabel);

		this.add(dp);
		this.setSize(500, FRAME_HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Super Mario");
		this.setResizable(false);
	}

	//��ư������
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == startButton) {
				start.setVisible(false);
				game.setIcon(gameImage);
				dp.add(game);

				mario.setIcon(m_right);
				mario.setBounds(240, 405, 20, 35);
				mario.setVisible(true);

				for(int i = 0; i<INDEX; i++) {
					listH.add(new Heart(i));
				}

				howButton.setVisible(false);
				startButton.setVisible(false);
				setSize(FRAME_WIDTH, FRAME_HEIGHT);
				t.start();
			}
			if(e.getSource() == howButton) {
				howButtonb = true;
				start.setIcon(explainImage);
				howButton.setVisible(false);
				startButton.setVisible(false);
				setSize(FRAME_WIDTH, FRAME_HEIGHT);
			}
		}
	}

	//�̸� ����
	public void name() {
		listN.add(new players(player_name));
	}

	// ���� �� ����� �̸� �ޱ�
	public void nameInput() { 
		for(;;) {
			player_name = (String)JOptionPane.showInputDialog(this, "�÷��̾��� �̸��� �Է��ϼ���(5�� �̳�)", "MARIO", JOptionPane.PLAIN_MESSAGE);
			if(player_name == null)
				System.exit(0);
			else if(player_name.length() > 5)
				player_name = (String)JOptionPane.showInputDialog(this, "�÷��̾��� �̸��� �Է��ϼ���(5�� �̳�)", "MARIO", JOptionPane.PLAIN_MESSAGE);
			else if(player_name.length() <= 5) {
				break;
			}
		}
	}

	//����, ���� ����
	class Draw implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
			count++;

			//���� ������ �������� ����
			if((mario.x<=440 && mario.y+35<=350&& !fall && !jump) || (mario.x>=550&&mario.x<=725 && mario.y+35<=350&& !fall && !jump)
					|| (mario.x>=876&&mario.x<=1045&&mario.y+35<=350&&!fall&!jump)||(mario.x>=1200&&mario.y+35<=350&& !fall && !jump) ) {
				new Thread(new Runnable() {
					public void run() {
						int under = mario.y+35;
						int set = 0;
						while(under<430) {
							set++;
							if(under + set > 430) {
								mario.setY(398);
								break;
							}

							mario.fall(set);
							under = mario.getY()+35;
							try {
								Thread.sleep(10);
							}catch(InterruptedException e1) {
								e1.printStackTrace();
							}
						}
					}
				}).start();
			}

			//����Ű�� ������ ����
			if(right_ma) {
				mario.setIcon(m_right);
				if(right_runma) {
					mario.setIcon(m_rightRun);
					if(mario.x<=1480)
						mario.moveRIGHT();
				}
			}

			if(left_ma) {
				mario.setIcon(m_left);
				if(left_runma) {
					mario.setIcon(m_leftRun);
					if(mario.x>=0)
						mario.moveLEFT();
				}
			}
			
			//������ ����
			if(at) {
				t2.start();
			} else if(at == false)
				t2.stop();

			//��������
			if(count == 1) {
				listM.add(new Mushroom(600,405));
			}
			
			//����
			if(count == 1) {
				listC.add(new Coin(461,333)); listC.add(new Coin(476,333)); listC.add(new Coin(491,333));
				listC.add(new Coin(506,333)); listC.add(new Coin(521,333)); listC.add(new Coin(536,333));
				listC.add(new Coin(550,333)); listC.add(new Coin(731,333)); listC.add(new Coin(746,333)); 
				listC.add(new Coin(761,333)); listC.add(new Coin(776,333)); listC.add(new Coin(791,333));
				listC.add(new Coin(806,333)); listC.add(new Coin(821,333)); listC.add(new Coin(835,333)); 
				listC.add(new Coin(849,333)); listC.add(new Coin(864,333)); listC.add(new Coin(1047,333));
				listC.add(new Coin(1062,333)); listC.add(new Coin(1077,333)); listC.add(new Coin(1092,333));
				listC.add(new Coin(1106,333)); listC.add(new Coin(1120,333)); listC.add(new Coin(1134,333));
				listC.add(new Coin(1148,333)); listC.add(new Coin(1163,333)); listC.add(new Coin(1178,333));
				listC.add(new Coin(115,412)); listC.add(new Coin(155,412)); listC.add(new Coin(195,412));
				listC.add(new Coin(235,412)); listC.add(new Coin(275,412)); listC.add(new Coin(385,412));
				listC.add(new Coin(465,412)); listC.add(new Coin(545,412)); listC.add(new Coin(624,412));
				listC.add(new Coin(755,412)); listC.add(new Coin(835,412)); listC.add(new Coin(915,412));
				listC.add(new Coin(995,412)); listC.add(new Coin(1075,412));
			}

			//��������
			if(count == 1) {
				listMO.add(new Monster_m(800,410));
			}

			//�ʷϰź���
			if(count == 1)
				listT.add(new Turtle(1130,405));
			
			//�Ĺ�
			if(count == 1) {
				listP.add(new Plant(320,400));
				listP.add(new Plant(700,400));
				listP.add(new Plant(1120,400));
			}

			//2�ܰ� ����ź���, ����, ����
			if(listT.isEmpty()&&listMO.isEmpty()) {
				level = 2;
				count2++;

				int xT;
				int count4=0;
				int xY1;


				if(count2 == 1) {
					xY1 = (int)(Math.random()*1400);
					listTY.add(new YTurtle(xY1, 405));
					
					xT = (int)(Math.random()*500);
					listK.add(new King(xT,385));
				}

				if(count2 % 100 == 0) {
					for(YTurtle yt : listTY) {
						if(atr) {
							listAT.add(new Attack_t(yt.tX+16,yt.tY+8,true));

						}
						else if(atl) {
							listAT.add(new Attack_t(yt.tX+16,yt.tY+8,false));
						}
					}
				}			


				if(count2 % 100 == 0) {
					for(King k : listK) {
						{
							if(fr) {
								listF.add(new Fire(k.kX+25,k.kY+25,true));
							}
							else if(fl) {
								listF.add(new Fire(k.kX+25,k.kY+25,false));
							}
						}
					}
				}
				
				//���� ���Ḧ ���� �� ����
				if(listK.isEmpty()) {
					count4++;
					if(count4==1)
						listS.add(new Star(750,280));
				}
			}
		}
	}


	//�������� �ϴ� ����
	class MakeA implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int gX;
			int gY;
			count++;

			gX = mario.x;
			gY = mario.y;

			if(listM.isEmpty()) {
				if(right_ma) {
					listAM.add(new Attack_m(gX+20,gY+9,true));
				}
				else if(left_ma) {
					listAM.add(new Attack_m(gX,gY+9,false));
				}
			}
		}
	}


	
	class DrawPanel extends JPanel implements KeyListener{
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			g.drawImage(backImg,0,0,this);

			//���� ����
			if(INDEX <= 0) {			
				for(Plant p : listP)
					PRemove.add(p);
				for(Coin c : listC)
					CRemove.add(c);
				mario.setBounds(mario.x, mario.y, 0, 0);

				//����ġ �缳��
				nameLabel.setBounds(400,250,300,100);
				scoreLabel.setBounds(600,250, 300, 100); 
				levelLabel.setBounds(800,250, 300, 100);

				dp.remove(game);
				//��������ȭ��
				endLabel.setVisible(true);
				t.stop();
			}

			//�Ĺ�, �������� ���̸� ���� �� ���� ����
			for(Plant p : listP) {
				p.drawP(g);

				if(p.distanceP(mario.x, mario.y)<=25) {
					PRemove.add(p);
					INDEX--;
					HRemove.add(listH.get(INDEX));
				}
			}

			//������ ��Ÿ���� ��Ʈ
			for(Heart h : listH)
				h.drawH(g);

			//�������� �ϴ� ����
			for(Attack_m ma: listAM) {
				if(ma.getX()<0 || ma.getX()>1500)
					AMRemove.add(ma);

				if(listAM.isEmpty()!=true) {
					ma.drawAM(g);
					ma.moveA();
				}
				for(King k : listK) {
					if(k.distanceK(ma.getX(), ma.getY())<30) {
						k.khp();
						AMRemove.add(ma);
					}
				}
			}

			//���� ����(���ݷ� ȹ��)
			for(Mushroom m : listM) {
				m.drawM(g);
				if(mushR && m.getX() < 684) {
					m.rightM();
				}
				if(m.getX() >= 684) {
					mushR = false;
					mushL = true;
				}
				if(m.getX() > 338 && mushL) {
					m.leftM();
				}
				if(m.getX() <= 338) {
					mushR = true;
					mushL = false;
				}

				if(m.distanceM(mario.x, mario.y)<=30) {
					MRemove.add(m);
				}
			}

			//����(����ȹ��)
			for(Coin c: listC) {
				c.drawC(g);

				if(c.distanceC(mario.x, mario.y)<=30) {
					CRemove.add(c);
					score++;
					coin();
				}
			}

			//��������(����)
			for(Monster_m mo : listMO) {
				mo.drawMO(g);
				//���������� ������
				if(monster_mR && mo.getX() < 1102) {
					mo.rightM();
				}
				//���ʸ������ �ٲ�
				if(mo.getX() >= 1102) {
					monster_mR = false;
					monster_mL = true;
				}
				//�������� ������
				if(mo.getX() > 718 && monster_mL) {
					mo.leftM();
				}
				//�����ʸ������ �ٲ�
				if(mo.getX() <= 718) {
					monster_mR = true;
					monster_mL = false;
				}
				

				//�����ؼ� ���̱�
				if(mario.x+10>=mo.getX()&&mario.x+10<=mo.getX()+30&&mario.y+35>mo.getY()&&jump) {
					die = new Die(listMO.get(0).getX(), listMO.get(0).getY());
					dieX = mo.getX();
					dieY = mo.getY();
					//���� ����
					dp.add(die);
					die.setIcon(diemonster_m);
					die.setBounds(dieX, dieY, 30, 30);
					MORemove.add(mo);
					die.setVisible(true);
				}

				
				if(mo.distanceMO(mario.x, mario.y)<25) {
					MORemove.add(mo);
					INDEX--;
					HRemove.add(listH.get(INDEX));
				}
			}

			//�ʷϰź���
			for(Turtle t : listT) {
				if(tR && t.getX() < 1470) {
					t.drawT(g,r_turtle);
					t.rightM();
				}
				if(t.getX() >= 1470) {
					tR = false;
					tL = true;
				}
				if(t.getX() > 1138 && tL) {
					t.drawT(g,l_turtle);
					t.leftM();
				}
				if(t.getX() <= 1138) {
					tR = true;
					tL = false;
				}

				for(Attack_m ma : listAM) {
					if(t.distanceT(ma.pX+8, ma.pY)<15) {
						AMRemove.add(ma);
						t.hp();
					}
					if(t.hp<=0) {
						score+=10;
						TRemove.add(t);
					}
				}

				if(t.distanceT(mario.x, mario.y)<30) {
					score--;
				}

				if(t.distanceT(mario.x, mario.y)<30) {
					TRemove.add(t);
					INDEX--;
					HRemove.add(listH.get(INDEX));
				}
			}

			//����ź��� ����
			for(YTurtle yt : listTY) {

				if(ytR && yt.getX() < 1470) {
					yt.drawTY(g,r_Yturtle);
					yt.rightM();
					atr = true;
					atl = false;
				}
				if(yt.getX() >= 1470) {
					ytR = false;
					ytL = true;
				}
				if(yt.getX() > 0 && ytL) {
					yt.drawTY(g,l_Yturtle);
					yt.leftM();
					atr = false;
					atl = true;
				}
				if(yt.getX() <= 0) {
					ytR = true;
					ytL = false;
				}

				for(Attack_m ma : listAM) {
					if(yt.distanceT(ma.pX+8, ma.pY)<15) {
						AMRemove.add(ma);
						yt.hp();
					}
					if(yt.hp<=0) {
						score+=10;
						TYRemove.add(yt);
					}
				}

				if(yt.distanceT(mario.x, mario.y)<30) {
					score--;
				}

				if(yt.distanceT(mario.x, mario.y)<30) {
					TYRemove.add(yt);
					INDEX--;
					HRemove.add(listH.get(INDEX));
				}
			}

			//����ź��̰� �ϴ� ����
			for(Attack_t at : listAT) {

				if(at.getX()<0 || at.getX()>1500)
					ATRemove.add(at);

				if(listAT.isEmpty()!=true) {
					at.drawAT(g);
					at.moveAT();
				}

				if(at.distanceAT(mario.x,mario.y)<35) {
					ATRemove.add(at);
					INDEX--;
					HRemove.add(listH.get(INDEX));
				}
			}

			
			//���� ����
			for(King k : listK) {

				if(kR && k.getX() < 1450) {
					k.drawK(g,r_king);
					k.rightK();
					fr = true;
					fl = false;

				}
				if(k.getX() >= 1450) {
					kR = false;
					kL = true;
				}
				if(k.getX() > 0 && kL) {
					k.drawK(g,l_king);
					k.leftK();
					fr = false;
					fl = true;
				}
				if(k.getX() <= 0) {
					kR = true;
					kL = false;
				}

				if(k.distanceK(mario.x, mario.y)<30) {
					score -= 5;
				}

				if(k.khp<0) {
					score+=20;
					KRemove.add(k);
				}
			}

			//���İ� �ϴ� ����
			for(Fire f : listF) {

				if(f.getX()<0 || f.getX()>1500)
					FRemove.add(f);

				if(listF.isEmpty()!=true) {
					f.drawF(g);
					f.moveF();
				}

				if(f.distanceF(mario.x,mario.y)<35) {
					FRemove.add(f);
					INDEX--;
					HRemove.add(listH.get(INDEX));
				}
			}

			//���� ���Ḧ ���� ��
			for(Star s : listS) {
				if(listS.isEmpty()!=true) {
					s.drawS(g);
				}
				if(s.distanceS(mario.x, mario.y)<33) {
					SRemove.add(s);
					end = true;
				}

				if(end) {
					for(Plant p : listP)
						PRemove.add(p);
					for(Coin c : listC)
						CRemove.add(c);
					mario.setBounds(mario.x, mario.y, 0, 0);

					nameLabel.setBounds(400,250,300,100);
					scoreLabel.setBounds(600,250, 300, 100); //setBounds(x,y,����,����) ������ġ
					levelLabel.setBounds(800,250, 300, 100);

					dp.remove(game);
					endLabel.setVisible(true);

					t.stop();
				}
			}

			//����
			for(Coin c : CRemove)
				listC.remove(c);
			for(Heart h : HRemove)
				listH.remove(h);
			for(Mushroom m : MRemove)
				listM.remove(m);
			for(Monster_m mo : MORemove)
				listMO.remove(mo);
			for(Turtle t : TRemove)
				listT.remove(t);
			for(YTurtle yt : TYRemove)
				listTY.remove(yt);
			for(Attack_m ma : AMRemove)
				listAM.remove(ma);
			for(Attack_t at : ATRemove)
				listAT.remove(at);
			for(Plant p : PRemove)
				listP.remove(p);
			for(King k : KRemove)
				listK.remove(k);
			for(Fire f : FRemove)
				listF.remove(f);
			for(Star s : SRemove)
				listS.remove(s);

			//score�� level ���ΰ�ħ
			scoreLabel.setText("SCORE: " + score);
			levelLabel.setText("LEVEL: " +level);

			requestFocus();
			setFocusable(true);

		}


		@Override
		public void keyTyped(KeyEvent e) {}

		
		@Override
		public void keyPressed(KeyEvent e) {
			int keycode = e.getKeyCode();

			if(keycode == KeyEvent.VK_SPACE && jump == false) {
				jump();
				mario.setIcon(m_rightJump);
				new Thread(new Runnable() {

					//������ ����
					@Override 
					public void run() {
						if(fall == false) {
							jump = true;

							int set = 15;

							while (set > 0){
								set--;
								mario.jump(set);

								try {
									Thread.sleep(10);
								}catch(InterruptedException e) {
									e.printStackTrace();
								}
							}

							int under = mario.getY()+35;

							while(under<430) {
								set++;
								if(under + set > 430) {
									mario.setY(398);
									break;
								}

								else if(mario.x+10>=440&&mario.x<=550&&mario.y+35>=350) {
									mario.setY(315);
									break;
								}
								else if(mario.x+10>=730&&mario.x<=876&&mario.y+35>=350) {
									mario.setY(315);
									break;
								}
								else if(mario.x+10>=1045&&mario.x<=1175&&mario.y+35>=350) {
									mario.setY(315);
									break;
								}

								mario.fall(set);
								under = mario.getY()+35;
								try {
									Thread.sleep(10);
								}catch(InterruptedException e) {
									e.printStackTrace();
								}
							}

							jump = false;
							fall = false;
							mario.setIcon(m_right);
						}
					}

				}).start();
			}

			else if(keycode == KeyEvent.VK_RIGHT) {
				right_ma = true;
				move_screen = true;
				left_ma = false;
				right_runma = true;
			}

			else if(keycode == KeyEvent.VK_LEFT) {
				right_ma = false;
				move_screen = true;
				left_ma = true;
				left_runma = true;
			}

			else if(keycode == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}

			//����
			else if(keycode == KeyEvent.VK_A) {
				at = true;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int keycode = e.getKeyCode();

			if(keycode == KeyEvent.VK_SPACE) {
				up_ma = false;
				mario.setIcon(m_rightJump);

			}
			if(keycode == KeyEvent.VK_RIGHT) {
				right_ma = false;
				move_screen = false;
				right_runma = false;
				right_ma = true;

			}
			if(keycode == KeyEvent.VK_LEFT) {
				left_ma = false;
				move_screen = false;
				left_runma = false;
				left_ma = true;
			}
			if(keycode == KeyEvent.VK_A) {
				at = false;
			}
			
			//���� �������� �Ѿ�� ����
			if(keycode == KeyEvent.VK_ENTER&&howButtonb) {

				game.setIcon(gameImage);
				dp.add(game);
				start.setVisible(false);

				mario.setIcon(m_right);
				mario.setBounds(240, 405, 20, 35);
				mario.setVisible(true);

				for(int i = 0; i<INDEX; i++) {
					listH.add(new Heart(i));
				}
				
				t.start();
			}
		}
	}

	public static void main(String[] args) {
		new MarioGame();
	}
	
	//����
	class Heart{
		int index;

		Heart(int i){
			index = i;
		}
		public void drawH(Graphics g) {
			g.drawImage(heart, 980+index*30, 5,27,21,null);
		}
	}

	//�Ĺ�
	class Plant{
		int x;
		int y;
		int w;
		int h;

		Plant(int x, int y){
			this.x = x;
			this.y = y;
			w = 24;
			h = 32;
		}
		public void drawP(Graphics g) {
			g.drawImage(plant,this.x, this.y, w, h, null);
		}
		public int getX() {
			return this.x;
		}
		public int getY() {
			return this.y;
		}
		public double distanceP(int x, int y) {
			return Math.sqrt(Math.pow((this.x+w/2) - x, 2) + Math.pow((this.y+h/2) - y, 2));
		}
	}
	
	//��������
	class Mushroom {

		int mX;
		int mY;
		int w=21;
		int h=28;

		Mushroom(int x, int y){
			mX = x;
			mY = y;
		}
		public void drawM(Graphics g) {
			g.drawImage(mushroom, mX, mY, w, h, null);
		}
		public void rightM() {
			mX += moveXM;
		}
		public void leftM() {
			mX -= moveXM;
		}
		public int getX() {
			return mX;
		}
		public int getY() {
			return mY;
		}
		public double distanceM(int x, int y) {
			return Math.sqrt(Math.pow((mX+w/2) - x, 2) + Math.pow((mY+h/2) - y, 2));
		}
	}

	//��������(����)
	class Monster_m {

		int moX;
		int moY;
		int w=25;
		int h=25;
		int moXM = 2;

		Monster_m(int x, int y){
			moX = x;
			moY = y;
		}
		public void drawMO(Graphics g) {
			g.drawImage(monster_m, moX, moY, w, h, null);	
		}
		public void rightM() {
			moX += moXM;
		}
		public void leftM() {
			moX -= moXM;
		}
		public int getX() {
			return moX;
		}
		public int getY() {
			return moY;
		}
		public double distanceMO(int x, int y) {
			return Math.sqrt(Math.pow((moX+w/2) - x, 2) + Math.pow((moY+h/2) - y, 2));
		}
	}

	//�ʷϰź���
	class Turtle {

		int tX;
		int tY;
		int tw=36;
		int th=30;
		int hp=10;
		int moveXT = 2;

		Turtle(int tosx, int tosy){
			tX = tosx;
			tY = tosy;
		}
		public void drawT(Graphics g, Image i) {
			g.drawImage(i, tX, tY, tw, th, null);
		}
		public void rightM() {
			tX += moveXT;
		}
		public void leftM() {
			tX -= moveXT;
		}
		public int getX() {
			return tX;
		}
		public int getY() {
			return tY;
		}
		public void hp() {
			hp--;
		}
		public double distanceT(int x, int y) {
			return Math.sqrt(Math.pow((tX+tw/2) - x, 2) + Math.pow((tY+th/2) - y, 2));
		}

	}

	//����ź���
	class YTurtle {

		int tX;
		int tY;
		int tw=36;
		int th=30;
		int hp=20;
		int moveXT = 2;

		YTurtle(int tosx, int tosy){
			tX = tosx;
			tY = tosy;
		}
		public void drawTY(Graphics g, Image i) {
			g.drawImage(i, tX, tY, tw, th, null);
		}
		public void rightM() {
			tX += moveXT;
		}
		public void leftM() {
			tX -= moveXT;
		}
		public int getX() {
			return tX;
		}
		public int getY() {
			return tY;
		}
		public void hp() {
			hp--;
		}
		public double distanceT(int x, int y) {
			return Math.sqrt(Math.pow((tX+tw/2) - x, 2) + Math.pow((tY+th/2) - y, 2));
		}

	}

	//����ź��̰� �ϴ� ����
	class Attack_t{
		int aX;
		int aY;
		int w;
		int h;
		boolean rightk;
		boolean leftk;

		Attack_t(int x, int y, boolean at){
			aX = x;
			aY = y;
			w = 16;
			h = 16;
			if(at== true)
				rightk = true;
			if(at==false)
				leftk = true;
		}
		public void moveAT() {
			if(rightk)
				aX += 5;
			else if(leftk)
				aX -= 5;
		}
		public void drawAT(Graphics g) { //����Ⱑ �����Ҷ�
			g.drawImage(attack_t, this.aX, this.aY,this.w,this.h,null);
		}
		public int getY() {
			return aY;
		}
		public int getX() {
			return aX;
		}
		public double distanceAT(int x, int y) {
			return Math.sqrt(Math.pow((aX+w/2) - x, 2) + Math.pow((aY+h/2) - y, 2));
		}
	}

	//����
	class Coin{
		int cX;
		int cY;
		int w = 11;
		int h = 16;

		Coin(int posX, int posY){
			cX = posX;
			cY = posY;
			w = 11;
			h = 16;
		}
		public void drawC(Graphics g) {
			g.drawImage(coin, cX, cY, w, h, null);
		}
		public double distanceC(int x, int y) {
			return Math.sqrt(Math.pow((cX+w/2) - x, 2) + Math.pow((cY+h/2) - y, 2));
		}
	}

	//��������
	class Die extends JLabel{
		int x;
		int y;
		int w=30;
		int h=30;

		Die( int inputX,int inputY){
			x = inputX;
			y = inputY+5;
		}
		public void drawH(Graphics g) {
			g.drawImage(heart, this.x, this.y,w,h,null);
		}
	}

	//������
	class Mario extends JLabel {
		int x;
		int y;
		int w;
		int h;
		int moveX = 3;
		int moveY = 3;

		Mario(int posX, int posY){
			x = posX;
			y = posY;
			w = MARIO_WIDTH;
			h = MARIO_HEIGHT;

			super.setIcon(m_right);
		}
		public void moveUP() {
			y-= moveY;
		}
		public void moveDOWN() {
			y += moveY;
		}
		public void moveRIGHT() {
			x += moveX;
		}
		public void moveLEFT() {
			x -= moveX;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public int setY(int i) {
			return y = i;
		}
		public void fall(int y) {
			this.y += y;
		}
		public void jump(int x) {
			this.y -= x;
		}
	}


	//�������� �ϴ� ����
	class Attack_m{
		int pX;
		int pY;
		int w;
		int h;
		boolean right;
		boolean left;

		Attack_m(int x, int y, boolean b){
			pX = x;
			pY = y;
			w = 16;
			h = 16;
			if(b == true)
				right = true;
			else if(b== false)
				left = true;
		}
		public void moveA() {
			if(right)
				pX += 10;
			else if(left)
				pX -= 10;
		}
		public void drawAM(Graphics g) { //����Ⱑ �����Ҷ�
			g.drawImage(attack_m, this.pX, this.pY,this.w,this.h,null);
		}
		public int getY() {
			return pY;
		}
		public int getX() {
			return pX;
		}
	}

	//�÷��̾� �̸�
	class players{
		String name;

		players(String player_name){
			this.name = player_name;
		}

		public String name() {
			return this.name;
		}
	}

	//����
	class King{
		int kX;
		int kY;
		int kw= 50;
		int kh= 50;
		int khp= 50;
		int moveXK = 3;


		King(int tosx, int tosy){
			kX = tosx;
			kY = tosy;
		}
		public void drawK(Graphics g, Image i) {
			g.drawImage(i, kX, kY, kw, kh, null);
		}
		public void rightK() {
			kX += moveXK;
		}
		public void leftK() {
			kX -= moveXK;
		}
		public int getX() {
			return kX;
		}
		public int getY() {
			return kY;
		}
		public void khp() {
			khp-= 1;
		}
		public double distanceK(int x, int y) {
			return Math.sqrt(Math.pow((kX+kw/2) - x, 2) + Math.pow((kY+kh/2) - y, 2));
		}
	}

	//���İ� �ϴ� ����
	class Fire{
		int pX;
		int pY;
		int w;
		int h;
		boolean rightk;
		boolean leftk;

		Fire(int x, int y, boolean f){
			pX = x;
			pY = y;
			w = 24;
			h = 10;
			if(f== true)
				rightk = true;
			if(f==false)
				leftk = true;
		}
		public void moveF() {
			if(rightk)
				pX += 5;
			else if(leftk)
				pX -= 5;
		}
		public void drawF(Graphics g) { //����Ⱑ �����Ҷ�
			g.drawImage(fire, this.pX, this.pY,this.w,this.h,null);
		}
		public int getY() {
			return pY;
		}
		public int getX() {
			return pX;
		}
		public double distanceF(int x, int y) {
			return Math.sqrt(Math.pow((pX+w/2) - x, 2) + Math.pow((pY+h/2) - y, 2));
		}
	}
	
	//��
	class Star{
		int sX;
		int sY;
		int w;
		int h;

		Star(int posX, int posY){
			sX = posX;
			sY = posY;
			w = 60;
			h = 60;
		}
		public void drawS(Graphics g) {
			g.drawImage(star, sX, sY, w, h, null);
		}
		public double distanceS(int x, int y) {
			return Math.sqrt(Math.pow((sX+w/2) - x, 2) + Math.pow((sY+h/2) - y, 2));
		}
	}
}
