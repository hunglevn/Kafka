package com.kl.kafka.bean;

public class DocumentStatus {
	private String documentId;
	private Status status;

	public DocumentStatus() {
	}
	
	public DocumentStatus(String documentId, Status status) {
		this.documentId = documentId;
		this.status = status;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		
		return "documentId:" + documentId + ", status:" + status;
	}
}
