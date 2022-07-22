package com.example.suryatraderscustomer.views;

import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import com.example.suryatraderscustomer.R;
import com.example.suryatraderscustomer.adapters.ImageSliderAdapter;
import com.example.suryatraderscustomer.databinding.FragmentProductDetailBinding;
import com.example.suryatraderscustomer.models.ThirdCategory;
import com.example.suryatraderscustomer.models.ThirdCategoryMsg;
import com.example.suryatraderscustomer.models.UnitColorQuantity;
import com.example.suryatraderscustomer.tokenmanager.TokenManager;
import com.example.suryatraderscustomer.viewmodels.CartViewModel;
import com.example.suryatraderscustomer.viewmodels.CustomerViewModel;
import com.example.suryatraderscustomer.viewmodels.ThirdCategoryViewModel;


import java.util.ArrayList;
import java.util.List;



public class ProductDetailFragment extends Fragment {

    private static final String TAG="ProductDetailFragment";

    FragmentProductDetailBinding fragmentProductDetailBinding;
    ThirdCategoryViewModel thirdCategoryViewModel;
    CartViewModel cartViewModel;
    Spinner colorSpinner;
    EditText unitTextView;
    public static Integer count=0;

    List<com.example.suryatraderscustomer.models.Color> colorList;
    List<String> colorLabelList;

    LinearLayout linearLayout;

    List<UnitColorQuantity> unitColorQuantityList;
    UnitColorQuantity unitColorQuantity;

    ThirdCategoryMsg thirdCategoryMsg;
    String productCode;
    String units;

    ImageView imageView;



    public ProductDetailFragment() {}

    public static ProductDetailFragment newInstance(String param1, String param2) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thirdCategoryMsg=new ThirdCategoryMsg();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        fragmentProductDetailBinding = FragmentProductDetailBinding.inflate(inflater,container,false);
        return fragmentProductDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartViewModel=new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        linearLayout=view.findViewById(R.id.ll);

        if(getArguments()!=null)
        {
            ProductDetailFragmentArgs args=ProductDetailFragmentArgs.fromBundle(getArguments());
            colorList=args.getThirdCategoryMsg().getColors();
            units=args.getThirdCategoryMsg().getUnits();
            productCode=args.getThirdCategoryMsg().getId();
            thirdCategoryMsg.setId(productCode);
        }

        fragmentProductDetailBinding.addMoreItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();
            }
        });

        fragmentProductDetailBinding.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfValid())
                {
                  cartViewModel.addProductToCart(thirdCategoryMsg,null);

                }
                cartViewModel.getAddToCartMsg().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String addToCartMsg) {
                        Toast.makeText(getActivity(),addToCartMsg,Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"Msg: "+addToCartMsg);
                    }
                });
            }
        });



        thirdCategoryViewModel=new ViewModelProvider(requireActivity()).get(ThirdCategoryViewModel.class);
        fragmentProductDetailBinding.setThirdCategoryViewModel(thirdCategoryViewModel);

    }

    private Boolean checkIfValid() {
        boolean result=true;
        unitColorQuantityList=new ArrayList<>();

        for (int i=0;i<linearLayout.getChildCount();i++)
        {
            View view=linearLayout.getChildAt(i);

            EditText quantityTextView=view.findViewById(R.id.quantityTV);
            AppCompatSpinner colorDropDown=view.findViewById(i);

            unitColorQuantity=new UnitColorQuantity();
            com.example.suryatraderscustomer.models.Color color=new com.example.suryatraderscustomer.models.Color();
            if (!quantityTextView.getText().toString().equals("") && quantityTextView.getText().toString()!=null)
            {
                unitColorQuantity.setQuantity(quantityTextView.getText().toString());
            }
            else
            {
                result=false;
                Toast.makeText(getActivity(),"Please enter quantity",Toast.LENGTH_LONG).show();
            }

            if (colorDropDown.getSelectedItem()!=null && !colorDropDown.getSelectedItem().toString().equals("") )
            {
                color.setLabel(colorDropDown.getSelectedItem().toString());
                unitColorQuantity.setColor(color);
            }
            else {
                result = false;
                Toast.makeText(getActivity(),"Please choose color",Toast.LENGTH_LONG).show();
            }

            unitColorQuantity.setUnits(units);
            unitColorQuantityList.add(unitColorQuantity);

        }
        if (unitColorQuantityList.size()==0)
        {
            result=false;
            Toast.makeText(getActivity(),"Please add the product",Toast.LENGTH_LONG).show();
        }
        else if (result)
        {
            thirdCategoryMsg.setUnitColorQuantityList(unitColorQuantityList);
        }
        return result;
    }

    public void addView()
    {
        final View view=getLayoutInflater().inflate(R.layout.add_more_elements,null,false);

        colorSpinner=view.findViewById(R.id.colorSpinner);
        unitTextView=view.findViewById(R.id.unitTV);

        colorSpinner.setId(count);
        unitTextView.setId(count);
        unitTextView.setTag("unitTextView_"+count);

        Log.d(TAG,"Tag "+unitTextView.getTag());

        Log.d(TAG,"pj Unit text view Id "+unitTextView.getId());
        Log.d(TAG,"pj Color Spinner view Id "+colorSpinner.getId());

         EditText quantityTextView=view.findViewById(R.id.quantityTV);

        AppCompatButton closeButton=view.findViewById(R.id.closeButton);

         colorLabelList=new ArrayList<>();

         for (int i=0;i<colorList.size();i++)
         {
            colorLabelList.add(colorList.get(i).getLabel());
         }

        ArrayAdapter colorArrayAdapter=new ArrayAdapter(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,colorLabelList);

        colorSpinner.setAdapter(colorArrayAdapter);
        colorSpinner.setOnItemSelectedListener(listener);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(view);
            }
        });

        linearLayout.addView(view);
        count++;
    }

    private AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                    Log.d(TAG,"pj colorSpinner id "+colorSpinner.getId());
                    Log.d(TAG,"pj view getId "+view.getId());
                    Log.d(TAG,"pj parent getId "+parent.getId());

                    String selectedColor=colorSpinner.getItemAtPosition(i).toString();
                    changeImage(selectedColor,colorLabelList,i,parent.getId());
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };


    public void changeImage(String selectedColor,List<String> colorLabelList ,Integer i, Integer colorSpinnerID)
    {
        imageView=fragmentProductDetailBinding.productImageView;

        View view=linearLayout.getChildAt(colorSpinnerID);
        EditText unitTextView=view.findViewWithTag("unitTextView_"+colorSpinnerID);

         if (colorLabelList.contains(selectedColor))
        {
            if(colorList.get(i).getLabel().equalsIgnoreCase(selectedColor))
            {
                ThirdCategoryMsg.loadImage(imageView,colorList.get(i).getImage());
                GradientDrawable gradientDrawable=(GradientDrawable)unitTextView.getBackground().mutate();
                gradientDrawable.setColor(android.graphics.Color.parseColor(colorList.get(i).getValue()));

            }
        }
    }

    public void removeView(View view)
    {
        linearLayout.removeView(view);
        count--;
    }

}