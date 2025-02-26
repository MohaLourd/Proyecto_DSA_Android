package com.example.restclientservweb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductAdapter extends ArrayAdapter<Products> {
    private ApiService apiService;
    private String username;
    private String idUser;
    private boolean isPurchasedList;
    private int dinero;

    public ProductAdapter(Context context, List<Products> products, String username, boolean isPurchasedList, int dinero, String idUser) {
        super(context, 0, products);
        this.username = username;
        this.isPurchasedList = isPurchasedList;
        this.dinero = dinero;
        this.idUser = idUser;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://147.83.7.203:80/dsaApp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.apiService = retrofit.create(ApiService.class); // Inicializar apiService

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            int layoutId = isPurchasedList ? R.layout.list_item_comprado : R.layout.list_item;
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        Products product = getItem(position);


        TextView textViewProductName = convertView.findViewById(R.id.textViewProductName);
        textViewProductName.setText(product.getName() + "         " + product.getPrice() + "€");
        ImageView imageViewProduct = convertView.findViewById(R.id.imageViewStore);
        if (imageViewProduct != null) {

            if (product.getName().equals("avatar2"))
                imageViewProduct.setImageResource(R.drawable.avatar2);
            else if (product.getName().equals("avatar1"))
                imageViewProduct.setImageResource(R.drawable.avatar1);
            else if (product.getName().equals("avatar3"))
                imageViewProduct.setImageResource(R.drawable.avatar3);
            else if (product.getName().equals("avatar4"))
                imageViewProduct.setImageResource(R.drawable.avatar4);

            else
                imageViewProduct.setImageResource(R.drawable.mejora);
        }



        if (!isPurchasedList) {
            Button buttonAction = convertView.findViewById(R.id.buttonAction);
            buttonAction.setOnClickListener(v -> {
                if (dinero >= product.getPrice()) {
                    dinero = dinero - product.getPrice();
                    addProductToUser(product);
                    remove(product);//cada vez que compras un producto desaparece de la lista de la tienda

                } else {
                    Toast.makeText(getContext(), "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return convertView;
    }
    private void addProductToUser(Products product) {
        Call<Products> call = apiService.addProductToUser(idUser, product.getId());

        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Product added to user", Toast.LENGTH_SHORT).show();
                    // Add the product to the list of purchased products
                    ListView listViewComprados = ((StoreActivity) getContext()).findViewById(R.id.listViewComprados);
                    ArrayAdapter<Products> adapter = (ArrayAdapter<Products>) listViewComprados.getAdapter();
                    adapter.add(product);
                    adapter.notifyDataSetChanged();

                    // Actualizar dineroDisplay
                    ((StoreActivity) getContext()).updateDineroDisplay(dinero);
                } else {
                    Toast.makeText(getContext(), "Failed to add product", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }
}