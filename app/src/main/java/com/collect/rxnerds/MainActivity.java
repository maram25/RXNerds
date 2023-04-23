package com.collect.rxnerds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.TimeUtils;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/////cold Observable
		io.reactivex.rxjava3.core.Observable<Long> Cold = Observable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS);
		Cold.subscribe(i -> Log.d(TAG, "onCreate:Student 1 " + i));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Cold.subscribe(i -> Log.d(TAG, "onCreate:Student 2 " + i));


		/////connectable Observable
		ConnectableObservable<Long> Connectable = ConnectableObservable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS).publish();
		Connectable.connect();
		Connectable.subscribe(i -> Log.d(TAG, "Connectable:Student 1 " + i));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Connectable.subscribe(i -> Log.d(TAG, "Connectable:Student 2 " + i));


		////   PublishSubject to Hot
		PublishSubject<String> Subject = PublishSubject.create();
		Subject.subscribe(i-> Log.d(TAG, "onCreate: Hot First"+i));
		Subject.onNext("A");
		Sleep(1000);
		Subject.onNext("B");
		Sleep(1000);
		Subject.onNext("C");
		Sleep(1000);
		Subject.onNext("D");
		Sleep(1000);
		Subject.subscribe(i-> Log.d(TAG, "onCreate: Hot Seconed"+i));
		Subject.onNext("E");
		Sleep(1000);
		Subject.onNext("F");
		Sleep(1000);
		Subject.onNext("G");
		Sleep(1000);




		////  BehaviorSubjectSubject to Hot
		BehaviorSubject<String> BSubject = BehaviorSubject.create();
		BSubject.subscribe(i-> Log.d(TAG, "onCreate: Hot Behavior First"+i));
		BSubject.onNext("A");
		Sleep(1000);
		BSubject.onNext("B");
		Sleep(1000);
		BSubject.onNext("C");
		Sleep(1000);
		BSubject.onNext("D");
		Sleep(1000);
		BSubject.subscribe(i-> Log.d(TAG, "onCreate: Hot Behavior Seconed"+i));

		BSubject.onNext("E");
		Sleep(1000);
		BSubject.onNext("F");
		Sleep(1000);
		BSubject.onNext("G");
		Sleep(1000);



		//// ReplaySubject to Hot
		ReplaySubject<String> RSubject = ReplaySubject.create();
		RSubject.subscribe(i-> Log.d(TAG, "onCreate: Hot Behavior First"+i));
		RSubject.onNext("A");
		Sleep(1000);
		RSubject.onNext("B");
		Sleep(1000);
		RSubject.onNext("C");
		Sleep(1000);
		RSubject.onNext("D");
		Sleep(1000);
		RSubject.subscribe(i-> Log.d(TAG, "onCreate: Hot Behavior Seconed"+i));
		RSubject.onNext("E");
		Sleep(1000);
		RSubject.onNext("F");
		Sleep(1000);
		RSubject.onNext("G");
		Sleep(1000);


		//// AsyncSubject to Hot
		AsyncSubject<String> ASubject = AsyncSubject.create();
		ASubject.subscribe(i-> Log.d(TAG, "onCreate: Hot Behavior First"+i));
		ASubject.onNext("A");
		Sleep(1000);
		ASubject.onNext("B");
		Sleep(1000);
		ASubject.onNext("C");
		Sleep(1000);
		ASubject.onNext("D");
		Sleep(1000);
		ASubject.subscribe(i-> Log.d(TAG, "onCreate: Hot Behavior Seconed"+i));
		RSubject.onNext("E");
		Sleep(1000);
		ASubject.onNext("F");
		Sleep(1000);
		ASubject.onNext("G");
		Sleep(1000);
		ASubject.onComplete();


	}

	public void Sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}