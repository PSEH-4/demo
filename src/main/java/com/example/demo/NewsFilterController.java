package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NewsFilterController {
	
	@RequestMapping("newsFilter")
	public List<NewsFilterModel> getNewsFilterContent(InputModel inputModel) {
		
	    final String uri = "https://newsapi.org/v2/top-headlines?country="+inputModel.getCountry()+"&category="+inputModel.getCategory()+"&apiKey=ccaf5d41cc5140c984818c344edcc14d";

	    RestTemplate restTemplate = new RestTemplate();
	    ApiModel result = restTemplate.getForObject(uri, ApiModel.class);
	    
	    List<NewsFilterModel> newsFilterModelList = new ArrayList<>();
	    
	    for(Articles article: result.getArticles()) {
	    	
	    	if(article.getContent()!=null && article.getContent().contains(inputModel.getKeyword())) {
	    		
	    		NewsFilterModel newsFilterModel = new NewsFilterModel();
	    		newsFilterModel.setTitle(article.getTitle());
	    		newsFilterModel.setDescription(article.getContent());
	    		newsFilterModel.setCategory(inputModel.getCategory());
	    		newsFilterModel.setCountry(inputModel.getCountry());
	    		newsFilterModel.setKeyword(inputModel.getKeyword());
	    		newsFilterModelList.add(newsFilterModel);
	    	}
	    	
	    }
	    return newsFilterModelList;
	}

}
