package ch.unibas.cs.dbis.cineast.core.data;


public class Frame {

	private final int id;
	private MultiImage img;
	
	public Frame(int id, MultiImage image){
		this.id = id;
		this.img = image;
	}
	
	public int getId(){
		return this.id;
	}
	
	public MultiImage getImage(){
		return this.img;
	}
	
	public void clear(){
		this.img.clear();
		this.img = null;
	}
}
