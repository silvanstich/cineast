package org.vitrivr.cineast.core.runtime;

import java.util.concurrent.LinkedBlockingQueue;

import org.vitrivr.cineast.core.data.SegmentContainer;
import org.vitrivr.cineast.core.data.providers.ShotProvider;

class ShotProviderThread extends Thread {

	private LinkedBlockingQueue<SegmentContainer> shotQueue;
	private ShotProvider provider;
	
	ShotProviderThread(LinkedBlockingQueue<SegmentContainer> shotQueue, ShotProvider provider) {
		super("ShotProviderThread");
		this.provider = provider;
		this.shotQueue = shotQueue;
	}
	
	@Override
	public void run() {
		SegmentContainer shot;
		while((shot = provider.getNextShot()) != null && !this.isInterrupted()){
			try {
				this.shotQueue.put(shot);
			} catch (InterruptedException e) {
				this.interrupt();
			}
		}
	}

	
	
}