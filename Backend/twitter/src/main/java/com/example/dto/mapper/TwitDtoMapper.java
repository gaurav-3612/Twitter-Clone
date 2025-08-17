package com.example.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.dto.TwitDto;
import com.example.dto.UserDto;
import com.example.model.Twit;
import com.example.model.User;
import com.example.util.TweetUtil;

import lombok.Data;
@Data

public class TwitDtoMapper {
	
	public static TwitDto toTwitDto(Twit twit,User reqUser) {
		UserDto user=UserDtoMapper.toUserDto(twit.getUser());
		
		boolean isLiked=TweetUtil.isLikedByReqUser(reqUser, twit);
		boolean isRetwited=TweetUtil.isLikedByReqUser(reqUser, twit);
		
		List<Long> retwitUserId=new ArrayList<>();
		
		for(User user1 : twit.getRetwitUser()) {
			retwitUserId.add(user1.getId());
		}
		
		TwitDto twitDto=new TwitDto();
		twitDto.setId(twit.getId());
		twitDto.setContent(twit.getContent());
		twitDto.setCreatedAt(twit.getCreatedAt());
		twitDto.setImage(twit.getImage());
		twitDto.setTotalLikes(twit.getLikes().size());
		twitDto.setTotalReplies(twit.getReplyTwits().size());
		twitDto.setTotalRetweets(twit.getRetwitUser().size());
		twitDto.setUser(user);
		twitDto.setLiked(isLiked);
		twitDto.setRetwit(isRetwited);
		twitDto.setRetwitUsersId(retwitUserId);
		twitDto.setReplyTwits(toTwitDtos(twit.getReplyTwits(), reqUser));
		twitDto.setVideo(twit.getVideo());
		
		
		return twitDto;
	}
	
	public static List<TwitDto> toTwitDtos(List<Twit> twits, User reqUser) {
		
		List<TwitDto> twitDtos=new ArrayList<>();
		
		for(Twit twit : twits) {
		
			TwitDto twitDto=toReplyTwitDto(twit, reqUser);
		
			twitDtos.add(twitDto);
		}
		
		
		return twitDtos;
	}
	
	public static TwitDto toReplyTwitDto(Twit twit, User reqUser) {
		UserDto user=UserDtoMapper.toUserDto(twit.getUser());
		
		boolean isLiked=TweetUtil.isLikedByReqUser(reqUser, twit);
		boolean isRetwited=TweetUtil.isLikedByReqUser(reqUser, twit);
		
		List<Long> retwitUserId=new ArrayList<>();
		
		for(User user1 : twit.getRetwitUser()) {
			retwitUserId.add(user1.getId());
		}
		
		TwitDto twitDto=new TwitDto();
		twitDto.setId(twit.getId());
		twitDto.setContent(twit.getContent());
		twitDto.setCreatedAt(twit.getCreatedAt());
		twitDto.setImage(twit.getImage());
		twitDto.setTotalLikes(twit.getLikes().size());
		twitDto.setTotalReplies(twit.getReplyTwits().size());
		twitDto.setTotalRetweets(twit.getRetwitUser().size());
		twitDto.setUser(user);
		twitDto.setLiked(isLiked);
		twitDto.setRetwit(isRetwited);
		twitDto.setRetwitUsersId(retwitUserId);
		twitDto.setVideo(twit.getVideo());
		
		return twitDto;
	}
	
	


}

