package com.example.suryatraderscustomer.api;

import com.example.suryatraderscustomer.models.AuthenticationResponse;
import com.example.suryatraderscustomer.models.CSRFToken;
import com.example.suryatraderscustomer.models.GetCartMsg;
import com.example.suryatraderscustomer.models.CartResponse;
import com.example.suryatraderscustomer.models.FirstCategory;
import com.example.suryatraderscustomer.models.GetCartResponse;
import com.example.suryatraderscustomer.models.OrderResponse;
import com.example.suryatraderscustomer.models.PlaceOrderResponse;
import com.example.suryatraderscustomer.models.RemoveCartItemResponse;
import com.example.suryatraderscustomer.models.ReqData;
import com.example.suryatraderscustomer.models.Review;
import com.example.suryatraderscustomer.models.ReviewMsg;
import com.example.suryatraderscustomer.models.SecondCategory;
import com.example.suryatraderscustomer.models.SubOrderResponse;
import com.example.suryatraderscustomer.models.SubmitReviewResponse;
import com.example.suryatraderscustomer.models.Token;
import com.example.suryatraderscustomer.models.UnitColorQuantity;
import com.google.gson.JsonArray;

import java.util.List;

import androidx.room.Delete;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {


    @FormUrlEncoded
    @POST("customer/add")
    Call<AuthenticationResponse> registerCustomer(
            @Field("first_name") String firstName,
            @Field("last_name") String lastName,
            @Field("mobile") String mobileNo,
            @Field("password") String password,
            @Field("address") String address,
            @Field("company") String company
    );

    @FormUrlEncoded
    @POST("cart/add")
    Call<CartResponse> addToCart(
            @Field("customer_id") String customer_id,
            @Field("cat_level") String cat_level,
            @Field("product_code") String product_code,
            @Field("req_data") String list);

    @Headers("Content-Type:application/json;")
    @GET("customer/category1/list?")
    Call<FirstCategory> getFirstCategoryListItems();

    @Headers("Content-Type:application/json;")
    @GET("customer/category1/get")
    Call<FirstCategory> getFirstCategoryChildren(@Query("product_code") String firstCategoryId);

    @Headers("Content-Type:application/json;")
    @GET("customer/category2/get/")
    Call<SecondCategory> getSecondCategoryChildren(@Query("product_code") String secondCategoryId);

    @FormUrlEncoded
    @POST("customer/login")
    Call<AuthenticationResponse> login(@Field("mobile") String mobile, @Field("password") String password,@Field("notification_id") String notification_id);

    @FormUrlEncoded
    @POST("customer/changePassword")
    Call<AuthenticationResponse> changePassword(@Field("current_password") String currentPassword, @Field("new_password") String newPassword);

    @FormUrlEncoded
    @POST("customer/feedback")
    Call<AuthenticationResponse> submitFeedbackForm(@Field("feedbackSubject") String feedbackSubject, @Field("feedbackMessage") String feedbackMessage);

    @GET("csrf")
    Call<CSRFToken> getCSRFToken();

    @GET("cart/get?")
    Call<GetCartResponse> getAllCartItems(@Query("customer_id") String customerId);

    @GET("customer/order/detail?")
    Call<SubOrderResponse> getSubOrderList(@Query("order_id") String orderId);

    @GET("customer/order/list?")
    Call<OrderResponse> getAllOrders();

    @POST("customer/order/add")
    Call<PlaceOrderResponse> placeOrder();

    @FormUrlEncoded
    @POST("customer/cart/delete")
    Call<RemoveCartItemResponse> removeItemFromCart(@Field("cart_id") String cartId);

    @GET("customer/order/reviews/list")
    Call<ReviewMsg> getReviews(@Query("order_id") Integer orderId);

    @FormUrlEncoded
    @POST("customer/order/reviews/add")
    Call<SubmitReviewResponse> submitReviews(@Field("order_id") Integer orderId, @Field("added_by_type") String addByType, @Field("remarks") String reviews);


}
