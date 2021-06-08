package com.mlindos.projects.mqObserver.peepingTom;

import com.mlindos.projects.mqObserver.peepingTom.component.InquireQueueManager;
import com.mlindos.projects.mqObserver.peepingTom.component.Logger;
import com.mlindos.projects.mqObserver.peepingTom.component.StartInit;
import com.mlindos.projects.mqObserver.peepingTom.config.PeepingTomConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PeepingTomApplication implements CommandLineRunner {

	@Autowired
	PeepingTomConfig peepingTomConfig;

	@Autowired
	StartInit startInit;

	@Autowired
	InquireQueueManager inquireQueueManager;

	@Autowired
	Logger logger;

	String mqInquireCommands[] = new String[12]; //= {"-m", "SIRESS.WEB.QDEV", "-h", "172.30.3.41","-p","1414","-c","DEV.WAS.CONNECTION","-u","admin","-x","admin"};

	public static void main(String[] args) {
		SpringApplication.run(PeepingTomApplication.class, args);
	}

	public void run(String... args) throws Exception{

    	mqInquireCommands[0] = peepingTomConfig.getActionManager();
 		mqInquireCommands[1] = peepingTomConfig.getQueueManager();
 		mqInquireCommands[2] = peepingTomConfig.getActionHost();
 		mqInquireCommands[3] = peepingTomConfig.getHost();
 		mqInquireCommands[4] = peepingTomConfig.getActionPort();
  		mqInquireCommands[5] = String.valueOf(peepingTomConfig.getPort());
 		mqInquireCommands[6] = peepingTomConfig.getActionChannel();
 		mqInquireCommands[7] = peepingTomConfig.getChannel();
    	mqInquireCommands[8] = peepingTomConfig.getActionUser();
 		mqInquireCommands[9] = peepingTomConfig.getMqUserName();
 		mqInquireCommands[10] = peepingTomConfig.getActionPassword();
 		mqInquireCommands[11] = peepingTomConfig.getUserPassword();

 		inquireQueueManager.doPCF(peepingTomConfig.getQueueManager(),startInit.init(mqInquireCommands));

	}

}
