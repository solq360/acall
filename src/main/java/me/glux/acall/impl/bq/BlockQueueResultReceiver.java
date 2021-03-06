package me.glux.acall.impl.bq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import me.glux.acall.ResultReceiver;
import me.glux.acall.asyn.ParkedObject;
import me.glux.acall.asyn.TimeoutObjectManager;

public class BlockQueueResultReceiver extends ResultReceiver {
	private BlockingQueue<String> queue;
	private TimeoutObjectManager<? extends ParkedObject> callers;

	public BlockQueueResultReceiver(BlockingQueue<String> queue, TimeoutObjectManager<? extends ParkedObject> callers) {
		this.queue = queue;
		this.callers = callers;
		super.start();
	}

	@Override
	protected byte[] receive() {
		do {
			try {
				return queue.poll(5, TimeUnit.SECONDS).getBytes();
			} catch (InterruptedException e) {

			}
		} while (true);
	}

	@Override
	protected void notifyRunner(String id) {
		ParkedObject caller = callers.get(id);
		if (null != caller) {
			caller.done();
		}
	}
}
