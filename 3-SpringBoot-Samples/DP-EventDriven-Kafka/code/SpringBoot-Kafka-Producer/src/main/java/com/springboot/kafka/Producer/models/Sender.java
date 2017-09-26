package com.springboot.kafka.Producer.models;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;

public class Sender {
	
	    @Autowired
	    private Producer<String,FundTransferRequest> producer;    
	    
	    public void sendMessage(String topic,FundTransferRequest object) throws InterruptedException { 
	    	
	 	         producer.send(new ProducerRecord<String, FundTransferRequest>(topic, object));
	 	         System.out.println("Message sent successfully");	 	        
	 	         producer.flush();
	      
	    }
	 
}

