package com.example.service;

import java.util.List;

import com.example.exception.LikeException;
import com.example.exception.TwitException;
import com.example.exception.UserException;
import com.example.model.Like;
import com.example.model.Twit;
import com.example.model.User;

public interface LikesService {
	
	public Like likeTwit(Long twitId, User user) throws UserException, TwitException;
	
	public Like unlikeTwit(Long twitId, User user) throws UserException, TwitException, LikeException;
	
	public List<Like> getAllLikes(Long twitId) throws TwitException;

}

