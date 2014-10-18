package monkMapCreator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import resources.ResourceLoader;


public class Main extends JFrame{

	private static final long serialVersionUID = 1L;
	
	static final int CANVAS_WIDTH = 800;    // width and height of the game screen
	static final int CANVAS_HEIGHT = 800;
	static final String VERSION = "v0.2.2";
	
	private GameCanvas canvas;
	private MenuListener ml;
	private SaveLoad sl;
	private DrawListener dl;
	private ButtonListener bl;
	private MapItem map;
	private SheetGrabber sg1;
	
	public Tile TILE_GRASS_BASIC, TILE_GRASS_FENCE_VERT, TILE_GRASS_FENCE_HORZ, TILE_GRASS_FENCE_TLC, TILE_GRASS_FENCE_TRC, 
	TILE_GRASS_FENCE_BLC, TILE_GRASS_FENCE_BRC, TILE_GRASS_SIGN, TILE_GRASS_FENCE_VERT_BLACK, TILE_GRASS_FENCE_HORZ_BLACK,
	TILE_GRASS_FENCE_TLC_BLACK, TILE_GRASS_FENCE_TRC_BLACK, TILE_GRASS_FENCE_BLC_BLACK, TILE_GRASS_FENCE_BRC_BLACK,
	TILE_GRASS_TOMB_JB, TILE_GRASS_TOMB_TY, TILE_GRASS_TOMB_FR, TILE_GRASS_TOMB_SQ, TILE_GRASS_BENCH_LEFT_DOWN, TILE_GRASS_BENCH_RIGHT_DOWN,
	TILE_GRASS_BENCH_LEFT_UP, TILE_GRASS_BENCH_RIGHT_UP,
	TILE_HOUSE_PURPLE, TILE_DOOR_HOUSE_PURPLE, TILE_HOUSE_ORANGE, TILE_DOOR_HOUSE_ORANGE, TILE_HOUSE_RED, TILE_DOOR_HOUSE_RED, 
	TILE_HOUSE_BLUE, TILE_DOOR_HOUSE_BLUE, TILE_HOUSE_WHITE, TILE_DOOR_HOUSE_WHITE, TILE_HOUSE_RED_PAT_GREY, TILE_DOOR_HOUSE_RED_PAT_GREY,
	TILE_HOUSE_BLACK_PAT_GREY, TILE_DOOR_HOUSE_BLACK_PAT_GREY,TILE_HOUSE_BROWN_PAT_MUST, TILE_DOOR_HOUSE_BROWN_PAT_MUST, TILE_HOUSE_SAND,
	TILE_DOOR_HOUSE_SAND_UP, TILE_DOOR_HOUSE_SAND_LEFT, TILE_HOUSE_LBLUE, TILE_DOOR_HOUSE_LBLUE_DOWN, TILE_DOOR_HOUSE_LBLUE_LEFT, 
	TILE_HOUSE_GOLD, TILE_DOOR_HOUSE_GOLD, TILE_HOUSE_GREY_PAT_BLUE, TILE_DOOR_HOUSE_GREY_PAT_BLUE, TILE_HOUSE_BLUE_PAT_PINK,
	TILE_DOOR_HOUSE_BLUE_PAT_PINK, TILE_HOUSE_WHITE_PAT_BLUE, TILE_DOOR_HOUSE_WHITE_PAT_BLUE_LEFT, TILE_DOOR_HOUSE_WHITE_PAT_BLUE_RIGHT, 
	TILE_HOUSE_WHITE_PAT_ORANGE, TILE_DOOR_HOUSE_WHITE_PAT_ORANGE_UP, TILE_DOOR_HOUSE_WHITE_PAT_ORANGE_DOWN, TILE_PATH_GREYRED, TILE_PATH_DIRT,
	TILE_BLANK, TILE_TREE_LEFT_CENT, TILE_TREE_LEFT_TOP, TILE_TREE_CENT_TOP, TILE_TREE_RIGHT_TOP, TILE_TREE_RIGHT_CENT, TILE_TREE_RIGHT_BTM,
	TILE_TREE_CENT_BTM, TILE_TREE_LEFT_BTM, TILE_TREE_CENT_CENT, TILE_HOUSE_BLACK_PAT_BROWN, TILE_DOOR_HOUSE_BLACK_PAT_BROWN, 
	TILE_DOOR_HOUSE_MINE, TILE_WATER_BASIC;
	
