package mt.com.uom.project.pest.application;

public enum AkkaManager {
	INSTANCE;
	
	private AkkaThread m_akkaThread = null;
	private Thread m_thread = null;
	public void initialise() {
		if ( null == m_thread ) {
			m_akkaThread = new AkkaThread();
			m_thread = new Thread(m_akkaThread);
			m_thread.start();
		}			
	}
	
	public void shutdown() throws InterruptedException {
		if ( null != m_thread ) {
			m_akkaThread.shutdown();
			m_thread.join();
		}
	}
	
	public void receiveRequest(Object msg) {
		m_akkaThread.receiveRequest(msg);
	}
}
