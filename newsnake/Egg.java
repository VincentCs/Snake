import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Egg {
	
	int row,col;
	int w=Yard.BLOCK_SIZE;
	int h=Yard.BLOCK_SIZE;
	
	private static Random r=new Random();
	
	public Egg(int r,int c){
		this.row=r;
		this.col=c;
	}
	public Egg(){
		this.row=Math.abs(r.nextInt()%(Yard.ROW-2))+2;
		this.col=Math.abs(r.nextInt()%Yard.COL);
	}
	public void nextEgg(){
		this.row=Math.abs(r.nextInt()%(Yard.ROW-2))+2;
		this.col=Math.abs(r.nextInt()%Yard.COL);
	}
	void draw(Graphics g){
    	Color c=g.getColor();
    	g.setColor(Color.RED);
    	g.fillOval(Yard.BLOCK_SIZE*col, Yard.BLOCK_SIZE*row, w, h);
    	//g.fillRect(Yard.BLOCK_SIZE*col, Yard.BLOCK_SIZE*row, w, h);
    	g.setColor(c);
    	
    }

}