	public Tile  TILE_IN_YELLOW_PAT_CREME, TILE_IN_YELLOW_PAT_CREME_FMAT, TILE_IN_YELLOW_PAT_CREME_DESK, TILE_IN_YELLOW_PAT_CREME_BENCH_LEFT_UP,
	TILE_IN_YELLOW_PAT_CREME_BENCH_RIGHT_UP, TILE_IN_YELLOW_PAT_CREME_BENCH_LEFT_DOWN, TILE_IN_YELLOW_PAT_CREME_BENCH_RIGHT_DOWN,
	TILE_IN_GREEN_CARP, TILE_IN_GREEN_CARP_BED_TOP, TILE_IN_GREEN_CARP_BED_BTM, TILE_IN_GREEN_CARP_CHAIR_DOWN, TILE_IN_GREEN_CARP_CHAIR_UP, TILE_IN_GREEN_CARP_TTL, TILE_IN_GREEN_CARP_TTR,
	TILE_IN_GREEN_CARP_TBL, TILE_IN_GREEN_CARP_TBR,
	TILE_IN_WHITE_PAT_PINK_MARB, TILE_IN_WHITE_PAT_PINK_MARB_BED_TOP, TILE_IN_WHITE_PAT_PINK_MARB_BED_BTM, TILE_IN_WHITE_PAT_PINK_MARB_DESK, TILE_IN_WHITE_PAT_PINK_MARB_COUCH_TOP,
	TILE_IN_WHITE_PAT_PINK_MARB_COUCH_BTM, TILE_IN_BLACK_STAIR_TL_U, TILE_IN_BLACK_STAIR_TR_U, TILE_IN_BLACK_STAIR_BL_U, TILE_IN_BLACK_STAIR_BR_U,
	TILE_IN_BLACK_STAIR_TL_D, TILE_IN_BLACK_STAIR_TR_D, TILE_IN_BLACK_STAIR_BL_D, TILE_IN_BLACK_STAIR_BR_D,
	TILE_IN_WOOD_RED, TILE_IN_WOOD_RED_BAR, TILE_IN_WOOD_RED_STOOL, TILE_IN_WOOD_RED_BTL, TILE_IN_WOOD_RED_TT, TILE_IN_WOOD_RED_BTR, TILE_IN_WOOD_RED_BBL, 
	TILE_IN_WOOD_RED_TB, TILE_IN_WOOD_RED_BBR, TILE_IN_MAGENTA_STAGE, TILE_IN_MAGENTA_STAGE_POLE,
	TILE_OUT_BUS, TILE_OUT_DOOR_BUS, TILE_OUT_HOUSE_ORANGE, TILE_OUT_HOUSE_ORANGE_DOOR_UP, TILE_OUT_HOUSE_ORANGE_DOOR_DOWN,
	TILE_OUT_HOTEL, TILE_OUT_HOTEL_DOOR,
	TILE_OUT_CLUB, TILE_OUT_CLUB_DOOR,
	TILE_GRASS_BENCH_LEFT_TOP, TILE_GRASS_BENCH_LEFT_BTM,
	TILE_STREET_BASIC, TILE_STREET_LEFT, TILE_STREET_RIGHT, TILE_STREET_CENT, TILE_PATH_GREY_PAT_RED;
	
	
	
	public Tile[] tileArray = new Tile[1100];
	public Tile pen = TILE_BLANK;
	public static enum Mode {
	      SINGLE, MULTIPLE
	   }
	public static Mode mode;
	
	private int startSel = 0, endSel = 0;
	
	private boolean changesMade = false;
	
	
	public Main(){
		mode = Mode.SINGLE; 
		canvas = new GameCanvas();
		ml = new MenuListener();
		sl = new SaveLoad();
		dl = new DrawListener();
		bl = new ButtonListener();
		setSquares();
		setInsideSquares();
		map = new MapItem(TILE_BLANK);
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		this.setContentPane(canvas);
		// Other UI components such as button, score board, if any.
		// ......
		//this.setIconImage();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		//do most things after packing it
		this.setLocationRelativeTo(null);
		this.setTitle("Map Creator " + VERSION);
		this.setVisible(true);
		this.initMenuBar();
		String filePathString = System.getProperty("user.home")+"/maps/";
		File f = new File(filePathString);
		if(!f.exists()) {  
			File d = new File(System.getProperty("user.home")+"/maps/");
			d.mkdirs();
			System.out.println("Dir made");
		}
		
		
	}
	
