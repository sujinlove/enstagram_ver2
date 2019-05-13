package com.enstagram.controller;

import java.io.FileOutputStream;
import java.util.List;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.enstagram.model.EnstaFeed;
import com.enstagram.service.EnstaService;

@RestController
@RequestMapping("/api")
public class EnstaFeedController {

	@Autowired
	EnstaService enstaService;

	/*
	 * Get Feed List
	 */

	@RequestMapping("/feedList")
	public List<EnstaFeed> feedList() {
		return enstaService.feedList();
	}

	/*
	 * Create Feed
	 */

	@RequestMapping(value = "/feedUpload", method = RequestMethod.POST)
	public void createFeed(@RequestBody EnstaFeed enstaFeed) {
//		System.out.println(enstaFeed.get);
		// enstaFeed.setFile_name(enstaFeed.getFile().getOriginalFilename());

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(enstaFeed.getFile().getOriginalFilename());
			IOUtils.copy(enstaFeed.getFile().getInputStream(), fos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		enstaService.createFeed(enstaFeed);
	}
}
