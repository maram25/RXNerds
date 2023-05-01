package com.collect.rxnerds;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsClient {
	private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
	private PostInterface postInterface;
	private static PostsClient INSTANCE;

	public PostsClient() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		postInterface = retrofit.create(PostInterface.class);
	}

	public static PostsClient getINSTANCE() {
		if (null == INSTANCE){
			INSTANCE = new PostsClient();
		}
		return INSTANCE;
	}

	public Observable<List<PostModel>> getPosts(){
		return postInterface.getPosts();
	}

}
