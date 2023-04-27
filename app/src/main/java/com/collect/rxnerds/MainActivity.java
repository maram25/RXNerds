package com.collect.rxnerds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.TimeUtils;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.observers.BlockingMultiObserver;
import io.reactivex.rxjava3.internal.operators.single.SingleContains;
import io.reactivex.rxjava3.internal.operators.single.SingleObserveOn;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;
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

//		Observable();
//		Observer();

   Observable.just(1,2,3,4,5)
		   .subscribeOn(Schedulers.computation())
		   .doOnNext(c-> Log.d(TAG, "Up stream: "+c+" currentThread"+Thread.currentThread().getName()))
		   .observeOn(Schedulers.computation())
		   .subscribe(o-> Log.d(TAG, "down Stream: "+o+" currentThread"+Thread.currentThread().getName()));   //o for Observable


	}


	public void  SinpleObserver()
	{
		Observable observable= Observable.create(new ObservableOnSubscribe<Object>() {
			/**
			 * Called for each {@link Observer} that subscribes.
			 *
			 * @param emitter the safe emitter instance, never {@code null}
			 * @throws Throwable on error
			 */
			@Override
			public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
				for ( int i=0;i<5;i++)
				{
					emitter.onNext(i);
				}
				emitter.onComplete();

			}
		});


		Observable observable2= Observable.just(0,1,2,3,4,5,6,7,8,9);
		Integer [] list = new Integer[5];
		list[0]=0;
		list[1]=0;
		list[2]=1;
		list[3]=2;
		list[4]=3;
		Observable observable3= Observable.fromArray(list);


		// simple
		Observer observer = new Observer() {
			@Override
			public void onSubscribe(@NonNull Disposable d) {
				Log.d(TAG, "onSubscribe: ");

			}

			@Override
			public void onNext(Object o) {
				Log.d(TAG, "onNext: "+o);
			}

			@Override
			public void onError(@NonNull Throwable e) {
				Log.d(TAG, "onError: "+e);
			}

			@Override
			public void onComplete() {
				Log.d(TAG, "onComplete: ");

			}
		};
		//observable.subscribe(observer);
		// observable2.subscribe(observer);
		observable3.subscribe(observer);


	}
	public void Observer()
	{

 // simple
		Observer observer = new Observer() {
			@Override
			public void onSubscribe(@NonNull Disposable d) {
				Log.d(TAG, "onSubscribe: ");
				
			}

			@Override
			public void onNext(Object o) {
				Log.d(TAG, "onNext: "+o);
			}

			@Override
			public void onError(@NonNull Throwable e) {
				Log.d(TAG, "onError: "+e);
			}

			@Override
			public void onComplete() {
				Log.d(TAG, "onComplete: ");

			}
		};

// single
		SingleObserver singleObserver=new SingleObserver() {
			@Override
			public void onSubscribe(@NonNull Disposable d) {

			}

			@Override
			public void onSuccess(Object o) {

			}

			@Override
			public void onError(@NonNull Throwable e) {

			}
		};
/// MaybeObserver

		MaybeObserver maybeObserver=new MaybeObserver() {
			@Override
			public void onSubscribe(@NonNull Disposable d) {

			}

			@Override
			public void onSuccess(Object o) {

			}

			@Override
			public void onError(@NonNull Throwable e) {

			}

			@Override
			public void onComplete() {

			}
		};



		//Completable
		CompletableObserver completableObserver=new CompletableObserver() {
			@Override
			public void onSubscribe(@NonNull Disposable d) {

			}

			@Override
			public void onComplete() {

			}

			@Override
			public void onError(@NonNull Throwable e) {

			}
		};

	}
	public void Observable() {

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
		Subject.subscribe(i -> Log.d(TAG, "onCreate: Hot First" + i));
		Subject.onNext("A");
		Sleep(1000);
		Subject.onNext("B");
		Sleep(1000);
		Subject.onNext("C");
		Sleep(1000);
		Subject.onNext("D");
		Sleep(1000);
		Subject.subscribe(i -> Log.d(TAG, "onCreate: Hot Seconed" + i));
		Subject.onNext("E");
		Sleep(1000);
		Subject.onNext("F");
		Sleep(1000);
		Subject.onNext("G");
		Sleep(1000);


		////  BehaviorSubjectSubject to Hot
		BehaviorSubject<String> BSubject = BehaviorSubject.create();
		BSubject.subscribe(i -> Log.d(TAG, "onCreate: Hot Behavior First" + i));
		BSubject.onNext("A");
		Sleep(1000);
		BSubject.onNext("B");
		Sleep(1000);
		BSubject.onNext("C");
		Sleep(1000);
		BSubject.onNext("D");
		Sleep(1000);
		BSubject.subscribe(i -> Log.d(TAG, "onCreate: Hot Behavior Seconed" + i));
		BSubject.onNext("E");
		Sleep(1000);
		BSubject.onNext("F");
		Sleep(1000);
		BSubject.onNext("G");
		Sleep(1000);


		//// ReplaySubject to Hot
		ReplaySubject<String> RSubject = ReplaySubject.create();
		RSubject.subscribe(i -> Log.d(TAG, "onCreate: Hot Behavior First" + i));
		RSubject.onNext("A");
		Sleep(1000);
		RSubject.onNext("B");
		Sleep(1000);
		RSubject.onNext("C");
		Sleep(1000);
		RSubject.onNext("D");
		Sleep(1000);
		RSubject.subscribe(i -> Log.d(TAG, "onCreate: Hot Behavior Seconed" + i));
		RSubject.onNext("E");
		Sleep(1000);
		RSubject.onNext("F");
		Sleep(1000);
		RSubject.onNext("G");
		Sleep(1000);


		//// AsyncSubject to Hot
		AsyncSubject<String> ASubject = AsyncSubject.create();
		ASubject.subscribe(i -> Log.d(TAG, "onCreate: Hot Behavior First" + i));
		ASubject.onNext("A");
		Sleep(1000);
		ASubject.onNext("B");
		Sleep(1000);
		ASubject.onNext("C");
		Sleep(1000);
		ASubject.onNext("D");
		Sleep(1000);
		ASubject.subscribe(i -> Log.d(TAG, "onCreate: Hot Behavior Seconed" + i));
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