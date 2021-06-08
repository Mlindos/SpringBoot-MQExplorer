package com.mlindos.projects.mqObserver.peepingTom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Linda Ndabana
 * @since 1.0
 * @version IBM MQ Explorer
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class PeepingTomConfig {
	
	@Value("${servers.mq.host}") 
	private String host;
	
	@Value("${servers.mq.port}")
	private Integer port;
	
	@Value("${servers.mq.queue-manager}")
	private String queueManager;
	
	@Value("${servers.mq.channel}")
	private String channel;
	
	@Value("${servers.mq.queue}")
	private String queue;

	@Value("${servers.mq.timeout}")
	private long timeout;

	@Value("${browse.action.manager}")
	private String actionManager;

	@Value("${browse.action.host}")
	private String actionHost;

	@Value("${browse.action.port}")
	private String actionPort;

	@Value("${browse.action.channel}")
	private String actionChannel;

	@Value("${browse.action.user}")
	private String actionUser;

	@Value("${browse.action.password}")
	private String actionPassword;

	@Value("${browse.action.mqusername}")
	private String mqUserName;

	@Value("${browse.action.userpassword}")
	private String userPassword;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(String queueManager) {
		this.queueManager = queueManager;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public String getMqUserName() {
		return mqUserName;
	}

	public void setMqUserName(String mqUserName) {
		this.mqUserName = mqUserName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getActionManager() {
		return actionManager;
	}

	public void setActionManager(String actionManager) {
		this.actionManager = actionManager;
	}

	public String getActionHost() {
		return actionHost;
	}

	public void setActionHost(String actionHost) {
		this.actionHost = actionHost;
	}

	public String getActionPort() {
		return actionPort;
	}

	public void setActionPort(String actionPort) {
		this.actionPort = actionPort;
	}

	public String getActionChannel() {
		return actionChannel;
	}

	public void setActionChannel(String actionChannel) {
		this.actionChannel = actionChannel;
	}

	public String getActionUser() {
		return actionUser;
	}

	public void setActionUser(String actionUser) {
		this.actionUser = actionUser;
	}

	public String getActionPassword() {
		return actionPassword;
	}

	public void setActionPassword(String actionPassword) {
		this.actionPassword = actionPassword;
	}

	@Override
	public String toString() {
		return "PeepingTomConfig{" +
				"\nhost='" + host + '\'' +
				", \nport=" + port +
				", \nqueueManager='" + queueManager + '\'' +
				", \nchannel='" + channel + '\'' +
				", \nqueue='" + queue + '\'' +
				", \ntimeout=" + timeout +
				", \nactionManager='" + actionManager + '\'' +
				", \nactionHost='" + actionHost + '\'' +
				", \nactionPort='" + actionPort + '\'' +
				", \nactionChannel='" + actionChannel + '\'' +
				", \nactionUser='" + actionUser + '\'' +
				", \nactionPassword='" + actionPassword + '\'' +
				'}';
	}
}
