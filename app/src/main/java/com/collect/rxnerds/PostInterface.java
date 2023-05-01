package com.collect.rxnerds;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PostInterface {
	@GET("posts")
	public Observable<List<PostModel>> getPosts();
}
