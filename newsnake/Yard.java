import java.awt.*;
import java.awt.event.*;
public class Yard extends Frame{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Yard yard=new Yard();
		yard.publish();
	}
	public final static int BLOCK_SIZE=15;
	public final static int ROW=30;
	public final static int COL=30;
	private boolean gameOver=false;
	private Font fontGameOver =new Font("宋体",Font.BOLD,50);
	private int score=0;
	
	Snake s=new Snake(this);
	Egg e=new Egg();
	public PaintThread paintThread=new PaintThread();
	
	public void publish(){
		this.setBackground(Color.WHITE);
		this.setLocation(200,200);
		this.setTitle("snake：F1加速，F2减速，F5重新开始，space暂停/启动");
		this.setName("snake");
		this.setSize(BLOCK_SIZE*ROW, BLOCK_SIZE*COL);
		this.addKeyListener(new KeyMonitor());
		this.addWindowListener(new WindowAdapter(){
			

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
				//super.windowClosing(e);
			}
	});
		
		this.setVisible(true);
		new Thread(paintThread).start();
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		Color c=g.getColor();
		g.setColor(Color.blue);
		for(int i=0;i<ROW;i++)
		    g.drawLine(0, BLOCK_SIZE*i, BLOCK_SIZE*COL, BLOCK_SIZE*i);
		for(int i=0;i<COL;i++)
			g.drawLine(i*BLOCK_SIZE,0,BLOCK_SIZE*i,BLOCK_SIZE*ROW);
		g.setColor(Color.BLACK);
		g.drawString("score: "+score, 10, 100);
		if(gameOver){
			g.setFont(fontGameOver);
			g.drawString("游戏结束", 120, 180);
			paintThread.GameOver();
			//new Thread(paintThread).pause();
		}
		g.setColor(c);
		e.draw(g);
		s.draw(g);
		//super.paint(g);
	}
	
	public void update(Graphics g){
		Image t=createImage(getWidth(),getHeight());
		Graphics GraImage=t.getGraphics();
		paint(GraImage);
		GraImage.dispose();
		g.drawImage(t,0,0,null);
	}
	
	class PaintThread implements Runnable{
		private boolean running=true;
		private boolean pause=true;
		private int timer=100;
		
		public void run(){
			while(running){
				if(pause)continue;
				repaint();
				
				try{
					Thread.sleep(timer);
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		public void pause(){
			if(this.pause==true)
				this.pause=false;
			else this.pause=true;
		}
		public void reStart(){
			this.pause=false;
			s=new Snake(Yard.this);
			gameOver=false;
		}
		public void GameOver(){
			running=false;
		}
		public void addV(){
			if(timer>0)
			    timer-=50;
		}
		public void minusV(){
			timer+=50;
		}
	}

	
	private class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int key=e.getKeyCode();
			if(key==KeyEvent.VK_F5){      //重新开始
				paintThread.reStart();
			}
			if(key==KeyEvent.VK_SPACE){   //暂停和开始
				paintThread.pause();
			}
			if(key==KeyEvent.VK_F1){     //加速
				paintThread.addV();
			}
			if(key==KeyEvent.VK_F2){       //减速
				paintThread.minusV();
			}
			s.KeyPressed(e);
		}
		
	}
	public int getScore(){
		return score;
	}
	public void setScore(int score){
		this.score=score;
	}
	public void setGameOver(){
		gameOver=true;
	}

}