	//gets all images from tiles sheet and stores them into variable
	public void setSquares(){
		BufferedImage tiles = null;
		ResourceLoader rl =  new ResourceLoader();
		try{
			tiles = rl.getSquares("squareSheet.png");
		}catch(IOException ex){
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		sg1 = new SheetGrabber(tiles);

		tileArray[0] = TILE_GRASS_BASIC = new Tile(false, false, 0, 0, sg1);
		tileArray[1] = TILE_GRASS_FENCE_VERT = new Tile(true, false, 20,0, sg1);
		tileArray[2] = TILE_GRASS_FENCE_HORZ = new Tile(true, false, 40,0, sg1); 
		tileArray[3] = TILE_GRASS_FENCE_TLC = new Tile(true, false, 60, 0, sg1);
		tileArray[4] = TILE_GRASS_FENCE_TRC = new Tile(true, false, 80, 0, sg1);
		tileArray[5] = TILE_GRASS_FENCE_BLC = new Tile(true, false,100, 0, sg1);
		tileArray[6] = TILE_GRASS_FENCE_BRC = new Tile(true, false, 120, 0, sg1);
		tileArray[7] = TILE_GRASS_SIGN = new Tile(true, false, true, 140, 0, sg1);
		tileArray[8] = TILE_GRASS_FENCE_VERT_BLACK = new Tile(true, false, 160, 0, sg1);
		tileArray[9] = TILE_GRASS_FENCE_HORZ_BLACK = new Tile(true, false,180, 0, sg1); 
		tileArray[10] = TILE_GRASS_FENCE_TLC_BLACK = new Tile(true, false, 200, 0, sg1);
		tileArray[11] = TILE_GRASS_FENCE_TRC_BLACK = new Tile(true, false, 220, 0, sg1);
		tileArray[12] = TILE_GRASS_FENCE_BLC_BLACK = new Tile(true, false, 240, 0, sg1);
		tileArray[13] = TILE_GRASS_FENCE_BRC_BLACK = new Tile(true, false,260, 0, sg1);
		tileArray[14] = TILE_GRASS_TOMB_JB = new Tile(true, false, 280, 0, sg1);
		tileArray[15] = TILE_GRASS_TOMB_TY = new Tile(true, false, 300, 0, sg1);
		tileArray[16] = TILE_GRASS_TOMB_FR = new Tile(true, false, 320, 0, sg1);
		tileArray[17] = TILE_GRASS_TOMB_SQ = new Tile(true, false, 340, 0, sg1);
		tileArray[18] = TILE_GRASS_BENCH_LEFT_DOWN = new Tile(true, false, 360, 0, sg1);
		tileArray[19] = TILE_GRASS_BENCH_RIGHT_DOWN = new Tile(true, false, 380, 0, sg1);
		tileArray[20] = TILE_GRASS_BENCH_LEFT_UP = new Tile(true, false, 400, 0, sg1);
		tileArray[21] = TILE_GRASS_BENCH_RIGHT_UP = new Tile(true, false, 420, 0, sg1);
		tileArray[22] = TILE_HOUSE_PURPLE = new Tile(true, false,0, 20, sg1);
		tileArray[23] = TILE_DOOR_HOUSE_PURPLE = new Tile(true, true, 20, 20, sg1);
		tileArray[24] = TILE_HOUSE_ORANGE = new Tile(true, false, 40, 20, sg1);
		tileArray[25] = TILE_DOOR_HOUSE_ORANGE = new Tile(true, true, 60, 20, sg1);
		tileArray[26] = TILE_HOUSE_RED = new Tile(true, false,80, 20, sg1);
		tileArray[27] = TILE_DOOR_HOUSE_RED = new Tile(true, true, 100, 20, sg1);
		tileArray[28] = TILE_HOUSE_BLUE = new Tile(true, false, 120, 20, sg1);
		tileArray[29] = TILE_DOOR_HOUSE_BLUE = new Tile(true, true, 140, 20, sg1);
		tileArray[30] = TILE_HOUSE_WHITE = new Tile(true, false, 160, 20, sg1);
		tileArray[31] = TILE_DOOR_HOUSE_WHITE = new Tile(true, true, 180, 20, sg1);
		tileArray[32] = TILE_HOUSE_RED_PAT_GREY = new Tile(true, false, 200, 20, sg1);
		tileArray[33] = TILE_DOOR_HOUSE_RED_PAT_GREY = new Tile(true, true, 220, 20, sg1);
		tileArray[34] = TILE_HOUSE_BLACK_PAT_GREY = new Tile(true, false, 240, 20, sg1);
		tileArray[35] = TILE_DOOR_HOUSE_BLACK_PAT_GREY = new Tile(true, true, 260, 20, sg1);
		tileArray[36] = TILE_HOUSE_BROWN_PAT_MUST = new Tile(true, false, 280, 20, sg1);
		tileArray[37] = TILE_DOOR_HOUSE_BROWN_PAT_MUST = new Tile(true, true, 300, 20, sg1);
		tileArray[38] = TILE_HOUSE_SAND = new Tile(true, false, 320, 20, sg1);
		tileArray[39] = TILE_DOOR_HOUSE_SAND_UP = new Tile(true, true, 340, 20, sg1);
		tileArray[40] = TILE_DOOR_HOUSE_SAND_LEFT = new Tile(true, true, 360, 20, sg1);
		tileArray[41] = TILE_HOUSE_LBLUE = new Tile(true, false, 380, 20, sg1);
		tileArray[42] = TILE_DOOR_HOUSE_LBLUE_DOWN = new Tile(true, true, 400, 20, sg1);
		tileArray[43] = TILE_DOOR_HOUSE_LBLUE_LEFT = new Tile(true, true, 420, 20, sg1);
		tileArray[44] = TILE_HOUSE_GOLD = new Tile(true, false, 440, 20, sg1);
		tileArray[45] = TILE_DOOR_HOUSE_GOLD = new Tile(true, true, 460, 20, sg1);
		tileArray[46] = TILE_HOUSE_GREY_PAT_BLUE = new Tile(true, false, 480, 20, sg1);
		tileArray[47] = TILE_DOOR_HOUSE_GREY_PAT_BLUE = new Tile(true, true, 500, 20, sg1);
		tileArray[48] = TILE_HOUSE_BLUE_PAT_PINK = new Tile(true, false, 520, 20, sg1);
		tileArray[49] = TILE_DOOR_HOUSE_BLUE_PAT_PINK = new Tile(true, true, 540, 20, sg1);
		tileArray[50] = TILE_HOUSE_WHITE_PAT_BLUE = new Tile(true, false, 560, 20, sg1);
		tileArray[51] = TILE_DOOR_HOUSE_WHITE_PAT_BLUE_LEFT = new Tile(true, true, 580, 20, sg1);
		tileArray[52] = TILE_DOOR_HOUSE_WHITE_PAT_BLUE_RIGHT = new Tile(true, true, 600, 20, sg1);
		tileArray[53] = TILE_HOUSE_WHITE_PAT_ORANGE = new Tile(true, false, 620, 20, sg1);
		tileArray[54] = TILE_DOOR_HOUSE_WHITE_PAT_ORANGE_UP = new Tile(true, true, 640, 20, sg1);
		tileArray[55] = TILE_DOOR_HOUSE_WHITE_PAT_ORANGE_DOWN = new Tile(true, true, 660, 20, sg1);
		tileArray[56] = TILE_PATH_GREYRED = new Tile(false, false, 0, 40, sg1);
		tileArray[57] = TILE_PATH_DIRT = new Tile(false, false, 20, 40, sg1);
		tileArray[58] = TILE_BLANK = new Tile(false, false, 0, 60, sg1);
		tileArray[59] = TILE_TREE_LEFT_CENT = new Tile(false, false, 20, 60, sg1);
		tileArray[60] = TILE_TREE_LEFT_TOP = new Tile(false, false, 40, 60, sg1);
		tileArray[61] = TILE_TREE_CENT_TOP = new Tile(false, false, 60, 60, sg1);
		tileArray[62] = TILE_TREE_RIGHT_TOP = new Tile(false, false, 80, 60, sg1);
		tileArray[63] = TILE_TREE_RIGHT_CENT = new Tile(false, false, 100, 60, sg1);
		tileArray[64] = TILE_TREE_RIGHT_BTM = new Tile(false, false, 120, 60, sg1);
		tileArray[65] = TILE_TREE_CENT_BTM = new Tile(false, false, 140, 60, sg1);
		tileArray[66] = TILE_TREE_LEFT_BTM = new Tile(false, false, 160, 60, sg1);
		tileArray[67] = TILE_TREE_CENT_CENT = new Tile(true, false, 180, 60, sg1);
		tileArray[68] = TILE_HOUSE_BLACK_PAT_BROWN = new Tile(true, false, 200, 60, sg1);
		tileArray[69] = TILE_DOOR_HOUSE_BLACK_PAT_BROWN = new Tile(true, true, 220, 60, sg1);
		tileArray[70] = TILE_DOOR_HOUSE_MINE = new Tile(true, true, 240, 60, sg1);
		tileArray[71] = TILE_WATER_BASIC = new Tile(true, false, 260, 60, sg1);
		
		
	}
	
	public void setInsideSquares(){	
		BufferedImage tiles = null;
		ResourceLoader rl =  new ResourceLoader();
		try{
			tiles = rl.getSquares("squareSheet.png");
		}catch(IOException ex){
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		SheetGrabber sg1 = new SheetGrabber(tiles);
		int loc = 1000;
		tileArray[loc++] = TILE_GRASS_BENCH_LEFT_TOP = new Tile(true, false, 440,0, sg1,1000);
		tileArray[loc++] = TILE_GRASS_BENCH_LEFT_BTM = new Tile(true, false, 460,0, sg1,1001);
		tileArray[loc++] = TILE_OUT_BUS = new Tile(true, false, 200,20, sg1,1002);
		tileArray[loc++] = TILE_OUT_DOOR_BUS = new Tile(true, true, 220,20, sg1,1003);
		tileArray[loc++] = TILE_OUT_HOTEL = new Tile(true, false, 520,20, sg1,1004);
		tileArray[loc++] = TILE_OUT_HOTEL_DOOR = new Tile(true, true, 540,20, sg1,1005);
		tileArray[loc++] = TILE_OUT_HOUSE_ORANGE = new Tile(true, false, 620,20, sg1,1006);
		tileArray[loc++] = TILE_OUT_HOUSE_ORANGE_DOOR_UP = new Tile(true, true, 640,20, sg1,1007);
		tileArray[loc++] = TILE_OUT_HOUSE_ORANGE_DOOR_DOWN = new Tile(true, true, 660,20, sg1,1008);
		tileArray[loc++] = TILE_OUT_CLUB = new Tile(true, false, 240,20, sg1,1009);
		tileArray[loc++] = TILE_OUT_CLUB_DOOR = new Tile(true, true, 260,20, sg1,1010);
		tileArray[loc++] = TILE_PATH_GREY_PAT_RED = new Tile(false, false, 0,40, sg1,1011);
		tileArray[loc++] = TILE_STREET_BASIC = new Tile(false, false, 40,40, sg1,1012);
		tileArray[loc++] = TILE_STREET_LEFT = new Tile(false, false, 60,40, sg1,1013);
		tileArray[loc++] = TILE_STREET_CENT = new Tile(false, false, 80,40, sg1,1014);
		tileArray[loc++] = TILE_STREET_RIGHT = new Tile(false, false, 100,40, sg1,1015);
		tileArray[loc++] = TILE_IN_YELLOW_PAT_CREME = new Tile(false, false, 0,80, sg1,1016);
		tileArray[loc++] = TILE_IN_YELLOW_PAT_CREME_FMAT = new Tile(false, false, 20,80, sg1,1017);
		tileArray[loc++] = TILE_IN_YELLOW_PAT_CREME_DESK = new Tile(true, false, 40,80, sg1,1018);
		tileArray[loc++] = TILE_IN_YELLOW_PAT_CREME_BENCH_LEFT_UP = new Tile(true, false, 60,80, sg1,1019);
		tileArray[loc++] = TILE_IN_YELLOW_PAT_CREME_BENCH_RIGHT_UP = new Tile(true, false, 80,80, sg1,1020);
		tileArray[loc++] = TILE_IN_YELLOW_PAT_CREME_BENCH_LEFT_DOWN = new Tile(true, false, 100,80, sg1,1021);
		tileArray[loc++] = TILE_IN_YELLOW_PAT_CREME_BENCH_RIGHT_DOWN = new Tile(true, false, 120,80, sg1,1022);
		tileArray[loc++] = TILE_IN_GREEN_CARP = new Tile(false, false, 140,80, sg1,1023);
		tileArray[loc++] = TILE_IN_GREEN_CARP_BED_TOP = new Tile(true, false, 160,80, sg1,1024);
		tileArray[loc++] = TILE_IN_GREEN_CARP_BED_BTM = new Tile(true, false, 180,80, sg1,1025);
		tileArray[loc++] = TILE_IN_GREEN_CARP_CHAIR_DOWN = new Tile(true, false, 200,80, sg1,1026);
		tileArray[loc++] = TILE_IN_GREEN_CARP_TTL = new Tile(true, false, 220,80, sg1,1027);
		tileArray[loc++] = TILE_IN_GREEN_CARP_TTR = new Tile(true, false, 240,80, sg1,1028);
		tileArray[loc++] = TILE_IN_GREEN_CARP_TBL = new Tile(true, false, 260,80, sg1,1029);
		tileArray[loc++] = TILE_IN_GREEN_CARP_TBR = new Tile(true, false, 280,80, sg1,1030);
		tileArray[loc++] = TILE_IN_GREEN_CARP_CHAIR_UP = new Tile(true, false, 300,80, sg1,1031);
		tileArray[loc++] = TILE_IN_WHITE_PAT_PINK_MARB = new Tile(false, false, 320,80, sg1,1032);
		tileArray[loc++] = TILE_IN_WHITE_PAT_PINK_MARB_BED_TOP = new Tile(true, false, 340,80, sg1,1033);
		tileArray[loc++] = TILE_IN_WHITE_PAT_PINK_MARB_BED_BTM = new Tile(true, false, 360,80, sg1,1034);
		tileArray[loc++] = TILE_IN_WHITE_PAT_PINK_MARB_DESK = new Tile(true, false, 380,80, sg1,1035);
		tileArray[loc++] = TILE_IN_WHITE_PAT_PINK_MARB_COUCH_TOP = new Tile(true, false, 400,80, sg1,1036);
		tileArray[loc++] = TILE_IN_WHITE_PAT_PINK_MARB_COUCH_BTM = new Tile(true, false, 420,80, sg1,1037);
		tileArray[loc++] = TILE_IN_BLACK_STAIR_TL_U = new Tile(false, false, 440,80, sg1,1038);
		tileArray[loc++] = TILE_IN_BLACK_STAIR_TR_U = new Tile(false, false, 460,80, sg1,1039);
		tileArray[loc++] = TILE_IN_BLACK_STAIR_BL_U = new Tile(false, false, 480,80, sg1,1040);
		tileArray[loc++] = TILE_IN_BLACK_STAIR_BR_U = new Tile(false, false, 500,80, sg1,1041);
		tileArray[loc++] = TILE_IN_BLACK_STAIR_TL_D = new Tile(false, false, 520,80, sg1,1042);
		tileArray[loc++] = TILE_IN_BLACK_STAIR_TR_D = new Tile(false, false, 540,80, sg1,1043);
		tileArray[loc++] = TILE_IN_BLACK_STAIR_BL_D = new Tile(false, false, 560,80, sg1,1044);
		tileArray[loc++] = TILE_IN_BLACK_STAIR_BR_D = new Tile(false, false, 580,80, sg1,1045);
		tileArray[loc++] = TILE_IN_WOOD_RED = new Tile(false, false, 600,80, sg1,1046);
		tileArray[loc++] = TILE_IN_WOOD_RED_BAR = new Tile(true, false, 620,80, sg1,1047);
		tileArray[loc++] = TILE_IN_WOOD_RED_STOOL = new Tile(true, false, 640,80, sg1,1048);
		tileArray[loc++] = TILE_IN_WOOD_RED_BTL = new Tile(true, false, 660,80, sg1,1049);
		tileArray[loc++] = TILE_IN_WOOD_RED_TT = new Tile(true, false, 680,80, sg1,1050);
		tileArray[loc++] = TILE_IN_WOOD_RED_BTR = new Tile(true, false, 700,80, sg1,1051);
		tileArray[loc++] = TILE_IN_WOOD_RED_BBL = new Tile(true, false, 720,80, sg1,1052);
		tileArray[loc++] = TILE_IN_WOOD_RED_TB = new Tile(true, false, 740,80, sg1,1053);
		tileArray[loc++] = TILE_IN_WOOD_RED_BBR = new Tile(true, false, 760,80, sg1,1054);
		tileArray[loc++] = TILE_IN_MAGENTA_STAGE = new Tile(true, false, 780,80, sg1,1055);
		tileArray[loc++] = TILE_IN_MAGENTA_STAGE_POLE = new Tile(true, false, 800,80, sg1,1056);
	}
	
	
	
	
	private void initMenuBar(){
		//Where the GUI is created:
		JMenuBar menuBar;
		JMenu menu,submenu;
		JMenuItem menuItem;

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "File - Save Load Options");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("Save", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "Save Project");
		menuItem.setActionCommand("Save");
		menuItem.addActionListener(ml);
		menu.add(menuItem);

		menuItem = new JMenuItem("Load", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "Load Existing Project");
		menuItem.setActionCommand("Load");
		menuItem.addActionListener(ml);
		menu.add(menuItem);
		
		submenu = new JMenu("Export");
		submenu.setMnemonic(KeyEvent.VK_E);

		menuItem = new JMenuItem("Outside Map");
		menuItem.setActionCommand("Outside");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuItem.addActionListener(ml);
		submenu.add(menuItem);

		menuItem = new JMenuItem("Inside Map");
		menuItem.setActionCommand("Inside");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		menuItem.addActionListener(ml);
		submenu.add(menuItem);
		menu.add(submenu);
		
		this.setJMenuBar(menuBar);
		
		String[] tileStrings = { "GRASS_BASIC", "GRASS_FENCE_VERT", "GRASS_FENCE_HORZ", "GRASS_FENCE_TLC", "GRASS_FENCE_TRC", 
				"GRASS_FENCE_BLC", "GRASS_FENCE_BRC", "GRASS_SIGN", "GRASS_FENCE_VERT_BLACK", "GRASS_FENCE_HORZ_BLACK",
				"GRASS_FENCE_TLC_BLACK", "GRASS_FENCE_TRC_BLACK", "GRASS_FENCE_BLC_BLACK", "GRASS_FENCE_BRC_BLACK",
				"GRASS_TOMB_JB", "GRASS_TOMB_TY", "GRASS_TOMB_FR", "GRASS_TOMB_SQ", "GRASS_BENCH_LEFT_DOWN", "GRASS_BENCH_RIGHT_DOWN",
				"GRASS_BENCH_LEFT_UP", "GRASS_BENCH_RIGHT_UP",
				"HOUSE_PURPLE", "DOOR_HOUSE_PURPLE", "HOUSE_ORANGE", "DOOR_HOUSE_ORANGE", "HOUSE_RED", "DOOR_HOUSE_RED", 
				"HOUSE_BLUE", "DOOR_HOUSE_BLUE", "HOUSE_WHITE", "DOOR_HOUSE_WHITE", "HOUSE_RED_PAT_GREY", "DOOR_HOUSE_RED_PAT_GREY",
				"HOUSE_BLACK_PAT_GREY", "DOOR_HOUSE_BLACK_PAT_GREY","HOUSE_BROWN_PAT_MUST", "DOOR_HOUSE_BROWN_PAT_MUST", "HOUSE_SAND",
				"DOOR_HOUSE_SAND_UP", "DOOR_HOUSE_SAND_LEFT", "HOUSE_LBLUE", "DOOR_HOUSE_LBLUE_DOWN", "DOOR_HOUSE_LBLUE_LEFT", 
				"HOUSE_GOLD", "DOOR_HOUSE_GOLD", "HOUSE_GREY_PAT_BLUE", "DOOR_HOUSE_GREY_PAT_BLUE", "HOUSE_BLUE_PAT_PINK",
				"DOOR_HOUSE_BLUE_PAT_PINK", "HOUSE_WHITE_PAT_BLUE", "DOOR_HOUSE_WHITE_PAT_BLUE_LEFT", "DOOR_HOUSE_WHITE_PAT_BLUE_RIGHT", 
				"HOUSE_WHITE_PAT_ORANGE", "DOOR_HOUSE_WHITE_PAT_ORANGE_UP", "DOOR_HOUSE_WHITE_PAT_ORANGE_DOWN", "PATH_GREYRED", "PATH_DIRT",
				"BLANK", "TREE_LEFT_CENT", "TREE_LEFT_TOP", "TREE_CENT_TOP", "TREE_RIGHT_TOP", "TREE_RIGHT_CENT", "TREE_RIGHT_BTM",
				"TREE_CENT_BTM", "TREE_LEFT_BTM", "TREE_CENT_CENT", "HOUSE_BLACK_PAT_BROWN", "DOOR_HOUSE_BLACK_PAT_BROWN", 
				"DOOR_HOUSE_MINE", "WATER_BASIC", 
				
				//inside ones. start at 1000
				"GRASS_BENCH_LEFT_TOP", "GRASS_BENCH_LEFT_BTM","OUT_BUS","OUT_DOOR_BUS","OUT_HOTEL","OUT_HOTEL_DOOR","OUT_HOUSE_ORANGE",
				"OUT_HOUSE_ORANGE_DOOR_UP","OUT_HOUSE_ORANGE_DOOR_DOWN","OUT_CLUB","OUT_CLUB_DOOR","PATH_GREY_PAT_RED","STREET_BASIC","STREET_LEFT",
				"STREET_CENT","STREET_RIGHT","IN_YELLOW_PAT_CREME","IN_YELLOW_PAT_CREME_FMAT","IN_YELLOW_PAT_CREME_DESK","IN_YELLOW_PAT_CREME_BENCH_LEFT_UP",
				"IN_YELLOW_PAT_CREME_BENCH_RIGHT_UP","IN_YELLOW_PAT_CREME_BENCH_LEFT_DOWN","IN_YELLOW_PAT_CREME_BENCH_RIGHT_DOWN","IN_GREEN_CARP",
				"IN_GREEN_CARP_BED_TOP","IN_GREEN_CARP_BED_BTM","IN_GREEN_CARP_CHAIR_DOWN","IN_GREEN_CARP_TTL","IN_GREEN_CARP_TTR",
				"IN_GREEN_CARP_TBL","IN_GREEN_CARP_TBR","IN_GREEN_CARP_CHAIR_UP","IN_WHITE_PAT_PINK_MARB","IN_WHITE_PAT_PINK_MARB_BED_TOP","IN_WHITE_PAT_PINK_MARB_BED_BTM",
				"IN_WHITE_PAT_PINK_MARB_DESK","IN_WHITE_PAT_PINK_MARB_COUCH_TOP","IN_WHITE_PAT_PINK_MARB_COUCH_BTM","IN_BLACK_STAIR_TL_U","IN_BLACK_STAIR_TR_U",
				"IN_BLACK_STAIR_BL_U","IN_BLACK_STAIR_BR_U","IN_BLACK_STAIR_TL_D","IN_BLACK_STAIR_TR_D","IN_BLACK_STAIR_BL_D","IN_BLACK_STAIR_BR_D",
				"IN_WOOD_RED","IN_WOOD_RED_BAR","IN_WOOD_RED_STOOL","IN_WOOD_RED_BTL","IN_WOOD_RED_TT","IN_WOOD_RED_BTR","IN_WOOD_RED_BBL","IN_WOOD_RED_TB",
				"IN_WOOD_RED_BBR","IN_MAGENTA_STAGE","IN_MAGENTA_STAGE_POLE"};
		
        @SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox list = new JComboBox(tileStrings);
		list.setSelectedIndex(tileStrings.length-1);
		list.addActionListener(dl);
		list.setBounds(10, 620,300,25);
		canvas.add(list);
		pen = tileArray[list.getSelectedIndex()-72+1000];
		
		JButton b1 = new JButton("Multi-Select Mode");
	    b1.setVerticalTextPosition(AbstractButton.CENTER);
	    b1.setHorizontalTextPosition(AbstractButton.LEADING); 
	    b1.setMnemonic(KeyEvent.VK_D);
	    b1.setActionCommand("Multi");
	    b1.addActionListener(bl);
	    b1.setBounds(10, 650, 200, 25);
	    canvas.add(b1);
	    
	    JButton b2 = new JButton("Draw");
	    b2.setVerticalTextPosition(AbstractButton.CENTER);
	    b2.setHorizontalTextPosition(AbstractButton.LEADING); 
	    b2.setMnemonic(KeyEvent.VK_D);
	    b2.setActionCommand("Draw");
	    b2.addActionListener(bl);
	    b2.setBounds(420, 650, 100, 25);
	    canvas.add(b2);
		
	}
	
	public void drawTiles(Graphics2D g2d){
		map.drawTiles(g2d,sg1);
		map.drawTrees(g2d, sg1);
		g2d.setColor(Color.BLACK);
		int x = 0;
		for(int y = 0; y <= 600;y+=20){
			g2d.drawLine(x, y, x+800, y);
		}
		int y = 0;
		for(x = 0; x <= 800; x+=20){
			g2d.drawLine(x, y, x, y+600);
		}
		g2d.drawString("Current Item: ", 500, 640);
		g2d.drawImage(map.getImage(pen.x, pen.y,sg1), 600, 625, null);
		if(mode == Mode.MULTIPLE){
			g2d.drawString("Multi", 220, 670);
			g2d.drawString("Start: " + startSel, 260, 670);
			g2d.drawString("End: " + endSel, 340, 670);
		}else
			g2d.drawString("Single", 220, 670);
	}
	class DrawListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] tileStrings = { "GRASS_BASIC", "GRASS_FENCE_VERT", "GRASS_FENCE_HORZ", "GRASS_FENCE_TLC", "GRASS_FENCE_TRC", 
					"GRASS_FENCE_BLC", "GRASS_FENCE_BRC", "GRASS_SIGN", "GRASS_FENCE_VERT_BLACK", "GRASS_FENCE_HORZ_BLACK",
					"GRASS_FENCE_TLC_BLACK", "GRASS_FENCE_TRC_BLACK", "GRASS_FENCE_BLC_BLACK", "GRASS_FENCE_BRC_BLACK",
					"GRASS_TOMB_JB", "GRASS_TOMB_TY", "GRASS_TOMB_FR", "GRASS_TOMB_SQ", "GRASS_BENCH_LEFT_DOWN", "GRASS_BENCH_RIGHT_DOWN",
					"GRASS_BENCH_LEFT_UP", "GRASS_BENCH_RIGHT_UP",
					"HOUSE_PURPLE", "DOOR_HOUSE_PURPLE", "HOUSE_ORANGE", "DOOR_HOUSE_ORANGE", "HOUSE_RED", "DOOR_HOUSE_RED", 
					"HOUSE_BLUE", "DOOR_HOUSE_BLUE", "HOUSE_WHITE", "DOOR_HOUSE_WHITE", "HOUSE_RED_PAT_GREY", "DOOR_HOUSE_RED_PAT_GREY",
					"HOUSE_BLACK_PAT_GREY", "DOOR_HOUSE_BLACK_PAT_GREY","HOUSE_BROWN_PAT_MUST", "DOOR_HOUSE_BROWN_PAT_MUST", "HOUSE_SAND",
					"DOOR_HOUSE_SAND_UP", "DOOR_HOUSE_SAND_LEFT", "HOUSE_LBLUE", "DOOR_HOUSE_LBLUE_DOWN", "DOOR_HOUSE_LBLUE_LEFT", 
					"HOUSE_GOLD", "DOOR_HOUSE_GOLD", "HOUSE_GREY_PAT_BLUE", "DOOR_HOUSE_GREY_PAT_BLUE", "HOUSE_BLUE_PAT_PINK",
					"DOOR_HOUSE_BLUE_PAT_PINK", "HOUSE_WHITE_PAT_BLUE", "DOOR_HOUSE_WHITE_PAT_BLUE_LEFT", "DOOR_HOUSE_WHITE_PAT_BLUE_RIGHT", 
					"HOUSE_WHITE_PAT_ORANGE", "DOOR_HOUSE_WHITE_PAT_ORANGE_UP", "DOOR_HOUSE_WHITE_PAT_ORANGE_DOWN", "PATH_GREYRED", "PATH_DIRT",
					"BLANK", "TREE_LEFT_CENT", "TREE_LEFT_TOP", "TREE_CENT_TOP", "TREE_RIGHT_TOP", "TREE_RIGHT_CENT", "TREE_RIGHT_BTM",
					"TREE_CENT_BTM", "TREE_LEFT_BTM", "TREE_CENT_CENT", "HOUSE_BLACK_PAT_BROWN", "DOOR_HOUSE_BLACK_PAT_BROWN", 
					"DOOR_HOUSE_MINE", "WATER_BASIC", 
					
