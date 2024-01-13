package semana06_ProcuraTexto_CountDownLatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;


class SearcherThread extends Thread{
	private String myText;
	private String textToFind;
	private CountDownLatch cdl;
	private int result=-1;

	public SearcherThread(String myText, String textToFind,
			CountDownLatch cdl) {
		this.myText = myText;
		this.textToFind = textToFind;
		this.cdl = cdl;
	}

	public String getMyText() {
		return myText;
	}

	public int getResult() {
		return result;
	}

	@Override
	public void run() {
		result=myText.indexOf(textToFind);
		cdl.countDown();

	}


}

