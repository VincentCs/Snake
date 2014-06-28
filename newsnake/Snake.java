import java.awt.*;
import java.awt.event.KeyEvent;
public class Snake {
	private Node head=null;
	private Node tail=null;
	private int size=0;
	
	private Node n=new Node(10,10,Dir.L);
	private Yard y;
	
	public Snake(Yard y){
		head=n;
		tail=n;
		size=1;
		this.y=y;
	}
	public void draw(Graphics g){
		if(size<=0)return;
		move();
		for(Node n=head;n!=null;n=n.next){
			n.draw(g);
		}
	}
	private void move(){
		if(isGameOver()){
			y.setGameOver();
			//y.paintThread.pause();
		}
		addToHead();
		
		if(head.col==y.e.col&&head.row==y.e.row){
			y.e.nextEgg();
			y.setScore(y.getScore()+10);
		}
		else
		    deleteFromTail();
		
		//System.out.println(this.size);
	}
	private void deleteFromTail(){
		if(size==0)return;
		tail=tail.prev;
		tail.next=null;
		size--;
	}
	public void addToHead(){
		Node node=null;
		switch(head.dir){
		case L:
			node=new Node(head.row,head.col-1,head.dir);
			break;
		case U:
			node=new Node(head.row-1,head.col,head.dir);
			break;
		case R:
			node=new Node(head.row,head.col+1,head.dir);
			break;
		case D:
			node=new Node(head.row+1,head.col,head.dir);
			break;
		}
		node.next=head;
		head.prev=node;
		head=node;
		size++;
	}
	
	public boolean isGameOver(){
		if(head.row<=2||head.col<0||head.row>=30||head.col>30)
			return true;
		Node n=head.next;
		while (n!=null){
			if(n.col==head.col&&n.row==head.row)
					return true;
			n=n.next;
		}
		return false;
	}
	
	
	
	
	
	private class Node{
		int w=Yard.BLOCK_SIZE;
	    int h=Yard.BLOCK_SIZE;
	    int row,col;
	    Dir dir=Dir.L;
	    Node next=null;
	    Node prev=null;
	    Node(int row,int col,Dir dir){
	    	this.row=row;
	    	this.col=col;
	    	this.dir=dir;
	    }
	    void draw(Graphics g){
	    	Color c=g.getColor();
	    	g.setColor(Color.BLACK);
	    	g.fillRect(Yard.BLOCK_SIZE*col, Yard.BLOCK_SIZE*row, w, h);
	    	g.setColor(c);
	    	
	    }
	}
	public void KeyPressed(KeyEvent e){
		int key=e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT:
			if(head.dir!=Dir.R)
				head.dir=Dir.L;
			break;
		case KeyEvent.VK_UP:
			if(head.dir!=Dir.D)
				head.dir=Dir.U;
			break;
		case KeyEvent.VK_RIGHT:
			if(head.dir!=Dir.L)
				head.dir=Dir.R;
			break;
		case KeyEvent.VK_DOWN:
			if(head.dir!=Dir.U)
				head.dir=Dir.D;
			break;
		}
	}

}
