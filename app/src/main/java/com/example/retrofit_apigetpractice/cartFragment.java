package com.example.retrofit_apigetpractice;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView cartItemRecyclerView;
    CartItemRecyclerViewAdapter cartItemRecyclerViewAdapter;
    List<Food> foodItemList;
    Map<String, Integer> cartDataItems;

    public cartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static cartFragment newInstance(String param1, String param2) {
        cartFragment fragment = new cartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View FragmentView = inflater.inflate(R.layout.fragment_cart, container, false);
        cartItemRecyclerView = FragmentView.findViewById(R.id.cartRecyclerView);
        cartItemRecyclerView.setHasFixedSize(true);

        foodItemList = new ArrayList<>();

        cartDataItems = new HashMap<>();

        loadFoodItems();
        getCartData();


//        cartItemRecyclerViewAdapter = new CartItemRecyclerViewAdapter(getContext(),foodItemList,cartDataItems);
//        cartItemRecyclerView.setAdapter(cartItemRecyclerViewAdapter);
//        cartItemRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//

        return FragmentView;
    }

    public void getCartData(){
        Retrofit cartDataretrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:4000/api/v1/cart/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartResponseJsonPlaceHolder cartResponseJsonPlaceHolder = cartDataretrofit.create(CartResponseJsonPlaceHolder.class);

        SharedPreferences loginPreferences = getActivity().getSharedPreferences("LoginToken", MODE_PRIVATE);
        String loginToken = loginPreferences.getString("LoginId", null);

        Call<CartItemsReponse> cartItemCall = cartResponseJsonPlaceHolder.getCartData(loginToken);
        cartItemCall.enqueue(new Callback<CartItemsReponse>() {
            @Override
            public void onResponse(Call<CartItemsReponse> call, Response<CartItemsReponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(requireContext(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                assert response.body() != null;
                Map<String, Integer> cartItems = response.body().getCartData();
                cartDataItems.putAll(cartItems);
//                Map<String, Integer> cartItemData = response.body().getCartData();
                for (Map.Entry<String, Integer> entry : cartDataItems.entrySet()) {
                    Log.d("CartItemsgetcarddataundercartfragment", "ID: " + entry.getKey() + " Quantity: " + entry.getValue());
                    // You can store or process these key-value pairs as needed
                }
                if (cartItemRecyclerViewAdapter != null) {
                    cartItemRecyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CartItemsReponse> call, Throwable throwable) {
                Log.d("ftechfailed",throwable.toString());
                Toast.makeText(requireContext(), "Failed to fetch data.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadFoodItems() {
        Retrofit fooditemretrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:4000/api/v1/food/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodJsonPlaceHolder foodItemJsonPlaceHolder = fooditemretrofit.create(FoodJsonPlaceHolder.class);
        Call<FoodResponse> foodItemcall = foodItemJsonPlaceHolder.getFoodList();

        foodItemcall.enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(requireContext(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                assert response.body() != null;
                List<Food> foodItemlist = response.body().getFooddata();
                SharedPreferences categorySharedPrefernces = getActivity().getSharedPreferences("Category", MODE_PRIVATE);
                String category = categorySharedPrefernces.getString("Category","All");
                Log.d("category update",category);
                for (Food fooditem:
                        foodItemlist) {
                    Log.d("name",fooditem.getName());
                    Log.d("id",fooditem.getId());
                    Log.d("desc",fooditem.getDescription());
                    Log.d("imgurl",fooditem.getImageurl());
                    Log.d("category",fooditem.getCategory());
                    Log.d("price","price:"+fooditem.getPrice());
                    if(fooditem.getCategory().equals(category) || category.equals("All")){
                        foodItemList.add(new Food(fooditem.getId(),fooditem.getName(),fooditem.getDescription(),fooditem.getImageurl(),fooditem.getCategory(),fooditem.getPrice()));
                    }
//                    foodItemList.add(new Food(fooditem.getId(),fooditem.getName(),fooditem.getDescription(),fooditem.getImageurl(),fooditem.getCategory(),fooditem.getPrice()));
                }

                cartItemRecyclerViewAdapter = new CartItemRecyclerViewAdapter(getContext(), foodItemlist, cartDataItems);
                cartItemRecyclerView.setAdapter(cartItemRecyclerViewAdapter);
                cartItemRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                cartItemRecyclerViewAdapter.notifyDataSetChanged();
//                foodItemAdapter = new FoodItemRecyclerViewAdapter(foodItemList,getContext(),cartDataItems);
//                foodItemRecyclerView.setAdapter(foodItemAdapter);
//                foodItemRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
//                foodItemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable throwable) {
                Log.d("ftechfailed",throwable.toString());
                Toast.makeText(requireContext(), "Failed to fetch data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterFoodItems() {
        List<Food> filteredFoodItems = new ArrayList<>();
        for (Food fooditem : foodItemList) {
            if (cartDataItems.containsKey(fooditem.getId()) && cartDataItems.get(fooditem.getId()) > 0) {
                filteredFoodItems.add(fooditem);
            }
        }

        // Initialize or update the adapter only when both food items and cart data are loaded
        if (cartItemRecyclerViewAdapter == null) {
            cartItemRecyclerViewAdapter = new CartItemRecyclerViewAdapter(getContext(), filteredFoodItems, cartDataItems);
            cartItemRecyclerView.setAdapter(cartItemRecyclerViewAdapter);
            cartItemRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            cartItemRecyclerViewAdapter.updateFoodList(filteredFoodItems);
            cartItemRecyclerViewAdapter.notifyDataSetChanged();
        }
    }
}