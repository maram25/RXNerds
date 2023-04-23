package com.collect.rxnerds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.TimeUtils;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

    	/////cold Observable
		io.reactivex.rxjava3.core.Observable<Long> Cold = Observable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS);
		Cold.subscribe(i-> Log.d(TAG, "onCreate:Student 1 "+i));
		try {
			Thread.sleep(3000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Cold.subscribe(i-> Log.d(TAG, "onCreate:Student 2 "+i));





		/////connectable Observable
		ConnectableObservable<Long> Connectable = ConnectableObservable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS).publish();
		Connectable.connect();
		Connectable.subscribe(i-> Log.d(TAG, "Connectable:Student 1 "+i));
		try {
			Thread.sleep(3000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Connectable.subscribe(i-> Log.d(TAG, "Connectable:Student 2 "+i));


	}
}