package com.bhardwaj.abhishek.payrollapp;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HomeFragment extends Fragment {

    View myfragment;
    MaterialCardView cardView,cardEarning,cardCalender,cardFlight;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myfragment = inflater.inflate(R.layout.home_fragment,container,false);
        cardView = myfragment.findViewById(R.id.cardMessage);
        cardEarning = myfragment.findViewById(R.id.card2);
        cardCalender = myfragment.findViewById(R.id.card3);
        cardFlight = myfragment.findViewById(R.id.card4);


        return myfragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fm=getActivity().getSupportFragmentManager();
        // On pressing Message Card
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fm.beginTransaction().replace(R.id.fragment_container,new MessageFragment());

                fragmentTransaction.commit();
            }
        });

        //On pressing Earning Card
        cardEarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fm.beginTransaction().replace(R.id.fragment_container,new EarningFragment());
                fragmentTransaction.commit();
            }
        });

        //On clicking Calender Card
        cardCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fm.beginTransaction().replace(R.id.fragment_container,new CalenderFragment());
                fragmentTransaction.commit();
            }
        });

        //On clicking Flight Card
        cardFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fm.beginTransaction().replace(R.id.fragment_container,new FlightFragment());
                fragmentTransaction.commit();
            }
        });

    }

}
