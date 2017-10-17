package com.example.spring.resttemplate.demo.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // needed because senderID and timestamp are not in the response
public class Quote {

    private String type;     // this param is fetched from external MS 
    private Value value;     // this param is fetched from external MS
    private String senderId; // this param is set internally
    private String timestamp;// this param is set internally
    
    public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public Quote() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return " \nSender:" + senderId + 
        		"\nQuote{" +
                	"\ntype='" + type + '\'' +
                	",\n value=" + value +
                	'}' +
                "\nTimestamp :" + timestamp;
    }
}