package com.example.retrofit_apigetpractice;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link orderHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class orderHistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView orderItemRecyclerView;

    List<OrderItemsModel> orderItemsModelList;
    OrderHistoryListRecycleViewAdapter orderHistoryItemAdapter;

    public orderHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment orderHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static orderHistoryFragment newInstance(String param1, String param2) {
        orderHistoryFragment fragment = new orderHistoryFragment();
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

        View FragmentView = inflater.inflate(R.layout.fragment_order_history, container, false);

        orderItemRecyclerView = FragmentView.findViewById(R.id.OrderHistoryRecyclerView);
        orderItemRecyclerView.setHasFixedSize(true);
        orderItemsModelList = new ArrayList<>();

        loadOrderItems();

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.toolbarmenuframe,new HomeFragment())
                        .commit();
            }
        });
        // Inflate the layout for this fragment
        return FragmentView;

    }

    private void loadOrderItems() {

        Retrofit orderitemretrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:4000/api/v1/order/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderDataJsonPlaceHolder orderDataJsonPlaceHolder = orderitemretrofit.create(OrderDataJsonPlaceHolder.class);
//        String loginToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyZXN0YXVyYW50X05hbWUiOiJFdmVuaW5nIFBvc3QiLCJ0YWJsZU5vIjoxLCJpYXQiOjE3MjA2OTQ2NTV9.jSLYu9ix3pFQfzPqAVo646hR4yheOKMnfKmpkm-UqWw";

        SharedPreferences loginPreferences = getActivity().getSharedPreferences("LoginToken", MODE_PRIVATE);
        Map<String, ?> allEntries = loginPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("SharedPrefs", entry.getKey() + ": " + entry.getValue().toString());
        }

//        SharedPreferences loginPreferences = this.getActivity().getSharedPreferences("LoginToken", MODE_PRIVATE);
        String loginToken = loginPreferences.getString("LoginId", null);

        Call<OrderHistoryResponse> orderItemCall = orderDataJsonPlaceHolder.getOrderHistoryByTableNo(loginToken);
        orderItemCall.enqueue(new Callback<OrderHistoryResponse>() {
            @Override
            public void onResponse(Call<OrderHistoryResponse> call, Response<OrderHistoryResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(requireContext(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                assert response.body() != null;
                List<OrderHistoryDataModel> orderHistoryList = response.body().getOrderData();

                Log.d("orderHistoryData",orderHistoryList.toString());

                for (OrderHistoryDataModel orderHistoryDataModel : orderHistoryList){


                    orderItemsModelList.addAll(orderHistoryDataModel.getOrderedItem());

                }


                orderHistoryItemAdapter = new OrderHistoryListRecycleViewAdapter(getContext(),orderItemsModelList);
                orderItemRecyclerView.setAdapter(orderHistoryItemAdapter);
                orderItemRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<OrderHistoryResponse> call, Throwable throwable) {
                Log.d("ftechfailed",throwable.toString());
                Toast.makeText(requireContext(), "Failed to fetch data.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}