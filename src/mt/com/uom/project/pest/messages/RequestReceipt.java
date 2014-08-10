package mt.com.uom.project.pest.messages;

import java.io.Serializable;

public class RequestReceipt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1891603509483080540L;
	
	private int receiptId;

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}
	
}
