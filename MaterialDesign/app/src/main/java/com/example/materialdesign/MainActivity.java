package com.example.materialdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity {

    MainActivityViewModel viewModel;
    ViewPager2 viewPager;
    FragmentAdapter adapter;
    TabLayout tab;
    FloatingActionButton floatingButton;
    FloatingActionButton floatingButton1;
    FloatingActionButton floatingButton2;
    MaterialCardView card;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.init();
        viewPager = findViewById(R.id.viewPager);
        adapter = new FragmentAdapter(this, viewModel.getRepositoryViewModel().getValue());
        viewPager.setAdapter(adapter);

        floatingButton = findViewById(R.id.floating_action_button);
        floatingButton1 = findViewById(R.id.floating_action_button1);
        floatingButton2 = findViewById(R.id.floating_action_button2);
        card = findViewById(R.id.card);
        title = findViewById(R.id.title);

        tab = findViewById(R.id.tabs);


        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                floatingButton1.setVisibility(View.VISIBLE);
                floatingButton2.setVisibility(View.VISIBLE);
                floatingButton.animate().rotation(135f);

            }
        });

        floatingButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_LONG).show();
                floatingButton1.setVisibility(View.GONE);
                floatingButton2.setVisibility(View.GONE);
                floatingButton.animate().rotation(0);
            }
        });


        floatingButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_LONG).show();
                floatingButton1.setVisibility(View.GONE);
                floatingButton2.setVisibility(View.GONE);
                floatingButton.animate().rotation(0);
            }
        });



        TabLayoutMediator.TabConfigurationStrategy tabConfig = new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position) {
                    case 0:
                        tab.setText("home");
                        tab.setIcon(R.drawable.round_home_black_18dp);
                        break;
                    case 1:
                        tab.setText("profile");
                        tab.setIcon(R.drawable.round_face_black_18dp);
                        break;

                }
            }
        };

        TabLayoutMediator tabMediator = new TabLayoutMediator(tab, viewPager, tabConfig);
        tabMediator.attach();




    }

}
