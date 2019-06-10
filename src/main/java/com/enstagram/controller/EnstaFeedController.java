package com.enstagram.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enstagram.model.EnstaAccount;
import com.enstagram.model.EnstaFeed;
import com.enstagram.model.EnstaReply;
import com.enstagram.service.EnstaService;

@RestController
public class EnstaFeedController {

	@Autowired
	EnstaService enstaService;

	/*
	 * Create Feed When Upload File
	 */

	@RequestMapping(value = "/api/feedUpload", method = { RequestMethod.POST, RequestMethod.GET })
	public Integer createFeed(@ModelAttribute EnstaFeed enstaFeed, @RequestParam MultipartFile file) {
		String profileName = RandomStringUtils.randomAlphanumeric(12);
		enstaFeed.setFile_name("/upload/" + profileName + "." + file.getOriginalFilename().split("\\.")[1]);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File("./src/main/resources/static/upload/" + profileName + "."
					+ file.getOriginalFilename().split("\\.")[1]));
			IOUtils.copy(file.getInputStream(), fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		enstaService.createFeed(enstaFeed);
		return enstaFeed.getFeed_num();
	}

	/*
	 * Get Feed
	 */

	@RequestMapping(value = "/api/feed/{feed_num}", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, Object> getFeed(@PathVariable Integer feed_num) {
		Map<String, Object> map = enstaService.getFeedInfo(feed_num);
		return map;
	}

	/*
	 * Get Feed List of Follow
	 */

	@RequestMapping(value = "/api/feed/follow", method = { RequestMethod.POST, RequestMethod.GET })
	public Integer[] getFollowFeed(@ModelAttribute EnstaFeed enstaFeed) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUser = authentication.getName();

		return enstaService.getFollowFeed(enstaService.getAccountNum(currentUser));
	}

	/*
	 * Get Heart User List of Feed
	 */

	@RequestMapping(value = "/api/feed/{feed_num}/heart", method = { RequestMethod.POST, RequestMethod.GET })
	public Integer[] getFeedHeartList(@PathVariable Integer feed_num) {
		return enstaService.getFeedHeartList(feed_num);
	}

	/*
	 * Edit Feed
	 */

	@RequestMapping(value = "/api/feed/edit", method = { RequestMethod.POST, RequestMethod.GET })
	public void editFeed(@RequestBody EnstaFeed enstaFeed) {
		enstaService.editFeed(enstaFeed);
	}

	/*
	 * 
	 */

	@RequestMapping(value = "/api/check/feedNum", method = { RequestMethod.POST, RequestMethod.GET })
	public Integer checkFeedNum(@RequestBody EnstaFeed enstaFeed) {
		return enstaService.checkFeedNum(enstaFeed.getFeed_num());
	}

	/*
	 * Remove Feed
	 */

	@RequestMapping(value = "/api/feed/remove", method = { RequestMethod.POST, RequestMethod.GET })
	public void removeFeed(@RequestBody EnstaFeed enstaFeed) {
		Integer feed_num = enstaFeed.getFeed_num();
		File file = new File("./src/main/resources/static" + enstaService.getFeedFileName(feed_num));
		file.delete();
		enstaService.unlikeFeed(enstaFeed);
		enstaService.removeFeed(feed_num);
	}

	/*
	 * Add Heart to Feed
	 */

	@RequestMapping(value = "/api/feed/like", method = { RequestMethod.POST, RequestMethod.GET })
	public void likeFeed(@RequestBody EnstaFeed enstaFeed) {
		enstaService.likeFeed(enstaFeed);
		enstaService.updateHeart(enstaFeed.getFeed_num());
	}

	/*
	 * Cancel Heart to Feed
	 */

	@RequestMapping(value = "/api/feed/unlike", method = { RequestMethod.POST, RequestMethod.GET })
	public void unlikeFeed(@RequestBody EnstaFeed enstaFeed) {
		enstaService.unlikeFeed(enstaFeed);
		enstaService.updateHeart(enstaFeed.getFeed_num());
	}

	/*
	 * Add Reply to Feed
	 */

	@RequestMapping(value = "/api/reply", method = { RequestMethod.POST, RequestMethod.GET })
	public void createReply(@RequestBody EnstaReply enstaReply) {
		enstaService.createReply(enstaReply);
	}

	/*
	 * Get Reply Num
	 */

	@RequestMapping(value = "/api/replyList", method = { RequestMethod.POST, RequestMethod.GET })
	public List<EnstaReply> getReplyList(@RequestBody EnstaReply enstaReply) {
		return enstaService.getReplyList(enstaReply.getFeed_num());
	}

	/*
	 * Remove Reply Info
	 */

	@RequestMapping(value = "/api/reply/remove", method = { RequestMethod.POST, RequestMethod.GET })
	public void removeReplyInfo(@RequestBody EnstaReply enstaReply) {
		enstaService.removeReplyInfo(enstaReply.getReply_num());
	}

}
