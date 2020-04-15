package com.example.newinsiteaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.location.Location;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

  DrawerLayout drawerLayout;
  FirebaseAuth firebaseAuth;
  FirebaseDatabase firebaseDatabase;
  FirebaseStorage firebaseStorage;
  StorageReference storageReference;
  RecyclerView recyclerView,recyclerView1;
  DatabaseReference databaseReference,databaseReference1;

    ArrayList<AdapterModel> adapterModelLis ;
    Handler handler=new Handler();
    View viewHeader;

    ArrayList<RecycleViewAdapterModelTwo> adapterModelTwos;


    TextView mobileNumberHeader,nameHeader;
    ImageView imageView;
    TextView txtLocation;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawable_layout);

       txtLocation=findViewById(R.id.txtLocation);
        recyclerView=findViewById(R.id.recycleView);
        recyclerView1=findViewById(R.id.recycleViewHome1);
        firebaseAuth=FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference().child("images");



        adapterModelLis=new ArrayList<>();
        adapterModelLis.add(new AdapterModel(R.drawable.image1,"$ 600 / person","5 Days - 5 Night","Manipur"));
        adapterModelLis.add(new AdapterModel(R.drawable.image2,"$ 500 / person","7 Days - 6 Night","tower bridge London"));
        adapterModelLis.add(new AdapterModel(R.drawable.image3,"$ 1000 / person","4 Days - 5 Night","The other Part of world"));
        adapterModelLis.add(new AdapterModel(R.drawable.image4,"$ 900 / person","6 Days - 6 Night","Goa"));
        adapterModelLis.add(new AdapterModel(R.drawable.image5,"$ 4000 / person","9 Days - 12 Night","Golden temple"));
        adapterModelLis.add(new AdapterModel(R.drawable.image6,"$2500 / person","5Days - 4 Night","Mausoore"));
        adapterModelLis.add(new AdapterModel(R.drawable.image7,"$5000 / person","7Days - 6 Night","Satpura National Park, Hoshangabad"));
        adapterModelLis.add(new AdapterModel(R.drawable.image8,"$2000 / person","3Days - 2 Night","Islamnagar Fort, Islamnagar"));
        adapterModelLis.add(new AdapterModel(R.drawable.image10,"$1000 / person","3Days - 2 Night","Indore"));
        adapterModelLis.add(new AdapterModel(R.drawable.image11,"$1500 / person","2Days - 2 Night","Khajuraho"));
        adapterModelLis.add(new AdapterModel(R.drawable.image12,"$2000 / person","7Days - 6 Night","Pench National Park"));


        ImageAdapter imageAdapter=new ImageAdapter(this,adapterModelLis);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(imageAdapter);

        //RECYCLE VIEW 1CODE
        adapterModelTwos=new ArrayList<>();
        adapterModelTwos.add(new RecycleViewAdapterModelTwo(R.drawable.image13,"Taj","555","mumbai"));
        adapterModelTwos.add(new RecycleViewAdapterModelTwo(R.drawable.image13,"Taj","555","mumbai"));
        adapterModelTwos.add(new RecycleViewAdapterModelTwo(R.drawable.image13,"Taj","555","mumbai"));
        adapterModelTwos.add(new RecycleViewAdapterModelTwo(R.drawable.image13,"Taj","555","mumbai"));
        adapterModelTwos.add(new RecycleViewAdapterModelTwo(R.drawable.image13,"Taj","555","mumbai"));
        adapterModelTwos.add(new RecycleViewAdapterModelTwo(R.drawable.image13,"Taj","555","mumbai"));

        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(this,adapterModelTwos);
        LinearLayoutManager layoutManager1=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(recyclerViewAdapter);

        //ViewPager Code
        viewPager2=findViewById(R.id.viewPagerImageSlider);
        List<SliderItem> sliderItems=new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.tour1));
        sliderItems.add(new SliderItem(R.drawable.tour2));
        sliderItems.add(new SliderItem(R.drawable.tour3));
        sliderItems.add(new SliderItem(R.drawable.tour4));
        sliderItems.add(new SliderItem(R.drawable.tour5));

        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f + r*0.15f);

            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageSelected(position);
                handler.removeCallbacks(sliderRunnable);
                handler.postDelayed(sliderRunnable,2000);
            }
        });


        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Home");

        drawerLayout=findViewById(R.id.drawer_layout);

        Intent loc=getIntent();
        String locat=loc.getStringExtra("location");
        txtLocation.setText(locat);

        NavigationView navigationView=findViewById(R.id.navigation_view);
       viewHeader=navigationView.getHeaderView(0);
        mobileNumberHeader=viewHeader.findViewById(R.id.headernumber);
        nameHeader=viewHeader.findViewById(R.id.headername);
       imageView=viewHeader.findViewById(R.id.UserImageProfile);

       try {
           databaseReference = FirebaseDatabase.getInstance().getReference().child("Image").child(firebaseAuth.getCurrentUser().getUid()).child("image");
           databaseReference.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   try {
                       String link = dataSnapshot.getValue().toString();
                       Log.d("image", "image" + link);
                       Picasso.get().load(link).into(imageView);
                   }catch (Exception e){

                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {
               }
           });
       }catch (Exception e){

       }

       databaseReference1=FirebaseDatabase.getInstance().getReference().child("Users").child("PersonalDetails");
       databaseReference1.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               try {
                   String name = dataSnapshot.child(firebaseAuth.getCurrentUser().getUid()).child("name").getValue().toString();
                   String mobile = dataSnapshot.child(firebaseAuth.getCurrentUser().getUid()).child("mobile").getValue().toString();
                   mobileNumberHeader.setText(mobile);
                   nameHeader.setText(name);
               }catch (Exception e){

               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });


      navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //navigationView.setNavigationItemSelectedListener(this);

    }

    Runnable sliderRunnable =new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
           case R.id.nav_home:
            break;
            case R.id.nav_my_booking:
                startActivity(new Intent(HomeActivity.this,Mybooking.class));
                break;
            case R.id.nav_most_tour:
                startActivity(new Intent(HomeActivity.this,PopularTours.class));
                break;
            case R.id.nav_faqs:
                startActivity(new Intent(HomeActivity.this,FAQs.class));
            case R.id.nav_profile:
                startActivity(new Intent(HomeActivity.this,ProfileActivity.class));
                break;
            case R.id.nav_term_condition:
                startActivity(new Intent(HomeActivity.this,TermAndCondition.class));
                break;
            case R.id.nav_edit_profile:
                startActivity(new Intent(HomeActivity.this,EditProfile.class));
                break;
            case R.id.nav_sign_out:{
                firebaseAuth.signOut();
                startActivity(new Intent(HomeActivity.this,MainActivity.class));
                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            if (firebaseAuth.getCurrentUser().getUid() == null) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }catch (Exception e){
            Log.d("error","error"+e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.location:
            {
                Intent intent=new Intent(HomeActivity.this,MapActivity.class);
                startActivity(intent);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}


