package com.enstagram.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enstagram.model.EnstaFeed;
import com.enstagram.service.EnstaService;

@RestController
public class EnstaFeedController {

	@Autowired
	EnstaService enstaService;

	/*
	 * Get Feed List
	 */

	@RequestMapping("/api/feedList")
	public List<EnstaFeed> feedList() {
		return enstaService.feedList();
	}

	/*
	 * Create Feed
	 */

	@RequestMapping(value = "/api/feedUpload", method = { RequestMethod.POST, RequestMethod.GET })
	public void createFeed(@ModelAttribute EnstaFeed enstaFeed, @RequestParam MultipartFile file) {
		String profileName = RandomStringUtils.randomAlphanumeric(12);
		enstaFeed.setFile_name("/upload/" + profileName + "." + file.getOriginalFilename().split("\\.")[1]);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File("./src/main/resources/static/upload/" + profileName + "." + file.getOriginalFilename().split("\\.")[1]));
			IOUtils.copy(file.getInputStream(), fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		enstaService.createFeed(enstaFeed);
	}
	

	/*
	 * Get Feed
	 */

	@RequestMapping(value = "/api/feed/{feed_num}", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, Object> getFeed(@PathVariable Integer feed_num) {
        return enstaService.getFeed(feed_num);
	}
	
	/*
	 * Remove Feed
	 */

	@RequestMapping(value = "/api/feed/remove", method = { RequestMethod.POST, RequestMethod.GET })
	public void removeFeed(@RequestParam Integer feed_num) {
		enstaService.removeFeed(feed_num);
	}
	
	/*
	 * Like Feed
	 */

	@RequestMapping(value = "/api/feed/like", method = { RequestMethod.POST, RequestMethod.GET })
	public void likeFeed(@RequestBody EnstaFeed enstaFeed) {
		enstaService.likeFeed(enstaFeed);
		enstaService.updateHeart(enstaFeed.getFeed_num());
	}
	
	/*
	 * Unlike Feed
	 */

	@RequestMapping(value = "/api/feed/unlike", method = { RequestMethod.POST, RequestMethod.GET })
	public void unlikeFeed(@RequestBody EnstaFeed enstaFeed) {
		enstaService.unlikeFeed(enstaFeed);
		enstaService.updateHeart(enstaFeed.getFeed_num());
	}
}
