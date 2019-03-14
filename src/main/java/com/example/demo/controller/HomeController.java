package com.example.demo.controller;

import java.lang.management.ManagementFactory;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Item;
import com.example.demo.repo.ItemRepository;
import com.zaxxer.hikari.HikariPoolMXBean;

@RestController
public class HomeController {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping("/")
	public String welcome() {
		return "welcome";
	}
	
	@RequestMapping(value="/items", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,String> getItems() {
		List<Item> items = itemRepository.findAll();
		return Collections.singletonMap("data", items.toString());
	}
	
	@GetMapping("/evict")
	public String evict() {
		try {
			MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
			ObjectName poolName = new ObjectName("com.zaxxer.hikari:type=Pool (MyHikariPool)");
			HikariPoolMXBean poolProxy = JMX.newMXBeanProxy(mBeanServer, poolName, HikariPoolMXBean.class);
			
			poolProxy.softEvictConnections();
			
			return "evicted.";
		} catch (MalformedObjectNameException e) {
			return e.getMessage();
		}
	}
}