					//inside ones. start at 1000
					"GRASS_BENCH_LEFT_TOP", "GRASS_BENCH_LEFT_BTM","OUT_BUS","OUT_DOOR_BUS","OUT_HOTEL","OUT_HOTEL_DOOR","OUT_HOUSE_ORANGE",
					"OUT_HOUSE_ORANGE_DOOR_UP","OUT_HOUSE_ORANGE_DOOR_DOWN","OUT_CLUB","OUT_CLUB_DOOR","PATH_GREY_PAT_RED","STREET_BASIC","STREET_LEFT",
					"STREET_CENT","STREET_RIGHT","IN_YELLOW_PAT_CREME","IN_YELLOW_PAT_CREME_FMAT","IN_YELLOW_PAT_CREME_DESK","IN_YELLOW_PAT_CREME_BENCH_LEFT_UP",
					"IN_YELLOW_PAT_CREME_BENCH_RIGHT_UP","IN_YELLOW_PAT_CREME_BENCH_LEFT_DOWN","IN_YELLOW_PAT_CREME_BENCH_RIGHT_DOWN","IN_GREEN_CARP",
					"IN_GREEN_CARP_BED_TOP","IN_GREEN_CARP_BED_BTM","IN_GREEN_CARP_CHAIR_DOWN","IN_GREEN_CARP_TTL","IN_GREEN_CARP_TTR",
					"IN_GREEN_CARP_TBL","IN_GREEN_CARP_TBR","IN_GREEN_CARP_CHAIR_UP","IN_WHITE_PAT_PINK_MARB","IN_WHITE_PAT_PINK_MARB_BED_TOP","IN_WHITE_PAT_PINK_MARB_BED_BTM",
					"IN_WHITE_PAT_PINK_MARB_DESK","IN_WHITE_PAT_PINK_MARB_COUCH_TOP","IN_WHITE_PAT_PINK_MARB_COUCH_BTM","IN_BLACK_STAIR_TL_U","IN_BLACK_STAIR_TR_U",
					"IN_BLACK_STAIR_BL_U","IN_BLACK_STAIR_BR_U","IN_BLACK_STAIR_TL_D","IN_BLACK_STAIR_TR_D","IN_BLACK_STAIR_BL_D","IN_BLACK_STAIR_BR_D",
					"IN_WOOD_RED","IN_WOOD_RED_BAR","IN_WOOD_RED_STOOL","IN_WOOD_RED_BTL","IN_WOOD_RED_TT","IN_WOOD_RED_BTR","IN_WOOD_RED_BBL","IN_WOOD_RED_TB",
					"IN_WOOD_RED_BBR","IN_MAGENTA_STAGE","IN_MAGENTA_STAGE_POLE"};
			
