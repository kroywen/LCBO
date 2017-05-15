package com.demo.lcboapp.domain.network;

import com.demo.lcboapp.domain.network.model.GetProductsListResponse;
import com.demo.lcboapp.domain.network.model.GetStoreProductsListResponse;
import com.demo.lcboapp.domain.network.model.GetStoresListResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.demo.lcboapp.Constants.ACCESS_KEY;
import static com.demo.lcboapp.Constants.BASE_URL;

public interface LCBOApiService {

	@GET("/stores")
	Observable<GetStoresListResponse> getStoresList(
			@Query("page") int page,
			@Query("per_page") int count,
			@Query("where") String where,
			@Query("q") String query
	);

	@GET("/stores/{id}/products")
	Observable<GetStoreProductsListResponse> getStoreProductsList(
			@Path("id") int storeId,
			@Query("page") int page,
			@Query("per_page") int count
	);

	@GET("/products")
	Observable<GetProductsListResponse> getProductsList(
			@Query("page") int page,
			@Query("per_page") int count,
			@Query("where") String where,
			@Query("q") String query
	);

	class Creator {

		public static LCBOApiService newApiService() {
			HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
			loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

			OkHttpClient okHttpClient = new OkHttpClient.Builder()
					.connectTimeout(30, TimeUnit.SECONDS)
					.readTimeout(30, TimeUnit.SECONDS)
					.addInterceptor(loggingInterceptor)
					.addInterceptor(chain -> {
						Request request = chain.request().newBuilder()
								.addHeader("Authorization", "Token " + ACCESS_KEY)
								.build();
						return chain.proceed(request);
					})
					.build();

			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.client(okHttpClient)
					.addConverterFactory(GsonConverterFactory.create())
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
					.build();

			return retrofit.create(LCBOApiService.class);
		}
	}

}