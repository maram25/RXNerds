package com.collect.rxnerds;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class PostViewModel extends ViewModel {
	MutableLiveData<List<PostModel>> postsMutableLiveData = new MutableLiveData<>();
	MutableLiveData<String> posts = new MutableLiveData<>();

	public void getPosts() {
		 Observable observable =PostsClient.getINSTANCE().getPosts()
				 .observeOn(Schedulers.io())
				 .observeOn(AndroidSchedulers.mainThread());
		 Observer<List<PostModel>> observer=new Observer<List<PostModel>>() {
			 @Override
			 public void onSubscribe(@NonNull Disposable d) {

			 }

			 @Override
			 public void onNext(@NonNull List<PostModel> postModels) {
				 postsMutableLiveData.setValue(postModels);
			 }

			 @Override
			 public void onError(@NonNull Throwable e) {
				 Log.d("MainActivity", "onError: "+e);

			 }

			 @Override
			 public void onComplete() {

			 }
		 };
	}
}