			@SuppressWarnings("rawtypes")
			JComboBox cb = (JComboBox)e.getSource();
	        String item = (String)cb.getSelectedItem();
			for(int i = 0; i < tileStrings.length; i++){
				if(tileStrings[i].equals(item)){
					if(i < 72)
						pen = tileArray[i];
					else
						pen = tileArray[i-72+1000];
					repaint();
				}
			}
			
		}
		
	}
	class MenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if("Save".equals(e.getActionCommand())){
				String fname = "map";
				String s = "" ;
				while(s.length() < 1){
					s = (String)JOptionPane.showInputDialog(
							"Enter name of file to save",fname);
					if(s == null) return;
					fname = s;
				}
				File o = new File(System.getProperty("user.home")+"/maps/"+fname+".tdmc");
				if(o.exists()){
					int n = JOptionPane.showConfirmDialog(canvas,
						    "This File Already Exists! Overwrite?",
						    "WARNING",
						    JOptionPane.YES_NO_OPTION);
					if(n == 1)
						return;
				}
				sl.save(fname,map);
				changesMade = false;
			}else if("Load".equals(e.getActionCommand())){
				String fname = "map";
				String s ="";
				if(changesMade){
					int n = JOptionPane.showConfirmDialog(canvas,
						    "You Haven't Saved Changes! Continue Anyway?",
						    "WARNING",
						    JOptionPane.YES_NO_OPTION);
					if(n == 1)
						return;
				}
				while(s.length() < 1){
					s = (String)JOptionPane.showInputDialog(
	                    "Enter name of file to load",fname);
					if(s == null) return;
					fname = s;
				}
				File o = new File(System.getProperty("user.home")+"/maps/"+fname+".tdmc");
				if(!o.exists()){
					JOptionPane.showMessageDialog(canvas,
						    "File Does Not Exist in:\n"+System.getProperty("user.home")+"/maps/",
						    "WARNING",
						    JOptionPane.WARNING_MESSAGE);
					return;
				}
				map = sl.load(fname);
				changesMade = false;
				repaint();
			}else if("Outside".equals(e.getActionCommand())){
				String fname = "map";
				String s = "";
				while(s.length() < 1){
					s = (String)JOptionPane.showInputDialog(
	                    "Enter name of file to Export\n"+"NOTE: This is for Outsides of buildings only!",fname);
					if(s == null) return;
					fname = s;
				}
				File o = new File(System.getProperty("user.home")+"/maps/"+fname+".txt");
				if(o.exists()){
					int n = JOptionPane.showConfirmDialog(canvas,
						    "This File Already Exists! Overwrite?",
						    "WARNING",
						    JOptionPane.YES_NO_OPTION);
					if(n == 1)
						return;
				}
				sl.exportPT(fname, map,true);
			}else if("Inside".equals(e.getActionCommand())){
				String fname = "map";
				String s = "";
				while(s.length() < 1){
					s = (String)JOptionPane.showInputDialog(
	                    "Enter name of file to Export\n"+"NOTE: This is for insides of buildings only!",fname);
					if(s == null) return;
					fname = s;
				}
				File o = new File(System.getProperty("user.home")+"/maps/"+fname+".txt");
				if(o.exists()){
					int n = JOptionPane.showConfirmDialog(canvas,
						    "This File Already Exists! Overwrite?",
						    "WARNING",
						    JOptionPane.YES_NO_OPTION);
					if(n == 1)
						return;
				}
				sl.exportPT(fname, map,false);
			}
			
		}
		
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if("Multi".equals(e.getActionCommand())){
				if(mode == Mode.SINGLE){
					mode = Mode.MULTIPLE;
				}else{
					mode = Mode.SINGLE;
				}
				repaint();
			}else if("Draw".equals(e.getActionCommand())){
				if(mode == Mode.MULTIPLE){
					if(startSel <= endSel){
						for(int i = startSel; i <= endSel; i++){
							map.setTile(i, pen);
						}
					}else{
						for(int i = endSel; i <= startSel; i++){
							map.setTile(i, pen);
						}
					}
					changesMade = true;
					repaint();
				}
			}
		}
		
	}
	
	@SuppressWarnings("serial")
	class GameCanvas extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
	      // Constructor
	      public GameCanvas() {
	    	 setLayout(null);
	         setFocusable(true);  // so that can receive key-events
	         requestFocus();
	         setResizable(false);
	         addKeyListener(this);
	         addMouseListener(this);
	         addMouseMotionListener(this);
	         
	      }
	   
	      // Override paintComponent to do custom drawing.
	      // Called back by repaint().
	      
	      public void paintComponent(Graphics g) {
	         Graphics2D g2d = (Graphics2D)g;
	         super.paintComponent(g2d);   // paint background
	         setBackground(Color.WHITE);  // may use an image for background
	   
	         // Draw the game objects
	         drawTiles(g2d);
	         

	         
	      }
	      // KeyEvent handlers
	      
	      public void keyPressed(KeyEvent e) {
	         //gameKeyPressed(e.getKeyCode());
	      }
	      
	      
	      public void keyReleased(KeyEvent e) {
	         //gameKeyReleased(e.getKeyCode());
	      }
	   
	      
	      public void keyTyped(KeyEvent e) {
	         //gameKeyTyped(e.getKeyChar());
	      }

	      
	    public void mouseMoved(MouseEvent me){
	    	
		}
		public void mouseClicked(MouseEvent me) {
			int x = me.getX();
			int y = me.getY();
			int mouseBlock = ((((y/20) * 40) ) + (x/20));
			if(y <= 600){
				if(mouseBlock > -1 && mouseBlock < 1200){
					if(mode == Mode.SINGLE){
						if(me.getButton() == 1){
							map.setTile(mouseBlock, pen);
							changesMade = true;
						}else if(me.getButton() == 3){
							pen = map.getTile(mouseBlock, TILE_BLANK);
						}
						repaint();
					}else if(mode == Mode.MULTIPLE){
						if(me.getButton() == 1){
							startSel = mouseBlock;
						}else if(me.getButton() == 3){
							endSel = mouseBlock;
						}
						repaint();
					}
				}
			}
			
			
			
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			
		}

		@Override
		public void mousePressed(MouseEvent me) {
			
		}

		@Override
		public void mouseReleased(MouseEvent me) {
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			
		}
	   }
	

	public static void main(String[] args) {
		// Use the event dispatch thread to build the UI for thread-safety.
	      SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	            new Main();
	            
	         }
	      });
	}	
}
