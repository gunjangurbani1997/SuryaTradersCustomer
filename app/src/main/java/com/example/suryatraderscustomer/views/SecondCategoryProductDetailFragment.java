package com.example.suryatraderscustomer.views;

import android.graphics.drawable.GradientDrawable;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suryatraderscustomer.models.SecondCategoryMsg;
import com.example.suryatraderscustomer.models.ThirdCategoryMsg;
import com.example.suryatraderscustomer.models.UnitColorQuantity;
import com.example.suryatraderscustomer.views.SecondCategoryProductDetailFragmentArgs;
import com.example.suryatraderscustomer.R;
import com.example.suryatraderscustomer.databinding.FragmentProductDetailBinding;
import com.example.suryatraderscustomer.databinding.FragmentSecondCategoryProductDetailBinding;
import com.example.suryatraderscustomer.models.Color;
import com.example.suryatraderscustomer.viewmodels.CartViewModel;
import com.example.suryatraderscustomer.viewmodels.SecondCategoryViewModel;
import com.example.suryatraderscustomer.viewmodels.ThirdCategoryViewModel;

import java.util.ArrayList;
import java.util.List;


public class SecondCategoryProductDetailFragment extends Fragment {

     FragmentSecondCategoryProductDetailBinding fragmentSecondCategoryProductDetailBinding;
     SecondCategoryViewModel secondCategoryViewModel;
     CartViewModel cartViewModel;
     List<Color> colorList;
     List<String> colorLabelList;
     List<String> unitList=null;
     LinearLayout linearLayoutSecondCategory;
     private static final String TAG="2CProdDetailFragment ";
     SecondCategoryMsg secondCategoryMsg;
     private static int count=0;

    Spinner colorSpinner;
    EditText unitTextView;
    LinearLayout linearLayout;

    String units;

    List<UnitColorQuantity> unitColorQuantityList;
    UnitColorQuantity unitColorQuantity;

    String productCode;

    ImageView imageView;
     public SecondCategoryProductDetailFragment() {
    }


    public static SecondCategoryProductDetailFragment newInstance(String param1, String param2) {
        SecondCategoryProductDetailFragment fragment = new SecondCategoryProductDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        secondCategoryMsg=new SecondCategoryMsg();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSecondCategoryProductDetailBinding = FragmentSecondCategoryProductDetailBinding.inflate(inflater,container,false);
        return fragmentSecondCategoryProductDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartViewModel=new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        linearLayout=view.findViewById(R.id.linearLayoutSecondCategory);

        if(getArguments()!=null)
        {
            SecondCategoryProductDetailFragmentArgs args=SecondCategoryProductDetailFragmentArgs.fromBundle(getArguments());
            colorList=args.getSecondCategoryMsg().getColors();
            units=args.getSecondCategoryMsg().getUnits();
            productCode=args.getSecondCategoryMsg().getId();
            secondCategoryMsg.setId(productCode);
        }

        fragmentSecondCategoryProductDetailBinding.addMoreItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();
            }
        });

        fragmentSecondCategoryProductDetailBinding.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfValid())
                {
                    cartViewModel.addProductToCart(null,secondCategoryMsg);
                    cartViewModel.getAddToCartMsg().observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(String addToCartMsg) {
                                Toast.makeText(getActivity(),addToCartMsg,Toast.LENGTH_SHORT).show();
                                Log.d(TAG,addToCartMsg);
                            }
                        });
                }
            }
        });
        secondCategoryViewModel=new ViewModelProvider(requireActivity()).get(SecondCategoryViewModel.class);
        fragmentSecondCategoryProductDetailBinding.setSecondCategoryViewModel(secondCategoryViewModel);

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
            Color color=new Color();
            if (!quantityTextView.getText().toString().equals(""))
            {
                unitColorQuantity.setQuantity(quantityTextView.getText().toString());

            }
            else
            {
                result=false;
                Toast.makeText(getActivity(),"Please enter quantity",Toast.LENGTH_LONG).show();
            }

            if (colorDropDown.getSelectedItem()!=null )
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
            secondCategoryMsg.setUnitColorQuantityList(unitColorQuantityList);
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

     //   unitTextView.setText(units);

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
        imageView=fragmentSecondCategoryProductDetailBinding.productImageView;

        View view=linearLayout.getChildAt(colorSpinnerID);
        EditText unitTextView=view.findViewWithTag("unitTextView_"+colorSpinnerID);

        if (colorLabelList.contains(selectedColor))
        {
            if(colorList.get(i).getLabel().equalsIgnoreCase(selectedColor))
            {
                ThirdCategoryMsg.loadImage(imageView,colorList.get(i).getImage());
                GradientDrawable gradientDrawable=(GradientDrawable)(unitTextView).getBackground().mutate();
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