package com.example.retrofit_apigetpractice;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
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
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements OnCategorySelectedListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView menuCaetgoryRecyclerView;
    List<MenuCategory> menuCategoryList;
    MenuCategoryAdapter menuCategoryAdapter;

    RecyclerView foodItemRecyclerView;
    List<Food> foodItemList;
    FoodItemRecyclerViewAdapter foodItemAdapter;

    Map<String, Integer> cartDataItems;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View FragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        menuCaetgoryRecyclerView = FragmentView.findViewById(R.id.menucategoryrecyclerview);
        menuCaetgoryRecyclerView.setHasFixedSize(true);
        menuCategoryList = new ArrayList<>();

        //fooditem recyclerview setup

        foodItemRecyclerView = FragmentView.findViewById(R.id.Itemsrecyclerview);
        foodItemRecyclerView.setHasFixedSize(true);
        foodItemList = new ArrayList<>();

        cartDataItems = new HashMap<>();
        getCartData();
        loadMenuCategories();
        loadFoodItems();
        //menu category recycler view java code

        //fooditem recyclerview java code


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
                    Log.d("CartItemsgetcarddata", "ID: " + entry.getKey() + " Quantity: " + entry.getValue());
                    // You can store or process these key-value pairs as needed
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
                foodItemAdapter = new FoodItemRecyclerViewAdapter(foodItemList,getContext(),cartDataItems);
                foodItemRecyclerView.setAdapter(foodItemAdapter);
                foodItemRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
                foodItemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable throwable) {
                Log.d("ftechfailed",throwable.toString());
                Toast.makeText(requireContext(), "Failed to fetch data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMenuCategories() {
        Retrofit menuCategoryRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:4000/api/v1/menu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MenuCategoryPlaceHolder menuCategoryJsonPlaceHolder = menuCategoryRetrofit.create(MenuCategoryPlaceHolder.class);
        Call<MenuCategoryResponse> menuCategoryCall = menuCategoryJsonPlaceHolder.getMenuCategoryList();

        menuCategoryCall.enqueue(new Callback<MenuCategoryResponse>() {
            @Override
            public void onResponse(Call<MenuCategoryResponse> call, Response<MenuCategoryResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(requireContext(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                assert response.body() != null;
                List<MenuCategory> menuCategoryList1 = response.body().getMenuCategorydata();
                for (MenuCategory menuCategory:
                        menuCategoryList1) {
                    Log.d("categoryId",menuCategory.getId());
                    Log.d("categoryname",menuCategory.getName());
                    Log.d("categoryimageUrl",menuCategory.getImageurl());
                    menuCategoryList.add(new MenuCategory(menuCategory.getId(),menuCategory.getName(),menuCategory.getImageurl()));
                }
                menuCategoryAdapter = new MenuCategoryAdapter(menuCategoryList,getContext(),HomeFragment.this);
                menuCaetgoryRecyclerView.setAdapter(menuCategoryAdapter);
                menuCaetgoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),GridLayoutManager.HORIZONTAL,false));

            }

            @Override
            public void onFailure(Call<MenuCategoryResponse> call, Throwable throwable) {
                Log.d("ftechfailed",throwable.toString());
                Toast.makeText(requireContext(), "Failed to fetch data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategorySelected(String category) {
        foodItemList.clear();
        loadFoodItems();
    }

    private void filterFoodItems(String category) {
        List<Food> filteredList = new ArrayList<>();

        for (Food food : foodItemList) {
            if (category.equals("All") || food.getCategory().equals(category)) {
                filteredList.add(food);
            }
        }

        foodItemAdapter.updateFoodList(filteredList);
    }
}