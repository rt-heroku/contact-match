package com.heroku.match.utils;

import java.util.List;

import org.jobrunr.jobs.context.JobContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heroku.match.model.Matched;

public class MatchUtils {
	private static final Logger log;
	static {
		log = LoggerFactory.getLogger(MatchUtils.class);
	}

	public static String toJson(Object o) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		String ret = "";
		try {
			ret = objectMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			if (log.isDebugEnabled())
				e.printStackTrace();
		}
		
		return ret;
	}
	public static void debugMatchedList(String msg, List<Matched> list) {
		if (log.isDebugEnabled()) {
			log.debug(msg);
			for (Matched m : list) {
				log.debug("\t" + m.toString());
			}
		}
	}
	
	public static void log( JobContext jobContext, String s) {
		jobContext.logger().info(s);		
		log(s);
	}
	public static void log(String s) {
		Logger log = LoggerFactory.getLogger(MatchUtils.class);
		log.info(s);
	}
	public static void error(JobContext jobContext, String msg) {
		jobContext.logger().error(msg);
		log.error(msg);
	}

}
